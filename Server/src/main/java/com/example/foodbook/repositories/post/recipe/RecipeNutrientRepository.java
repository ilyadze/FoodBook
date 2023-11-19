package com.example.foodbook.repositories.post.recipe;

import com.example.foodbook.models.post.recipe.RecipeNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeNutrientRepository extends JpaRepository<RecipeNutrient,Long> {
}
