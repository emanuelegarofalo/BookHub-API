package com.manu.BookHubAPI.dto;

import com.manu.BookHubAPI.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private Set<LoanDTO> loans;
}
