package org.gyl.crudgyl.dto.producto;

import java.time.Instant;

public record ProductoResponseDTO(
        long id,
        String nombre,
        Double precio,
        Integer stock,
        Instant fechaBaja
) {
}
