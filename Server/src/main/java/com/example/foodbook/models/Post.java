package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,
                mappedBy = "post")
    private List<PostLike> postLikeList;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,
            mappedBy = "post")
    private List<PostDislike> postDislikeList;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,
            mappedBy = "post")
    private List<Comment> commentList;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "pesronId")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "recipeId", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Recipe recipe;
}
