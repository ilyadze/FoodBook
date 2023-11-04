package com.example.foodbook.sevices;

import com.example.foodbook.dto.RegistrationUserDTO;
import com.example.foodbook.models.Person;

import com.example.foodbook.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {
    private   PersonRepository personRepository;
    private  RoleService roleService;
    private  PasswordEncoder passwordEncoder;

    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

    public Person findById(long personId){
     return personRepository.findById(personId).orElseThrow(()-> new UsernameNotFoundException(("User with this id not found")));//todo
    }

    public Optional<Person> findByUsername(String username) {

        return this.personRepository.findByUsername(username);
    }


    public  Optional<List<Person>> findFollowersById(Long id) {
        return null;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = findByUsername(username).orElseThrow(()->new UsernameNotFoundException(
                String.format("Пользователь '%s не найден",username)
        ));
        return new User(
                person.getUsername(),
                person.getPassword(),
                person.getRoles().stream().map(role ->  new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public Person createPerson( RegistrationUserDTO registrationUserDTO){
        //Проверку сделать
        System.out.println(1);
        Person person = new Person();
        person.setDescription(registrationUserDTO.getDescription());
        person.setPassword(passwordEncoder.encode(registrationUserDTO.getPassword()));
        person.setUsername(registrationUserDTO.getUsername());
        person.setEmail(registrationUserDTO.getEmail());
        person.setRoles(List.of(roleService.getUserRole()));
        System.out.println(2);
        return personRepository.save(person);
    }

}
