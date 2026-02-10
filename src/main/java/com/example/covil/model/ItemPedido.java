package com.example.covil.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Getter @Setter
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    // Guardamos o preço unitário do momento da venda
    private BigDecimal precoUnitario;
}