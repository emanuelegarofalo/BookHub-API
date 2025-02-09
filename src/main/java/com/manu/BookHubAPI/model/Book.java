package com.manu.BookHubAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

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
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Publisher is required")
    private String publisher;
    @NotNull(message = "Genre is required")
    private String genre;
    @NaturalId
    private Integer isbn;
    @Length(min = 10, message = "Description must be at least 10 characters long")
    private String description;
    @PositiveOrZero(message = "Price must be greater than 0")
    private BigDecimal price;
    @PositiveOrZero(message = "Quantity must be greater than 0")
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
