package org.gyl.crudgyl.dto.detalleVenta;

import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;
import org.gyl.crudgyl.entity.TipoProducto;

import java.time.Instant;

public record DetalleVentaResponseDTO(
        long id,
        String nombre,
        Double precio,
        Integer stock,
        TipoProductoResponseDTO tipoProducto,
        Instant fechaBaja
) {
}
