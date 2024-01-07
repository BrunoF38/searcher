package com.proyectos.buscador.services;

import com.proyectos.buscador.entities.Vocabulary;
import com.proyectos.buscador.repositories.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;

    @Autowired
    public VocabularyService(VocabularyRepository vocabularyRepository){
        this.vocabularyRepository = vocabularyRepository;
    }

    public Map<String, Vocabulary> getVocabularyAsMap(){
        return  vocabularyRepository.findAll().stream().collect(Collectors.toMap(Vocabulary::getWord, v -> v));
    }

    public void saveAll(List<Vocabulary> vocabularies){
        vocabularyRepository.saveAll(vocabularies);
    }

    public List<Vocabulary> getVocabularies(String text){
        List<String> words = Arrays.asList(text.split("\\W+"));

        return words.stream()
                .map(String::toLowerCase)
                .map(getVocabularyAsMap()::get)
                .filter(Objects::nonNull)
                .toList();
    }

    public int getDocumentCount(Vocabulary vocabulary){
        return vocabularyRepository.getDocumentCount(vocabulary.getId());
    }
}
