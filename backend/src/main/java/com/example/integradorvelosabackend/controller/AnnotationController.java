package com.example.integradorvelosabackend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integradorvelosabackend.Repository.AnnotationRepository;
import com.example.integradorvelosabackend.Entity.Annotation;

@RestController
@RequestMapping("/annotations")
@CrossOrigin(maxAge = 3600)
public class AnnotationController {

    @Autowired
    private final AnnotationRepository repo;

    public AnnotationController(AnnotationRepository repo) {
        this.repo = repo;
    }

    @DeleteMapping("/{annotationId}") // "8080/annotations/1"
    public ResponseEntity<?> deleteAnnotation(@PathVariable int annotationId) {
        Optional<Annotation> optionalAnnotation = repo.findById(annotationId);
        if (optionalAnnotation.isPresent()) {
            repo.delete(optionalAnnotation.get());
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/{annotationId}")
    public ResponseEntity<?> updateAnnotation(@RequestBody String annotation, @PathVariable int annotationId) {
        Optional<Annotation> box = repo.findById(annotationId);
        Annotation updatedAnnotation = null;
        if (box.isPresent())
            updatedAnnotation = box.get();
        else
            return ResponseEntity.status(404).build();

        updatedAnnotation.setAnnotation(annotation);
        repo.save(updatedAnnotation);
        return ResponseEntity.status(200).build();
    }

}
