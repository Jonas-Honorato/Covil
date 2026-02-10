package com.example.covil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ingredientes")
@Getter
@Setter
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do ingrediente não pode estar em branco")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    @Column(nullable = false)
    private Double quantidade;

    @NotBlank(message = "A unidade de medida (ex: kg, un) é obrigatória")
    @Column(name = "unidade_medida", nullable = false)
    private String unidadeMedida;

    public Ingrediente() {}
}