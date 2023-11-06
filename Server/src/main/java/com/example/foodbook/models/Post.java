package com.example.foodbook.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table
@Data
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,
                mappedBy = "post")
    private List<PostLike> postLikeList= new ArrayList<>();;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,
            mappedBy = "post")
    private List<PostDislike> postDislikeList= new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
            mappedBy = "post")
    private List<Comment> commentList= new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "personId")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "recipeId", referencedColumnName = "id")
    private Recipe recipe;
}
