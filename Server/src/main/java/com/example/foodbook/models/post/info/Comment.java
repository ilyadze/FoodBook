package com.example.foodbook.models.post.info;

import com.example.foodbook.models.post.Post;
import com.example.foodbook.models.person.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String comment;
    private LocalDateTime dateOfCreated;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "personId")
    private Person person;

    @PrePersist
    private void init(){
        dateOfCreated=LocalDateTime.now();
    }
}
