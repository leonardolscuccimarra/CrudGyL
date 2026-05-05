package org.gyl.crudgyl.repository;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.gyl.crudgyl.entity.Producto;
import org.gyl.crudgyl.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombre(String nombre);
    List<Producto> findByStock(Integer stock);
    List<Producto> findByPrecio(Double precio);
    List<Producto> findByFechaBajaIsNull();
    List<Producto> findByFechaBajaIsNotNull();
    List<Producto> findByTipoProducto(TipoProducto tipo);

    @Modifying
    @Transactional
    @Query("update Producto p set p.fechaBaja = :fecha where p.id_producto = :id")
    int updateFechaBaja(@Param("id") Long id, @Param("fecha") Instant fecha);
}
