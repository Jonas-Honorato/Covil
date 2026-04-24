package com.example.covil.controller;


import com.example.covil.dto.DashboardDTO;
import com.example.covil.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Relatórios e Dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Operation(summary = "Obtém resumo executivo", description = "Retorna um resumo rápido via DashboardService.")
    @GetMapping
    public DashboardDTO getResumo() {
        return dashboardService.obterResumo();
    }
}