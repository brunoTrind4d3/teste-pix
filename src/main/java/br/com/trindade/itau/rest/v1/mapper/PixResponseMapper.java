package br.com.trindade.itau.rest.v1.mapper;

import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.rest.v1.model.PixResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PixResponseMapper {

    public static PixResponse from(Pix model) {
        if(model == null){
            return null;
        }
        return PixResponse.builder()
                .id(model.getId())
                .isActive(model.isActive())
                .accountNumber(model.getAccountNumber())
                .accountType(model.getAccountType().getCode())
                .agencyNumber(model.getAgencyNumber())
                .holderLastName(model.getHolderLastName())
                .holderName(model.getHolderName())
                .keyType(model.getKeyType().getCode())
                .personType(model.getPersonType().getCode())
                .keyValue(model.getKeyValue())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .taxId(model.getTaxId())
                .build();
    }

}
