package com.example.foodbook.repositories;

import com.example.foodbook.models.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientRepository extends JpaRepository<Nutrient,Long> {
}
