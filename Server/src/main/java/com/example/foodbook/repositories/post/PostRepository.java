package com.example.foodbook.repositories.post;

import com.example.foodbook.models.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
   /* @Query("select p FROM Post p WHERE p.person.id = ?1 AND p.recipe.id = ?2")*/

    Optional<Post> findByPersonIdAndRecipeId(Long personId, Long recipeId);
    List<Post> getPostByPersonId(Long personId);
}
