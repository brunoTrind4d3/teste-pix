package br.com.trindade.itau.rest.v1;

import br.com.trindade.itau.domain.entity.BusinessError;
import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.exception.BusinessErrorException;
import br.com.trindade.itau.domain.service.PixService;
import br.com.trindade.itau.rest.v1.mapper.PixMapper;
import br.com.trindade.itau.rest.v1.mapper.PixResponseMapper;
import br.com.trindade.itau.rest.v1.model.CreatePixModel;
import br.com.trindade.itau.rest.v1.model.PixResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping
    public PixResponse create(@Valid @RequestBody CreatePixModel body) throws BusinessErrorException {
        return PixResponseMapper.from(this.pixService.create(PixMapper.from(body)));
    }

    @ExceptionHandler({ BusinessErrorException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<List<BusinessError>> handleBusinessException(BusinessErrorException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON)
                .body(ex.getErrors());
    }


}
