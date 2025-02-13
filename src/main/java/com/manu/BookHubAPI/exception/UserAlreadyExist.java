package com.manu.BookHubAPI.exception;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist() {
        super("user with this email already exist");
    }
}
