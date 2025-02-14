package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.dto.UserDTO;
import com.manu.BookHubAPI.exception.UserAlreadyExist;
import com.manu.BookHubAPI.exception.UserNotFoundException;
import com.manu.BookHubAPI.model.User;
import com.manu.BookHubAPI.repository.UserRepository;
import com.manu.BookHubAPI.repository.specifications.UserSpecification;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSpecification userSpecification;

    public Set<User> getUser(Long id, String username, String email) {
        Set<User> users = userRepository.findAll(userSpecification.combinedSpecification(id, username, email));

        if (users.isEmpty()) throw new UserNotFoundException();
        else return users;
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

    public User updateUser(Long id, String email, String userName, String password) {
        User userToUpdate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (email != null && !email.isEmpty()) {
            userToUpdate.setEmail(email);
        }
        if (userName != null && !userName.isEmpty()) {
            userToUpdate.setUsername(userName);
        }
        if (password != null && !password.isEmpty()) {
            userToUpdate.setPassword(password);
        }

        return userRepository.save(userToUpdate);
    }
}
