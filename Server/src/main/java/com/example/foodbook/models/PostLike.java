package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post_like")
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
