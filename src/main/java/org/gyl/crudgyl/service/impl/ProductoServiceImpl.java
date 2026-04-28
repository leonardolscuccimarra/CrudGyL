package org.gyl.crudgyl.service.impl;

import org.gyl.crudgyl.dto.ProductoRequestDTO;
import org.gyl.crudgyl.dto.ProductoResponseDTO;
import org.gyl.crudgyl.entity.Producto;
import org.gyl.crudgyl.exception.RecursoNoEncontradoException;
import org.gyl.crudgyl.mapper.ProductoMapper;
import org.gyl.crudgyl.repository.ProductoRepository;
import org.gyl.crudgyl.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Producto producto = ProductoMapper.toEntity(dto);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDTO(guardado);
    }

    @Override
    public List<ProductoResponseDTO> listar() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ProductoResponseDTO buscarPorID(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toResponseDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el id: " + id
                ));
    }

    @Override
    public List<ProductoResponseDTO> buscarPorNombre(String nombre){
        return productoRepository.findByNombre(nombre)
                .stream()
                .map(ProductoMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el id" + id
                ));

        ProductoMapper.updateEntity(producto,dto);
        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDTO(guardado);

    }

    @Override
    public void eliminar(Long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el id" + id
                ));

        productoRepository.delete(producto);
    }
}
