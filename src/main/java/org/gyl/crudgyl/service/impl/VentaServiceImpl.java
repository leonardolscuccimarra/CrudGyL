package org.gyl.crudgyl.service.impl;

import org.gyl.crudgyl.dto.detalleVenta.DetalleVentaRequestDTO;
import org.gyl.crudgyl.dto.venta.VentaRequestDTO;
import org.gyl.crudgyl.dto.venta.VentaResponseDTO;
import org.gyl.crudgyl.entity.Cliente;
import org.gyl.crudgyl.entity.DetalleVenta;
import org.gyl.crudgyl.entity.Producto;
import org.gyl.crudgyl.entity.Venta;
import org.gyl.crudgyl.exception.RecursoDesaparecidoException;
import org.gyl.crudgyl.exception.RecursoNoEncontradoException;
import org.gyl.crudgyl.exception.RecursoNoProcesableException;
import org.gyl.crudgyl.mapper.VentaMapper;
import org.gyl.crudgyl.repository.ClienteRepository;
import org.gyl.crudgyl.repository.DetalleVentaRepository;
import org.gyl.crudgyl.repository.ProductoRepository;
import org.gyl.crudgyl.repository.VentaRepository;
import org.gyl.crudgyl.service.VentaService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository, DetalleVentaRepository detalleVentaRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public VentaResponseDTO crear(VentaRequestDTO dto) {
        Venta venta = VentaMapper.toEntity(dto);
        Venta guardado = ventaRepository.save(venta);
        return VentaMapper.toResponseDTO(guardado);
    }

    @Override
    public List<VentaResponseDTO> listar() {
        return ventaRepository.findAll()
                .stream()
                .map(VentaMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<VentaResponseDTO> listar(boolean activo) {
        return activo ?
                ventaRepository.findByFechaBajaIsNull()
                .stream()
                .map(VentaMapper::toResponseDTO)
                .toList()
                :
                ventaRepository.findByFechaBajaIsNotNull()
                .stream()
                .map(VentaMapper::toResponseDTO)
                .toList();
    }


    @Override
    public VentaResponseDTO buscarPorID(Long id) {
        return ventaRepository.findById(id)
                .map(VentaMapper::toResponseDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        id
                ));
    }

    @Override
    public VentaResponseDTO actualizar(Long id, VentaRequestDTO dto) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        id
                ));

        VentaMapper.updateEntity(venta, dto);
        Venta guardado = ventaRepository.save(venta);
        return VentaMapper.toResponseDTO(guardado);

    }

    @Override
    public VentaResponseDTO eliminar(Long id){
        int columnasEliminadas = ventaRepository.updateFechaBaja(id, Instant.now());
        if (columnasEliminadas == 0) {
            throw new RecursoNoEncontradoException(id);
        }
        Venta eliminado = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Error al recuperar elemento archivado con ID: : " + id
                ));
        return VentaMapper.toResponseDTO(eliminado);
    }

    @Override
    public void eliminar(Long id, boolean borradoFisico) {
        if (!borradoFisico) {
            eliminar(id);
            return;
        }
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(id));

        ventaRepository.delete(venta);
    }

    @Override
    public VentaResponseDTO restaurar(Long id){
        int columnasRestauradas = ventaRepository.updateFechaBaja(id, null);
        if (columnasRestauradas == 0) {
            throw new RecursoNoEncontradoException(id);
        }
        Venta restaurado = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Error al recuperar elemento restaurado con ID: : " + id
                ));
        return VentaMapper.toResponseDTO(restaurado);
    }

    @Override
    public List<VentaResponseDTO> buscarPorComprador(Long idCliente){
        Cliente comprador = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró cliente con ID: " + idCliente));

        return ventaRepository.findByComprador(comprador)
                .stream()
                .map(VentaMapper::toResponseDTO)
                .toList();
    }

    @Override
    public DetalleVenta generarDetalle(Long idVenta, Long idProducto){
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encuentra venta con id: " + idVenta
                ));

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encuentra producto con id: " + idProducto
                ));

        DetalleVenta detalle;

        if (venta.getDetalles()
                .stream()
                .anyMatch(dv ->
                        dv.getProducto().equals(producto)))
        {
            detalle = venta.getDetalles()
                    .stream()
                    .filter(dv -> dv.getProducto().equals(producto))
                    .findAny()
                    .orElseThrow(() -> new RecursoDesaparecidoException(idProducto));

            detalle.setCantidad(detalle.getCantidad() + 1);
        } else {
            detalle = new DetalleVenta();
            detalle.setCantidad(1);
            detalle.setPrecio_unitario(producto.getPrecio());
            detalle.setVenta(venta);
            detalle.setProducto(producto);
        }
        return detalle;
    }

    @Override
    public VentaResponseDTO cargarDetalle(Long id, DetalleVenta detalle) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encuentra venta con id: " + id
                ));

        if (!venta.agregarDetalle(detalle)) {throw new RecursoNoProcesableException(id);}
        return VentaMapper.toResponseDTO(venta);
    }

    @Override
    public VentaResponseDTO generarYCargarDetalle(Long idVenta, Long idProducto){
        generarDetalle(idVenta,idProducto);
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encuentra venta con id: " + idVenta
                ));

        return VentaMapper.toResponseDTO(venta);
    }

    @Override
    public VentaResponseDTO eliminarDetalle(Long idDetalle) {
        DetalleVenta detalle = detalleVentaRepository.findById(idDetalle)
                .orElseThrow(() -> new RecursoNoEncontradoException(idDetalle));

        Venta venta = detalle.getVenta();

        detalle.setFechaBaja(Instant.now());

        return VentaMapper.toResponseDTO(venta);
    }

    @Override
    public void eliminarDetalle(Long idDetalle, boolean borradoFisico){
        if (!borradoFisico){
            eliminarDetalle(idDetalle);
            return;
        }
        DetalleVenta detalle = detalleVentaRepository.findById(idDetalle)
                .orElseThrow(() -> new RecursoNoEncontradoException(idDetalle));

        detalleVentaRepository.delete(detalle);
    }


}
