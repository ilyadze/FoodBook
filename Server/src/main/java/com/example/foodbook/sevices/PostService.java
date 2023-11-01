package com.example.foodbook.sevices;

import com.example.foodbook.dto.FullRecipeAPIDTO;
import com.example.foodbook.models.Person;
import com.example.foodbook.models.Post;
import com.example.foodbook.models.Recipe;
import com.example.foodbook.repositories.PostRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequiredArgsConstructor
public class PostService {

    private final PersonService personService;

    private final RecipeService recipeService;
    private final PostRepository postRepository;

    public void replyRecipe(Long id,String username){
        Person person = personService.findByUsername(username).get();

        Post post = createPost(recipeService.createRecipe(id));
        person.getPostList().add(post);
        post.setPerson(person);
        postRepository.save(post);

    }
    public Post createPost(Recipe recipe){
        Post post = new Post();
        post.setImage(recipe.getImage());
        post.setRecipe(recipe);
        recipe.getPosts().add(post);
        postRepository.save(post);
        return post;
    }
}
