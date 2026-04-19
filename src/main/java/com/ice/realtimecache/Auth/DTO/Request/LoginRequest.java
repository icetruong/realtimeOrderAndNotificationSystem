package com.ice.realtimecache.Auth.DTO.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String name;
    private String password;
}
