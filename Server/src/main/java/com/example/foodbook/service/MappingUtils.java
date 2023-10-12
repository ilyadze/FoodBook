package com.example.foodbook.service;

import com.example.foodbook.dto.PostDTO;
import com.example.foodbook.models.Post;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public Post mapToPostModel(PostDTO postDTO){
        Post post = new Post();

        post.setId(post.getId());
        post.setPostLikeList(postDTO.getPostLikeList());
        post.setPostDislikeList(postDTO.getPostDislikeList());
        post.setPerson(postDTO.getPerson());
        post.setImage(postDTO.getImage());
        post.setRecipe(postDTO.getRecipe());
        post.setCommentList(postDTO.getCommentList());

        return post;

    }
    public PostDTO mapToPostDTO(Post post){
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setPostLikeList(post.getPostLikeList());
        postDTO.setPostDislikeList(post.getPostDislikeList());
        postDTO.setPerson(post.getPerson());
        postDTO.setImage(post.getImage());
        postDTO.setRecipe(post.getRecipe());
        postDTO.setCommentList(post.getCommentList());

        return postDTO;

    }
}
