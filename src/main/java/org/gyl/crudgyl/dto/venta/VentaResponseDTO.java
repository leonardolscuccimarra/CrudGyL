package org.gyl.crudgyl.dto.venta;

import org.gyl.crudgyl.dto.cliente.ClienteResponseDTO;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;

import java.time.Instant;

public record VentaResponseDTO(
        long id,
        Instant fechaVenta,
        double total,
        ClienteResponseDTO comprador,
        TipoProductoResponseDTO tipoProducto,
        Instant fechaBaja
) {
}
