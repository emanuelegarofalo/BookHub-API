package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.config.BookMapper;
import com.manu.BookHubAPI.dto.BookDTO;
import com.manu.BookHubAPI.exception.BookAlreadyExistsException;
import com.manu.BookHubAPI.exception.BookNotFoundException;
import com.manu.BookHubAPI.exception.WriterNotFoundException;
import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.request.BookCriteriaDTO;
import com.manu.BookHubAPI.response.ApiResponse;
import com.manu.BookHubAPI.service.BookService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/find-by-criteria")
    public ResponseEntity<ApiResponse> findBookByCriteria(@ModelAttribute BookCriteriaDTO bookCriteriaDTO) {
        Set<Book> books = bookService.getBook(bookCriteriaDTO);
        return ResponseEntity.ok(new ApiResponse("Book found", books.stream().map(BookMapper.INSTANCE::toBookDTO).toList()));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBook(@RequestBody BookDTO book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Book created", bookService.createBook(book)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable Long id, @RequestParam Integer quantity) {
        bookService.updateBookQuantity(id, quantity);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
