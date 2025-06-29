package com.manu.BookHubAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Username is required")
    private String username;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Length(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> favoriteBooks = Set.of();

    @OneToMany(mappedBy = "user")
    private Set<Loan> loans = Set.of();

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void addFavoriteBook(Book bookToAdd) {
        favoriteBooks.add(bookToAdd);
        System.out.println(favoriteBooks.size());
    }
}
