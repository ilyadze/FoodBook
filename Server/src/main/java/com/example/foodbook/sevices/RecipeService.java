package com.example.foodbook.sevices;
import com.example.foodbook.dto.FullRecipeAPIDTO;
import com.example.foodbook.models.Ingredient;
import com.example.foodbook.models.Recipe;
import com.example.foodbook.repositories.EquipmentRepository;
import com.example.foodbook.repositories.RecipeRepository;
import com.example.foodbook.requests.FindRecipeRequest;
import com.example.foodbook.response.FindRecipeResponce;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@Data
@AllArgsConstructor
public class RecipeService {
    private final WebClient webClient;
    private final String API_KEY="88287130027b4c26bd5273c03ad85b36";
    /*private final String API_KEY="3345d443c0e4442c8060dee679aa8c53";*/
    private final String REQUEST="/recipes/complexSearch";
    private  final ModelMapper modelMapper;
    private final RecipeRepository recipeRepository;
    private final EquipmentRepository equipmentRepository;
    private final IngredientService ingredientService;
    public Recipe f(Long id){
        return recipeRepository.findById(id).get();
    }
    public FindRecipeResponce findRecipe(FindRecipeRequest findRecipeRequest) {
        Map<String, String> params = new HashMap<>();
        if (findRecipeRequest.getRecipeName() != null) {
            params.put("query", findRecipeRequest.getRecipeName());
        }
        if (findRecipeRequest.getIngridients() != null) {
            String ingridients = findRecipeRequest.getIngridients().stream().collect(Collectors.joining(", "));
            System.out.println("Ingridients :" + ingridients);
            params.put("includeIngredients", ingridients);
        }
        params.put("number", "100");
        params.put("apiKey", API_KEY);
        params.put("instructionsRequired", "true");
        MultiValueMap<String, String> p = new LinkedMultiValueMap<>();
        p.setAll(params);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(REQUEST)
                        .queryParams(p)
                        .build())
                .retrieve()
                .bodyToMono(FindRecipeResponce.class)
                .doOnError(error -> System.out.println("error: " + error.getMessage()))
                .block();
    }
    public FullRecipeAPIDTO getRecipeById(Long id){
        return webClient.get()
                .uri(uriBuilder ->uriBuilder
                        .path("recipes/"+id+"/information")
                        .queryParam("apiKey",API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(FullRecipeAPIDTO.class)
                .doOnError(error-> System.out.println("error: " +error.getMessage()))
                .block();
    }
    public Recipe createRecipe(Long id){
        FullRecipeAPIDTO fullRecipeAPIDTO = getRecipeById(id);
        System.out.println("Прикол");
        Recipe recipe = new Recipe();
        modelMapper.map(fullRecipeAPIDTO,recipe);
        List<Ingredient> ingredientList= fullRecipeAPIDTO.getExtendedIngredients();
        ingredientList.forEach(ingredientService::saveIngredient);
        recipe.setIngredients(ingredientList);
        recipe.setInstructions(fullRecipeAPIDTO.getInstructions());
        recipeRepository.save(recipe);
        return recipe;
    }
}
