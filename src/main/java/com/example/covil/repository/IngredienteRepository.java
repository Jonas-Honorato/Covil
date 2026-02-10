package com.example.covil.repository;

import com.example.covil.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    // Aqui você já ganha métodos como save(), findAll(), findById(), delete()
}