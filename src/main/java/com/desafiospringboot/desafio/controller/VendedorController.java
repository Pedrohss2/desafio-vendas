package com.desafiospringboot.desafio.controller;

import com.desafiospringboot.desafio.dto.VendedorDTO;
import com.desafiospringboot.desafio.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/{id}")
    @Operation(summary = "Atualizar um vendedor por id")
    public ResponseEntity<VendedorDTO> buscarVendedorPorId(@PathVariable Long id){
        VendedorDTO vendedorDTO = vendedorService.buscaPorId(id);
        return ResponseEntity.ok().body(vendedorDTO);
    }

    @PostMapping
    @Operation(summary = "Criar um vendedor")
    public ResponseEntity<VendedorDTO> criar(@Valid @RequestBody VendedorDTO dto) {
        dto = vendedorService.criar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um vendedor por id")
    public ResponseEntity<VendedorDTO> atualizar(@PathVariable Long id, @Valid @RequestBody VendedorDTO dto) {
        dto = vendedorService.atualizar(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um vendedor por id")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendedorService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
