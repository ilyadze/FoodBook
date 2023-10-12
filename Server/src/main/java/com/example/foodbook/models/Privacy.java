package com.example.foodbook.models;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Privacy {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "personId")
    private Person person; // Тот, кто скрыл

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "hiddenPersonId")
    private Person hiddenPerson; // Тот, кто скрыт
}
