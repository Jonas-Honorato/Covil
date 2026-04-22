package com.example.covil.dto;

import com.example.covil.model.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor // Importante para criar o objeto facilmente no Service
@NoArgsConstructor  // Importante para o Spring conseguir serializar o JSON
public class DashboardDTO {
    private Long totalPedidos;
    private Long totalPedidosHoje; // Adicionamos esse para o dono ver o movimento do dia
    private BigDecimal faturamentoTotal;
    private List<Ingrediente> itensEstoqueBaixo;
}