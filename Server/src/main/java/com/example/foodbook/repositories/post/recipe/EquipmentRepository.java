package com.example.foodbook.repositories.post.recipe;

import com.example.foodbook.models.post.recipe.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
