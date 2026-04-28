package org.gyl.crudgyl.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ClienteRequestDTO(
    @NotBlank(message = "El nombre no puede estar vacío")
    String nombre,

    @NotBlank(message = "El apellido no puede estar vacío")
    String apellido,

    @NotBlank(message = "El correo no puede estar vacío")
    String correo,

    @NotBlank(message = "El telefono no puede estar vacío")
    String telefono,

    @NotBlank(message = "La dirección no puede estar vacía")
    String direccion
){

}
