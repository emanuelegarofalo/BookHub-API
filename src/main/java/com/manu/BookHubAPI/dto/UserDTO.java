package com.manu.BookHubAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manu.BookHubAPI.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String username;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Role role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<LoanDTO> loans;
}
