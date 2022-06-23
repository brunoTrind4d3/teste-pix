package br.com.trindade.itau.domain.service;

import br.com.trindade.itau.domain.entity.BusinessError;
import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.exception.BusinessErrorException;
import br.com.trindade.itau.domain.repository.PixRepository;
import br.com.trindade.itau.domain.service.validation.PixKeyAndValueValidationService;
import br.com.trindade.itau.domain.service.validation.PixValidationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

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

    private void add(List<BusinessError> errors, BusinessError newError){
        Optional.ofNullable(newError).ifPresent(errors::add);
    }
    private void add(List<BusinessError> errors, List<BusinessError> newError){
        Optional.ofNullable(newError).ifPresent(errors::addAll);
    }
}
