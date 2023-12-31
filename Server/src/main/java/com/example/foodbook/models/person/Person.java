package com.example.foodbook.models.person;
import com.example.foodbook.models.post.info.Comment;
import com.example.foodbook.models.post.Post;
import com.example.foodbook.models.post.info.PostLike;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
@Table(name = "person")
@JsonIgnoreProperties({"followers", "followings", "postLikeList", "commentList", "postList", "blockedPersons"})
public class Person /*implements UserDetails*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String image;
    private String password;
    private String description;
    private Boolean isPrivate = false;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection <Role> roles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "follower")
    private List<Relationship> followers = new ArrayList<>(); // Подписчики

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "following")
    private List<Relationship> followings = new ArrayList<>(); // Подписки

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<PostLike> postLikeList= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Comment> commentList= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Post> postList= new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_blocked_persons",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "blocked_person_id")
    )
    private List<Person> blockedPersons = new ArrayList<>();

}
