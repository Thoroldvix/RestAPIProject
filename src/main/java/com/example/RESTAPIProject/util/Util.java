package com.example.RESTAPIProject.util;

import org.springframework.validation.BindingResult;




public final class Util {
    private Util() {
    }

    public static String getErrorMessages(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        bindingResult.getFieldErrors().forEach(error -> errorMessage
                .append(error.getField())
                .append(" - ")
                .append(error.getDefaultMessage())
                .append("; "));

        return errorMessage.toString();
    }
}