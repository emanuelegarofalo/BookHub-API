package com.manu.BookHubAPI.exception;

public class WriterNotFoundException extends RuntimeException {
    public WriterNotFoundException() {
        super("This writer does not exist");
    }
}
