package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.exception.BookNotFoundException;
import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.repository.BookRepository;
import com.manu.BookHubAPI.repository.specifications.BookSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookSpecifications bookSpecifications;
    private final BookRepository bookRepository;

    public Set<Book> getBook(Long id, String title,
                       String publisher, String genre,
                       Integer isbn, String description,
                       BigDecimal price, Integer quantity) throws BookNotFoundException {

        Set<Book> books = bookRepository.findAll(
                bookSpecifications.conditionalSearchForBook(
                        id, title,
                        publisher, genre,
                        isbn, description,
                        price, quantity)
        );

        if (books.isEmpty()) {
            throw new BookNotFoundException();
        } else return books;
    }
}
