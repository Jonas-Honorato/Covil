// 1. IMPORTS (As "importações" do Java)
import { useEffect, useState } from 'react'
import axios from 'axios'
import { PedidoCard } from './components/PedidoCard'
import { Dashboard } from './components/Dashboard' // Importando o novo Dashboard

// 2. INTERFACE (O seu DTO do Pedido)
interface Pedido {
  id: number;
  cliente: string;
  descricao: string;
  status: string;
}

function App() {
  // --- ESTADOS (Variáveis de controle da tela) ---
  const [pedidos, setPedidos] = useState<Pedido[]>([]);
  
  // Esse estado controla qual aba o usuário está vendo: 'monitor' ou 'dashboard'
  const [abaAtiva, setAbaAtiva] = useState<'monitor' | 'dashboard'>('monitor');

  // --- LÓGICA DE API (Igual ao que já tínhamos) ---
  const fetchPedidos = async () => {
    try {
      const response = await axios.get('http://localhost:8080/pedidos/fila');
      setPedidos(response.data);
    } catch (error) {
      console.error("Erro ao buscar pedidos:", error);
    }
  };

  const alterarStatus = async (id: number, novoStatus: string) => {
  try {
    // Trocamos .put por .patch para dar match com o seu Java
    await axios.patch(`http://localhost:8080/pedidos/${id}/status`, { 
      novoStatus: novoStatus // O nome aqui deve ser 'novoStatus' para bater com seu AtualizarStatusDTO
    });
    fetchPedidos();
  } catch (error) {
    console.error("Erro ao atualizar status:", error);
  }
};

  useEffect(() => {
    fetchPedidos();
    const timer = setInterval(fetchPedidos, 5000);
    return () => clearInterval(timer);
  }, []);

  // --- O QUE APARECE NA TELA (JSX) ---
  return (
    <div style={styles.container}>
      
      {/* 1. BARRA DE NAVEGAÇÃO (As abas do topo) */}
      <nav style={styles.nav}>
        <button 
          onClick={() => setAbaAtiva('monitor')} 
          style={abaAtiva === 'monitor' ? styles.btnAtivo : styles.btnNav}
        >
          🖥️ Monitor de Cozinha
        </button>
        <button 
          onClick={() => setAbaAtiva('dashboard')} 
          style={abaAtiva === 'dashboard' ? styles.btnAtivo : styles.btnNav}
        >
          📊 Dashboard Gestão
        </button>
      </nav>

      {/* 2. LÓGICA DE TROCA DE TELAS (O "if/else" visual) */}
      {abaAtiva === 'monitor' ? (
        // TELA DO MONITOR
        <>
          <header style={styles.header}>
            <h1>🍔 Covil Burger - Painel do Chapeiro</h1>
            <button onClick={fetchPedidos} style={styles.buttonAtualizar}>
              🔄 Atualizar Fila
            </button>
          </header>
          <hr />
          <div style={styles.grid}>
            {pedidos.length === 0 ? (
              <p>Nenhum pedido na fila...</p>
            ) : (
              pedidos.map((pedido) => (
                <PedidoCard key={pedido.id} pedido={pedido} aoFinalizar={alterarStatus} />
              ))
            )}
          </div>
        </>
      ) : (
        // TELA DO DASHBOARD (Quando abaAtiva não for 'monitor')
        <Dashboard />
      )}

    </div>
  )
}

// 3. ESTILOS (A "decoração" da tela)
const styles = {
  container: {
    fontFamily: 'Arial, sans-serif',
    padding: '20px',
    backgroundColor: '#121212',
    color: '#fff',
    minHeight: '100vh',
  },
  nav: {
    display: 'flex',
    gap: '10px',
    marginBottom: '30px',
    borderBottom: '1px solid #444',
    paddingBottom: '10px'
  },
  btnNav: {
    backgroundColor: 'transparent',
    color: '#fff',
    border: 'none',
    cursor: 'pointer',
    padding: '10px 20px',
    fontSize: '1rem'
  },
  btnAtivo: {
    backgroundColor: '#ffcc00',
    color: '#000',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
    padding: '10px 20px',
    fontWeight: 'bold' as const,
    fontSize: '1rem'
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  buttonAtualizar: {
    padding: '10px',
    backgroundColor: '#333',
    color: '#fff',
    border: '1px solid #555',
    borderRadius: '5px',
    cursor: 'pointer',
  },
  grid: {
    display: 'flex',
    gap: '20px',
    flexWrap: 'wrap' as const,
    marginTop: '20px',
  },
};

export default App