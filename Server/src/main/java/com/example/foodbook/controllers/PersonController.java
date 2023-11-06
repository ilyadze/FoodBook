package com.example.foodbook.controllers;

import com.example.foodbook.exceptions.CommentException;
import com.example.foodbook.models.Person;
import com.example.foodbook.sevices.PersonService;
import com.example.foodbook.sevices.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final RelationshipService relationshipService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<?> getPerson(Authentication authentication) {
        return ResponseEntity.ok(personService.findByUsername(authentication.getName()).get());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getPerson( @PathVariable("username") String username, Authentication authentication) {
        Person findingPerson = personService.findByUsername(username).get();
        Person person = personService.findByUsername(authentication.getName()).get();
        if(!findingPerson.getIsPrivate() && findingPerson.getBlockedPersons().contains(person)) {
            return ResponseEntity.ok(personService.findByUsername(authentication.getName()).get());
        } else {
            throw new CommentException(HttpStatus.BAD_REQUEST, "Аккаунт приватный");
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updatePerson(Authentication authentication,
                                          @RequestBody Person updatePerson) {
        Person person = personService.findByUsername(authentication.getName()).orElseThrow(RuntimeException::new);
        person.setUsername(updatePerson.getUsername());
        person.setPassword(passwordEncoder.encode(updatePerson.getPassword()));
        person.setDescription(updatePerson.getDescription());
        person.setIsPrivate(updatePerson.getIsPrivate());
        personService.savePerson(person);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(Authentication authentication) {
        String username = authentication.getName();
        Person person = personService.findByUsername(username).get();
        return ResponseEntity.ok(relationshipService.getFollowersByUserId(person.getId()));
    }


    @PostMapping("/follow")
    public ResponseEntity<?> addFollower(Authentication authentication,
                                              @RequestParam("following") String followingUsername) {
        String username = authentication.getName();
        Person follower = personService.findByUsername(username).orElseThrow(RuntimeException::new);
        Person following = personService.findByUsername(followingUsername).orElseThrow(RuntimeException::new);
        if (!relationshipService.ifPersonFollowed(follower, following)) {
            personService.addFollower(follower, following);
            return ResponseEntity.ok("Пользователь подписался");
        } else {
            return ResponseEntity.ok("Пользователь уже подписан");
        }
    }

    @GetMapping("/blocked_list")
    public ResponseEntity<?> getBlockedList(Authentication authentication) {
        String username = authentication.getName();
        Person person = personService.findByUsername(username).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(person.getBlockedPersons());
    }

    @PostMapping("/blocked_list")
    public ResponseEntity<?> addToBlockedList(Authentication authentication,
                                              @RequestParam("username") String blockedUsername) {
        String username = authentication.getName();
        Person person = personService.findByUsername(username).orElseThrow(RuntimeException::new);
        if (personService.addBlockedPerson(person, blockedUsername)) {
            return ResponseEntity.ok("Пользователь заблокирован");
        } else {
            throw new CommentException(HttpStatus.BAD_REQUEST, "Пользователь не найден");
        }
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Пользователь не аутентифицирован");
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Пользователь не аутентифицирован");
    }
}
