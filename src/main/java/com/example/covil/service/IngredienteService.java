package com.example.covil.service;

import com.example.covil.model.Ingrediente;
import com.example.covil.repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository repository;

    public Ingrediente salvar(Ingrediente ingrediente) {
        return repository.save(ingrediente);
    }

    public List<Ingrediente> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Ingrediente adicionarEstoque(Long id, Double quantidade) {
        Ingrediente ingrediente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        ingrediente.setQuantidade(ingrediente.getQuantidade() + quantidade);
        return repository.save(ingrediente);
    }
}