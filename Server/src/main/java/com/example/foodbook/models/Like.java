package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
