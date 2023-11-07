package com.example.foodbook.controllers;
import com.example.foodbook.sevices.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getFullPostInfo(id));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getPostsByUserId(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostsByUserId(id));
    }
}