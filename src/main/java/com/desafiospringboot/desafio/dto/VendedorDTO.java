package com.desafiospringboot.desafio.dto;

import jakarta.validation.constraints.NotBlank;

public record VendedorDTO(
        Long id,
        @NotBlank(message = "Campo 'nome' nao pode ser nulo/vazio")
        String nome,
        @NotBlank(message = "Campo 'email' nao pode ser nulo/vazio")
        String email
) { }
