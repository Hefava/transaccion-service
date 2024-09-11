package com.bootcamp.transaccion_service.transaccion_service.ports.persistency.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suministro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuministroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suministroID;

    @Column(name = "articuloID", nullable = false)
    private Long articuloID;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;
}