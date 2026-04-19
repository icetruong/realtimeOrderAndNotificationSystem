package com.ice.realtimecache.Auth.Service;

import com.ice.realtimecache.Auth.DTO.Request.RegisterRequest;
import com.ice.realtimecache.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public void register(RegisterRequest request) {
        userService.addUser(request.getFullName(),request.getEmail(),passwordEncoder.encode(request.getPassword()));
    }
}
