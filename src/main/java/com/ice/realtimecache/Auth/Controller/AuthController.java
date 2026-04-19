package com.ice.realtimecache.Auth.Controller;

import com.ice.realtimecache.Auth.DTO.Request.RegisterRequest;
import com.ice.realtimecache.Auth.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/register")
    public void register(@RequestBody RegisterRequest request)
    {
        authService.register(request);
    }
}
