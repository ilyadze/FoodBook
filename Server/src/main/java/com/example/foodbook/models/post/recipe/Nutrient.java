package com.example.foodbook.models.post.recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String unit;
    @OneToMany(mappedBy = "nutrient")
    private List<RecipeNutrient> recipeNutrients= new ArrayList<>();
}