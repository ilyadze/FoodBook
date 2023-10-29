package com.example.foodbook.dto;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegistrationUserDTO {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String description;


}
