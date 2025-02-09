package com.manu.BookHubAPI.repository.specifications;

import com.manu.BookHubAPI.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BookSpecifications {
    public Specification<Book> byId(Long id) {
    return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public Specification<Book> byTitle(String title) {
        return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    public Specification<Book> byPublisher(String publisher) {
        return (root, query, cb) -> cb.like(root.get("publisher"), "%" + publisher + "%");
    }

    public Specification<Book> byGenre(String genre) {
        return (root, query, cb) -> cb.like(root.get("genre"), "%" + genre + "%");
    }

    public Specification<Book> byIsbn(Integer isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public Specification<Book> byDescription(String description) {
        return (root, query, cb) -> cb.like(root.get("description"), "%" + description + "%");
    }

    public Specification<Book> byPrice(BigDecimal price) {
        return (root, query, cb) -> cb.equal(root.get("price"), price);
    }

    public Specification<Book> byQuantity(Integer quantity) {
        return (root, query, cb) -> cb.equal(root.get("quantity"), quantity);
    }

//    public Specification<Book> byWriter(String writer) {
//        return (root, query, cb) -> cb.like(root.get("writers"), "%" + writer + "%");
//    }

    public Specification<Book> combinedSpecification(Long id, String title, String publisher, String genre, Integer isbn, String description, BigDecimal price, Integer quantity) {
        Specification<Book> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(byId(id));
        }

        if (title != null && !title.isEmpty()) {
            spec = spec.and(byTitle(title));
        }

        if (publisher != null && !publisher.isEmpty()) {
            spec = spec.and(byPublisher(publisher));
        }

        if (genre != null && !genre.isEmpty()) {
            spec = spec.and(byGenre(genre));
        }

        if (isbn != null) {
            spec = spec.and(byIsbn(isbn));
        }

        if (description != null && !description.isEmpty()) {
            spec = spec.and(byDescription(description));
        }

        if (price != null) {
            spec = spec.and(byPrice(price));
        }

        if (quantity != null) {
            spec = spec.and(byQuantity(quantity));
        }

//        if (writer != null) {
//            spec = spec.and(byWriter(writer));
//        }

        return spec;
    }
}
