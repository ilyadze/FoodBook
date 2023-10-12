package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

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

    boolean isAPIRecept=true; //Потом исправить, мб поискать аннотацию с дефолтным значением

    String title;

    String image;

    String instruction;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    List<RecipeIngredient> recipeIngredient;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    List<Post> posts;

    @ManyToMany(mappedBy = "recipes", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    List<Equipment> equipment;


}
