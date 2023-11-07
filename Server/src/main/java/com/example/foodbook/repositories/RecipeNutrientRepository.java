package com.example.foodbook.repositories;

import com.example.foodbook.models.RecipeNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeNutrientRepository extends JpaRepository<RecipeNutrient,Long> {
}
