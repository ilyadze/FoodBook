package com.example.foodbook.controllers;

import com.example.foodbook.dto.RegistrationUserDTO;
import com.example.foodbook.response.JwtRequest;
import com.example.foodbook.sevices.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
       return authService.createAuthToken(authRequest);
    }
    @PostMapping("/registration")
    public ResponseEntity<?> createNewPerson(@RequestBody RegistrationUserDTO registrationUserDTO){
       return authService.createNewPerson(registrationUserDTO);
    }
}