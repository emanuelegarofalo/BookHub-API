package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.exception.*;
import com.manu.BookHubAPI.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BookNotFoundException.class,
            WriterNotFoundException.class,
            UserNotFoundException.class,
            LoanNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleNotFoundException(RuntimeException e) {
        return new ApiResponse(e.getMessage(), null);
    }

    @ExceptionHandler({BookAlreadyExistsException.class,
            WriterAlreadyExistsException.class,
            UserAlreadyExist.class,
            LoanAlreadyExistsException.class,
            BookNotAvailableException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse handleConflictException(RuntimeException e) {
        return new ApiResponse(e.getMessage(), null);
    }

    @ExceptionHandler({ConstraintViolationException.class,
            IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleConstraintViolationException(RuntimeException e) {
        return new ApiResponse("sembra che l'operazione richiesta non rispetti il formato giusto, di seguito l'errore nello specifico: " + e.getMessage(), null);
    }
}
