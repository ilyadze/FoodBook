package com.example.foodbook.dto.post.recipe;
import com.example.foodbook.models.post.recipe.Ingredient;
import com.example.foodbook.response.recipe.NutritionApiResponse;
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
    private String readyInMinutes;
    private List<Ingredient> extendedIngredients;
    private NutritionApiResponse nutrition;
    private String instructions;
    private List<EquipmentApiDTO> equipment;
  /*private List <EquipmentApiDTO> equipment;*/
}
