package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.dto.BookDTO;
import com.manu.BookHubAPI.exception.BookAlreadyExistsException;
import com.manu.BookHubAPI.exception.BookNotFoundException;
import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.model.Writer;
import com.manu.BookHubAPI.repository.BookRepository;
import com.manu.BookHubAPI.repository.specifications.BookSpecification;
import com.manu.BookHubAPI.request.BookCriteriaDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookSpecification bookSpecification;
    private final BookRepository bookRepository;
    private final WriterService writerService;

    public Set<Book> getBook(BookCriteriaDTO criteria) throws BookNotFoundException {

        Set<Book> books = bookRepository.findAll(
                bookSpecification.combinedSpecification(
                        criteria.id(),
                        criteria.title(),
                        criteria.genre(),
                        criteria.publisher(),
                        criteria.isbn(),
                        criteria.maxPrice(),
                        criteria.quantity(),
                        criteria.writerId()
                )

        );

        if (books.isEmpty()) {
            throw new BookNotFoundException();
        } else return books;
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
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
                book -> book.setQuantity(book.getQuantity() + quantity)
                , BookNotFoundException::new);
    }
}
