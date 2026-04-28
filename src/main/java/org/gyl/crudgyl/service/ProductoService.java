package org.gyl.crudgyl.service;

import org.gyl.crudgyl.dto.ProductoRequestDTO;
import org.gyl.crudgyl.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {

    ProductoResponseDTO crear(ProductoRequestDTO producto);

    List<ProductoResponseDTO> listar();

    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto);

    void eliminar(Long id);

    ProductoResponseDTO buscarPorID(Long id);

    List<ProductoResponseDTO> buscarPorNombre(String nombre);


}
