package com.manu.BookHubAPI.repository;

import com.manu.BookHubAPI.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {
    boolean existsByEmail(String email);

    Optional<Writer> findByNameAndLastName(String name, String lastName);
}
