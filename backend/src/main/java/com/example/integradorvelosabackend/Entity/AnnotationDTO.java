package com.example.integradorvelosabackend.Entity;

/**
 *
 * Ese metodo / clase record lo hice para filtar y solo guardar los datos de las
 * anotaciones
 *
 */
public record AnnotationDTO(Long id, String annotation) {
    public AnnotationDTO(Annotation a) {
        this(a.getId(), a.getAnnotation());
    }
}