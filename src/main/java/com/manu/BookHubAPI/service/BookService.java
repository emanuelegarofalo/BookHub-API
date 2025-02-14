package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.dto.BookDTO;
import com.manu.BookHubAPI.exception.BookAlreadyExistsException;
import com.manu.BookHubAPI.exception.BookNotFoundException;
import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.model.Writer;
import com.manu.BookHubAPI.repository.BookRepository;
import com.manu.BookHubAPI.repository.specifications.BookSpecifications;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookSpecifications bookSpecifications;
    private final BookRepository bookRepository;
    private final WriterService writerService;

    public Set<Book> getBook(Long id, String title,
                       String publisher, String genre,
                       Integer isbn, String description,
                       BigDecimal price, Integer quantity) throws BookNotFoundException {

        Set<Book> books = bookRepository.findAll(
                bookSpecifications.combinedSpecification(
                        id, title,
                        publisher, genre,
                        isbn, description,
                        price, quantity)
        );

        if (books.isEmpty()) {
            throw new BookNotFoundException();
        } else return books;
    }

    public Book createBook(BookDTO book) throws BookAlreadyExistsException {
        if (!bookRepository.existsByIsbn(book.getIsbn())) {
            Set<Writer> writers = getWriterObjects(book.getWritersNames());
            return bookRepository.save(new Book(book.getTitle(), book.getPublisher(),
                    book.getGenre(), book.getIsbn(), book.getDescription(),
                    book.getPrice(), book.getQuantity(), writers));
        } else throw new BookAlreadyExistsException();
    }

    private Set<Writer> getWriterObjects(Set<String> writersNames) {
        return writersNames.stream().map(name -> {
            String[] names = name.split(" ");
            return writerService.getWriterByFullName(names[0], names[1]);
        }).collect(Collectors.toSet());
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        bookRepository.findById(id).ifPresentOrElse(bookRepository::delete, BookNotFoundException::new);
    }

    @Transactional
    public void updateBookQuantity(Long id, Integer quantity) throws BookNotFoundException, ConstraintViolationException {
        bookRepository.findById(id).ifPresentOrElse(
                book -> {book.setQuantity(book.getQuantity() + quantity);
            if (book.getQuantity() == 0) bookRepository.delete(book);}
                , BookNotFoundException::new);
    }
}
