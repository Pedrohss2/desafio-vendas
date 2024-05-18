package com.desafiospringboot.desafio.service;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.dto.VendedorDTO;
import com.desafiospringboot.desafio.model.entity.Venda;
import com.desafiospringboot.desafio.model.entity.Vendedor;
import com.desafiospringboot.desafio.repository.VendaRepository;
import com.desafiospringboot.desafio.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    public VendaDTO criar(VendaDTO dto) {
        Vendedor vendedor = vendedorRepository.findById(dto.vendedor_id().getId())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado com id: " + dto.vendedor_id().getId()));

        Venda venda = new Venda();

        venda.setId(dto.id());
        venda.setValor(dto.valor());
        venda.setDataVenda(dto.dataVenda());

        venda.setVendedor(vendedor);

        venda.setCriadoEm(Date.from(Instant.now()));
        venda.setAtualizadoEm(Date.from(Instant.now()));

        return new VendaDTO(venda.getId(), venda.getValor(), venda.getDataVenda(), venda.getCriadoEm(), venda.getAtualizadoEm(), venda.getVendedor());
    }

}
