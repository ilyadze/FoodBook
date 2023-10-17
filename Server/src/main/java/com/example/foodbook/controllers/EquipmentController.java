package com.example.foodbook.controllers;

import com.example.foodbook.response.EquipmentApiResponse;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final ModelMapper modelMapper;


    @GetMapping("/equipment/{recipeId}")
    public ResponseEntity<?> getEquipment(@PathVariable Long recipeId) {

        RestTemplate restTemplate = new RestTemplate();

        String request = "https://api.spoonacular.com/recipes/" + recipeId + "/equipmentWidget.json"; //todo
        String apiKey = "3345d443c0e4442c8060dee679aa8c53"; //todo
        String apiUrl = request + "?apiKey=" + apiKey;
        EquipmentApiResponse response = restTemplate.getForObject(apiUrl, EquipmentApiResponse.class);
        return ResponseEntity.ok(response);
    }


}
