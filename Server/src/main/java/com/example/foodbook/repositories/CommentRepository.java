package com.example.foodbook.repositories;

import com.example.foodbook.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentByByPostId(long postId);
}
