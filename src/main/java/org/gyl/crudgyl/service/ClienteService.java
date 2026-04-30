package org.gyl.crudgyl.service;

import org.gyl.crudgyl.dto.ClienteRequestDTO;
import org.gyl.crudgyl.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    ClienteResponseDTO crear(ClienteRequestDTO producto);

    List<ClienteResponseDTO> listar();

    List<ClienteResponseDTO> listar(boolean activo);

    ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto);

    ClienteResponseDTO eliminar(Long id);

    void eliminar(Long id, boolean borradoFisico);

    ClienteResponseDTO restaurar(Long id);

    ClienteResponseDTO buscarPorID(Long id);

    List<ClienteResponseDTO> buscarPorNombre(String nombre);

    List<ClienteResponseDTO> buscarPorApellido(String apellido);

    ClienteResponseDTO buscarPorCorreo(String correo);

    List<ClienteResponseDTO> buscarPorTelefono(String telefono);

    List<ClienteResponseDTO> buscarPorDireccion(String direccion);
}
