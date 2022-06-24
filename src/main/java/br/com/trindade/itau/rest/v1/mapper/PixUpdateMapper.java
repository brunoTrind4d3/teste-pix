package br.com.trindade.itau.rest.v1.mapper;

import br.com.trindade.itau.domain.entity.AccountType;
import br.com.trindade.itau.domain.entity.PixUpdate;
import br.com.trindade.itau.rest.v1.model.UpdatePixModel;

public class PixUpdateMapper {
    public static PixUpdate from(UpdatePixModel model){
        if(model == null){
            return null;
        }
        return PixUpdate.builder()
                .accountNumber(model.getAccountNumber())
                .accountType(AccountType.findByCode(model.getAccountType()))
                .agencyNumber(model.getAgencyNumber())
                .holderLastName(model.getHolderLastName())
                .holderName(model.getHolderName())
                .build();
    }
}
