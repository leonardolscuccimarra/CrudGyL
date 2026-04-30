package org.gyl.crudgyl.mapper;

import org.gyl.crudgyl.dto.ClienteRequestDTO;
import org.gyl.crudgyl.dto.ClienteResponseDTO;
import org.gyl.crudgyl.entity.Cliente;

public class ClienteMapper {
    private ClienteMapper(){}

    public static Cliente toEntity(ClienteRequestDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setCorreo(dto.correo());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());
        return cliente;
    }

    public static ClienteResponseDTO toResponseDTO(Cliente cliente){
        return new ClienteResponseDTO(
                cliente.getId_cliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getFechaBaja()
        );
    }

    public static void updateEntity(Cliente cliente, ClienteRequestDTO dto){
        cliente.setNombre(dto.nombre());
        cliente.setApellido(dto.apellido());
        cliente.setCorreo(dto.correo());
        cliente.setTelefono(dto.telefono());
        cliente.setDireccion(dto.direccion());
    }
}
