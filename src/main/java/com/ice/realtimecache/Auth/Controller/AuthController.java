package com.ice.realtimecache.Auth.Controller;

import com.ice.realtimecache.Auth.DTO.Request.LoginRequest;
import com.ice.realtimecache.Auth.DTO.Request.RegisterRequest;
import com.ice.realtimecache.Auth.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request)
    {
        authService.register(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request)
    {
        return authService.verify(request);
    }

}
