package com.desafiospringboot.desafio.controller;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.dto.VendaResumoDTO;
import com.desafiospringboot.desafio.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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



    @GetMapping("/resumo")
    public ResponseEntity<List<VendaResumoDTO>> obterResumo(
            @RequestParam("dataInicio") String dataInicio,
            @RequestParam("dataFim")  String dataFim) {

        LocalDate dataInicioParse = LocalDate.parse(dataInicio);
        LocalDate dataFinalParse = LocalDate.parse(dataFim);

        var valores = vendaService.calcularVendasPorPeriodo(dataInicioParse, dataFinalParse);
        return ResponseEntity.ok().body(valores);
    }
}
