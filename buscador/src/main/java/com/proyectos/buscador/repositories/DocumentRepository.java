package com.proyectos.buscador.repositories;

import com.proyectos.buscador.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    boolean existsByTitle(String title);
}
