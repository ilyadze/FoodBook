package com.example.foodbook.requests;

import lombok.Data;

@Data
public class JwtRefreshRequest {
    private String token;
}
