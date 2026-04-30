package org.gyl.crudgyl.dto.venta;

import jakarta.validation.constraints.NotNull;
import org.gyl.crudgyl.entity.Cliente;
import org.gyl.crudgyl.entity.DetalleVenta;

import java.util.List;

public record VentaRequestDTO(
    @NotNull(message = "El comprador no puede ser nulo")
    Cliente comprador,

    List<DetalleVenta> detalles
){

}
