package com.desafiospringboot.desafio.dto;

import com.desafiospringboot.desafio.model.entity.Venda;
import com.desafiospringboot.desafio.model.entity.Vendedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VendaDTO {
    private Long id;
    private Double valor;
    private LocalDate dataVenda;
    private Vendedor vendedor_id;
    private Date criadoEm;
    private Date atualizadoEm;
}
