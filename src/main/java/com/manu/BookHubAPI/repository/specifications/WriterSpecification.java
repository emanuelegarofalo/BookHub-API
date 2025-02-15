package com.manu.BookHubAPI.repository.specifications;

import com.manu.BookHubAPI.model.Book;
import com.manu.BookHubAPI.model.Writer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Join;

@Component
public class WriterSpecification {
    public Specification<Writer> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public Specification<Writer> byName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public Specification<Writer> byLastName(String lastName) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public Specification<Writer> byEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public Specification<Writer> byBookId(Long bookId) {
        return (root, query, cb) -> {
            Join<Writer, Book> bookJoin = root.join("books");
            return cb.equal(bookJoin.get("id"), bookId);
        };
    }

    public Specification<Writer> combinedSpecification(
            Long id, String name, String lastName, String email, Long bookId) {
        Specification<Writer> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(byId(id));
        }

        if (name != null && !name.isEmpty()) {
            spec = spec.and(byName(name));
        }

        if (lastName != null && !lastName.isEmpty()) {
            spec = spec.and(byLastName(lastName));
        }

        if (email != null && !email.isEmpty()) {
            spec = spec.and(byEmail(email));
        }

        if (bookId != null) {
            spec = spec.and(byBookId(bookId));
        }

        return spec;
    }
}
