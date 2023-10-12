package com.example.foodbook.controllers;

import com.example.foodbook.dto.RecipeAPIDTO;
import com.example.foodbook.response.EquipmentApiResponse;
import com.example.foodbook.response.RecipeApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    private final String API_KEY="f3a620d7c1d545c995304d7e6efe0e3a";
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
                        String apiUrl2 = "http://localhost:8080/recipes/" + test.getResults().get(j).getId();

                       /* String apiUrl2 = "https://api.spoonacular.com/recipes/"+test.getResults().get(j).getId()+"/information?"+"&apiKey="+API_KEY;*/
                        System.out.println(apiUrl2);

                        test.getResults().set(j,restTemplate.getForObject(apiUrl2, RecipeAPIDTO.class));
                         String apiUrl3 = "http://localhost:8080/equipment/" + test.getResults().get(j).getId();
                         test.getResults().get(j).setEquipment(restTemplate.getForObject(apiUrl3, EquipmentApiResponse.class).getEquipment());
                        System.out.println(i+") "+test.getResults().get(j)+"\n");
                        i++;
                }
                return ResponseEntity.ok(test);

            } catch (Exception e) {
                // Обработка ошибок парсинга JSON
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при обработке данных");
            }
       /* } else {
        // Обработка ошибок при запросе к внешнему API
        return ResponseEntity.status(response.getStatusCode()).body("Ошибка при запросе к внешнему API");
        }*/
    }
    @GetMapping("/recipes/{id}")
    public ResponseEntity<?> getRescipeById(@PathVariable(name = "id") Long recipeId){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.spoonacular.com/recipes/"+recipeId+"/information?"+"&apiKey="+API_KEY;

        System.out.println("Вошли в нужный if ");
        try {
            RecipeAPIDTO test = restTemplate.getForObject(apiUrl, RecipeAPIDTO.class);


            System.out.println(test);
            int i=1;
            return ResponseEntity.ok(test);

        } catch (Exception e) {
            // Обработка ошибок парсинга JSON
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при обработке данных");
        }

    }
}
