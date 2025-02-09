package com.manu.BookHubAPI.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(Integer isbn) {
        super("Book with ISBN " + isbn + " already exists");
    }
}
