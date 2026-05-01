package org.gyl.crudgyl.repository;

import org.gyl.crudgyl.entity.DetalleVenta;
import org.gyl.crudgyl.entity.Producto;
import org.gyl.crudgyl.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByFechaBajaIsNull();
    List<DetalleVenta> findByFechaBajaIsNotNull();
    List<DetalleVenta> findByVenta(Venta venta);
    List<DetalleVenta> findByProducto(Producto producto);

    @Modifying
    @Transactional
    @Query("update DetalleVenta t set t.fechaBaja = :fecha where t.id_detalle_venta = :id")
    int updateFechaBaja(@Param("id") Long id, @Param("fecha") Instant fecha);
}
