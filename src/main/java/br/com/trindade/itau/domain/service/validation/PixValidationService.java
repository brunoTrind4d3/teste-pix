package br.com.trindade.itau.domain.service.validation;

import br.com.trindade.itau.domain.entity.BusinessError;
import br.com.trindade.itau.domain.entity.PersonType;
import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.repository.PixRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PixValidationService {

    private final PixRepository pixRepository;

    public List<BusinessError> validate(Pix body){
        var errors = new ArrayList<BusinessError>();

        this.add(errors, this.existsKeyValue(body));
        this.add(errors, this.validateNumberOfKeys(body));

        return errors;
    }

    public BusinessError existsKeyValue(Pix body){
        var keyValue = Optional.ofNullable(body).map(Pix::getKeyValue).orElse("");
        var result = this.pixRepository.findByKeyValue(keyValue);
        if(result != null){
            return BusinessError.builder()
                    .errorCode(BusinessError.BusinessErrorEnum.PIX_KEY_VALUE.getCode())
                    .errorMessage(BusinessError.BusinessErrorEnum.PIX_KEY_VALUE.getMessage())
                    .fields(List.of("keyValue"))
                    .build();
        }
        return null;
    }

    public BusinessError validateNumberOfKeys(Pix body){
        var taxId = Optional.ofNullable(body).map(Pix::getTaxId).orElse("");
        var maxKeys = Optional.ofNullable(body).map(Pix::getPersonType).map(PersonType::getMaxKeys).orElse(0);
        var result = this.pixRepository.findByTaxId(taxId);
        if(!result.isEmpty() && result.size() >= maxKeys){
            return BusinessError.builder()
                    .errorCode(BusinessError.BusinessErrorEnum.MAX_PIX_KEYS.getCode())
                    .errorMessage(BusinessError.BusinessErrorEnum.MAX_PIX_KEYS.getMessage())
                    .fields(List.of("keyValue"))
                    .build();
        }
        return null;
    }

    private void add(List<BusinessError> errors, BusinessError newError){
        Optional.ofNullable(newError).ifPresent(errors::add);
    }
}
