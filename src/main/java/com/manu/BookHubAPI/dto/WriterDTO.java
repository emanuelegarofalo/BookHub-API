package com.manu.BookHubAPI.dto;

import lombok.Data;

import java.util.Set;

@Data
public class WriterDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Set<String> books;
}
