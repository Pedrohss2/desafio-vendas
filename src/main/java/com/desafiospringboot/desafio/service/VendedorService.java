package com.desafiospringboot.desafio.service;

import com.desafiospringboot.desafio.dto.VendedorDTO;
import com.desafiospringboot.desafio.model.entity.Vendedor;
import com.desafiospringboot.desafio.repository.VendedorRepository;
import com.desafiospringboot.desafio.service.exception.BancoDeDadosException;
import com.desafiospringboot.desafio.service.exception.RecursoNaoEncontrado;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public VendedorDTO buscaPorId(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontrado("Vendedor não encontrado"));

        return new VendedorDTO(vendedor.getId(), vendedor.getNome(), vendedor.getEmail());
    }

    public VendedorDTO criar(VendedorDTO dto) {
        Vendedor vendedor1 = new Vendedor();

        vendedor1.setId(dto.id());
        vendedor1.setNome(dto.nome());
        vendedor1.setEmail(dto.email());

        vendedor1 = vendedorRepository.save(vendedor1);

        return new VendedorDTO(vendedor1.getId(), vendedor1.getNome(), vendedor1.getEmail());
    }

    public VendedorDTO atualizar(Long id, VendedorDTO dto) {

        try {
            Vendedor vendedor1 = new Vendedor();

            vendedor1.setId(id);
            vendedor1.setNome(dto.nome());
            vendedor1.setEmail(dto.email());

            vendedor1 = vendedorRepository.save(vendedor1);
            return new VendedorDTO(vendedor1.getId(), vendedor1.getNome(), vendedor1.getEmail());
        }
        catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado("Usuario não encontado");
        }
    }


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
