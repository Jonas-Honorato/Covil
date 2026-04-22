package com.example.covil.service;

import com.example.covil.exception.SaldoInsuficienteException;
import com.example.covil.model.Ingrediente;
import com.example.covil.model.ItemReceita;
import com.example.covil.model.Produto;
import com.example.covil.repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Transactional // Garante que se algo falhar, o banco volta ao estado anterior (Rollback)
    public void baixarEstoque(Produto produto) {

        // 1. Verificar disponibilidade de TODOS os itens primeiro
        for (ItemReceita item : produto.getReceita()) {
            Ingrediente ing = item.getIngrediente();
            if (ing.getQuantidade() < item.getQuantidade()) {
                // Se um falhar, lançamos a exceção e o método para aqui!
                throw new SaldoInsuficienteException("Estoque insuficiente de: " + ing.getNome());
            }
        }

        // 2. Se chegou aqui, é porque tem tudo! Agora podemos subtrair.
        for (ItemReceita item : produto.getReceita()) {
            Ingrediente ing = item.getIngrediente();
            ing.setQuantidade(ing.getQuantidade() - item.getQuantidade());
            ingredienteRepository.save(ing);
        }
    }
    @Transactional
    public void reporEstoque(Long ingredienteId, Integer quantidadeAdicionada) {
        // Buscamos o ingrediente. Se não existir, usamos a sua exceção ou uma genérica do Java.
        Ingrediente ing = ingredienteRepository.findById(ingredienteId)
                .orElseThrow(() -> new SaldoInsuficienteException("Ingrediente com ID " + ingredienteId + " não encontrado."));

        // Somamos a nova quantidade
        ing.setQuantidade(ing.getQuantidade() + quantidadeAdicionada);

        // Salvamos a atualização
        ingredienteRepository.save(ing);
    }
}