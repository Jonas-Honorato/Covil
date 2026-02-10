package com.example.covil.model;

import com.example.covil.model.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter @Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Registra o momento exato do pedido automaticamente
    private LocalDateTime dataHora = LocalDateTime.now();

    @Enumerated(EnumType.STRING) // Salva o nome do status no banco (ex: "RECEBIDO")
    private StatusPedido status = StatusPedido.RECEBIDO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    private BigDecimal valorTotal;

    private String observacao; // Ex: "Sem cebola"
}