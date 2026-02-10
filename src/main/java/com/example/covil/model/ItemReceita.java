package com.example.covil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itens_receita")
@Getter
@Setter
public class ItemReceita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id") // Cria a coluna de chave estrangeira para Produto
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id") // Cria a coluna de chave estrangeira para Ingrediente
    private Ingrediente ingrediente;

    @Column(nullable = false)
    private Double quantidade; // Quanto desse ingrediente vai neste produto
}