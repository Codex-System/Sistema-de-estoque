# 📦 Sistema de Gestão de Estoque Multi-Tenant (Codex System)

Sistema de back-end robusto para gestão de estoques e PDV (Ponto de Venda), desenvolvido com **Spring Boot 3**. O projeto utiliza uma arquitetura **Multi-Tenant**, garantindo isolamento total de dados entre diferentes lojas cadastradas.

## 🚀 Principais Funcionalidades

* **Multi-Tenancy:** Separação lógica de dados. Usuários da Loja A nunca acessam dados da Loja B.
* **Autenticação JWT:** Sistema de segurança com Access Token (15 min) e Refresh Token (24h).
* **Gestão de Perfis (RBAC):** * `ADMIN`: Pode cadastrar produtos e criar novos usuários vendedores.
   * `USER`: Pode consultar estoque e realizar vendas.
* **PDV Simplificado:** Vendas baseadas em código de barras, facilitando o uso com leitores físicos.
* **Baixa de Estoque Automática:** Validação de saldo e atualização em tempo real no ato da venda.

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Security** (Proteção de rotas e filtros)
* **JWT (auth0)** (Geração e validação de tokens)
* **Spring Data JPA** (Persistência de dados)
* **Lombok** (Produtividade no código)
* **Bean Validation** (Validação de entradas)

---

## 🏗️ Como Rodar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Codex-System/Sistema-de-estoque.git
    ```
2.  **Configuração de Banco:** No arquivo `src/main/resources/application.properties`, configure as credenciais do seu banco de dados (MySQL/PostgreSQL/H2).
3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```

---

## 📖 Guia de API (Endpoints)

### 1. Cadastro e Login
| Método | Rota | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| POST | `/cadastro/loja` | Cria uma nova loja e um Admin | Público |
| POST | `/auth/login` | Autentica e gera os tokens | Público |
| POST | `/auth/refresh` | Gera novo Access Token via Refresh | Público |

### 2. Gestão de Produtos
| Método | Rota | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| POST | `/produtos` | Cadastra um produto na sua loja | Admin |
| GET | `/produtos` | Lista todos os produtos da loja | Admin/User |
| DELETE | `/produtos/{id}`| Remove um produto específico | Admin |

### 3. Operação de Venda (PDV)
| Método | Rota | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| GET | `/pdv/produto/{code}`| Consulta produto por código de barras | Admin/User |
| POST | `/pdv/venda` | Realiza venda por código de barras | Admin/User |

### 4. Gestão de Usuários
| Método | Rota | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| POST | `/usuarios` | Cria um vendedor vinculado à sua loja | Admin |

---

## 🔒 Segurança

O sistema utiliza um `SecurityFilter` que intercepta cada requisição, extrai o token JWT e identifica o usuário e a loja a qual ele pertence.



> **Nota:** Nas rotas protegidas, o `loja_id` é injetado automaticamente pelo `UsuarioService` a partir do contexto de autenticação, impedindo que um usuário tente forjar o ID de outra loja no corpo do JSON.


---

## 👨‍💻 Autor

Desenvolvido por **Matheus Carlos de Almeida**
https://github.com/Matheuszy

---

## 📄 Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.