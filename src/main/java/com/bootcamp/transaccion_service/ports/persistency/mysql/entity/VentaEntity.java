package com.bootcamp.transaccion_service.ports.persistency.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaID;

    @Column(name = "usuarioID", nullable = false)
    private Long usuarioID;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "precioTotal", nullable = false)
    private Double precioTotal;
}
