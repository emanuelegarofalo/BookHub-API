package com.manu.BookHubAPI.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UserCriteriaDTO(
        @Positive(message = "ID must be a positive number") Long id,

        @Size(min = 5, max = 30, message = "the username must be a minimum of 5 characters and a maximum of 30") String username,

        @Size(min = 6, message = "password must be a minimum of 6 characters") String password,

        @Email String email,

        @Size(max = 6) String role
) {}
