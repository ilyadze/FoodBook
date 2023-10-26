package com.example.foodbook.response;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
