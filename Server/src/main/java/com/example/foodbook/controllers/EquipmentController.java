package com.example.foodbook.controllers;

import com.example.foodbook.models.Equipment;
import com.example.foodbook.models.response.EquipmentApiResponse;
import com.example.foodbook.models.response.RecipeApiResponse;
import com.example.foodbook.models.response.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final ModelMapper modelMapper;


    @GetMapping("/equipment/{recipeId}")
    public ResponseEntity<?> getEquipment(@PathVariable Long recipeId) {

        RestTemplate restTemplate = new RestTemplate();

        String request = "https://api.spoonacular.com/recipes/" + recipeId + "/equipmentWidget.json"; //todo
        String apiKey = "f3a620d7c1d545c995304d7e6efe0e3a&id=1003464"; //todo
        String apiUrl = request + "?apiKey=" + apiKey;

        EquipmentApiResponse response = restTemplate.getForObject(apiUrl, EquipmentApiResponse.class);
        System.out.println(response);

        return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatusCode.valueOf(1));
    }


}
