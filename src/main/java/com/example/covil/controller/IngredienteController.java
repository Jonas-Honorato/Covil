package com.example.covil.controller;

import com.example.covil.dto.ReposicaoEstoqueDTO;
import com.example.covil.model.Ingrediente;
import com.example.covil.service.EstoqueService;
import com.example.covil.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@Tag(name = "Estoque", description = "Gerenciamento de insumos e reposição de mercadorias")
public class IngredienteController {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private IngredienteService ingredienteService;

    @Operation(summary = "Cadastra um novo ingrediente", description = "Adiciona um item (ex: Pão, Carne) ao catálogo do Covil")
    @PostMapping
    public ResponseEntity<Ingrediente> salvar(@Valid @RequestBody Ingrediente ingrediente) {
        return ResponseEntity.ok(ingredienteService.salvar(ingrediente));
    }

    @Operation(summary = "Reposição via DTO", description = "Usa um objeto de reposição para atualizar a quantidade")
    @PatchMapping("/{id}/adicionar-estoque")
    public ResponseEntity<Ingrediente> adicionarEstoque(@PathVariable Long id, @Valid @RequestBody ReposicaoEstoqueDTO dto) {
        return ResponseEntity.ok(ingredienteService.adicionarEstoque(id, dto.getQuantidade()));
    }

    @Operation(summary = "Reposição rápida", description = "Atualiza a quantidade enviando apenas um número inteiro")
    @PatchMapping("/{id}/repor")
    public ResponseEntity<String> repor(@PathVariable Long id, @RequestBody Integer quantidade) {
        estoqueService.reporEstoque(id, quantidade);
        return ResponseEntity.ok("Estoque atualizado com sucesso!");
    }

    @Operation(summary = "Lista o inventário", description = "Retorna todos os ingredientes e suas quantidades atuais")
    @GetMapping
    public List<Ingrediente> listar() {
        return ingredienteService.listarTodos();
    }
}