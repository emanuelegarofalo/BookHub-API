package com.manu.BookHubAPI.repository;

import com.manu.BookHubAPI.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Set<User> findAll(Specification<User> specification);

    boolean existsByEmail(String email);
}
