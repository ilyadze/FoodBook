package com.example.foodbook.repositories;

import com.example.foodbook.models.Person;
import com.example.foodbook.models.Recipe;
import com.example.foodbook.models.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

    boolean existsByFollowerAndFollowing(Person follower, Person following);

    List<Relationship> findAllByFollowingId(Long userId);

    List<Relationship> findAll();
}
