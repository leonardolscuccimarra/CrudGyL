package org.gyl.crudgyl.repository;

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
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    List<TipoProducto> findByNombre(String nombre);
    List<TipoProducto> findByFechaBajaIsNull();
    List<TipoProducto> findByFechaBajaIsNotNull();

    @Modifying
    @Transactional
    @Query("update TipoProducto t set t.fechaBaja = :fecha where t.id_tipo_producto = :id")
    int updateFechaBaja(@Param("id") Long id, @Param("fecha") Instant fecha);
}
