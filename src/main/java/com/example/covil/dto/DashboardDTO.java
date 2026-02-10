package com.example.covil.dto;

import com.example.covil.model.Ingrediente;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class DashboardDTO {
    private Long totalPedidos;
    private BigDecimal faturamentoTotal;
    private List<Ingrediente> itensEstoqueBaixo;
}