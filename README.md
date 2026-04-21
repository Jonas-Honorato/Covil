# 🍔 Covil - Sistema de Gestão de Hamburgueria

O **Covil** é uma API REST desenvolvida em Java com Spring Boot para gerenciar o fluxo de pedidos de uma hamburgueria, focando no controle real de insumos.

## 🚀 Diferenciais do Projeto
- **Baixa de Estoque por Insumo:** Ao vender um hambúrguer, o sistema desconta automaticamente cada ingrediente (pão, carne, queijo) da ficha técnica.
- **Integridade de Dados:** Uso de transações (`@Transactional`) para garantir que o estoque não fique inconsistente em caso de erro.
- **Validação de Negócio:** Impede a venda de produtos sem estoque suficiente e valida dados de entrada.
- **Arquitetura Limpa:** Separação de responsabilidades entre DTOs, Controllers, Services e Repositories.

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL** (Banco de dados)
- **Lombok** (Produtividade)
- **Bean Validation** (Segurança de dados)

## 📌 Principais Endpoints

### Ingredientes
- `POST /ingredientes`: Cadastra um novo insumo.
- `GET /ingredientes`: Lista o estoque atual.

### Produtos
- `POST /produtos`: Cria um produto vinculando ingredientes (Ficha Técnica).

### Pedidos
- `POST /pedidos`: Realiza a venda e abate o estoque.
- `PUT /pedidos/{id}/cancelar`: Cancela o pedido e estorna os insumos.

## 🏁 Como Rodar o Projeto
1. Clone o repositório.
2. Configure o banco de dados no `application.properties`.
3. Execute `./mvnw spring-boot:run`.