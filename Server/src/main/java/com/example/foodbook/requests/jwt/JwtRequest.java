package com.example.foodbook.requests.jwt;

import lombok.Data;

@Data
public  class JwtRequest {
    private String username;
    private String password;
}