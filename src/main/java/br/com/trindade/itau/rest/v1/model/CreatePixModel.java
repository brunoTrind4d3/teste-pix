package br.com.trindade.itau.rest.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePixModel {

    @NotNull
    @Pattern(regexp = "^([FJ])$", message = "must provide 'F' or 'J'")
    private String personType;
    @NotBlank
    private String taxId;
    @NotNull
    @Pattern(regexp = "^(celular|email|cpf|cnpj|aleatorio)$", message = "must provide 'celular' or 'email' or 'cpf' or 'cnpj' or 'aleatorio'")
    private String keyType;
    @NotBlank
    private String keyValue;
    @NotNull
    @Pattern(regexp = "^(corrente|poupanca)$", message = "must provide 'corrente' or 'poupanca'")
    private String accountType;
    @NotBlank
    @Size(max = 4)
    private String agencyNumber;
    @NotBlank
    @Size(max = 8)
    private String accountNumber;
    @NotBlank
    @Size(max = 30)
    private String holderName;

    @Size(max = 45)
    private String holderLastName;

}
