package com.example.foodbook.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "recipes_nutrients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeNutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;

    private double amount;

}