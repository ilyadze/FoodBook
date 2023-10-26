/*
package com.example.foodbook.models;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

*/
/*
public enum Role implements GrantedAuthority {
    USER,ADMIN; 
    @Override
    public String getAuthority() {
        return name();
    }
}
*//*

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}*/
