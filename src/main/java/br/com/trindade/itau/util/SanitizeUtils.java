package br.com.trindade.itau.util;

public class SanitizeUtils {

    public static String sanitize(String value){
        if(value == null){
            return null;
        }
        return value.replaceAll("[^a-zA-Z0-9]+", "");
    }
}
