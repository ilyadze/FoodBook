package com.example.foodbook.repositories;

import com.example.foodbook.models.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Person,Long> {
}
