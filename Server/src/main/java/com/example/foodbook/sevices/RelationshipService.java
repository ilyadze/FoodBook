package com.example.foodbook.sevices;

import com.example.foodbook.models.Person;
import com.example.foodbook.repositories.PersonRepository;
import com.example.foodbook.repositories.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;


}
