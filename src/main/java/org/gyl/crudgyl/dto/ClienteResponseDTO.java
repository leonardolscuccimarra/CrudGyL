package org.gyl.crudgyl.dto;

public record ClienteResponseDTO(
        long id,
        String nombre,
        String apellido,
        String correo,
        String telefono,
        String direccion
) {
}
