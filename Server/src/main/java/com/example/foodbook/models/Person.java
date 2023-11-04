package com.example.foodbook.models;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

@Entity
@Data
@Table(name = "person")
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
    private List<Relationship> followerList= new ArrayList<>(); // Подписчики

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "following")
    private List<Relationship> followingList= new ArrayList<>(); // Подписки

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<PostLike> postLikeList= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Comment> commentList= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Post> postList= new ArrayList<>();

}
