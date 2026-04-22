package com.example.covil.repository;

import com.example.covil.model.Pedido;
import com.example.covil.model.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(StatusPedido status);

    // Soma o faturamento total de pedidos que não foram cancelados
    @Query("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.status != 'CANCELADO'")
    BigDecimal calcularFaturamentoTotal();

    // Conta quantos pedidos foram feitos hoje
    @Query("SELECT COUNT(p) FROM Pedido p WHERE CAST(p.dataHora AS date) = CURRENT_DATE")    Long contarPedidosHoje();
}