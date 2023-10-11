package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    Integer amount;


    @OneToOne
    @Cascade(CascadeType.ALL)
    Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "recipeId", referencedColumnName = "id")
    @Cascade(CascadeType.ALL)
    Recipe recipe;

}
