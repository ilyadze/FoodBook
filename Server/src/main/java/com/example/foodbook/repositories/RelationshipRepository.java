package com.example.foodbook.repositories;

import com.example.foodbook.models.Recipe;
import com.example.foodbook.models.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
}
