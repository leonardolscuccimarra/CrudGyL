package org.gyl.crudgyl.dto.cliente;

import java.time.Instant;

public record ClienteResponseDTO(
        long id,
        String nombre,
        String apellido,
        String correo,
        String telefono,
        String direccion,
        Instant fechaBaja) {
}
