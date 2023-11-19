package com.example.foodbook.sevices.person;

import com.example.foodbook.models.person.Person;
import com.example.foodbook.models.person.Relationship;
import com.example.foodbook.repositories.person.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;


    public List<Person> getFollowersByUserId(Long userId) {
//        List<Relationship> subscriptions = relationshipRepository.findAllByFollowingId(userId);
        List<Relationship> subscriptions = relationshipRepository.findAll();
        System.out.println(subscriptions);
        List<Person> followers = subscriptions.stream()
                .map(Relationship::getFollower)
                .collect(Collectors.toList());
        return followers;
    }

    public boolean ifPersonFollowed(Person follower, Person following) {
        return relationshipRepository.existsByFollowerAndFollowing(follower, following);
    }
}
