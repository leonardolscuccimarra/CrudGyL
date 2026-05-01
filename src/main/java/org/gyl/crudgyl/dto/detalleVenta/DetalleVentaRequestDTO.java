package org.gyl.crudgyl.dto.detalleVenta;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoRequestDTO;
import org.gyl.crudgyl.entity.Producto;
import org.gyl.crudgyl.entity.Venta;

public record DetalleVentaRequestDTO(
    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    long cantidad
){

}
