package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String comment;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated=LocalDateTime.now();
    }
}
