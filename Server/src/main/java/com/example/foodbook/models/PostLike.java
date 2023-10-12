package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post_like")
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "personId")
    private Person person;


}
