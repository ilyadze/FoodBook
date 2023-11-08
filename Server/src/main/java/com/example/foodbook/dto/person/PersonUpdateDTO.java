package com.example.foodbook.dto.person;


import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

//{
//    "username": "user3",
//    "description": "test update account",
//    "password": "user3",
//    "isPrivate": false
//}
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonUpdateDTO {
     String username;
     String image;
     String password;
     String description;
     Boolean isPrivate = false;
}
