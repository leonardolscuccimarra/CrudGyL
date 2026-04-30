package org.gyl.crudgyl.dto.tipoProducto;

import java.time.Instant;

public record TipoProductoResponseDTO(
        long id,
        String nombre,
        String descripcion,
        Instant fechaBaja
) {
}
