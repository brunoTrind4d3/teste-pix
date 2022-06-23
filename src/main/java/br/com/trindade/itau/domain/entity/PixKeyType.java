package br.com.trindade.itau.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum PixKeyType {

    PHONE("celular", "PHONE"),
    NATURAL_TAX_ID("cpf", "NATURAL_TAX_ID"),
    LEGAL_TAX_ID("cnpj", "LEGAL_TAX_ID"),
    EMAIL("email", "EMAIL"),
    RANDOM("aleatorio", "RANDOM"),
    INVALID("invalido", "INVALID");

    private final String code;
    private final String description;

    public static PixKeyType findByCode(String code){
        if(code == null){
            return null;
        }
        for(PixKeyType pixType: PixKeyType.values()){
            if(Objects.equals(pixType.getCode(), code)){
                return pixType;
            }
        }
        return null;
    }

    public PixKeyType findByDescription(String description){
        if(description == null){
            return null;
        }
        for(PixKeyType pixType: PixKeyType.values()){
            if(Objects.equals(pixType.getDescription(), description)){
                return pixType;
            }
        }
        return null;
    }

}
