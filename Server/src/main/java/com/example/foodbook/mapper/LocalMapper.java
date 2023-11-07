package com.example.foodbook.mapper;
import com.example.foodbook.dto.FullRecipeAPIDTO;
import com.example.foodbook.dto.NutrientDTO;
import com.example.foodbook.dto.PostDTO;
import com.example.foodbook.models.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class LocalMapper {
    private final ModelMapper modelMapper;
    //TODO

    public static Recipe convert(FullRecipeAPIDTO fullRecipe) {
        Recipe recipe = new Recipe();
        List<RecipeNutrient> recipeNutrients = new ArrayList<>();
        if (fullRecipe.getNutrition() != null && fullRecipe.getNutrition().getNutrients() != null) {
            for (NutrientDTO nutrientDTO : fullRecipe.getNutrition().getNutrients()) {
                Nutrient nutrient = new Nutrient();
                nutrient.setName(nutrientDTO.getName());
                nutrient.setUnit(nutrientDTO.getUnit());

                RecipeNutrient recipeNutrient = new RecipeNutrient();
                recipeNutrient.setNutrient(nutrient);
                recipeNutrient.setAmount(nutrientDTO.getAmount());
                recipeNutrient.setRecipe(recipe);

                recipeNutrients.add(recipeNutrient);
            }
        }
        recipe.setRecipeNutrients(recipeNutrients);


        return recipe;
    }
    public FullRecipeAPIDTO convertRecipeToDTO(Recipe recipe){
        FullRecipeAPIDTO fullRecipeAPIDTO = new FullRecipeAPIDTO();
        modelMapper.map(recipe,fullRecipeAPIDTO);
        fullRecipeAPIDTO.setExtendedIngredients(recipe.getIngredients());
        return fullRecipeAPIDTO;
    }
    public PostDTO convertPostToDto(Post post){
        PostDTO postDTO = new PostDTO();
        modelMapper.map(post,postDTO);
        postDTO.getRecipe().setExtendedIngredients(post.getRecipe().getIngredients());
        return postDTO;
    }

}
