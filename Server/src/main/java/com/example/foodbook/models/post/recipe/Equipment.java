package com.example.foodbook.models.post.recipe;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipment {
    @Id
    @Column(name = "id", nullable = false)
    Long id;

    String name;


    @ManyToMany
//    @JoinTable(
//            name = "recipe_equipment",
//            joinColumns = @JoinColumn(name = "equipment_id"),
//            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    List<Recipe> recipes;
}
