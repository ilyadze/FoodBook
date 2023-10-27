package com.example.foodbook.sevices;

import com.example.foodbook.dto.RegistrationUserDTO;
import com.example.foodbook.models.Person;

import com.example.foodbook.models.Role;
import com.example.foodbook.repositories.PersonRepository;
import com.example.foodbook.repositories.RoleRepository;
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
    private   PersonRepository repository;
    private  RoleService roleService;
    private  PasswordEncoder passwordEncoder;
    @Autowired
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> getAll() {
        return this.repository.findAll();
    }

    public Optional<Person> findByUsername(String username) {

        return this.repository.findByUsername(username);
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
        person.setPassword(passwordEncoder.encode(registrationUserDTO.getPassword()));
        person.setUsername(registrationUserDTO.getUsername());
        person.setEmail(registrationUserDTO.getEmail());
        person.setRoles(List.of(roleService.getUserRole()));
        System.out.println(2);
        return repository.save(person);
    }

}
