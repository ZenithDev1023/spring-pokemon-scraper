package com.pokemon.analysis.exception;

public class ResponseStatusException extends RuntimeException {
    
    public ResponseStatusException(String text) {
        super(text);
    }

    public ResponseStatusException(String text, Throwable cause) {
        super(text, cause);
    }
}
