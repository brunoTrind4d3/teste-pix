package br.com.trindade.itau.util;

public class ValidatingUtils {

    public static Boolean onlyNumbers(String value) {
        if (value == null) {
            return false;
        }
        return value.matches("[0-9]+");
    }

    public static Boolean maxLength(String value, Integer maxLength) {
        if (value == null || maxLength == null) {
            return false;
        }
        return value.length() <= maxLength;
    }

    public static Boolean mustHaveLength(String value, Integer maxLength) {
        if (value == null || maxLength == null) {
            return false;
        }
        return value.length() == maxLength;
    }

}
