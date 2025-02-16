package com.manu.BookHubAPI.config.security;

import com.manu.BookHubAPI.model.User;
import com.manu.BookHubAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);

        return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(user.getPassword()).roles(String.valueOf(user.getRole())).build();
    }
}
