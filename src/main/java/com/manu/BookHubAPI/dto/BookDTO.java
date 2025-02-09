package com.manu.BookHubAPI.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String publisher;
    private String Genre;
    private Integer isbn;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Set<LoanDTO> loans;
    private Set<String> writersNames;

}
