package org.gyl.crudgyl.dto;

public record ProductoResponseDTO(
        long id,
        String nombre,
        Double precio,
        Integer stock
) {
}
