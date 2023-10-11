package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;

}
