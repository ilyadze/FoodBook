package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Entity
@Table
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;

    @ManyToOne
    @JoinColumn(name = "recipeId", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    Recipe recipe;
}
