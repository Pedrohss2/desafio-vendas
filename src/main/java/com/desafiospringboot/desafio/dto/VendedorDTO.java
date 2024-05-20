package com.desafiospringboot.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VendedorDTO {
    private Long id;
    @NotBlank(message = "Campo 'nome' nao pode ser nulo/vazio")
    private String nome;
    @NotBlank(message = "Campo 'email' nao pode ser nulo/vazio")
    private String email;
    private Date criadoEm;
    private Date atualizadoEm;
 }
