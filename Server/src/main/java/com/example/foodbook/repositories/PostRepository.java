package com.example.foodbook.repositories;

import com.example.foodbook.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
