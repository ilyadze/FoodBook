package com.example.foodbook.sevices.post.recipe;

import com.example.foodbook.models.post.recipe.Nutrient;
import com.example.foodbook.repositories.post.recipe.NutrientRepository;
import lombok.AllArgsConstructor;
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
