package com.example.foodbook.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


/*
public enum Role implements GrantedAuthority {
    USER,ADMIN; 
    @Override
    public String getAuthority() {
        return name();
    }
}
*/

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public Role() {

    }
}
