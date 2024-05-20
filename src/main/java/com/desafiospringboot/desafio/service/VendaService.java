package com.desafiospringboot.desafio.service;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.dto.VendaResumoDTO;
import com.desafiospringboot.desafio.dto.VendedorDTO;
import com.desafiospringboot.desafio.model.entity.Venda;
import com.desafiospringboot.desafio.model.entity.Vendedor;
import com.desafiospringboot.desafio.repository.VendaRepository;
import com.desafiospringboot.desafio.repository.VendedorRepository;
import jakarta.servlet.http.PushBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    @Autowired
    private ModelMapper modelMapper;

    public VendaDTO criar(VendaDTO dto) {

        Venda venda = modelMapper.map(dto, Venda.class);

        venda.setVendedor(dto.getVendedor_id());

        venda.setCriadoEm(Date.from(Instant.now()));
        venda.setAtualizadoEm(Date.from(Instant.now()));

        venda = vendaRepository.save(venda);

        return modelMapper.map(venda, VendaDTO.class);
    }

    public List<VendaResumoDTO> calcularVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        List<Vendedor> vendedores = vendedorRepository.findAll();

        return vendedores.stream().map(vendedor -> {

            this.validarData(dataInicio, dataFim);

            double totalDeVendas = vendedor.getVendas().size();

            List<Venda> venda = vendaRepository.findVendaByVendedor(vendedor.getId(), dataInicio, dataFim); // Seleciona pelo periodo de tempo

            var caucularMediaDiaria = this.calcularMediaDiaria(dataInicio, dataFim, totalDeVendas);

            return new VendaResumoDTO(vendedor.getNome(), totalDeVendas, caucularMediaDiaria);
        }).collect(Collectors.toList());
    }


    public double calcularMediaDiaria(LocalDate dataInicio, LocalDate dataFim, double totalDeVendas) {

        double dias = dataInicio.until(dataFim).getDays() + 1;

        double medidaDeVendasDiarias = totalDeVendas / dias;

        return medidaDeVendasDiarias;
    }

    public void validarData(LocalDate dataInicio, LocalDate dataFim) {
        if(dataInicio.isAfter(dataFim)) throw new IllegalArgumentException("Data inicial não pode ser apos a final!");
        if(dataFim.isBefore(dataInicio)) throw new IllegalArgumentException("Data final não pode ser antes da data inicial!");
    }
}
