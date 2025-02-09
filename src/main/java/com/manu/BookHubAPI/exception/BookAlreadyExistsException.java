package com.manu.BookHubAPI.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException() {
        super("Book with ISBN already exists");
    }
}
