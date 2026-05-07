package org.gyl.crudgyl.dto.cliente;

public record ClientePatchRequestDTO(

    String nombre,
    String apellido,
    String correo,
    String telefono,
    String direccion
){

}
