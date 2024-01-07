package com.proyectos.buscador.repositories;

import com.proyectos.buscador.entities.Post;
import com.proyectos.buscador.entities.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.vocabulary IN :vocabularies")
     List<Post> findAllByVocabularies(@Param("vocabularies")List<Vocabulary> vocabularies);
}
