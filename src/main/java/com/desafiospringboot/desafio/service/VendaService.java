package com.desafiospringboot.desafio.service;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.dto.VendaMinDTO;
import com.desafiospringboot.desafio.dto.VendaResumoDTO;
import com.desafiospringboot.desafio.model.entity.Venda;
import com.desafiospringboot.desafio.model.entity.Vendedor;
import com.desafiospringboot.desafio.repository.VendaRepository;
import com.desafiospringboot.desafio.repository.VendedorRepository;
import com.desafiospringboot.desafio.service.exception.BancoDeDadosException;
import com.desafiospringboot.desafio.service.exception.RecursoNaoEncontrado;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public VendaMinDTO buscaPorId(Long id) {
        Venda venda = vendaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Vendedor não encontrado"));

        return new VendaMinDTO(venda);
    }

    public VendaDTO criar(VendaDTO dto) {

        Venda venda = modelMapper.map(dto, Venda.class);

        venda.setCriadoEm(Date.from(Instant.now()));
        venda.setAtualizadoEm(Date.from(Instant.now()));

        venda = vendaRepository.save(venda);

        return modelMapper.map(venda, VendaDTO.class);
    }

    public VendaDTO atualizar(Long id, VendaDTO dto) {
        try {
            Venda venda = modelMapper.map(dto, Venda.class);

            venda.setCriadoEm(Date.from(Instant.now()));
            venda.setAtualizadoEm(Date.from(Instant.now()));

            venda = vendaRepository.save(venda);

            return modelMapper.map(venda, VendaDTO.class);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado("Usuario não encontado");
        }
    }

    public void deletar(Long id) {
        vendaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Usuario não encotrado"));

        try {
            vendaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException("Erro ao deletar vendedor");
        }
    }

    public List<VendaResumoDTO> calcularVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        List<Vendedor> vendedores = vendedorRepository.findAll();

        return vendedores.stream().map(vendedor -> {

            this.validarData(dataInicio, dataFim);

            double totalDeVendas = vendedor.getVendas().size();

            List<Venda> venda = vendaRepository.findVendaByVendedor(vendedor.getId(), dataInicio, dataFim);

            double mediaDiaria = this.calcularMediaDiaria(dataInicio, dataFim, totalDeVendas);

            return new VendaResumoDTO(vendedor.getNome(), totalDeVendas, mediaDiaria);
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
