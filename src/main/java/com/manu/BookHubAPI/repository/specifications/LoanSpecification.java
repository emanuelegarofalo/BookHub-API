package com.manu.BookHubAPI.repository.specifications;

import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.model.Loan;
import com.manu.BookHubAPI.model.StateOfLoan;
import com.manu.BookHubAPI.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Join;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LoanSpecification {
    private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Specification<Loan> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public Specification<Loan> byLoanDate(LocalDate loanDate) {
        return (root, query, cb) -> cb.equal(root.get("loanDate"), loanDate);
    }

    public Specification<Loan> byReturnDate(LocalDate returnDate) {
        return (root, query, cb) -> cb.equal(root.get("returnDate"), returnDate);
    }

    public Specification<Loan> byStatus(StateOfLoan status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }

    public Specification<Loan> byUserId(Long userId) {
        return (root, query, cb) -> {
            Join<Loan, User> userJoin = root.join("user");
            return cb.equal(userJoin.get("id"), userId);
        };
    }

    public Specification<Loan> byBookId(Long bookId) {
        return (root, query, cb) -> {
            Join<Loan, Book> bookJoin = root.join("book");
            return cb.equal(bookJoin.get("id"), bookId);
        };
    }

    public Specification<Loan> combinedSpecification(
            Long id, String loanDate, String returnDate, StateOfLoan status, Long userId, Long bookId) {
        Specification<Loan> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(byId(id));
        }

        if (loanDate != null) {
            spec = spec.and(byLoanDate(LocalDate.parse(loanDate, DATE_PATTERN)));
        }

        if (returnDate != null) {
            spec = spec.and(byReturnDate(LocalDate.parse(returnDate, DATE_PATTERN)));
        }

        if (status != null) {
            spec = spec.and(byStatus(status));
        }

        if (userId != null) {
            spec = spec.and(byUserId(userId));
        }

        if (bookId != null) {
            spec = spec.and(byBookId(bookId));
        }

        return spec;
    }
}
