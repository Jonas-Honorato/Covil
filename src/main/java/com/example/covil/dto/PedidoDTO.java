package com.example.covil.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter @Setter
public class PedidoDTO {
    @NotEmpty(message = "O pedido deve ter pelo menos um item")
    private List<ItemPedidoDTO> itens;

    private String observacao;
}