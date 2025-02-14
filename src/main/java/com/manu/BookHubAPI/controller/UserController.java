package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.config.UserMapper;
import com.manu.BookHubAPI.dto.UserDTO;
import com.manu.BookHubAPI.model.User;
import com.manu.BookHubAPI.response.ApiResponse;
import com.manu.BookHubAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getuser")
    public ResponseEntity<ApiResponse> getUser(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String username,
                                               @RequestParam(required = false) String email) {
        Set<User> users = userService.getUser(id, username, email);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new ApiResponse("user found",
                        users.stream().map(UserMapper.INSTANCE::toUserDTO)));
    }

    @PostMapping("/createuser")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDTO user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user created", null ));
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id,
                                                  @RequestParam(required = false) String username,
                                                  @RequestParam(required = false) String email,
                                                  @RequestParam(required = false) String password) {
        User userUpdated = userService.updateUser(id, email, username, password);
        return ResponseEntity.ok(new ApiResponse("user info updated", userUpdated));
    }
}
