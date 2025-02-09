package com.manu.BookHubAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class BookDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String title;
    private String publisher;
    private String genre;
    private Integer isbn;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<LoanDTO> loans;

    private Set<String> writersNames;
}
