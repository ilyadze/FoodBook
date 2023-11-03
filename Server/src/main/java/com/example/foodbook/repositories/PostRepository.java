package com.example.foodbook.repositories;

import com.example.foodbook.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByRecipeId(Long recipeId);
    List<Post> getPostByPersonId(Long personId);
}
