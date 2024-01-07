package com.proyectos.buscador.repositories;

import com.proyectos.buscador.entities.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary,Long> {
    @Query("SELECT COUNT(p) FROM Post p WHERE p.vocabulary.id = :idVocabulary")
    int getDocumentCount(@Param("idVocabulary") Long id);
}
