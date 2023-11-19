package com.example.foodbook.dto.post.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutrientDTO {
    private Long id;
    private String name;
    private double amount;
    private String unit;
}
