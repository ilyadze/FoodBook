package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class PostDislike {
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
