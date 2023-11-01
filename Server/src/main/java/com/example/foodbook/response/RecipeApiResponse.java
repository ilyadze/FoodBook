package com.example.foodbook.response;
import com.example.foodbook.dto.FullRecipeAPIDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeApiResponse {
    /*Long id;
    String title;
    String Image;*/
    List <FullRecipeAPIDTO> results;
}