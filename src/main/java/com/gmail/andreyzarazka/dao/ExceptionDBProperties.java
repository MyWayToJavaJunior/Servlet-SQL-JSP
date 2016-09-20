package com.gmail.andreyzarazka.dao;

public class ExceptionDBProperties extends RuntimeException {
    public ExceptionDBProperties() {
        super();
    }

    public ExceptionDBProperties(String message) {
        super(message);
    }

    public ExceptionDBProperties(String message, Throwable cause) {
        super(message, cause);
    }
}
