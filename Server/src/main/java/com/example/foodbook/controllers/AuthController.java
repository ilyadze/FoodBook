/*
package com.example.foodbook.controllers;

import com.example.foodbook.exceptions.AppError;
import com.example.foodbook.models.Person;
import com.example.foodbook.response.JwtRequest;
import com.example.foodbook.response.JwtResponse;
import com.example.foodbook.sevices.PersonService;
import com.example.foodbook.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final PersonService personService;
    private final JWTUtil jwtUtil;
    private  AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));

        } catch(BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),"Неправильный логин или пароль"),HttpStatus.UNAUTHORIZED);

        }
        UserDetails userDetails = personService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


}
*/
