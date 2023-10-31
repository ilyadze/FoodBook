package com.example.foodbook.response;

import lombok.Data;

@Data
public class JwtRefreshRequest {
    private String token;
}
