package com.manu.BookHubAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String publisher;
    private String genre;
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
    @JsonIgnore
    private Set<Writer> writers = new HashSet<>();

    public Book(String title,
                String publisher,
                String genre,
                Integer isbn,
                String description,
                BigDecimal price,
                Integer quantity,
                Set<Writer> writers) {
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
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
