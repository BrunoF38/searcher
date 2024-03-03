package com.proyectos.buscador.services;

import com.proyectos.buscador.entities.Document;
import com.proyectos.buscador.entities.Post;
import com.proyectos.buscador.entities.Vocabulary;
import com.proyectos.buscador.repositories.DocumentRepository;
import com.proyectos.buscador.repositories.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final VocabularyService vocabularyService;
    private final PostService postService;
    @Autowired
    public DocumentService(DocumentRepository documentRepository, VocabularyService vocabularyService, PostService postService){
        this.documentRepository = documentRepository;
        this.vocabularyService = vocabularyService;
        this.postService = postService;
    }

    public boolean saveDocument(Document document){
        if(document == null || documentRepository.existsByTitle(document.getTitle()))return false;

        Map<String,Vocabulary> vocabularyMap = vocabularyService.getVocabularyAsMap();
        Map<Vocabulary,Post> postMap = new HashMap<>();
        String[] words = document.getContent().toLowerCase().split("\\W+");

        for (String word : words){
            Vocabulary vocabulary = vocabularyMap.computeIfAbsent(word, Vocabulary::new);

            Post post = postMap.computeIfAbsent(vocabulary, v -> new Post(v,document));
            post.setWordCount(post.getWordCount() + 1);
        }

        documentRepository.save(document);

        List<Vocabulary> newVocabularies = vocabularyMap.values().stream().filter(v -> v.getId() == -1).toList();
        vocabularyService.saveAll(newVocabularies);

        List<Post> posts = postMap.values().stream().toList();
        postService.saveAll(posts);

        return document.getId() != null;
    }

    public List<Document> search(String text){
        List<Vocabulary> vocabularies = vocabularyService.getVocabularies(text);

        List<Post> posts = postService.getByVocabularies(vocabularies);

        int documentTotal = documentRepository.findAll().size();

        for (Post post:
             posts) {
            int documentCount = vocabularyService.getDocumentCount(post.getVocabulary());
            double importance = post.getWordCount() * Math.log((double) documentTotal / documentCount);

            post.getDocument().setImportance(post.getDocument().getImportance() + importance);
        }

        posts.stream().map(Post::getDocument).forEach(d -> System.out.println(d.getTitle() + " - " + d.getImportance()));
        return posts.stream()
                .map(Post::getDocument)
                .distinct()
                .sorted(Comparator.comparingDouble(Document::getImportance).reversed())
                .toList();
    }
}
