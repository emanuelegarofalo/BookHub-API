package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.exception.BookNotAvailableException;
import com.manu.BookHubAPI.exception.LoanAlreadyExistsException;
import com.manu.BookHubAPI.exception.LoanNotFoundException;
import com.manu.BookHubAPI.model.Loan;
import com.manu.BookHubAPI.model.User;
import com.manu.BookHubAPI.repository.LoanRepository;
import com.manu.BookHubAPI.repository.specifications.LoanSpecification;
import com.manu.BookHubAPI.request.LoanCriteriaDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanSpecification loanSpecification;
    private final UserService userService;
    private final BookService bookService;
    private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Transactional
    public Loan createLoan(Long userId, Long bookId, String returnDate) {
        User user = userService.getUser(userId);
        if (user.getLoans().isEmpty() || user.getLoans().stream().noneMatch(loan -> loan.getBook().getId().equals(bookId))) {
            if (bookService.getBook(bookId).getQuantity() == 0) {
                throw new BookNotAvailableException();
            }

            Loan newLoan = new Loan(
                    userService.getUser(userId),
                    bookService.getBook(bookId),
                    LocalDate.parse(returnDate, DATE_PATTERN)
            );
            loanRepository.save(newLoan);
            bookService.updateBookQuantity(bookId, -1);
            return newLoan;
        } else throw new LoanAlreadyExistsException();
    }

    public Set<Loan> getLoan(LoanCriteriaDTO criteria) {
        Set<Loan> loans = loanRepository.findAll(
                loanSpecification.combinedSpecification(
                        criteria.id(), criteria.loanDate(),
                        criteria.returnDate(), criteria.state(),
                        criteria.userId(), criteria.bookId()
                )
        );

        if (loans.isEmpty()) {
            throw new LoanNotFoundException();
        } else return loans;
    }

    public void deleteLoan(Long id) {
        loanRepository.findById(id).ifPresentOrElse(loanRepository::delete, LoanNotFoundException::new);
    }

    public void updateLoan(Long id, LoanCriteriaDTO criteria) {
        Loan loan = loanRepository.findById(id).orElseThrow(LoanNotFoundException::new);

        if (criteria.returnDate() != null)
            loan.setReturnDate(LocalDate.parse(criteria.returnDate(), DATE_PATTERN));

        if (criteria.state() != null)
            loan.setStatus(criteria.state());

        if (criteria.userId() != null)
            loan.setUser(userService.getUser(criteria.userId()));

        if (criteria.bookId() != null)
            loan.setBook(bookService.getBook(criteria.bookId()));


        loanRepository.save(loan);
    }
}
