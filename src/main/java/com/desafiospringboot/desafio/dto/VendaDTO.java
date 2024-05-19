package com.desafiospringboot.desafio.dto;

import com.desafiospringboot.desafio.model.entity.Vendedor;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;


public record VendaDTO(
        Long id,
        @NotBlank(message = "Campo 'valor' nao pode ser nulo/vazio")
        Double valor,
        @NotBlank(message = "Campo 'dataVenda' não pode ser nulo/vazio")
        LocalDate dataVenda,
        Vendedor vendedor_id,
        Date criadoEm,
        Date atualizadoEm
) {
}
