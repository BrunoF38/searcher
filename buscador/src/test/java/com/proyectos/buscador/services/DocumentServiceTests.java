package com.proyectos.buscador.services;

import com.proyectos.buscador.entities.Document;
import com.proyectos.buscador.repositories.VocabularyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.Doc;
import java.util.List;

@SpringBootTest
public class DocumentServiceTests {

    @Autowired
    private DocumentService documentService;

    @Test
    void saveDocument(){
        Document document = new Document("hola","Hola Hola Hola Hola Hola Hola Hola Hola Hola Hola hola hola hola Hola Hola Hola Hola Hola Hola Hola Hola Hola Hola hola hola hola");

        documentService.saveDocument(document);
    }

    @Test
    void search(){
        String busqueda = "hola";

        List<Document> documents = documentService.search(busqueda);
        System.out.println(documents);
    }
}
