package org.gyl.crudgyl.dto.tipoProducto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TipoProductoRequestDTO(
    @NotBlank(message = "El nombre no puede ser vacío")
    String nombre,

    @NotNull(message = "La descripción no puede ser nula")
    String descripcion
){

}
