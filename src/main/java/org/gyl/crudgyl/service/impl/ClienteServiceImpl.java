package org.gyl.crudgyl.service.impl;

import org.gyl.crudgyl.dto.ClienteRequestDTO;
import org.gyl.crudgyl.dto.ClienteResponseDTO;
import org.gyl.crudgyl.entity.Cliente;
import org.gyl.crudgyl.exception.RecursoNoEncontradoException;
import org.gyl.crudgyl.mapper.ClienteMapper;
import org.gyl.crudgyl.repository.ClienteRepository;
import org.gyl.crudgyl.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDTO crear(ClienteRequestDTO dto) {
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

    @Override
    public ClienteResponseDTO buscarPorID(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteMapper::toResponseDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el id: " + id
                ));
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
        return ClienteMapper.toResponseDTO(clienteRepository.findByCorreo(correo));
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
        Cliente producto = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el id" + id
                ));

        ClienteMapper.updateEntity(producto,dto);
        Cliente guardado = clienteRepository.save(producto);
        return ClienteMapper.toResponseDTO(guardado);

    }

    @Override
    public void eliminar(Long id){
        Cliente producto = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el id" + id
                ));

        clienteRepository.delete(producto);
    }
}
