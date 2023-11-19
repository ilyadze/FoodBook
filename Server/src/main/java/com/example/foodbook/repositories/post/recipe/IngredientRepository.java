package com.example.foodbook.repositories.post.recipe;

import com.example.foodbook.models.post.recipe.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
