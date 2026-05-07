package org.gyl.crudgyl.dto.detalleVenta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetalleVentaRequestDTO(
    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    long cantidad
){

}
