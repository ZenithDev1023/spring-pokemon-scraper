package com.pokemon.analysis.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String text) {
        super(text);
    }

    public InvalidInputException(String text, Throwable cause) {
        super(text, cause);
    }
}
