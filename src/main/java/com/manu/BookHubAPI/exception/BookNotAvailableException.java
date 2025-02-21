package com.manu.BookHubAPI.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException() {
        super("Book not available");
    }
}
