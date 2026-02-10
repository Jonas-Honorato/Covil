# 🍔 Hamburgueria API - Gestão de Pedidos e Stock

Esta é uma API REST desenvolvida em **Spring Boot** para gerir as operações de uma hamburgueria. O sistema permite o controlo total desde o stock de ingredientes até ao fecho de caixa diário através de um dashboard.

---

## 🚀 Funcionalidades Principais

- **Gestão de Stock**: Registo de ingredientes com validação de quantidades.
- **Receitas Inteligentes**: Associação de ingredientes a produtos. Ao vender um hambúrguer, o sistema abate automaticamente as quantidades exatas do stock.
- **Fluxo de Pedidos**: Sistema de pedidos com estados (Recebido, Em Preparação, Pronto, Entregue e Cancelado).
- **Segurança de Negócio**: Impedimento de pedidos caso não haja stock suficiente, utilizando exceções personalizadas.
- **Dashboard de Gestão**: Endpoint que resume o faturamento total (excluindo cancelados), total de pedidos e alerta de itens com stock baixo.

## 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

---

## 📋 Como Executar o Projeto

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/teu-utilizador/hamburgueria-api.git](https://github.com/teu-utilizador/hamburgueria-api.git)