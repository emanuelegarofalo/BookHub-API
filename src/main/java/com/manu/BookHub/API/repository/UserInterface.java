package com.manu.BookHub.API.repository;

import com.manu.BookHub.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterface  extends JpaRepository<User, Long> {
}
