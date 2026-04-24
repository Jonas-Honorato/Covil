package com.example.covil.controller;

import com.example.covil.dto.DashboardDTO;
import com.example.covil.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
@Tag(name = "Relatórios e Dashboard", description = "Análise de vendas e performance da hamburgueria")
public class RelatorioController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Obtém dados detalhados do dashboard", description = "Retorna métricas de pedidos e vendas via PedidoService.")
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> obterDashboard() {
        // Chamamos o método que criamos no Service
        DashboardDTO dados = pedidoService.obterDadosDashboard();
        return ResponseEntity.ok(dados);
    }
}