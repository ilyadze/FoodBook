package com.example.foodbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO {
        private Long id;
        private String username;
        private String email;
}
