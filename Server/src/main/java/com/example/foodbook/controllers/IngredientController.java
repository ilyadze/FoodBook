package com.example.foodbook.controllers;

import com.example.foodbook.models.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IngredientController {
    private final String API_KEY_VLAD = "88287130027b4c26bd5273c03ad85b36";

    @GetMapping("/ingridients")
    public ResponseEntity<?> getExternalData(@RequestParam(name = "quaery") String query) {
        // Здесь вы будете выполнять запрос к внешнему API и обрабатывать данные
        // Пример использования RestTemplate:
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.spoonacular.com/food/ingredients/search?query=" + query + "&number=10&apiKey=" + API_KEY_VLAD;
        /*String apiUrl = "https://api.spoonacular.com/recipes/1003464/ingredientWidget.json?apiKey=88287130027b4c26bd5273c03ad85b36";*/
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        ApiResponse test = restTemplate.getForObject(apiUrl, ApiResponse.class);
        System.out.println(test);

        return ResponseEntity.ok(test.getResults());
    }
}

