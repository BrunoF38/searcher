package com.proyectos.buscador.services;

import com.proyectos.buscador.entities.Post;
import com.proyectos.buscador.entities.Vocabulary;
import com.proyectos.buscador.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void saveAll(List<Post> posts){
        postRepository.saveAll(posts);
    }

    public List<Post> getByVocabularies(List<Vocabulary> vocabularies){
        return postRepository.findAllByVocabularies(vocabularies);
    }
}
