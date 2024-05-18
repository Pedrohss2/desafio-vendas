package com.desafiospringboot.desafio.controller;

import com.desafiospringboot.desafio.dto.VendedorDTO;
import com.desafiospringboot.desafio.service.VendedorService;
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
    public ResponseEntity<VendedorDTO> buscarVendedorPorId(@PathVariable Long id){
        VendedorDTO vendedorDTO = vendedorService.buscaPorId(id);
        return ResponseEntity.ok(vendedorDTO);
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> inserir(@RequestBody VendedorDTO dto) {
        dto = vendedorService.criar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.id())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> atualizar(@PathVariable Long id,@RequestBody VendedorDTO dto) {
        dto = vendedorService.atualizar(id, dto);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendedorService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
