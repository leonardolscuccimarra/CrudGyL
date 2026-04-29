package org.gyl.crudgyl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name="tipos_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_tipo_producto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column
    private Instant fechaBaja;
}
