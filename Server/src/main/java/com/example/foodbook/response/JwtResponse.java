package com.example.foodbook.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private String refreshToken;
}
