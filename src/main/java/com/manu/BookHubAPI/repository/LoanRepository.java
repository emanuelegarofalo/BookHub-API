package com.manu.BookHubAPI.repository;

import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.model.Loan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Set<Loan> findAll(Specification<Loan> loanSpecification);

    boolean existsByBookId(Long bookId);
}
