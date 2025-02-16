package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.dto.UserDTO;
import com.manu.BookHubAPI.exception.UserAlreadyExist;
import com.manu.BookHubAPI.exception.UserNotFoundException;
import com.manu.BookHubAPI.model.User;
import com.manu.BookHubAPI.repository.UserRepository;
import com.manu.BookHubAPI.repository.specifications.UserSpecification;
import com.manu.BookHubAPI.request.UserCriteriaDTO;
import com.manu.BookHubAPI.request.WriterCriteriaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSpecification userSpecification;

    public Set<User> getUser(UserCriteriaDTO criteria) {
        Set<User> users = userRepository.findAll(
                userSpecification.combinedSpecification(criteria.id(), criteria.username(),
                        criteria.email(), criteria.role())
        );

        if (users.isEmpty()) throw new UserNotFoundException();
        else return users;
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public void createUser(@NotNull UserDTO user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(
                    new User(user.getUsername(), user.getEmail(),user.getPassword(), user.getRole()));
        } else throw new UserAlreadyExist();
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else throw new UserNotFoundException();
    }

    public void updateUser(Long id, UserCriteriaDTO criteria) {
        User userToUpdate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (criteria.email() != null && !criteria.email().isEmpty()) {
            if (!userRepository.existsByEmail(criteria.email())) {
                userToUpdate.setEmail(criteria.email());
            } else throw new UserAlreadyExist();
        }
        if (criteria.username() != null && !criteria.username().isEmpty()) {
            userToUpdate.setUsername(criteria.username());
        }
        if (criteria.password() != null && !criteria.password().isEmpty()) {
            userToUpdate.setPassword(criteria.password());
        }

        userRepository.save(userToUpdate);
    }
}
