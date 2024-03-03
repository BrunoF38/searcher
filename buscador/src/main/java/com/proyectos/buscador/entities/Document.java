package com.proyectos.buscador.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "documents")
public class Document {

    public Document(){

    }

    public Document(String title, String content){
        this.id = -1L;
        this.title = title;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "upload_date")
    private Date uploadDate = new Date();

    @Transient
    private double importance;
}
