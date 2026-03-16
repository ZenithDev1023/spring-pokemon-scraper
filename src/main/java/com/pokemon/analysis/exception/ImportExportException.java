package com.pokemon.analysis.exception;

public class ImportExportException extends RuntimeException {
    public ImportExportException(String text) {
        super(text);
    }

    public ImportExportException(String text, Throwable cause) {
        super(text, cause);
    }
}
