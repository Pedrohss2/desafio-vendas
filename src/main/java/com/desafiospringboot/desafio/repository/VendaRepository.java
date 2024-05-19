package com.desafiospringboot.desafio.repository;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda,Long> {

    @Query("SELECT v FROM Venda v WHERE v.vendedor.id = :vendedorId AND v.dataVenda BETWEEN :dataInicio AND :dataFim")
    List<Venda> findVendaByVendedor(@Param("vendedorId") Long vendedorId, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
