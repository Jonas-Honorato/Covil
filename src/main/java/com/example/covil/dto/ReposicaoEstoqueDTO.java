package com.example.covil.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReposicaoEstoqueDTO {
    @Positive(message = "A quantidade de reposição deve ser maior que zero")
    private Double quantidade;
}