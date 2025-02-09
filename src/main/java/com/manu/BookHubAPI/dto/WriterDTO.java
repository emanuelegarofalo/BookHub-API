package com.manu.BookHubAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class WriterDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;
    private String lastName;
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<String> books;
}
