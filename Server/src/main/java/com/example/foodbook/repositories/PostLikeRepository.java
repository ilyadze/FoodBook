package com.example.foodbook.repositories;

import com.example.foodbook.models.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
}