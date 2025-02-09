package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.exception.BookAlreadyExistsException;
import com.manu.BookHubAPI.exception.BookNotFoundException;
import com.manu.BookHubAPI.exception.WriterAlreadyExistsException;
import com.manu.BookHubAPI.exception.WriterNotFoundException;
import com.manu.BookHubAPI.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BookNotFoundException.class,
            WriterNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleNotFoundException(RuntimeException e) {
        return new ApiResponse(e.getMessage(), null);
    }

    @ExceptionHandler({BookAlreadyExistsException.class,
            WriterAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse handleConflictException(RuntimeException e) {
        return new ApiResponse(e.getMessage(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleConstraintViolationException(ConstraintViolationException e) {
        return new ApiResponse("sembra che le modifiche che sono state richieste non rispettano i vincoli", null);
    }
}
