package com.example.foodbook.controllers;

import com.example.foodbook.models.Person;
import com.example.foodbook.sevices.PersonService;
import com.example.foodbook.sevices.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final RelationshipService relationshipService;

    @GetMapping("/followers")
    public List<Person> getFollowers() {
        return null;
    }




}
