package com.example.foodbook.controllers;

import com.example.foodbook.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IngredientController {

    @Value("${api.key.ilya}")
    private  String API_KEY_VLAD;

    @GetMapping("/ingredients")
    public ResponseEntity<?> getExternalData(@RequestParam(name = "query") String query,
                                             @RequestParam(name = "number", required = false, defaultValue = "10") String number) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.spoonacular.com/food/ingredients/search?query=" + query + "&number=" + number + "&apiKey=" + API_KEY_VLAD;
        ApiResponse response = restTemplate.getForObject(apiUrl, ApiResponse.class);

        return ResponseEntity.ok(response);
    }
}

