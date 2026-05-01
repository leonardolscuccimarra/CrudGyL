package org.gyl.crudgyl.controller;

import jakarta.validation.Valid;
import org.gyl.crudgyl.dto.venta.VentaRequestDTO;
import org.gyl.crudgyl.dto.venta.VentaResponseDTO;
import org.gyl.crudgyl.service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService){
        this.ventaService = ventaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDTO crear(@Valid @RequestBody VentaRequestDTO dto){
        return ventaService.crear(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VentaResponseDTO> listarActivos(){
        return ventaService.listar(true);
    }

    @GetMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    public List<VentaResponseDTO> listarTodo(){
        return ventaService.listar();
    }

    @GetMapping("/archivado")
    @ResponseStatus(HttpStatus.OK)
    public List<VentaResponseDTO> listarArchivados(){
        return ventaService.listar(false);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VentaResponseDTO actualizar(@Valid @PathVariable Long id, @Valid @RequestBody VentaRequestDTO dto){
        return ventaService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VentaResponseDTO eliminar(@Valid @PathVariable Long id){
        return ventaService.eliminar(id);
    }

    @PatchMapping("/restaurar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VentaResponseDTO restaurar(@Valid @PathVariable Long id) { return ventaService.restaurar(id); }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VentaResponseDTO buscarPorID(@Valid @PathVariable Long id){
        return ventaService.buscarPorID(id);
    }

    @PostMapping("/{idVenta}/detalle/{idProducto}")
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDTO crearDetalle(@Valid @PathVariable Long idVenta,@Valid @PathVariable Long idProducto){
        return ventaService.generarYCargarDetalle(idVenta,idProducto);
    }

    @DeleteMapping("/detalle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VentaResponseDTO eliminarDetalle(@Valid @PathVariable Long id){
        return ventaService.eliminarDetalle(id);
    }
}
