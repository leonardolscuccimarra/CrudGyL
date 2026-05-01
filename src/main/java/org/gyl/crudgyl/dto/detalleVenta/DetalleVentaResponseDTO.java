package org.gyl.crudgyl.dto.detalleVenta;

import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;
import org.gyl.crudgyl.entity.Producto;
import org.gyl.crudgyl.entity.Venta;

import java.time.Instant;

public record DetalleVentaResponseDTO(
        long id_detalle_venta,
        long cantidad,
        double precio_unitario,
        double subtotal,
        Venta venta,
        Producto producto,
        Instant fechaBaja
) {
}
