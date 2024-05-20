package com.desafiospringboot.desafio.service;

import com.desafiospringboot.desafio.dto.VendedorDTO;
import com.desafiospringboot.desafio.model.entity.Vendedor;
import com.desafiospringboot.desafio.repository.VendedorRepository;
import com.desafiospringboot.desafio.service.exception.BancoDeDadosException;
import com.desafiospringboot.desafio.service.exception.RecursoNaoEncontrado;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public VendedorDTO buscaPorId(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Vendedor não encontrado"));

        return new VendedorDTO(vendedor.getId(), vendedor.getNome(), vendedor.getEmail());
    }

    @Transactional
    public VendedorDTO criar(VendedorDTO dto) {

        Vendedor vendedor = modelMapper.map(dto, Vendedor.class);

        vendedor = vendedorRepository.save(vendedor);

        return modelMapper.map(vendedor, VendedorDTO.class);
    }

    @Transactional
    public VendedorDTO atualizar(Long id, VendedorDTO dto) {

        try {
            Vendedor vendedor = modelMapper.map(dto, Vendedor.class);

            vendedor = vendedorRepository.save(vendedor);

            return modelMapper.map(vendedor, VendedorDTO.class);
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado("Usuario não encontado");
        }
    }

    @Transactional
    public void deletar(Long id) {
        vendedorRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Usuario não encotrado"));

        try {
            vendedorRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException("Erro ao deletar vendedor");
        }
    }
}
