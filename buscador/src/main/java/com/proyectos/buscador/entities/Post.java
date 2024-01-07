package com.proyectos.buscador.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {

    public Post(){

    }

    public Post(Vocabulary vocabulary, Document document){
        this.id = -1L;
        this.vocabulary = vocabulary;
        this.document = document;
        this.wordCount = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vocabulary")
    private Vocabulary vocabulary;

    @ManyToOne
    @JoinColumn(name = "id_document")
    private Document document;

    @Column(name = "word_count",nullable = false)
    private int wordCount;

}
