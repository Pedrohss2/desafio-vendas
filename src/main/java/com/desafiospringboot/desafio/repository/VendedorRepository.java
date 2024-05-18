package com.desafiospringboot.desafio.repository;

import com.desafiospringboot.desafio.model.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
