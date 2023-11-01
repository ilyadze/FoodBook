package com.example.foodbook.response;

import com.example.foodbook.dto.FullRecipeAPIDTO;
import lombok.Data;

import java.util.List;

@Data

public class FindRecipeResponce {
    List <FullRecipeAPIDTO> results;
}
