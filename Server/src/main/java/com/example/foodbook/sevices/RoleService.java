package com.example.foodbook.sevices;

import com.example.foodbook.models.Role;
import com.example.foodbook.repositories.RoleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role findRoleByName(String name){
        return roleRepository.findByName(name).get();
    }
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
