package com.example.foodbook.repositories;

import com.example.foodbook.models.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    Optional<List<PostLike>> findPostLikeByPostId(long postId);
    Optional<List<PostLike>> findPostLikeByPersonUsername(String username);
    Integer countAllByPostId(long postId);
}
