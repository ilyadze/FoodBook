package com.example.foodbook.repositories;

import com.example.foodbook.models.PostDislike;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostDislikeRepository extends JpaRepository<PostDislike,Long> {
}
