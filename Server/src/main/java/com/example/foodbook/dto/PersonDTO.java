package com.example.foodbook.dto;

import com.example.foodbook.models.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
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
    private List<Relationship> followerList; // Подписчики
    private List<Relationship> followingList; // Подписки
    private List<Privacy> privacyList; // Кто у нас скрыт
    private List<Privacy> notOurPrivacyList; // У кого мы скрыты

    private int amountPost;
    private int amountFollower;
    private int amountFollowing;

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        this.amountPost=postList.size();
    }

    public void setFollowerList(List<Relationship> followerList) {
        this.followerList = followerList;
        this.amountFollower= followerList.size();
    }

    public void setFollowingList(List<Relationship> followingList) {
        this.followingList = followingList;
        this.amountFollowing= followingList.size();
    }
}
