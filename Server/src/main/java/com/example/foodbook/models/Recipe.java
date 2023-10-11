package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    Long creatorId;

    String title;

    String image;

    String instruction;

    @OneToMany(mappedBy = "recipe")
    @Cascade(CascadeType.REFRESH)
    List<RecipeIngredient> recipeIngredient;

    @OneToMany(mappedBy = "recipe")
    @Cascade(CascadeType.REFRESH)
    List<Post> posts;

    @ManyToMany(mappedBy = "recipes")
    List<Equipment> equipment;



}
