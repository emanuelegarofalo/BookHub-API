package com.manu.BookHubAPI.exception;

public class WriterAlreadyExistsException extends RuntimeException {
    public WriterAlreadyExistsException(String email) {
        super("Writer with email " + email + " already exists");
    }
}
