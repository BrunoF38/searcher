package com.proyectos.buscador.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vocabulary")
public class Vocabulary {

    public Vocabulary(){

    }
    public Vocabulary(String word){
        this.id = -1L;
        this.word = word;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "word", nullable = false)
    private String word;

    @Transient
    private int documentCount;
}
