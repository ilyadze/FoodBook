package com.example.foodbook.response.recipe;

import com.example.foodbook.dto.post.recipe.FullRecipeAPIDTO;
import lombok.Data;

import java.util.List;

@Data

public class FindRecipeResponce {
    List <FullRecipeAPIDTO> results;
}
