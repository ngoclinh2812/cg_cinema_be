package com.codegym.c11.exception.api;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String message) {
        super(message);
    }
}
