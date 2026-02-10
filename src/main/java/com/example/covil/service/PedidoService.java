package com.example.covil.service;


import com.example.covil.dto.ItemPedidoDTO;
import com.example.covil.dto.PedidoDTO;
import com.example.covil.model.ItemPedido;
import com.example.covil.model.Pedido;
import com.example.covil.model.Produto;
import com.example.covil.model.enums.StatusPedido;
import com.example.covil.repository.PedidoRepository;
import com.example.covil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueService estoqueService; // Reutilizando a lógica que discutimos!

    @Transactional
    public Pedido criarPedido(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setObservacao(dto.getObservacao());

        List<ItemPedido> itensDoPedido = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;

        // 1. Converter DTO para Entidades e calcular preços
        for (ItemPedidoDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            // Validar e baixar estoque para CADA unidade desse produto
            // Aqui chamamos o serviço que você idealizou!
            for (int i = 0; i < itemDto.getQuantidade(); i++) {
                estoqueService.baixarEstoque(produto);
            }

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());

            // Calcula o subtotal do item e soma ao total do pedido
            BigDecimal subtotal = produto.getPreco().multiply(new BigDecimal(itemDto.getQuantidade()));
            valorTotal = valorTotal.add(subtotal);

            itensDoPedido.add(item);
        }

        pedido.setItens(itensDoPedido);
        pedido.setValorTotal(valorTotal);

        return pedidoRepository.save(pedido);
    }
    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        // 1. Procura o pedido no banco
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));

        // 2. Atualiza o status
        pedido.setStatus(novoStatus);

        // 3. Guarda a alteração (o @Transactional garante o commit)
        return pedidoRepository.save(pedido);
    }
    @Transactional
    public Pedido cancelarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Regra de negócio: Só cancela se não tiver sido entregue
        if (pedido.getStatus() == StatusPedido.ENTREGUE) {
            throw new RuntimeException("Não é possível cancelar um pedido que já foi entregue.");
        }

        pedido.setStatus(StatusPedido.CANCELADO);

        // OPCIONAL: Lógica para devolver os ingredientes ao estoque
        // Para cada item do pedido, você percorreria a receita do produto
        // e somaria as quantidades de volta no IngredienteRepository.

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
}