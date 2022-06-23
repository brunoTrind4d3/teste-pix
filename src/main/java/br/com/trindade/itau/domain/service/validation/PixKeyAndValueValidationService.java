package br.com.trindade.itau.domain.service.validation;

import br.com.trindade.itau.domain.entity.BusinessError;
import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.entity.PixKeyType;
import br.com.trindade.itau.util.SanitizeUtils;
import br.com.trindade.itau.util.ValidatingUtils;

import java.util.List;
import java.util.Optional;

public class PixKeyAndValueValidationService {

    public BusinessError validate(Pix pix) {

        var pixValue = Optional.ofNullable(pix).map(Pix::getKeyValue).orElse("");
        var pixType = Optional.ofNullable(pix).map(Pix::getKeyType).orElse(PixKeyType.INVALID);

        switch (pixType) {
            case EMAIL:
                if (!this.validateEmail(pixValue)) {
                    return this.getError();
                }
                break;
            case PHONE:
                if (!this.validatePhone(pixValue)) {
                    return this.getError();
                }
                break;
            case LEGAL_TAX_ID:
                if (!this.validateLegalTaxId(pixValue)) {
                    return this.getError();
                }
                break;
            case NATURAL_TAX_ID:
                if (!this.validateNaturalTaxId(pixValue)) {
                    return this.getError();
                }
                break;
            case RANDOM:
                if (!this.validateRandom(pixValue)) {
                    return this.getError();
                }
                break;
            default:
                return this.getError();
        }
        return null;
    }

    private boolean validateEmail(String email) {
        return email.contains("@") && ValidatingUtils.maxLength(email, 77);
    }

    private boolean validatePhone(String phone) {
        return phone.startsWith("+") && ValidatingUtils.maxLength(phone, 12);
    }

    private boolean validateLegalTaxId(String taxId) {
        var sanitizedTaxId = SanitizeUtils.sanitize(taxId);
        return ValidatingUtils.mustHaveLength(sanitizedTaxId, 14) && ValidatingUtils.onlyNumbers(sanitizedTaxId);
    }

    private boolean validateNaturalTaxId(String taxId) {
        var sanitizedTaxId = SanitizeUtils.sanitize(taxId);
        return ValidatingUtils.mustHaveLength(sanitizedTaxId, 11) && ValidatingUtils.onlyNumbers(sanitizedTaxId);
    }

    private boolean validateRandom(String random) {
        var sanitizedTaxId = SanitizeUtils.sanitize(random);
        return ValidatingUtils.maxLength(sanitizedTaxId, 36);
    }

    private BusinessError getError() {
        return BusinessError.builder()
                .errorCode(BusinessError.BusinessErrorEnum.INVALID_FIELDS.getCode())
                .errorMessage(BusinessError.BusinessErrorEnum.INVALID_FIELDS.getMessage())
                .fields(List.of("keyValue"))
                .build();
    }


}
