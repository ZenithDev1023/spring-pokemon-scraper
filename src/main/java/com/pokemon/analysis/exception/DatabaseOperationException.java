package com.pokemon.analysis.exception;

public class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String text) {
        super(text);
    }

    public DatabaseOperationException(String text, Throwable cause) {
        super(text, cause);
    }
}
