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

    private String id;
    private PixKeyType keyType;
    private String agency;
    private String account;
    private String name;

}
