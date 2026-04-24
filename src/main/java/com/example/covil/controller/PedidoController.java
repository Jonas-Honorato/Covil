package com.example.covil.controller;

import com.example.covil.dto.AtualizarStatusDTO;
import com.example.covil.dto.PedidoDTO;
import com.example.covil.model.Pedido;
import com.example.covil.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Fila de Pedidos", description = "Gerenciamento do fluxo de produção: do recebimento à entrega")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService; // Lógica pesada: baixa estoque, calcula preço, etc.

    @Operation(summary = "Registra um novo pedido", description = "Recebe os itens, calcula o preço e abate os ingredientes do estoque automaticamente.")
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criarPedido(dto));
    }

    @Operation(summary = "Atualiza o status do pedido", description = "Move o pedido entre os estados (Ex: PENDENTE -> PREPARANDO -> PRONTO).")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @Valid @RequestBody AtualizarStatusDTO dto) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, dto.getNovoStatus()));
    }

    @Operation(summary = "Cancela um pedido", description = "Interrompe a produção e pode estornar itens ao estoque (dependendo da regra de negócio).")
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.cancelarPedido(id));
    }

    @Operation(summary = "Histórico geral", description = "Lista todos os pedidos realizados, independente do status.")
    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @Operation(summary = "Monitor da Cozinha", description = "Lista apenas os pedidos que estão aguardando ou em produção. É o coração da operação!")
    @GetMapping("/fila")
    public List<Pedido> getFilaCozinha() {
        return pedidoService.listarPedidosPendentes();
    }
}