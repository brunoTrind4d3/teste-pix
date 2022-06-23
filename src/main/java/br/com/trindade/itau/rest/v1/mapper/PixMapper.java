package br.com.trindade.itau.rest.v1.mapper;

import br.com.trindade.itau.domain.entity.AccountType;
import br.com.trindade.itau.domain.entity.PersonType;
import br.com.trindade.itau.domain.entity.Pix;
import br.com.trindade.itau.domain.entity.PixKeyType;
import br.com.trindade.itau.rest.v1.model.CreatePixModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PixMapper {

    public static Pix from(CreatePixModel model){
        if(model == null){
            return null;
        }
        return Pix.builder()
                .taxId(model.getTaxId())
                .accountNumber(model.getAccountNumber())
                .accountType(AccountType.findByCode(model.getAccountType()))
                .agencyNumber(model.getAgencyNumber())
                .holderLastName(model.getHolderLastName())
                .holderName(model.getHolderName())
                .keyType(PixKeyType.findByCode(model.getKeyType()))
                .personType(PersonType.findByCode(model.getPersonType()))
                .keyValue(model.getKeyValue())
                .build();
    }
}
