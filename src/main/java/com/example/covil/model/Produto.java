package com.example.covil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    // O "mappedBy" diz que quem manda na relação é o atributo "produto" lá na classe ItemReceita
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItemReceita> receita;
}