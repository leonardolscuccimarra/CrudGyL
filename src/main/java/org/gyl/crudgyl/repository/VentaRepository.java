package org.gyl.crudgyl.repository;

import org.gyl.crudgyl.entity.Cliente;
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
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByComprador(Cliente comprador);
    List<Venta> findByFechaVenta(Instant fechaVenta);
    List<Venta> findByFechaVentaIsNull();
    List<Venta> findByFechaVentaIsNotNull();
    List<Venta> findByTotal(double total);
    List<Venta> findByFechaBajaIsNull();
    List<Venta> findByFechaBajaIsNotNull();

    @Modifying
    @Transactional
    @Query("update Venta p set p.fechaBaja = :fecha where p.id_venta = :id")
    int updateFechaBaja(@Param("id") Long id, @Param("fecha") Instant fecha);
}
