package com.example.foodbook.sevices;

import com.example.foodbook.dto.PostDTO;
import com.example.foodbook.exceptions.LocalException;
import com.example.foodbook.mapper.LocalMapper;
import com.example.foodbook.models.Person;
import com.example.foodbook.models.Post;
import com.example.foodbook.models.Recipe;
import com.example.foodbook.repositories.PostRepository;
import jakarta.persistence.NonUniqueResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostService {

    private final PersonService personService;
    private final RecipeService recipeService;
    private final PostRepository postRepository;
    private final LocalMapper localMapper;

    public Post findPostById(long id){
        return postRepository.findById(id).orElseThrow();//todo add exception
    }
    public List<PostDTO> getPostsByUserId(Long id){
        List<Post> posts= postRepository.getPostByPersonId(id);
        List<PostDTO> postDTOList = new ArrayList<>();
        posts.forEach(post -> {
            PostDTO postDTO = getFullPostInfo(post.getId());
            postDTOList.add(postDTO);

        });
        return postDTOList;
    }
    public PostDTO getFullPostInfo(Long id){
        Post post = findPostById(id);//todo add exception
        return  localMapper.convertPostToDto(post);
    }

    public void replyRecipe(Long recipeId,String username){
        Person person = personService.findByUsername(username);
        if (isPostAlive(recipeId,person.getId())){
            throw new LocalException(HttpStatus.CONFLICT,"Такой рецпт уже добавлен");
        }
        Post post = createPost(recipeId);
        person.getPostList().add(post);
        post.setPerson(person);
        postRepository.save(post);
    }
    public Post createPost(Long recipeId){
       Recipe recipe = recipeService.isRecipePresent(recipeId)?
                recipeService.getRecipeById(recipeId):
                recipeService.saveRecipe(recipeId);
        Post post = new Post();
        post.setImage(recipe.getImage());
        post.setRecipe(recipe);
        recipe.getPosts().add(post);
        postRepository.save(post);
        return post;
    }
    public boolean isPostAlive(Long idRecipe,Long userId){
        try{
            return  postRepository.findByPersonIdAndRecipeId(userId,idRecipe).isPresent();
        }
        catch (NonUniqueResultException e){
            System.out.println("Error :" + e.getMessage());
        }
        return true;
    }
}
