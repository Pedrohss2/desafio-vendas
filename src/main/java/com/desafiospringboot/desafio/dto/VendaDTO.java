package com.desafiospringboot.desafio.dto;

import com.desafiospringboot.desafio.model.entity.Vendedor;

import java.time.LocalDate;
import java.util.Date;

public record VendaDTO(Long id, Double valor, LocalDate dataVenda, Date criadoEm, Date atualizadoEm, Vendedor vendedor_id) {
}
