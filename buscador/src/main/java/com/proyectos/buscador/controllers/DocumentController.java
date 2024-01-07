package com.proyectos.buscador.controllers;

import com.proyectos.buscador.services.DocumentService;
import com.proyectos.buscador.entities.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentController){
        this.documentService = documentController;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveDocument(@RequestBody Document document){
        boolean success = documentService.saveDocument(document);

        return success ?
                ResponseEntity.status(HttpStatus.CREATED).body("Document saved successfully") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save document");
    }

    @PostMapping("/saveFiles")
    public ResponseEntity<String> saveDocument(@RequestParam(name = "file") List<MultipartFile> files) throws IOException {
        if(files.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("files cannot be empty");

        int errorDocument = 0;
        for (MultipartFile file : files){
            if(file.isEmpty() || !Objects.requireNonNull(file.getOriginalFilename()).endsWith(".txt"))continue;
            String title = file.getOriginalFilename();
            String content = new String(file.getBytes());
            Document document = new Document(title, content);
            boolean success = documentService.saveDocument(document);
            if (!success)errorDocument++;
        }

        return ResponseEntity.status(HttpStatus.CREATED).
                body(String.format("Documents saved successfully: %d \nDocuments with errors: %d", files.size() - errorDocument, errorDocument));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Document>> search(@RequestParam(name = "text")String text){
        if(text == null || text.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        List<Document> documents = documentService.search(text);

        return documents != null ?
                ResponseEntity.ok(documents) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
