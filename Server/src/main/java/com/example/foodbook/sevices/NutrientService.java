package com.example.foodbook.sevices;

import com.example.foodbook.models.Ingredient;
import com.example.foodbook.models.Nutrient;
import com.example.foodbook.repositories.IngredientRepository;
import com.example.foodbook.repositories.NutrientRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class NutrientService {
    private final NutrientRepository nutrientRepository;
    public void saveNutrient(Nutrient nutrient){
        nutrientRepository.save(nutrient);
    }
    public boolean isNutrientPresent(String name){
        return nutrientRepository.findByName(name).isPresent();
    }
    public Optional<Nutrient> findByName(String name){
        return nutrientRepository.findByName(name);
    }
}
