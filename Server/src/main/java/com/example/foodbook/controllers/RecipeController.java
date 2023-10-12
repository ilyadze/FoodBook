package com.example.foodbook.controllers;

import com.example.foodbook.dto.RecipeAPIDTO;
import com.example.foodbook.models.Ingredient;
import com.example.foodbook.models.Recipe;
import com.example.foodbook.models.response.ApiResponse;
import com.example.foodbook.models.response.RecipeApiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//  Parametrs:
//
//          Used:
//  instructionsRequired	boolean	true	Whether the recipes must have instructions.
//  query	string	pasta	The (natural language) recipe search query.
//  includeIngredients	string	tomato,cheese	A comma-separated list of ingredients that should/must be used in the recipes.
//          Not used:
//  maxReadyTime	number	20 | The maximum time in minutes it should take to prepare and cook the recipe.
//  ignorePantry  boolean	true | Whether to ignore typical pantry items, such as water, salt, flour, etc.
//
//          Not interesting:
//  offset number	0 | The number of results to skip (between 0 and 900).
//  number number	10 | The number of expected results (between 1 and 100).

@RestController
public class RecipeController {
    private final String API_KEY="88287130027b4c26bd5273c03ad85b36";
    private final String REQUEST="https://api.spoonacular.com/recipes/complexSearch?instructionsRequired=true";

    @GetMapping("/recipes")
    public ResponseEntity<?> getExternalData(@RequestParam(name = "query",required = false) String query,
                                             @RequestParam(required = false)String includeIngredients) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = REQUEST+"&apiKey="+API_KEY;
        if(query!=null){
            apiUrl+="&query="+query;
        }
        if(includeIngredients!=null){
            apiUrl+="&includeIngredients="+includeIngredients;
        }

        /*ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        RecipeApiResponse test = restTemplate.getForObject(apiUrl, RecipeApiResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {*/

            System.out.println("Вошли в нужный if ");
            try {
                RecipeApiResponse test = restTemplate.getForObject(apiUrl, RecipeApiResponse.class);


                System.out.println(test);
                int i=1;
                for (int j = 0; j < test.getResults().size(); j++) {

                        System.out.println(i+") "+test.getResults().get(j)+"\n");

                        String apiUrl2 = "https://api.spoonacular.com/recipes/"+test.getResults().get(j).getId()+"/information?"+"&apiKey="+API_KEY;
                        System.out.println(apiUrl2);


                        test.getResults().set(j,restTemplate.getForObject(apiUrl2, RecipeAPIDTO.class));

                        System.out.println(i+") "+test.getResults().get(j)+"\n");
                        i++;
                }
                return ResponseEntity.ok(test.getResults());

            } catch (Exception e) {
                // Обработка ошибок парсинга JSON
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при обработке данных");
            }
       /* } else {
        // Обработка ошибок при запросе к внешнему API
        return ResponseEntity.status(response.getStatusCode()).body("Ошибка при запросе к внешнему API");
        }*/
    }
}
