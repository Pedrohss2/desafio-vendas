package com.desafiospringboot.desafio.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venda")
@Entity
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

    @Column(name = "criado_em")
    private LocalDate criadoEm;

    @Column(name = "atualizado_em")
    private LocalDate atualizadoEm;
}
