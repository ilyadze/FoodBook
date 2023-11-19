package com.example.foodbook.sevices;
import com.example.foodbook.configurations.MapperConfiguration;
import com.example.foodbook.dto.FullRecipeAPIDTO;
import com.example.foodbook.dto.NutrientDTO;
import com.example.foodbook.mapper.LocalMapper;
import com.example.foodbook.models.Ingredient;
import com.example.foodbook.models.Nutrient;
import com.example.foodbook.models.Recipe;
import com.example.foodbook.models.RecipeNutrient;
import com.example.foodbook.repositories.EquipmentRepository;
import com.example.foodbook.repositories.RecipeNutrientRepository;
import com.example.foodbook.repositories.RecipeRepository;
import com.example.foodbook.requests.FindRecipeRequest;
import com.example.foodbook.response.FindRecipeResponce;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;
@Service
@Data
@AllArgsConstructor
public class RecipeService {
    private final WebClient webClient;
    private final String API_KEY="88287130027b4c26bd5273c03ad85b36";
    /*private final String API_KEY="3345d443c0e4442c8060dee679aa8c53";*/
    private final String REQUEST="/recipes/complexSearch";
    private final LocalMapper localMapper;
    private final RecipeRepository recipeRepository;
    private final EquipmentRepository equipmentRepository;
    private final IngredientService ingredientService;
    private  final NutrientService nutrientService;
    private  final RecipeNutrientRepository recipeNutrientRepository;
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
    public FullRecipeAPIDTO getFullRicipeById(Long id){
         return recipeRepository.findById(id).isPresent()?
                 localMapper.convertRecipeToDTO(recipeRepository.findById(id).get()) :
                 getRecipeFromApiById(id);
    }
    public FullRecipeAPIDTO getRecipeFromApiById(Long id){
        Map<String, String> params = new HashMap<>();
        params.put("apiKey",API_KEY);
        params.put("includeNutrition","true");
        MultiValueMap<String, String> p = new LinkedMultiValueMap<>();
        p.setAll(params);
        return  webClient.get()
                .uri(uriBuilder ->uriBuilder
                        .path("recipes/"+id+"/information")
                        .queryParams(p)
                        .build())
                .retrieve()
                .bodyToMono(FullRecipeAPIDTO.class)
                .doOnError(error-> System.out.println("error: " +error.getMessage()))
                .block();
    }
    public Recipe getRecipeById(Long id){
        return recipeRepository.findById(id).get();
    }
    public Recipe saveRecipe(Long id){
        FullRecipeAPIDTO fullRecipeAPIDTO = getRecipeFromApiById(id);
        Recipe recipe = convertDTOToRecipe2(fullRecipeAPIDTO);
        //todo Проверка на то или есть такой ингридиент в бд, чтобы не перезаписывало
        recipeRepository.save(recipe);
        return recipe;
    }
    private final ModelMapper modelMapper;
    public Recipe convertDTOToRecipe(FullRecipeAPIDTO fullRecipeAPIDTO)
    {
        Recipe recipe = modelMapper.map(fullRecipeAPIDTO,Recipe.class);
        recipe.setIngredients(fullRecipeAPIDTO.getExtendedIngredients());
        List<Ingredient> ingredientsToSave = new ArrayList<>();
        List<RecipeNutrient> recipeNutrients = new ArrayList<>();
        for (NutrientDTO nutrientDTO :
                fullRecipeAPIDTO.getNutrition().getNutrients() ) {
            //
            Nutrient nutrient =nutrientService.findByName(nutrientDTO.getName()).get();
            if(nutrient==null){
                modelMapper.map(nutrientDTO,nutrient);
                nutrientService.saveNutrient(nutrient);
            }
            //
            RecipeNutrient recipeNutrient = new RecipeNutrient();
            recipeNutrient.setNutrient(nutrient);
            recipeNutrient.setAmount(nutrientDTO.getAmount());
            recipeNutrient.setRecipe(recipe);
            recipeNutrientRepository.save(recipeNutrient);
            recipeNutrients.add(recipeNutrient);
        }
        recipe.getIngredients().forEach(ingr -> {
            if (!ingredientService.isIngredientPresent(ingr.getId())) {
                ingredientsToSave.add(ingr);
            }
        });
        ingredientService.saveIngredients(ingredientsToSave);
        recipeRepository.save(recipe);
        recipe.setRecipeNutrients(recipeNutrients);
        return recipe;
    }
    public Recipe convertDTOToRecipe2(FullRecipeAPIDTO fullRecipeAPIDTO) {
        Recipe recipe = modelMapper.map(fullRecipeAPIDTO, Recipe.class);
        recipe.setIngredients(fullRecipeAPIDTO.getExtendedIngredients());
        List<Ingredient> ingredientsToSave = new ArrayList<>();
        List<RecipeNutrient> recipeNutrients = new ArrayList<>();
        for (NutrientDTO nutrientDTO : fullRecipeAPIDTO.getNutrition().getNutrients()) {
            Nutrient nutrient;
            if(nutrientService.isNutrientPresent(nutrientDTO.getName())){
                nutrient=nutrientService.findByName(nutrientDTO.getName()).get();
            }
            else {
                nutrient = modelMapper.map(nutrientDTO, Nutrient.class);
                nutrientService.saveNutrient(nutrient);
            }
            RecipeNutrient recipeNutrient = new RecipeNutrient();
            recipeNutrient.setNutrient(nutrient);
            recipeNutrient.setAmount(nutrientDTO.getAmount());
            recipeNutrient.setRecipe(recipe);
            recipeNutrients.add(recipeNutrient);
        }
        recipe.getIngredients().forEach(ingr -> {
            if (!ingredientService.isIngredientPresent(ingr.getId())) {
                ingredientsToSave.add(ingr);
            }
        });
        ingredientService.saveIngredients(ingredientsToSave);
        recipeRepository.save(recipe);
        recipeNutrientRepository.saveAll(recipeNutrients);
        recipe.setRecipeNutrients(recipeNutrients);
        return recipe;
    }

    public boolean isRecipePresent(Long recipeId){
        return recipeRepository.findById(recipeId).isPresent();
    }
}