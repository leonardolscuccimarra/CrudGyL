package org.gyl.crudgyl.repository;

import org.gyl.crudgyl.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNombre(String nombre);
    List<Cliente> findByApellido(String apellido);
    Cliente findByCorreo(String correo);
    List<Cliente> findByTelefono(String telefono);
    List<Cliente> findByDireccion(String direccion);
    List<Cliente> findByFechaBajaIsNull();
    List<Cliente> findByFechaBajaIsNotNull();
    boolean existsByCorreo(String correo);

    @Modifying
    @Transactional
    @Query("update Cliente c set c.fechaBaja = :fecha where c.id_cliente = :id")
    int updateFechaBaja(@Param("id") Long id, @Param("fecha") Instant fecha);
}
