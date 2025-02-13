package com.manu.BookHubAPI.service;

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

    public void deleteUser() {
        return;
    }
}
