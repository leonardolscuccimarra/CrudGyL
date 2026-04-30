package org.gyl.crudgyl.service;

import org.gyl.crudgyl.dto.producto.ProductoResponseDTO;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoRequestDTO;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;

import java.util.List;

public interface TipoProductoService {
    TipoProductoResponseDTO crear(TipoProductoRequestDTO tipoProducto);

    List<TipoProductoResponseDTO> listar();

    List<TipoProductoResponseDTO> listar(boolean activo);

    TipoProductoResponseDTO actualizar(Long id, TipoProductoRequestDTO dto);

    TipoProductoResponseDTO eliminar(Long id);

    void eliminar(Long id, boolean borradoFisico);

    TipoProductoResponseDTO restaurar(Long id);

    TipoProductoResponseDTO buscarPorID(Long id);

    List<TipoProductoResponseDTO> buscarPorNombre(String nombre);
}
