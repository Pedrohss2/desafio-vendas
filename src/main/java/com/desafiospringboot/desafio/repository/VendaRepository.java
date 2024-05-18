package com.desafiospringboot.desafio.repository;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda,Long> {

}
