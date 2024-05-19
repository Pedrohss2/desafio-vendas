package com.desafiospringboot.desafio.service;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.dto.VendaResumoDTO;
import com.desafiospringboot.desafio.dto.VendedorDTO;
import com.desafiospringboot.desafio.model.entity.Venda;
import com.desafiospringboot.desafio.model.entity.Vendedor;
import com.desafiospringboot.desafio.repository.VendaRepository;
import com.desafiospringboot.desafio.repository.VendedorRepository;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    public VendaDTO criar(VendaDTO dto) {

        Venda venda = new Venda();

        venda.setId(dto.id());
        venda.setValor(dto.valor());
        venda.setDataVenda(dto.dataVenda());

        venda.setVendedor(dto.vendedor_id());

        venda.setCriadoEm(Date.from(Instant.now()));
        venda.setAtualizadoEm(Date.from(Instant.now()));

        venda = vendaRepository.save(venda);

        return new VendaDTO(venda.getId(), venda.getValor(), venda.getDataVenda(), venda.getVendedor(), venda.getCriadoEm(), venda.getAtualizadoEm());
    }

    public List<VendaResumoDTO> calcularVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        List<Vendedor> vendedores = vendedorRepository.findAll();

        return vendedores.stream().map(vendedor -> {
            List<Venda> venda = vendaRepository.findVendaByVendedor(vendedor.getId(), dataInicio, dataFim);

            double totalDeVendas = vendedor.getVendas().size();

            double dias = dataInicio.until(dataFim).getDays() + 1;

            double medidaDeVendasDiarias = totalDeVendas / dias;

            return new VendaResumoDTO(vendedor.getNome(), totalDeVendas, medidaDeVendasDiarias);
        }).collect(Collectors.toList());
    }
}
