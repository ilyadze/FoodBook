package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Recipe {
    @Id
    @Column(name = "id", nullable = false)
    Long id;
    String title;
    String readyInMinutes;
    String image;
    @Column(columnDefinition = "text")
    String instructions;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    List<Post> posts= new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    List<RecipeNutrient> recipeNutrients= new ArrayList<>();

    @ManyToMany(mappedBy = "recipes", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    List<Equipment> equipment= new ArrayList<>();;
    @ManyToMany
    @JoinTable(
            name = "recipes_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    List<Ingredient> ingredients = new ArrayList<>();
}
