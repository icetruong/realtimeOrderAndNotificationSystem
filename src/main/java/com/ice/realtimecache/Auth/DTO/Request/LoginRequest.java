package com.ice.realtimecache.Auth.DTO.Request;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
