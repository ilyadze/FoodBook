package com.example.foodbook.repositories.post.info;

import com.example.foodbook.models.post.info.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentByPostId(long postId);
}
