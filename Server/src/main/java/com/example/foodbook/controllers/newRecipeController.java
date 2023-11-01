package com.example.foodbook.controllers;

import com.example.foodbook.models.Recipe;
import com.example.foodbook.requests.FindRecipeRequest;
import com.example.foodbook.sevices.PostService;
import com.example.foodbook.sevices.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class newRecipeController {
    private final RecipeService recipeService;
    private final PostService postService;
    @GetMapping("/findRecipe")
    public ResponseEntity<?> findRecipe(@RequestBody FindRecipeRequest findRecipeRequest){
        return ResponseEntity.ok(recipeService.findRecipe(findRecipeRequest));
    }
    @GetMapping("/recipeApi/{id}")
    public ResponseEntity<?> findRecipeApiById(@PathVariable(name = "id")Long id){
        /*Recipe recipe = recipeService.f(id);
        System.out.println(recipe);*/
        /*return ResponseEntity.ok(recipeService.f(id));*/
       /* return ResponseEntity.badRequest().body(recipe);*/
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }
    @PostMapping("/reply/{id}")
    public String replyRecipe(@PathVariable(name="id") Long id, Principal principal){
        postService.replyRecipe(id,principal.getName());
        return "Все ок";
    }
}
