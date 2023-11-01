package com.example.foodbook.dto;

import com.example.foodbook.models.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullRecipeAPIDTO {
    private Long id;
    private String image;
    private String title;
    private List<Ingredient> extendedIngredients;
    private String instructions;
    private List<EquipmentApiDTO> equipment;
  /*private List <EquipmentApiDTO> equipment;*/
}
