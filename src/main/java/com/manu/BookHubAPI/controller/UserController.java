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
    public ResponseEntity<ApiResponse> getUser(@RequestParam Long id,
                                               @RequestParam String username,
                                               @RequestParam String email) {
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

    @PutMapping("/updateemail/{id}")
    public ResponseEntity<ApiResponse> updateEmail(@PathVariable Long id, @RequestParam String email) {
        userService.updateEmail(id, email);
        return ResponseEntity.ok(new ApiResponse("user info updated", null));
    }

    @PutMapping("/updateusername/{id}")
    public ResponseEntity<ApiResponse> updateUsername(@PathVariable Long id, @RequestParam String username) {
        userService.updateUsername(id, username);
        return ResponseEntity.ok(new ApiResponse("user info updated", null));
    }

    @PutMapping("/updatepassword/{id}")
    public ResponseEntity<ApiResponse> updatePassword(@PathVariable Long id, @RequestParam String password) {
        userService.updatePassword(id, password);
        return ResponseEntity.ok(new ApiResponse("user info updated", null));
    }
}
