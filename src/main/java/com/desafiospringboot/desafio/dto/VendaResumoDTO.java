package com.desafiospringboot.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendaResumoDTO {
     @NotBlank(message = "Campo 'nome' nao pode ser nulo/vazio")
     private String nome;
     @NotBlank(message = "Campo 'totalDeVendas' nao pode ser nulo/vazio")
     private double totalDeVendas;
     private double mediDeVendasDiarias;
}
