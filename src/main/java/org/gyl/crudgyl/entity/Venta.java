package org.gyl.crudgyl.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name="ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_venta;

    @Column
    private Instant fechaVenta;

    @Column(nullable = false)
    private double total;

    @Column
    private Instant fechaBaja;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente comprador;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    public boolean agregarDetalle(DetalleVenta detalle){
        return this.detalles.add(detalle);
    }

    public boolean borrarDetalle(DetalleVenta detalle){
        return this.detalles.remove(detalle);
    }
}
