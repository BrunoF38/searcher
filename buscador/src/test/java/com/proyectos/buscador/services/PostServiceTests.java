package com.proyectos.buscador.services;

import com.proyectos.buscador.entities.Vocabulary;
import com.proyectos.buscador.repositories.PostRepository;
import com.proyectos.buscador.repositories.VocabularyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
public class PostServiceTests {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private VocabularyService vocabularyService;

    @Test
    void findAllByVocabularies(){
        var vocabulariesMap = vocabularyService.getVocabularyAsMap();
        String text = "hola como estas";
        List<String> words = Arrays.asList(text.split("\\W+"));

        List<Vocabulary> vocabularies = words.stream()
                .map(vocabulariesMap::get)
                .filter(Objects::nonNull)
                .toList();

        var posts = postRepository.findAllByVocabularies(vocabularies);
        System.out.println(posts);
    }
}
