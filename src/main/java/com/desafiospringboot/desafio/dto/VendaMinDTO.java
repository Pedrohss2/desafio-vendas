package com.desafiospringboot.desafio.dto;

import com.desafiospringboot.desafio.model.entity.Venda;
import com.desafiospringboot.desafio.model.entity.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendaMinDTO {

    private Long id;
    private Double valor;
    private LocalDate dataVenda;
    private Long vendedor_id;
    private Date criadoEm;
    private Date atualizadoEm;

    public VendaMinDTO(Venda venda) {
        this.id = venda.getId();
        this.valor = venda.getValor();
        this.dataVenda = venda.getDataVenda();
        this.vendedor_id = venda.getVendedor().getId();
        this.criadoEm = venda.getCriadoEm();
        this.atualizadoEm = venda.getAtualizadoEm();
    }
}
