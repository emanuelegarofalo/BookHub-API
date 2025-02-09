package com.manu.BookHubAPI.dto;

import com.manu.BookHubAPI.model.StateOfLoan;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanDTO {
    private Long id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private StateOfLoan status;
    private String userName;
    private String bookTitle;
}
