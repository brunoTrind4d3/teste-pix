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
public class UpdatePixModel {

    @Pattern(regexp = "^(corrente|poupanca)$", message = "must provide 'corrente' or 'poupanca'")
    private String accountType;
    @Size(max = 4)
    private String agencyNumber;
    @Size(max = 8)
    private String accountNumber;
    @Size(max = 30)
    private String holderName;
    @Size(max = 45)
    private String holderLastName;

}
