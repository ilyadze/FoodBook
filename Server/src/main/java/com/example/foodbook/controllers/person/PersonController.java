package com.example.foodbook.controllers.person;

import com.example.foodbook.dto.person.PersonUpdateDTO;
import com.example.foodbook.exceptions.CommentException;
import com.example.foodbook.models.person.Person;
import com.example.foodbook.sevices.person.PersonService;
import com.example.foodbook.sevices.person.RelationshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PersonController {

    PersonService personService;

    RelationshipService relationshipService;

    PasswordEncoder passwordEncoder;

    ModelMapper modelMapper;

    @Operation(
            summary = "Get person",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Info",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Person.class)))
                            })
            }
    )
    @GetMapping()
    public ResponseEntity<?> getPerson(Authentication authentication) {
        return ResponseEntity.ok(personService.findByUsername(authentication.getName()));
    }

    @Operation(
            summary = "Get person",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Info",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Person.class)))
                            })
            }
    )
    @GetMapping("/{username}")
    public ResponseEntity<?> getPerson( @PathVariable("username") String username, Authentication authentication) {
        Person findingPerson = personService.findByUsername(username);
        Person person = personService.findByUsername(authentication.getName());
        if(!findingPerson.getIsPrivate() && !findingPerson.getBlockedPersons().contains(person)) {
            return ResponseEntity.ok(findingPerson);
        } else {
            throw new CommentException(HttpStatus.BAD_REQUEST, "Аккаунт приватный");
        }
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<?> getFollowers(@PathVariable("username") String username) {
        Person person = personService.findByUsername(username);
        return ResponseEntity.ok(person.getFollowers());
    }

    @GetMapping("/{username}/followings")
    public ResponseEntity<?> getFollowings(@PathVariable("username") String username) {
        Person person = personService.findByUsername(username);

        return ResponseEntity.ok(person.getFollowings());
    }



    @PostMapping("/follow")
    public ResponseEntity<?> addFollower(Authentication authentication,
                                              @RequestParam("following") String followingUsername) {
        String username = authentication.getName();
        Person follower = personService.findByUsername(username);
        Person following = personService.findByUsername(followingUsername);
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
        Person person = personService.findByUsername(username);
        return ResponseEntity.ok(person.getBlockedPersons());
    }

    @PostMapping("/blocked_list")
    public ResponseEntity<?> addToBlockedList(Authentication authentication,
                                              @RequestParam("username") String blockedUsername) {
        String username = authentication.getName();
        Person person = personService.findByUsername(username);
        if (personService.addBlockedPerson(person, blockedUsername)) {
            return ResponseEntity.ok("Пользователь заблокирован");
        } else {
            throw new CommentException(HttpStatus.BAD_REQUEST, "Пользователь не найден");
        }
    }

    @Operation(
            summary = "Update person",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonUpdateDTO.class)))
                            })
            }
    )
    @PatchMapping("/update")
    public ResponseEntity<?> updatePerson(Authentication authentication,
                                          @RequestBody PersonUpdateDTO updatePerson) {
        Person person = personService.findByUsername(authentication.getName());
        person.setUsername(updatePerson.getUsername());
        person.setPassword(passwordEncoder.encode(updatePerson.getPassword()));
        person.setDescription(updatePerson.getDescription());
        person.setIsPrivate(updatePerson.getIsPrivate());
        personService.savePerson(person);
        return ResponseEntity.ok(person);
    }

}
