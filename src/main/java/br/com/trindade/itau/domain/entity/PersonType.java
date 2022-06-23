package br.com.trindade.itau.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum PersonType {

    NATURAL("F", "NATURAL", 5),
    LEGAL("J", "LEGAL", 20);

    private final String code;
    private final String description;
    private final Integer maxKeys;

    public static PersonType findByCode(String code){
        if(code == null){
            return null;
        }
        for(PersonType personType: PersonType.values()){
            if(Objects.equals(personType.getCode(), code)){
                return personType;
            }
        }
        return null;
    }

    public PersonType findByDescription(String description){
        if(description == null){
            return null;
        }
        for(PersonType personType: PersonType.values()){
            if(Objects.equals(personType.getDescription(), description)){
                return personType;
            }
        }
        return null;
    }

}
