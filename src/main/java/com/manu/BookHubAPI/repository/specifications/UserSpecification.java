package com.manu.BookHubAPI.repository.specifications;

import com.manu.BookHubAPI.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {
    public Specification<User> byUsername(String name) {
        return (root, query, cb) -> cb.like(root.get("username"), "%" + name + "%");
    }

    public Specification<User> byId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id); }

    public Specification<User> byEmail(String email) {
        return ((root, query, cb) -> cb.like(root.get("email"), "%" + email + "%"));
    }

    public Specification<User> byRole(String role) {
        return ((root, query, cb) -> cb.equal(root.get("role"), role));
    }

    public Specification<User> combinedSpecification(Long id, String username, String email, String role) {
        Specification<User> spec = Specification.where(null);

        if (id != null) {
            spec = spec.and(byId(id));
        }

        if (username != null && !username.isEmpty()) {
            spec = spec.and(byUsername(username));
        }

        if (email != null && !email.isEmpty()) {
            spec = spec.and(byEmail(email));
        }

        if (role != null && !role.isEmpty()) {
            spec = spec.and(byRole(role));
        }

        return spec;
    }
}
