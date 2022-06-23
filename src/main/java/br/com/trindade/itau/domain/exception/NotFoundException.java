package br.com.trindade.itau.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
}
