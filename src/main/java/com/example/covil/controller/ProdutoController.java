package com.example.covil.controller;

import com.example.covil.model.Produto;
import com.example.covil.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody Produto produto) {
        // O Hibernate vai salvar o produto e percorrer a lista de receitas salvando cada item
        Produto salvo = repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }
}