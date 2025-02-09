package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.config.BookMapper;
import com.manu.BookHubAPI.dto.BookDTO;
import com.manu.BookHubAPI.exception.BookAlreadyExistsException;
import com.manu.BookHubAPI.exception.BookNotFoundException;
import com.manu.BookHubAPI.exception.WriterNotFoundException;
import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.response.ApiResponse;
import com.manu.BookHubAPI.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

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
            Set<Book> books = bookService.getBook(id, title, publisher, genre, isbn, description, price, quantity);
            return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse("Book found", books.stream().map(BookMapper.INSTANCE::toBookDTO).toList()));
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBook(@RequestBody BookDTO book) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Book created", bookService.createBook(book)));
        } catch (BookAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        } catch (WriterNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
