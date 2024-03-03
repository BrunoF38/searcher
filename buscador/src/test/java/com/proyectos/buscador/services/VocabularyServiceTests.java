package com.proyectos.buscador.services;

import com.proyectos.buscador.repositories.VocabularyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VocabularyServiceTests {

    @Autowired
    public VocabularyService vocabularyService;
    @Autowired
    VocabularyRepository vocabularyRepository;

    @Test
    void vocabularyTest(){
        var vocabularies = vocabularyRepository.findAll();

        for (var v : vocabularies){
            System.out.println(v.getWord() + " - " + vocabularyService.getDocumentCount(v));
        }
    }
}
