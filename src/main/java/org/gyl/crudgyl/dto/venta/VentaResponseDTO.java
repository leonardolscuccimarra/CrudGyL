package org.gyl.crudgyl.dto.venta;



import org.gyl.crudgyl.dto.detalleVenta.DetalleVentaResponseDTO;
import org.gyl.crudgyl.entity.Cliente;
import org.gyl.crudgyl.entity.DetalleVenta;

import java.time.Instant;
import java.util.List;

public record VentaResponseDTO(
        long id,
        Instant fechaVenta,
        double total,
        Cliente comprador,
        List<DetalleVentaResponseDTO> detalleVentas,
        Instant fechaBaja
) {
}
