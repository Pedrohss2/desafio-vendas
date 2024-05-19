package com.desafiospringboot.desafio.dto;

import jakarta.validation.constraints.NotBlank;

public record VendaResumoDTO(
        @NotBlank(message = "Campo 'nome' nao pode ser nulo/vazio")
        String nome,
        @NotBlank(message = "Campo 'totalDeVendas' nao pode ser nulo/vazio")
        double totalDeVendas,
        double mediDeVendasDiarias
) {
}
