package com.codegym.c11.exception.api;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
