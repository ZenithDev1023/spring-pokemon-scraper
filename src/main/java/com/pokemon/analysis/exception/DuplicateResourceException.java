package com.pokemon.analysis.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String text) {
        super(text);
    }

    public DuplicateResourceException(String text, Throwable cause) {
        super(text, cause);
    }
}
