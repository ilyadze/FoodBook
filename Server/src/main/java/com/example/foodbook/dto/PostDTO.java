package com.example.foodbook.dto;

import com.example.foodbook.models.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
public class PostDTO {

    private Long id;
    private String image;
    private List<PostLike> postLikeList;
    private List<PostDislike> postDislikeList;
    private List<Comment> commentList;
    private Person person;
    private Recipe recipe;
    private int amountLike;
    private int amountDislike;
    private int amountComment;

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
        this.amountComment=commentList.size();
    }

    public void setPostDislikeList(List<PostDislike> postDislikeList) {
        this.postDislikeList = postDislikeList;
        this.amountDislike=postDislikeList.size();
    }
    public void setPostLikeList(List<PostLike> postLikeList) {
        this.postLikeList = postLikeList;
        this.amountLike=postLikeList.size();
    }
}
