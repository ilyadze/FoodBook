package com.example.foodbook.models.post.info;

import com.example.foodbook.models.post.Post;
import com.example.foodbook.models.person.Person;
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
