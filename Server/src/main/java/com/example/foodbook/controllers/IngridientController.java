package com.example.foodbook.controllers;

import com.example.foodbook.models.Ingredient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IngridientController {
    private final String API_KEY="88287130027b4c26bd5273c03ad85b36";

    @GetMapping("/ingridients")
    public ResponseEntity<?> getExternalData(@RequestParam(name = "quary") String quary) {
        // Здесь вы будете выполнять запрос к внешнему API и обрабатывать данные
        // Пример использования RestTemplate:
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.spoonacular.com/food/ingredients/search?query="+quary+"&number=10&apiKey="+API_KEY;
        /*String apiUrl = "https://api.spoonacular.com/recipes/1003464/ingredientWidget.json?apiKey=88287130027b4c26bd5273c03ad85b36";*/
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            // Парсинг JSON данных и извлечение необходимых полей
            String responseBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                // Извлечение необходимых полей
                 JsonNode jsonNode1 =  jsonNode.get("results");
                 List<Ingredient> ingredients = new ArrayList<>();
                for (JsonNode j:
                      jsonNode1) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setId(j.get("id").asLong());
                    ingredient.setTitle(j.get("name").asText());
                    ingredient.setImage(j.get("image").asText());
                    ingredients.add(ingredient);
                    System.out.println(ingredient);
                }

                return ResponseEntity.ok(ingredients);
            } catch (IOException e) {
                // Обработка ошибок парсинга JSON
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при обработке данных");
            }
        } else {
            // Обработка ошибок при запросе к внешнему API
            return ResponseEntity.status(response.getStatusCode()).body("Ошибка при запросе к внешнему API");
        }
    }
}

