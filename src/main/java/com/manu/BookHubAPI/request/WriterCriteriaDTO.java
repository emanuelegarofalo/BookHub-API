package com.manu.BookHubAPI.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record WriterCriteriaDTO(
        @Positive(message = "ID must be a positive number") Long id,

        @Size(min = 5, max = 30, message = "the name must be a minimum of 5 characters and a maximum of 30") String name,

        @Size(max = 30, message = "the lastname must be a maximum of 30") String lastName,
        @Email String email,

        @Positive(message = "Book ID must be a positive number") Long bookId
) {}
