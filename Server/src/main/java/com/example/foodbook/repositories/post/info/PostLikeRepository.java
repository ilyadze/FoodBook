package com.example.foodbook.repositories.post.info;

import com.example.foodbook.models.post.info.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    Optional<List<PostLike>> findPostLikeByPostId(long postId);
    Optional<List<PostLike>> findPostLikeByPersonUsername(String username);
    Integer countAllByPostId(long postId);
}
