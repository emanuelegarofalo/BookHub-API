package com.manu.BookHub.API.repository;

import com.manu.BookHub.API.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInterface extends JpaRepository<Book, Long> {
}
