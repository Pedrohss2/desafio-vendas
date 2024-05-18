package com.desafiospringboot.desafio.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venda")
@Entity
@Getter
@Setter
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data_venda")
    private LocalDate dataVenda;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Date criadoEm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "atualizado_em", nullable = false, updatable = false)
    private Date atualizadoEm;
}
