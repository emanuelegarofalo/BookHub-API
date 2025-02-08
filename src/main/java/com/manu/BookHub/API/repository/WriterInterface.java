package com.manu.BookHub.API.repository;

import com.manu.BookHub.API.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterInterface extends JpaRepository<Writer, Long> {
}
