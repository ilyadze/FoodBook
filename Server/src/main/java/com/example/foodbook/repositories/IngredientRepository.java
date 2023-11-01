package com.example.foodbook.repositories;

import com.example.foodbook.models.Ingredient;
import com.example.foodbook.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
