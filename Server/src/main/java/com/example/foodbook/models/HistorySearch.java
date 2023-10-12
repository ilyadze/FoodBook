package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class HistorySearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int amount;
    private String data;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "personId")
    private Person person;


}
