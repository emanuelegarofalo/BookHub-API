package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.config.mapper.LoanMapper;
import com.manu.BookHubAPI.model.Loan;
import com.manu.BookHubAPI.request.LoanCriteriaDTO;
import com.manu.BookHubAPI.response.ApiResponse;
import com.manu.BookHubAPI.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/find-by-criteria")
    public ResponseEntity<ApiResponse> getLoan(@ModelAttribute LoanCriteriaDTO criteria) {
        Set<Loan> loans = loanService.getLoan(criteria);
        return ResponseEntity.ok(new ApiResponse("Loan found", loans.stream().map(LoanMapper.INSTANCE::toLoanDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok(new ApiResponse("Loan deleted", null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateLoan(@PathVariable Long id, @ModelAttribute LoanCriteriaDTO criteria) {
        loanService.updateLoan(id, criteria);
        return ResponseEntity.ok(new ApiResponse("Loan updated", null));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createLoan(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam String returnDate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Loan created", LoanMapper.INSTANCE.toLoanDTO(loanService.createLoan(userId, bookId, returnDate))));
    }
}
