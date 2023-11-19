package com.example.foodbook.response.recipe;

import com.example.foodbook.dto.post.recipe.NutrientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionApiResponse {
    private List<NutrientDTO> nutrients;
}
