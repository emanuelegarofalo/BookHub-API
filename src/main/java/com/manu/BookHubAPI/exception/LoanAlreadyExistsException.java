package com.manu.BookHubAPI.exception;

public class LoanAlreadyExistsException extends RuntimeException {
    public LoanAlreadyExistsException() {
        super("Loan already exists");
    }
}
