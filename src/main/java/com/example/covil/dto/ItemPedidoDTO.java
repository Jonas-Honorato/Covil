package com.example.covil.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemPedidoDTO {

    @NotNull(message = "O ID do produto é obrigatório")
    private Long produtoId;

    @Min(value = 1, message = "A quantidade mínima é 1")
    private Integer quantidade;
}