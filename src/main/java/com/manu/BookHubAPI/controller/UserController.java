package com.manu.BookHubAPI.controller;

import com.manu.BookHubAPI.config.mapper.UserMapper;
import com.manu.BookHubAPI.dto.UserDTO;
import com.manu.BookHubAPI.model.User;
import com.manu.BookHubAPI.request.UserCriteriaDTO;
import com.manu.BookHubAPI.response.ApiResponse;
import com.manu.BookHubAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getuser")
    public ResponseEntity<ApiResponse> getUser(@ModelAttribute UserCriteriaDTO criteria, @RequestParam MultiValueMap<String, String> allParam) {
        if (allParam.containsKey("password")) {
            return ResponseEntity.badRequest().body(new ApiResponse("la ricerca in base alla password non è consentita", null));
        }

        Set<User> users = userService.getUser(criteria);
        return ResponseEntity.ok(new ApiResponse("user found",
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
                                                  @ModelAttribute UserCriteriaDTO criteria) {
        userService.updateUser(id, criteria);
        return ResponseEntity.ok(new ApiResponse("user info updated", null));
    }
}
