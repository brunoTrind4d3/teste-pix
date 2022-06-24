package br.com.trindade.itau.rest.v1;

import br.com.trindade.itau.domain.entity.BusinessError;
import br.com.trindade.itau.domain.entity.PixKeyType;
import br.com.trindade.itau.domain.entity.SearchFilters;
import br.com.trindade.itau.domain.exception.BusinessErrorException;
import br.com.trindade.itau.domain.exception.NotFoundException;
import br.com.trindade.itau.domain.service.PixService;
import br.com.trindade.itau.rest.v1.mapper.PixMapper;
import br.com.trindade.itau.rest.v1.mapper.PixResponseMapper;
import br.com.trindade.itau.rest.v1.model.CreatePixModel;
import br.com.trindade.itau.rest.v1.model.PixResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping
    public PixResponse create(@Valid @RequestBody CreatePixModel body) throws BusinessErrorException {
        return PixResponseMapper.from(this.pixService.create(PixMapper.from(body)));
    }

    @DeleteMapping("/inactive/{id}")
    public PixResponse inactive(@Valid @PathVariable String id) throws NotFoundException {
        return PixResponseMapper.from(this.pixService.inactive(id));
    }

    @GetMapping
    @Validated
    public List<PixResponse> search(@RequestHeader(name = "id", required = false) String id,
                                    @RequestHeader(name = "keyType", required = false) @Pattern(regexp = "^(celular|email|cpf|cnpj|aleatorio)$") String keyType,
                                    @RequestHeader(name = "agency", required = false) String agency,
                                    @RequestHeader(name = "account", required = false) String account,
                                    @RequestHeader(name = "name", required = false) String name) throws NotFoundException, BusinessErrorException {

        var filters = SearchFilters.builder()
                .id(id)
                .keyType(PixKeyType.findByCode(keyType))
                .account(account)
                .agency(agency)
                .name(name)
                .build();

        return this.pixService.findByFilters(filters).stream().map(PixResponseMapper::from).collect(Collectors.toList());
    }

    @ExceptionHandler({BusinessErrorException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<BusinessError>> handleBusinessException(BusinessErrorException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON)
                .body(ex.getErrors());
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BusinessError> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(BusinessError.builder().errorCode(404).errorMessage("NOT_FOUND").build());
    }


}
