package org.gyl.crudgyl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private Integer stock;

//    @ManyToOne
//    @JoinColumn(name = "id_tipo_producto")
//    private TipoProducto id_tipo_producto;
    //
    //COMENTAR CODIGO ESTA MAL
}
