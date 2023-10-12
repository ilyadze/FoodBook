package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String description;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "follower")
    private List<Relationship> followerList; // Подписчики

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "following")
    private List<Relationship> followingList; // Подписки

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "person")
    private List<Privacy> privacyList; // Кто у нас скрыт

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "hiddenPerson")
    private List<Privacy> notOurPrivacyList; // У кого мы скрыты


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
        mappedBy = "person")
    private List<PostLike> postLikeList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "person")
    private List<PostDislike> postDislikeList;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "person")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "person")
    private List<HistorySearch> historySearchList;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "person")
    private List<Post> postList;



}
