package org.gyl.crudgyl.dto.detalleVenta;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoRequestDTO;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;
import org.gyl.crudgyl.entity.TipoProducto;

public record DetalleVentaRequestDTO(
    @NotBlank(message = "El nombre no puede ser vacío")
    String nombre,

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    Double precio,

    @NotNull(message = "El stock es obligatorio")
    @Min(value=0, message= "El stock no puede ser negativo")
    Integer stock,

    TipoProductoRequestDTO tipoProducto
){

}
