package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.exception.BookNotFoundException;
import com.manu.BookHubAPI.response.ApiResponse;
import com.manu.BookHubAPI.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/find-by-criteria")
    public ResponseEntity<ApiResponse> findBookByCriteria(@RequestParam(required = false) Long id,
                                                          @RequestParam(required = false) String title,
                                                          @RequestParam(required = false) String publisher,
                                                          @RequestParam(required = false) String genre,
                                                          @RequestParam(required = false) Integer isbn,
                                                          @RequestParam(required = false) String description,
                                                          @RequestParam(required = false) BigDecimal price,
                                                          @RequestParam(required = false) Integer quantity) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse("Book found", bookService.getBook(id, title, publisher, genre, isbn, description, price, quantity)));
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
