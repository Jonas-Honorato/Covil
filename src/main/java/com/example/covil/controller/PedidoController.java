import com.example.covil.dto.AtualizarStatusDTO;
import com.example.covil.dto.PedidoDTO;
import com.example.covil.model.Pedido;
import com.example.covil.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService; // Lógica pesada: baixa estoque, calcula preço, etc.

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criarPedido(dto));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @Valid @RequestBody AtualizarStatusDTO dto) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, dto.getNovoStatus()));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.cancelarPedido(id));
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }
}