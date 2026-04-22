package com.example.covil.controller;

import com.example.covil.dto.ReposicaoEstoqueDTO;
import com.example.covil.model.Ingrediente;
import com.example.covil.service.EstoqueService;
import com.example.covil.service.IngredienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private IngredienteService ingredienteService; // Chama o Service para a lógica

    @PostMapping
    public ResponseEntity<Ingrediente> salvar(@Valid @RequestBody Ingrediente ingrediente) {
        return ResponseEntity.ok(ingredienteService.salvar(ingrediente));
    }

    @PatchMapping("/{id}/adicionar-estoque")
    public ResponseEntity<Ingrediente> adicionarEstoque(@PathVariable Long id, @Valid @RequestBody ReposicaoEstoqueDTO dto) {
        return ResponseEntity.ok(ingredienteService.adicionarEstoque(id, dto.getQuantidade()));
    }

    //  é usado para atualizações parciais (neste caso, apenas a quantidade)
    @PatchMapping("/{id}/repor")
    public ResponseEntity<String> repor(@PathVariable Long id, @RequestBody Integer quantidade) {
        estoqueService.reporEstoque(id, quantidade);
        return ResponseEntity.ok("Estoque atualizado com sucesso!");
    }

    @GetMapping
    public List<Ingrediente> listar() {
        return ingredienteService.listarTodos();
    }
}