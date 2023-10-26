package com.example.foodbook.sevices;

import com.example.foodbook.models.Person;

import com.example.foodbook.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService /*implements UserDetailsService */{
    private  final PersonRepository repository;

    public List<Person> getAll() {
        return this.repository.findAll();
    }

    public Optional<Person> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    /*@Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = findByUsername(username).orElseThrow(()->new UsernameNotFoundException(
                String.format("Пользователь '%s не найден",username)
        ));
        return new User(
                person.getUsername(),
                person.getPassword(),
                person.getRoles().stream().map(role ->  new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList())
        );

    }

    public void createPerson( Person person){
        //Проверку сделать
        person.setRoles(Set.of(Role.USER));
        repository.save(person);
    }*/

}
