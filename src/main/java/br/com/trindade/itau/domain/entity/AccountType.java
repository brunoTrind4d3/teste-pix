package br.com.trindade.itau.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum AccountType {

    SAVING_ACCOUNT("poupanca", "SAVING-ACCOUNT"),
    CURRENT_ACCOUNT("corrente", "CURRENT-ACCOUNT");

    private final String code;
    private final String description;

    public static AccountType findByCode(String code){
        if(code == null){
            return null;
        }
        for(AccountType accountType: AccountType.values()){
            if(Objects.equals(accountType.getCode(), code)){
                return accountType;
            }
        }
        return null;
    }

    public AccountType findByDescription(String description){
        if(description == null){
            return null;
        }
        for(AccountType accountType: AccountType.values()){
            if(Objects.equals(accountType.getDescription(), description)){
                return accountType;
            }
        }
        return null;
    }

}
