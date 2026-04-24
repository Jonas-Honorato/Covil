package com.example.covil.controller;

import com.example.covil.model.Produto;
import com.example.covil.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Cardápio", description = "Gerenciamento dos produtos finais (hambúrgueres, bebidas, etc.)")

public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Operation(summary = "Cadastra um novo produto", description = "Salva o produto e sua composição (receita) no banco de dados.")
    @PostMapping
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto) {
        // O Hibernate vai salvar o produto e percorrer a lista de receitas salvando cada item
        Produto salvo = repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(summary = "Lista o cardápio completo", description = "Retorna todos os produtos cadastrados com seus detalhes.")
    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }
}