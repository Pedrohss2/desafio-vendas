package com.desafiospringboot.desafio.controller;

import com.desafiospringboot.desafio.dto.VendaDTO;
import com.desafiospringboot.desafio.dto.VendaMinDTO;
import com.desafiospringboot.desafio.dto.VendaResumoDTO;
import com.desafiospringboot.desafio.service.VendaService;
import com.desafiospringboot.desafio.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/{id}")
    @Operation(summary = "Busca venda por id")
    public ResponseEntity<VendaMinDTO> buscarVendedorPorId(@PathVariable Long id){
        VendaMinDTO vendaMinDTO = vendaService.buscaPorId(id);
        return ResponseEntity.ok().body(vendaMinDTO);
    }

    @PostMapping
    @Operation(summary = "Ciar uma nova venda")
    public ResponseEntity<VendaDTO> criar(@Valid @RequestBody VendaDTO dto) {
        dto = vendaService.criar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma venda por id")
    public ResponseEntity<VendaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody VendaDTO dto) {
        dto = vendaService.atualizar(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um vendedor por id")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendaService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/resumo")
    @Operation(summary = "Bucar resumo de vendas")
    public ResponseEntity<List<VendaResumoDTO>> obterResumo(
            @RequestParam("dataInicio") String dataInicio,
            @RequestParam("dataFim")  String dataFim) {

        LocalDate dataInicioParse = LocalDate.parse(dataInicio);
        LocalDate dataFinalParse = LocalDate.parse(dataFim);

        var valores = vendaService.calcularVendasPorPeriodo(dataInicioParse, dataFinalParse);
        return ResponseEntity.ok().body(valores);
    }
}
