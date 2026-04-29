package org.gyl.crudgyl.dto;

import org.gyl.crudgyl.entity.TipoProducto;

import java.time.Instant;

public record ProductoResponseDTO(
        long id,
        String nombre,
        Double precio,
        Integer stock,
        TipoProducto tipoProducto,
        Instant fechaBaja
) {
}
