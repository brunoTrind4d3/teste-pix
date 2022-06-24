package br.com.trindade.itau.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PixUpdate {

    private AccountType accountType;
    private String agencyNumber;
    private String accountNumber;
    private String holderName;
    private String holderLastName;

}
