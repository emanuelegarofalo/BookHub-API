package com.manu.BookHub.API.repository;

import com.manu.BookHub.API.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanInterface extends JpaRepository<Loan, Long> {
}
