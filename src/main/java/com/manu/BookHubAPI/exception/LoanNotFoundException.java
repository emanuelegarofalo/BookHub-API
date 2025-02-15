package com.manu.BookHubAPI.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException() {
        super("Loan not found");
    }
}
