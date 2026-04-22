package com.example.covil.controller;

import com.example.covil.dto.DashboardDTO;
import com.example.covil.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> obterDashboard() {
        // Chamamos o método que criamos no Service
        DashboardDTO dados = pedidoService.obterDadosDashboard();
        return ResponseEntity.ok(dados);
    }
}