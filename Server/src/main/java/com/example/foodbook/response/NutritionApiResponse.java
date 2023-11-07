package com.example.foodbook.response;

import com.example.foodbook.dto.NutrientDTO;
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
