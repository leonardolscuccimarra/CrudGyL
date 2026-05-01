package org.gyl.crudgyl.service;

import org.gyl.crudgyl.dto.detalleVenta.DetalleVentaRequestDTO;
import org.gyl.crudgyl.dto.venta.VentaRequestDTO;
import org.gyl.crudgyl.dto.venta.VentaResponseDTO;
import org.gyl.crudgyl.entity.Cliente;
import org.gyl.crudgyl.entity.DetalleVenta;

import java.util.List;

public interface VentaService {
    VentaResponseDTO crear(VentaRequestDTO venta);

    List<VentaResponseDTO> listar();

    List<VentaResponseDTO> listar(boolean activo);

    VentaResponseDTO actualizar(Long id, VentaRequestDTO dto);

    VentaResponseDTO eliminar(Long id);

    void eliminar(Long id, boolean borradoFisico);

    VentaResponseDTO restaurar(Long id);

    VentaResponseDTO buscarPorID(Long id);

    List<VentaResponseDTO> buscarPorComprador(Long idCliente);

    DetalleVenta generarDetalle(Long idVenta, Long idProducto);

    VentaResponseDTO cargarDetalle(Long id, DetalleVenta detalle);

    VentaResponseDTO generarYCargarDetalle(Long idVenta, Long idProduct);

    VentaResponseDTO eliminarDetalle(Long idDetalle);

    void eliminarDetalle(Long idDetalle, boolean borradoFisico);
}
