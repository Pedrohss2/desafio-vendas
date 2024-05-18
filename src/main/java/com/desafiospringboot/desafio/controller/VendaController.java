package com.desafiospringboot.desafio.controller;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.service.VendaService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;


    @PostMapping
    public ResponseEntity<VendaDTO> criar(@RequestBody VendaDTO dto) {
        dto = vendaService.criar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.id())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }


}
