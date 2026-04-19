package com.ice.realtimecache.Auth.Service;

import com.ice.realtimecache.Auth.DTO.Request.LoginRequest;
import com.ice.realtimecache.Auth.DTO.Request.RegisterRequest;
import com.ice.realtimecache.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    public void register(RegisterRequest request) {
        userService.addUser(request.getFullName(),request.getEmail(),passwordEncoder.encode(request.getPassword()));
    }

    public String verify(LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword())
        );

        if(authentication.isAuthenticated())
            return jwtService.generateToken(request.getName());

        return "false";
    }
}
