package br.com.trindade.itau.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pix {

    private String id;
    private PersonType personType;
    private String taxId;

    private PixKeyType keyType;
    private String keyValue;
    private boolean isActive;

    private AccountType accountType;
    private String agencyNumber;
    private String accountNumber;
    private String holderName;
    private String holderLastName;

    private Date createdAt;
    private Date updatedAt;

}
