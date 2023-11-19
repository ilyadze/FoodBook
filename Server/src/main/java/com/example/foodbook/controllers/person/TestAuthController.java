package com.example.foodbook.controllers.person;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TestAuthController {
    @GetMapping("/unsecured")
    public String unsecuredData(Principal principal){
        return "Insecured data "+principal.getName();
    }

    @GetMapping("/secured")
    public String securedData(){
        return "Secured data";
    }

    @GetMapping("/admin")
    public String adminData(){
        return "Admin data";
    }

    @GetMapping("/info")
    public String UserData(Principal principal){
        return principal.getName();
    }
}
