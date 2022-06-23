package br.com.trindade.itau.rest.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PixResponse {

    private String id;
    private String personType;
    private String taxId;

    private String keyType;
    private String keyValue;
    private boolean isActive;

    private String accountType;
    private String agencyNumber;
    private String accountNumber;
    private String holderName;
    private String holderLastName;

    private Date createdAt;
    private Date updatedAt;

}
