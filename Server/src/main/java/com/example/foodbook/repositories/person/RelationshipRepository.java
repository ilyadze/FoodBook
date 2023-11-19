package com.example.foodbook.repositories.person;

import com.example.foodbook.models.person.Person;
import com.example.foodbook.models.person.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

    boolean existsByFollowerAndFollowing(Person follower, Person following);

    List<Relationship> findAllByFollowingId(Long userId);

    List<Relationship> findAll();
}
