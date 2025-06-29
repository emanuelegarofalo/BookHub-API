package com.manu.BookHubAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manu.BookHubAPI.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Positive(message = "ID must be a positive number")
    private Long id;

    @Size(min = 5, max = 30, message = "the username must be a minimum of 5 characters and a maximum of 30")
    private String username;
    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Size(max = 6)
    private Role role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<LoanDTO> loans;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<BookDTO> favoriteBooks;
}
