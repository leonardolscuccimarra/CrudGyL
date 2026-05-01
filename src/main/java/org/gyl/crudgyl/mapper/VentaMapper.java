package org.gyl.crudgyl.mapper;

import org.gyl.crudgyl.dto.venta.VentaRequestDTO;
import org.gyl.crudgyl.dto.venta.VentaResponseDTO;
import org.gyl.crudgyl.entity.DetalleVenta;
import org.gyl.crudgyl.entity.Venta;

import java.time.Instant;
import java.util.List;

public class VentaMapper {
    private VentaMapper(){}

    public static Venta toEntity(VentaRequestDTO dto){
        Venta venta = new Venta();
        venta.setFechaVenta(Instant.now());
        venta.setComprador(dto.comprador());
        venta.setDetalles(dto.detalles());

        venta.setTotal(contarSubtotales(dto.detalles()));
        return venta;
    }

    public static VentaResponseDTO toResponseDTO(Venta venta){
        return new VentaResponseDTO(
                venta.getId_venta(),
                venta.getFechaVenta(),
                venta.getTotal(),
                venta.getComprador(),
                venta.getDetalles(),
                venta.getFechaBaja()
        );
    }

    public static void updateEntity(Venta venta, VentaRequestDTO dto){
        venta.setFechaVenta(Instant.now());
        venta.setComprador(dto.comprador());
        venta.setDetalles(dto.detalles());

        venta.setTotal(contarSubtotales(dto.detalles()));
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
