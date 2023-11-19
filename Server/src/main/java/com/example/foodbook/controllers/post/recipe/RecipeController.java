package com.example.foodbook.controllers.post.recipe;
import com.example.foodbook.requests.FindRecipeRequest;
import com.example.foodbook.sevices.post.PostService;
import com.example.foodbook.sevices.post.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
@RestController
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final PostService postService;
    @GetMapping("/findRecipe")
    public ResponseEntity<?> findRecipe(@RequestBody FindRecipeRequest findRecipeRequest){
        return ResponseEntity.ok(recipeService.findRecipe(findRecipeRequest));
    }
    //TODO
    @GetMapping("/recipe/{id}")
    public ResponseEntity<?> findRecipeApiById(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok(recipeService.getFullRicipeById(id));
    }

    @PostMapping("/reply/{id}")
    public String replyRecipe(@PathVariable(name="id") Long id, Principal principal){
        postService.replyRecipe(id,principal.getName());
        //todo Сделать проверку на уже существующие посты
        return "Все ок";
    }
}