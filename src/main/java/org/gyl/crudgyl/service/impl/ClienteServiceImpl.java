package org.gyl.crudgyl.service.impl;

import org.gyl.crudgyl.dto.ClienteRequestDTO;
import org.gyl.crudgyl.dto.ClienteResponseDTO;
import org.gyl.crudgyl.entity.Cliente;
import org.gyl.crudgyl.exception.ClaveUnicaRepetida;
import org.gyl.crudgyl.exception.RecursoNoEncontradoException;
import org.gyl.crudgyl.mapper.ClienteMapper;
import org.gyl.crudgyl.repository.ClienteRepository;
import org.gyl.crudgyl.service.ClienteService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDTO crear(ClienteRequestDTO dto) {
        if (clienteRepository.existsByCorreo(dto.correo())) {
            throw new ClaveUnicaRepetida("Ya existe un cliente con el correo: " + dto.correo());
        }
            Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente guardado = clienteRepository.save(cliente);
        return ClienteMapper.toResponseDTO(guardado);
    }

    @Override
    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toResponseDTO)
                .toList();
    }

    public List<ClienteResponseDTO> listar(boolean activo) {
        return activo ?
                clienteRepository.findByFechaBajaIsNull()
                .stream()
                .map(ClienteMapper::toResponseDTO)
                .toList()
                :
                clienteRepository.findByFechaBajaIsNotNull()
                .stream()
                .map(ClienteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ClienteResponseDTO buscarPorID(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteMapper::toResponseDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException(id));
    }

    @Override
    public List<ClienteResponseDTO> buscarPorNombre(String nombre){
        return clienteRepository.findByNombre(nombre)
                .stream()
                .map(ClienteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ClienteResponseDTO> buscarPorApellido(String apellido) {
        return clienteRepository.findByApellido(apellido)
                .stream()
                .map(ClienteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ClienteResponseDTO buscarPorCorreo(String correo) {
        Cliente busqueda = clienteRepository.findByCorreo(correo);
        if (busqueda == null) {throw new RecursoNoEncontradoException("No existe cliente con el correo: " + correo);}
        return ClienteMapper.toResponseDTO(busqueda);
    }

    @Override
    public List<ClienteResponseDTO> buscarPorTelefono(String telefono) {
        return clienteRepository.findByTelefono(telefono)
                .stream()
                .map(ClienteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<ClienteResponseDTO> buscarPorDireccion(String direccion) {
        return clienteRepository.findByDireccion(direccion)
                .stream()
                .map(ClienteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(id));

        if (clienteRepository.existsByCorreo(dto.correo())) {
            throw new ClaveUnicaRepetida("Ya existe un cliente con el correo: " + dto.correo());
        }

        ClienteMapper.updateEntity(cliente,dto);
        Cliente guardado = clienteRepository.save(cliente);
        return ClienteMapper.toResponseDTO(guardado);

    }

    @Override
    public ClienteResponseDTO eliminar(Long id){
        int columnasEliminadas = clienteRepository.updateFechaBaja(id, Instant.now());
        if (columnasEliminadas == 0) {
            throw new RecursoNoEncontradoException(id);
        }
        Cliente eliminado = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Error al recuperar elemento archivado con ID: : " + id
                ));
        return ClienteMapper.toResponseDTO(eliminado);
    }


    @Override
    public void eliminar(Long id, boolean borradoFisico){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(id));

        clienteRepository.delete(cliente);
    }

    @Override
    public ClienteResponseDTO restaurar(Long id){
        int columnasRestauradas = clienteRepository.updateFechaBaja(id, null);
        if (columnasRestauradas == 0) {
            throw new RecursoNoEncontradoException(id);
        }
        Cliente restaurado = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Error al recuperar elemento restaurado con ID: : " + id
                ));
        return ClienteMapper.toResponseDTO(restaurado);
    }
}
