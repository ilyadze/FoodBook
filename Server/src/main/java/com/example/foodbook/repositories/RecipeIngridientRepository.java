package com.example.foodbook.repositories;

import com.example.foodbook.models.Privacy;
import com.example.foodbook.models.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngridientRepository extends JpaRepository<RecipeIngredient,Long> {
}
