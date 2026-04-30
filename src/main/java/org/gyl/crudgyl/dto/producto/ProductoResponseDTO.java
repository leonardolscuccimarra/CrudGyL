package org.gyl.crudgyl.dto.producto;

import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;
import org.gyl.crudgyl.entity.TipoProducto;

import java.time.Instant;

public record ProductoResponseDTO(
        long id,
        String nombre,
        Double precio,
        Integer stock,
        Instant fechaBaja
) {
}
