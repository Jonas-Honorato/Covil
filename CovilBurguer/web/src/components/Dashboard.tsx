import { useEffect, useState } from 'react';
import axios from 'axios';

interface DadosDashboard {
  totalVendas: number;
  pedidosConcluidos: number;
  itemMaisVendido: string;
}

export function Dashboard() {
  const [dados, setDados] = useState<DadosDashboard | null>(null);

  useEffect(() => {
    // 1. DADOS SIMULADOS (Enquanto o Java não fica pronto)
    const dadosSimulados = {
      totalVendas: 1540.50,
      pedidosConcluidos: 42,
      itemMaisVendido: "X-Monster Burger"
    };

    // Simulando um pequeno atraso de rede (como se fosse o Java respondendo)
    setTimeout(() => {
      setDados(dadosSimulados);
    }, 500);

    /* QUANDO O SEU JAVA ESTIVER PRONTO, 
       VOCÊ VAI USAR ESTE CÓDIGO ABAIXO E APAGAR O DE CIMA:

       axios.get('http://localhost:8080/dashboard/resumo')
         .then(res => setDados(res.data))
         .catch(err => console.error("Erro no dashboard", err));
    */
  }, []);

  return (
    <div style={styles.container}>
      <h2>📊 Relatório de Gestão (Covil Burger)</h2>
      
      <div style={styles.grid}>
        <div style={styles.card}>
          <h3 style={styles.label}>Faturamento Total</h3>
          <p style={styles.valor}>R$ {dados?.totalVendas.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}</p>
        </div>

        <div style={styles.card}>
          <h3 style={styles.label}>Pedidos Entregues</h3>
          <p style={styles.valor}>{dados?.pedidosConcluidos}</p>
        </div>

        <div style={styles.card}>
          <h3 style={styles.label}>Burger Campeão</h3>
          <p style={styles.valor}>{dados?.itemMaisVendido}</p>
        </div>
      </div>

      <div style={styles.alertaBack}>
        ⚠️ Exibindo dados simulados. Implemente o endpoint <code>/dashboard/resumo</code> no seu Spring Boot.
      </div>
    </div>
  );
}

const styles = {
  container: { padding: '20px' },
  grid: { 
    display: 'flex', 
    gap: '20px', 
    marginTop: '20px',
    flexWrap: 'wrap' as const 
  },
  card: { 
    backgroundColor: '#1e1e1e', 
    padding: '30px', 
    borderRadius: '12px', 
    flex: '1 1 250px', 
    textAlign: 'center' as const,
    border: '1px solid #333',
    boxShadow: '0 4px 6px rgba(0,0,0,0.3)'
  },
  label: { fontSize: '0.9rem', color: '#888', marginBottom: '10px', textTransform: 'uppercase' as const },
  valor: { fontSize: '2.2rem', color: '#ffcc00', fontWeight: 'bold' },
  alertaBack: {
    marginTop: '40px',
    padding: '15px',
    backgroundColor: '#332b00',
    color: '#ffcc00',
    borderRadius: '8px',
    border: '1px solid #665500',
    fontSize: '0.9rem'
  }
};