package com.desafiospringboot.desafio.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendedor")
@Entity
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = true)
    private String nome;

    @OneToMany(mappedBy = "vendedor")
    private List<Venda> vendas = new ArrayList<>();

}
