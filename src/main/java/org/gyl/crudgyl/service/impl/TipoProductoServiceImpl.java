package org.gyl.crudgyl.service.impl;

import org.gyl.crudgyl.dto.tipoProducto.TipoProductoRequestDTO;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;
import org.gyl.crudgyl.entity.TipoProducto;
import org.gyl.crudgyl.exception.RecursoNoEncontradoException;
import org.gyl.crudgyl.mapper.TipoProductoMapper;
import org.gyl.crudgyl.repository.TipoProductoRepository;
import org.gyl.crudgyl.service.TipoProductoService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

    private final TipoProductoRepository tipoProductoRepository;

    public TipoProductoServiceImpl(TipoProductoRepository tipoProductoRepository) {
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Override
    public TipoProductoResponseDTO crear(TipoProductoRequestDTO dto) {
        TipoProducto tipoProducto = TipoProductoMapper.toEntity(dto);
        TipoProducto guardado = tipoProductoRepository.save(tipoProducto);
        return TipoProductoMapper.toResponseDTO(guardado);
    }

    @Override
    public List<TipoProductoResponseDTO> listar() {
        return tipoProductoRepository.findAll()
                .stream()
                .map(TipoProductoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<TipoProductoResponseDTO> listar(boolean activo) {
        return activo ?
                tipoProductoRepository.findByFechaBajaIsNull()
                .stream()
                .map(TipoProductoMapper::toResponseDTO)
                .toList()
                :
                tipoProductoRepository.findByFechaBajaIsNotNull()
                .stream()
                .map(TipoProductoMapper::toResponseDTO)
                .toList();
    }


    @Override
    public TipoProductoResponseDTO buscarPorID(Long id) {
        return tipoProductoRepository.findById(id)
                .map(TipoProductoMapper::toResponseDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        id
                ));
    }

    @Override
    public List<TipoProductoResponseDTO> buscarPorNombre(String nombre) {
        return tipoProductoRepository.findByNombre(nombre)
                .stream()
                .map(TipoProductoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public TipoProductoResponseDTO actualizar(Long id, TipoProductoRequestDTO dto) {
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        id
                ));

        TipoProductoMapper.updateEntity(tipoProducto, dto);
        TipoProducto guardado = tipoProductoRepository.save(tipoProducto);
        return TipoProductoMapper.toResponseDTO(guardado);

    }

    @Override
    public TipoProductoResponseDTO eliminar(Long id){
        int columnasEliminadas = tipoProductoRepository.updateFechaBaja(id, Instant.now());
        if (columnasEliminadas == 0) {
            throw new RecursoNoEncontradoException(id);
        }
        TipoProducto eliminado = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Error al recuperar elemento archivado con ID: : " + id
                ));
        return TipoProductoMapper.toResponseDTO(eliminado);
    }

    @Override
    public void eliminar(Long id, boolean borradoFisico) {
        if (!borradoFisico) {
            eliminar(id);
            return;
        }
        TipoProducto tipoProducto = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(id));

        tipoProductoRepository.delete(tipoProducto);
    }

    @Override
    public TipoProductoResponseDTO restaurar(Long id){
        int columnasRestauradas = tipoProductoRepository.updateFechaBaja(id, null);
        if (columnasRestauradas == 0) {
            throw new RecursoNoEncontradoException(id);
        }
        TipoProducto restaurado = tipoProductoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Error al recuperar elemento restaurado con ID: : " + id
                ));
        return TipoProductoMapper.toResponseDTO(restaurado);
    }
}
