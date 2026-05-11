// Definimos o que esse card precisa receber para funcionar (como os parâmetros de um método)
interface PedidoCardProps {
  pedido: {
    id: number;
    cliente: string;
    descricao: string;
    status: string;
  };
  aoFinalizar: (id: number, novoStatus: string) => void; // Isso é uma função passada como parâmetro
}

export function PedidoCard({ pedido, aoFinalizar }: PedidoCardProps) {
  return (
    <div style={styles.card}>
      <div style={styles.cardHeader}>
        <strong>Pedido #{pedido.id}</strong>
        <span style={styles.badge}>{pedido.status}</span>
      </div>

      <div style={styles.cardBody}>
        <p><strong>Cliente:</strong> {pedido.cliente}</p>
        <p style={styles.descricao}>📝 {pedido.descricao}</p>
      </div>

      <button 
        onClick={() => aoFinalizar(pedido.id, 'PRONTO')} 
        style={styles.buttonAcao}
      >
        ✅ Marcar como Pronto
      </button>
    </div>
  );
}

// Estilos específicos do Card (tirei do App.tsx e trouxe para cá)
const styles = {
  card: {
    backgroundColor: '#1e1e1e',
    border: '1px solid #333',
    borderRadius: '10px',
    padding: '15px',
    width: '250px',
    display: 'flex',
    flexDirection: 'column' as const,
    justifyContent: 'space-between',
  },
  cardHeader: {
    display: 'flex',
    justifyContent: 'space-between',
    marginBottom: '10px',
  },
  badge: {
    backgroundColor: '#f39c12',
    padding: '2px 6px',
    borderRadius: '4px',
    fontSize: '0.7rem',
    color: '#000',
    fontWeight: 'bold' as const,
  },
  cardBody: {
    marginBottom: '15px',
  },
  descricao: {
    fontSize: '0.9rem',
    color: '#bbb',
  },
  buttonAcao: {
    backgroundColor: '#27ae60',
    color: '#fff',
    border: 'none',
    padding: '10px',
    borderRadius: '5px',
    cursor: 'pointer',
    fontWeight: 'bold' as const,
  }
};