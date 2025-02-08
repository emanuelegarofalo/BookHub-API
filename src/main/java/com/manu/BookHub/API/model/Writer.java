package com.manu.BookHub.API.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.Set;

@Entity
@Getter
@Setter
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @NaturalId
    private String email;

    @ManyToMany(mappedBy = "writers")
    private Set<Book> books = Set.of();

    public Writer(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }
}
