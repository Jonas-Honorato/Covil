package com.example.covil.service;

import com.example.covil.dto.DashboardDTO;
import com.example.covil.model.Pedido;
import com.example.covil.model.enums.StatusPedido;
import com.example.covil.repository.IngredienteRepository;
import com.example.covil.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public DashboardDTO obterResumo() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        // 1. Filtra apenas os pedidos que NÃO foram cancelados para o faturamento
        BigDecimal faturamento = todosPedidos.stream()
                .filter(p -> p.getStatus() != StatusPedido.CANCELADO)
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. Busca ingredientes com estoque baixo (ex: menos de 5 unidades/kg)
        // Você pode ajustar esse valor '5.0' conforme a necessidade
        var estoqueBaixo = ingredienteRepository.findAll().stream()
                .filter(i -> i.getQuantidade() < 5.0)
                .toList();

        DashboardDTO dto = new DashboardDTO();
        dto.setTotalPedidos((long) todosPedidos.size());
        dto.setFaturamentoTotal(faturamento);
        dto.setItensEstoqueBaixo(estoqueBaixo);

        return dto;
    }
}