package br.com.trindade.itau.domain.exception;

import br.com.trindade.itau.domain.entity.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BusinessErrorException extends Exception {
    private static final long serialVersionUID = 1L;
    public final List<BusinessError> errors;
}
