package br.com.trindade.itau.domain.service;

import br.com.trindade.itau.domain.entity.BusinessError;
import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.entity.PixUpdate;
import br.com.trindade.itau.domain.entity.SearchFilters;
import br.com.trindade.itau.domain.exception.BusinessErrorException;
import br.com.trindade.itau.domain.exception.NotFoundException;
import br.com.trindade.itau.domain.repository.PixRepository;
import br.com.trindade.itau.domain.service.validation.PixKeyAndValueValidationService;
import br.com.trindade.itau.domain.service.validation.PixValidationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PixService {

    private final PixRepository pixRepository;
    private final PixKeyAndValueValidationService pixKeyAndValueValidationService;
    private final PixValidationService pixValidationService;

    public Pix create(Pix body) throws BusinessErrorException {

        var errors = new ArrayList<BusinessError>();

        this.add(errors, this.pixKeyAndValueValidationService.validate(body));
        this.add(errors, this.pixValidationService.validate(body));
        if(!errors.isEmpty()){
            throw new BusinessErrorException(errors);
        }
        return this.pixRepository.create(body);
    }

    public Pix inactive(String id) throws NotFoundException {
        var result = this.pixRepository.inactive(id);
        if(result == null){
            throw new NotFoundException();
        }
        return result;
    }

    public Pix update(String id, PixUpdate pix) throws NotFoundException {
        var result = this.pixRepository.update(id, pix);
        if(result == null){
            throw new NotFoundException();
        }
        return result;
    }

    public List<Pix> findByFilters(SearchFilters filters) throws BusinessErrorException, NotFoundException {

       var optionalFilters = Optional.ofNullable(filters);
       var id = optionalFilters.map(SearchFilters::getId).orElse(null);
       var accountNumber = optionalFilters.map(SearchFilters::getAccount).orElse(null);
       var keyType = optionalFilters.map(SearchFilters::getKeyType).orElse(null);
       var agencyNumber = optionalFilters.map(SearchFilters::getAgency).orElse(null);
       var name = optionalFilters.map(SearchFilters::getName).orElse(null);

       if(id != null && (agencyNumber != null||accountNumber != null||keyType != null||name != null)){
           throw new BusinessErrorException(List.of(BusinessError.builder()
                           .errorCode(BusinessError.BusinessErrorEnum.ONLY_ID_MUST_BE_INFORMED.getCode())
                           .errorMessage(BusinessError.BusinessErrorEnum.ONLY_ID_MUST_BE_INFORMED.getMessage())
                   .build()));
       }
       if(id != null){
           var result = this.pixRepository.findById(id);
           if(result == null){
               throw new NotFoundException();
           }
           return List.of(result);
       }
       var result = this.pixRepository.findByFilters(filters);
       if(result.isEmpty()){
           throw new NotFoundException();
       }

        return result;
    }

    private void add(List<BusinessError> errors, BusinessError newError){
        Optional.ofNullable(newError).ifPresent(errors::add);
    }
    private void add(List<BusinessError> errors, List<BusinessError> newError){
        Optional.ofNullable(newError).ifPresent(errors::addAll);
    }
}
