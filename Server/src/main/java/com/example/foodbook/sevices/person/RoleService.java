package com.example.foodbook.sevices.person;

import com.example.foodbook.models.person.Role;
import com.example.foodbook.repositories.person.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role findRoleByName(String name){
        return roleRepository.findByName(name).get();
    }
    public Role getUserRole() {//TODO
        /*Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        roleRepository.save(role1);*/
        return roleRepository.findByName("ROLE_USER").get();
    }
}
