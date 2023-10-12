package com.example.foodbook.dto;

import com.example.foodbook.models.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String description;
    private List<PostLike> postLikeList;
    private List<PostDislike> postDislikeList;

    private List<Comment> commentList;

    private List<HistorySearch> historySearchList;

    private List<Post> postList;
    private int amountPost;

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        this.amountPost=postList.size();
    }
}
