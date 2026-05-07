package org.gyl.crudgyl.mapper;

import org.gyl.crudgyl.dto.venta.VentaRequestDTO;
import org.gyl.crudgyl.dto.venta.VentaResponseDTO;
import org.gyl.crudgyl.entity.DetalleVenta;
import org.gyl.crudgyl.entity.Venta;
import org.gyl.crudgyl.exception.RecursoNoEncontradoException;
import org.gyl.crudgyl.repository.ClienteRepository;
import org.gyl.crudgyl.repository.DetalleVentaRepository;

import java.time.Instant;
import java.util.List;

public class VentaMapper {
    private VentaMapper(){}

    public static Venta toEntity(VentaRequestDTO dto, ClienteRepository clienteRepository, DetalleVentaRepository detalleVentaRepository){
        Venta venta = new Venta();
        venta.setFechaVenta(Instant.now());
        venta.setComprador(clienteRepository.findById(dto.comprador())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe cliente con id: "+ dto.comprador())));

        if (!dto.id_detalles().isEmpty()) {
            venta.setDetalles(detalleVentaRepository.findAllById(dto.id_detalles()));
            venta.setTotal(contarSubtotales(venta.getDetalles()));
        }
        return venta;
    }

    public static VentaResponseDTO toResponseDTO(Venta venta){
        return new VentaResponseDTO(
                venta.getId_venta(),
                venta.getFechaVenta(),
                venta.getTotal(),
                venta.getComprador(),
                venta.getDetalles()
                        .stream()
                        .map(DetalleVentaMapper::toResponseDTO)
                        .toList(),
                venta.getFechaBaja()
        );
    }

    public static void updateEntity(Venta venta, VentaRequestDTO dto,ClienteRepository clienteRepository, DetalleVentaRepository detalleVentaRepository){
        venta.setFechaVenta(Instant.now());
        venta.setComprador(clienteRepository.findById(dto.comprador())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe cliente con id: "+ dto.comprador())));
        venta.setDetalles(detalleVentaRepository.findAllById(dto.id_detalles()));

        venta.setTotal(contarSubtotales(venta.getDetalles()));
    }

    private static double contarSubtotales(List<DetalleVenta> detalles){
        return detalles
                .stream()
                .filter(detalleVenta ->
                        detalleVenta.getFechaBaja() == null)
                .mapToDouble(v ->
                                v.getPrecio_unitario() * v.getCantidad())
                .sum();
    }
}
