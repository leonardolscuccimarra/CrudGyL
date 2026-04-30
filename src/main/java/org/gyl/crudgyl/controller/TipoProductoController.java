package org.gyl.crudgyl.controller;

import jakarta.validation.Valid;
import org.gyl.crudgyl.dto.producto.ProductoResponseDTO;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoRequestDTO;
import org.gyl.crudgyl.dto.tipoProducto.TipoProductoResponseDTO;
import org.gyl.crudgyl.service.ProductoService;
import org.gyl.crudgyl.service.TipoProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos/tipos")
public class TipoProductoController {

    private final TipoProductoService tipoProductoService;
    private final ProductoService productoService;

    public TipoProductoController(TipoProductoService tipoProductoService, ProductoService productoService){
        this.tipoProductoService = tipoProductoService;
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoProductoResponseDTO crear(@Valid @RequestBody TipoProductoRequestDTO dto){
        return tipoProductoService.crear(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProductoResponseDTO> listarActivos(){
        return tipoProductoService.listar(true);
    }

    @GetMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProductoResponseDTO> listarTodo(){
        return tipoProductoService.listar();
    }

    @GetMapping("/archivado")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProductoResponseDTO> listarArchivados(){
        return tipoProductoService.listar(false);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDTO actualizar(@Valid @PathVariable Long id, @Valid @RequestBody TipoProductoRequestDTO dto){
        return tipoProductoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDTO eliminar(@Valid @PathVariable Long id){
        return tipoProductoService.eliminar(id);
    }

    @PatchMapping("/restaurar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDTO restaurar(@Valid @PathVariable Long id) { return tipoProductoService.restaurar(id); }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoProductoResponseDTO buscarPorID(@Valid @PathVariable Long id){
        return tipoProductoService.buscarPorID(id);
    }

    @GetMapping("/nombre/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoProductoResponseDTO> buscarPorNombre(@Valid @PathVariable String nombre){
        return tipoProductoService.buscarPorNombre(nombre);
    }

    @GetMapping("/{id}/contenido")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoResponseDTO> listarContenido(@Valid @PathVariable Long id){
        return productoService.buscarPorTipo(id);
    }
}
