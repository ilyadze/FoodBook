package com.example.foodbook.sevices;
import com.example.foodbook.dto.RegistrationUserDTO;
import com.example.foodbook.exceptions.LocalException;
import com.example.foodbook.models.Person;
import com.example.foodbook.models.Relationship;
import com.example.foodbook.repositories.PersonRepository;
import com.example.foodbook.repositories.RelationshipRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class PersonService implements UserDetailsService {
    private final  PersonRepository personRepository;
    private final  RoleService roleService;
    private final  PasswordEncoder passwordEncoder;
    private final RelationshipRepository relationshipRepository;
    @Autowired
    public PersonService(PersonRepository personRepository, RoleService roleService, PasswordEncoder passwordEncoder, RelationshipRepository relationshipRepository) {
        this.personRepository = personRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.relationshipRepository = relationshipRepository;
    }
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }
    public Person findById(long personId){
     return personRepository.findById(personId).orElseThrow(()-> new UsernameNotFoundException(("User with this id not found")));//todo
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(("User with this id not found")));
    }
    public boolean isPersonAlive(String username){
        return  personRepository.findByUsername(username).isPresent();
    }


    public  Optional<List<Person>> findFollowersById(Long id) {
        return null;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findByUsername(username);
        return new User(
                person.getUsername(),
                person.getPassword(),
                person.getRoles().stream().map(role ->  new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public Person createPerson( RegistrationUserDTO registrationUserDTO){
        //TODO
        //Проверку сделать
        Person person = new Person();
        person.setDescription(registrationUserDTO.getDescription());
        person.setPassword(passwordEncoder.encode(registrationUserDTO.getPassword()));
        person.setUsername(registrationUserDTO.getUsername());
        person.setEmail(registrationUserDTO.getEmail());
        person.setRoles(List.of(roleService.getUserRole()));

        System.out.println(2);
        try {
            return personRepository.save(person);
        }
        catch (DataIntegrityViolationException e){
            throw new LocalException(HttpStatus.CONFLICT,"Такой email уже зарегистрирован");
        }

    }

    public boolean addBlockedPerson(Person person, String blockedPersonUsername) {
        Optional<Person> blockedPerson = personRepository.findByUsername(blockedPersonUsername);
        if (blockedPerson.isPresent()) {
            person.getBlockedPersons().add(blockedPerson.get());
            personRepository.save(person);
            return true;
        } else {
            return false;
        }
    }

    public boolean addFollower(Person follower, Person following) {
        Relationship subscription = new Relationship();
        subscription.setFollower(follower);
        subscription.setFollowing(following);

        relationshipRepository.save(subscription);
        return true;

    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }
}
