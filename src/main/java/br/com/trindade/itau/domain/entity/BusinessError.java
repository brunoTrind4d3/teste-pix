package br.com.trindade.itau.domain.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessError {

    private Integer errorCode;
    private String errorMessage;
    private List<String> fields;

    @AllArgsConstructor
    @Getter
    public enum BusinessErrorEnum{

        INVALID_FIELDS(1 ,"invalid fields"),
        EMPTY_FIELDS(2 ,"fields must not be empty"),
        PIX_KEY_VALUE(3 ,"pix key value already exists"),
        MAX_PIX_KEYS(4 ,"max keys reached");
        private final Integer code;
        private final String message;
    }
}
