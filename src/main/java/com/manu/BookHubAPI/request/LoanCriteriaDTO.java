package com.manu.BookHubAPI.request;

import com.manu.BookHubAPI.model.StateOfLoan;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record LoanCriteriaDTO(
        @Positive(message = "ID must be a positive number") Long id,

        @PastOrPresent(message = "Loan date cannot be in the future") String loanDate,

        @FutureOrPresent(message = "Return date must be today or in the future") String returnDate,

        StateOfLoan state,

        @Positive(message = "User ID must be a positive number") Long userId,

        @Positive(message = "Book ID must be a positive number") Long bookId
) {}
