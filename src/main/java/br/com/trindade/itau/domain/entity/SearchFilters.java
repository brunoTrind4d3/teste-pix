package br.com.trindade.itau.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilters {

    private PersonType personType;
    private AccountType accountType;
    private PixKeyType keyType;
    private String keyValue;

}
