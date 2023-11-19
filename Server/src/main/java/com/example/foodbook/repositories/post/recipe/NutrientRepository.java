package com.example.foodbook.repositories.post.recipe;

import com.example.foodbook.models.post.recipe.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NutrientRepository extends JpaRepository<Nutrient,String> {
    Optional<Nutrient> findByName(String name);
}