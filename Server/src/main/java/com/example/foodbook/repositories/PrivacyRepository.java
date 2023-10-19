package com.example.foodbook.repositories;

import com.example.foodbook.models.Privacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyRepository extends JpaRepository<Privacy,Long> {
}
