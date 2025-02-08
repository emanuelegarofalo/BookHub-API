package com.manu.BookHubAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String publisher;
    private String Genre;
    @NaturalId
    private Integer isbn;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    @OneToMany(mappedBy = "book")
    private Set<Loan> loans = Set.of();

    @ManyToMany
    @JoinTable(
            name = "book_writer",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id")
    )
    private Set<Writer> writers = Set.of();

    public Book(String title,
                String publisher,
                Integer isbn,
                String description,
                BigDecimal price,
                Integer quantity,
                Set<Writer> writers) {
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        setWriters(writers);
    }

    public void setWriters(Set<Writer> writers) {
        for (Writer writer : writers) {
            this.writers.add(writer);
            writer.getBooks().add(this);
        }
    }
}
