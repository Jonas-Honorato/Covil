package com.example.covil.repository;

import com.example.covil.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    Optional<Ingrediente> findByNomeContainingIgnoreCase(String nome);

    List<Ingrediente> findByQuantidadeLessThan(Integer limite);
    // Aqui você já ganha métodos como save(), findAll(), findById(), delete()
}