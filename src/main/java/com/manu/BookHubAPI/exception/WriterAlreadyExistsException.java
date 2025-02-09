package com.manu.BookHubAPI.exception;

public class WriterAlreadyExistsException extends RuntimeException {
    public WriterAlreadyExistsException() {
        super("This writer already exists");
    }
}
