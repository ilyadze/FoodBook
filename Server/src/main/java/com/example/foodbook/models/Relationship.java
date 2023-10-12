package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "followerId")
    private Person follower; // Тот, кто подписывается

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "followingId")
    private Person following; // Тот, на кого подписываются
}