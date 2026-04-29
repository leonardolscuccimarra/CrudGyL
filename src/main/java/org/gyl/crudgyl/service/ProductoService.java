package org.gyl.crudgyl.service;

import org.gyl.crudgyl.dto.ProductoRequestDTO;
import org.gyl.crudgyl.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

    ProductoResponseDTO crear(ProductoRequestDTO producto);

    List<ProductoResponseDTO> listar();

    List<ProductoResponseDTO> listar(boolean activo);

    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto);

    ProductoResponseDTO eliminar(Long id);

    void eliminar(Long id, boolean borradoFisico);

    ProductoResponseDTO restaurar(Long id);

    ProductoResponseDTO buscarPorID(Long id);

    List<ProductoResponseDTO> buscarPorNombre(String nombre);


}
