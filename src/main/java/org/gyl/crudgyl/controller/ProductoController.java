package org.gyl.crudgyl.controller;

import jakarta.validation.Valid;
import org.gyl.crudgyl.dto.ProductoRequestDTO;
import org.gyl.crudgyl.dto.ProductoResponseDTO;
import org.gyl.crudgyl.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDTO crear(@Valid @RequestBody ProductoRequestDTO dto){
        return productoService.crear(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoResponseDTO> listar(){
        return productoService.listar();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoResponseDTO actualizar(@Valid @PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto){
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@Valid @PathVariable Long id){
        productoService.eliminar(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoResponseDTO buscarPorID(@Valid @PathVariable Long id){
        return productoService.buscarPorID(id);
    }

    @GetMapping("/nombre/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoResponseDTO> buscarPorNombre(@Valid @PathVariable String nombre){
        return productoService.buscarPorNombre(nombre);
    }
}
