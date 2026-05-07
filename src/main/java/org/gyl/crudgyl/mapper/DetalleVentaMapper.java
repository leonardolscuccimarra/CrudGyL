package org.gyl.crudgyl.mapper;


import org.gyl.crudgyl.dto.detalleVenta.DetalleVentaResponseDTO;
import org.gyl.crudgyl.entity.DetalleVenta;


public class DetalleVentaMapper {
    private DetalleVentaMapper(){}

    public static DetalleVentaResponseDTO toResponseDTO(DetalleVenta detalleVenta){
        return new DetalleVentaResponseDTO(
                detalleVenta.getId_detalle_venta(),
                detalleVenta.getCantidad(),
                detalleVenta.getPrecio_unitario(),
                detalleVenta.getPrecio_unitario() * detalleVenta.getCantidad(),
                ProductoMapper.toResponseDTO(detalleVenta.getProducto()),
                detalleVenta.getFechaBaja()
        );
    }
}

