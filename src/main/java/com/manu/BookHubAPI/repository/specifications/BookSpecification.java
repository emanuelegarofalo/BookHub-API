package com.manu.BookHubAPI.repository.specifications;

import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.model.Writer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import jakarta.persistence.criteria.Join;
import java.math.BigDecimal;

@Component
public class BookSpecification {

    public Specification<Book> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public Specification<Book> byTitle(String title) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public Specification<Book> byGenre(String genre) {
        return (root, query, cb) -> cb.equal(root.get("genre"), genre);
    }

    public Specification<Book> byPublisher(String publisher) {
        return (root, query, cb) -> cb.equal(root.get("publisher"), publisher);
    }

    public Specification<Book> byIsbn(Integer isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public Specification<Book> byMaxPrice(BigDecimal maxPrice) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public Specification<Book> byQuantity(Integer quantity) {
        return (root, query, cb) -> cb.equal(root.get("quantity"), quantity);
    }

    public Specification<Book> byWriterId(Long writerId) {
        return (root, query, cb) -> {
            Join<Book, Writer> writerJoin = root.join("writers");
            return cb.equal(writerJoin.get("id"), writerId);
        };
    }

    public Specification<Book> combinedSpecification(
            Long id, String title, String genre, String publisher, Integer isbn, BigDecimal maxPrice, Integer quantity, Long writerId) {
        Specification<Book> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(byId(id));
        }

        if (title != null && !title.isEmpty()) {
            spec = spec.and(byTitle(title));
        }

        if (genre != null && !genre.isEmpty()) {
            spec = spec.and(byGenre(genre));
        }

        if (publisher != null && !publisher.isEmpty()) {
            spec = spec.and(byPublisher(publisher));
        }

        if (isbn != null) {
            spec = spec.and(byIsbn(isbn));
        }

        if (maxPrice != null) {
            spec = spec.and(byMaxPrice(maxPrice));
        }

        if (quantity != null) {
            spec = spec.and(byQuantity(quantity));
        }

        if (writerId != null) {
            spec = spec.and(byWriterId(writerId));
        }

        return spec;
    }
}
