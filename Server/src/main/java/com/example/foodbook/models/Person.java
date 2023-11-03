package com.example.foodbook.models;
import jakarta.persistence.*;
import lombok.Data;
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
/*
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns =@JoinColumn(name="user_id") )
    @Enumerated(EnumType.STRING)
    private Set<Role > roles = new HashSet<>();*/
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Privacy> privacyList= new ArrayList<>(); // Кто у нас скрыт

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hiddenPerson")
    private List<Privacy> notOurPrivacyList= new ArrayList<>();// У кого мы скрыты

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<PostLike> postLikeList= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Comment> commentList= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Post> postList= new ArrayList<>();
    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) roles;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }*/
}
