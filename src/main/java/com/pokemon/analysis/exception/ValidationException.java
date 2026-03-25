package com.pokemon.analysis.exception;

public class ValidationException extends RuntimeException {
    
    public ValidationException(String text) {
        super(text);
    }

    public ValidationException(String text, Throwable cause) {
        super(text, cause);
    }
    
}
