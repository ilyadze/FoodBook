package com.example.foodbook.requests.jwt;

import lombok.Data;

@Data
public class JwtRefreshRequest {
    private String token;
}
