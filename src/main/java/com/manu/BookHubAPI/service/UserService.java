package com.manu.BookHubAPI.service;

import com.manu.BookHubAPI.dto.UserDTO;
import com.manu.BookHubAPI.exception.UserAlreadyExist;
import com.manu.BookHubAPI.exception.UserNotFoundException;
import com.manu.BookHubAPI.model.User;
import com.manu.BookHubAPI.repository.UserRepository;
import com.manu.BookHubAPI.repository.specifications.UserSpecification;
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

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void createUser(UserDTO user) {
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

    public void updateEmail(Long id, String email) {
        User userToUpdate = getUserById(id);
        userToUpdate.setEmail(email);
        userRepository.save(userToUpdate);
    }

    public void updateUsername(Long id, String username) {
        User userToUpdate = getUserById(id);
        userToUpdate.setUsername(username);
        userRepository.save(userToUpdate);
    }

    public void updatePassword(Long id, String password) {
        User userToUpdate = getUserById(id);
        userToUpdate.setPassword(password);
        userRepository.save(userToUpdate);
    }
}
