package com.desafiospringboot.desafio.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendedor")
@Entity
@Getter
@Setter
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = true)
    private String nome;

    @Email
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "vendedor")
    private List<Venda> vendas = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(email, vendedor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
