package com.manu.BookHubAPI.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record BookCriteriaDTO(
        @Positive(message = "ID must be a positive number") Long id,

        @Size(max = 100, message = "the title can be a maximum of 100 characters long") String title,

        @Size(max = 50, message = "the name of the publisher can be a maximum of 50 characters long") String publisher,

        @Size(max = 30, message = "the genre can be a maximum of 30 characters long") String genre,

        @Digits(integer = 13, fraction = 0) Integer isbn,

        @DecimalMin("0.0") BigDecimal maxPrice,

        @Min(0) Integer quantity,

        @Positive(message = "Writer ID must be a positive number") Long writerId
) {}
