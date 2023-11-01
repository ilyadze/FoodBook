package com.example.foodbook.repositories;

import com.example.foodbook.models.Equipment;
import com.example.foodbook.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
