package com.example.foodbook.sevices;
import com.example.foodbook.models.Ingredient;
import com.example.foodbook.repositories.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    public void saveIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }
    public boolean isIngredientPresent(Long id){
        return ingredientRepository.findById(id).isPresent();
    }
    public void saveIngredients(List<Ingredient> ingredients) {
        ingredientRepository.saveAll(ingredients);
    }
}