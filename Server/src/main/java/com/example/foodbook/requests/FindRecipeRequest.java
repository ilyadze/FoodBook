package com.example.foodbook.requests;

import lombok.Data;

import java.util.List;

@Data
public class FindRecipeRequest {
    String recipeName;
    List<String>  ingridients;


}
