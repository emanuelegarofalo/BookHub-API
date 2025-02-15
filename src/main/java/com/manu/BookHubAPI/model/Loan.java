package com.manu.BookHubAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Loan date is required")
    private LocalDate loanDate;
    @NotNull(message = "Return date is required")
    private LocalDate returnDate;
    @Enumerated(EnumType.STRING)
    private StateOfLoan status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public Loan(User user, Book book, LocalDate returnDate) {
        this.user = user;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.returnDate = returnDate;
        this.status = StateOfLoan.ACTIVE;
    }
}
