package com.desafiospringboot.desafio.dto;

import com.desafiospringboot.desafio.model.entity.Vendedor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


public record VendaDTO(Long id,Double valor, LocalDate dataVenda, Vendedor vendedor_id, Date criadoEm, Date atualizadoEm) {
}
