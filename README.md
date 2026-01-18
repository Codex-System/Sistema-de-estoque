📦 Sistema de Gestão de Estoque Multi-Tenant
Este é um sistema de back-end robusto para gestão de estoques e PDV (Ponto de Venda), desenvolvido com Spring Boot 3. A arquitetura é Multi-Tenant, o que significa que múltiplas lojas podem utilizar o sistema simultaneamente com isolamento total de dados.

🚀 Principais Funcionalidades
Multi-Tenancy: Cada loja possui seus próprios produtos e usuários. Um usuário de uma loja nunca acessa os dados de outra.

Autenticação JWT: Segurança baseada em tokens com suporte a Access Token e Refresh Token.

Gestão de Perfis (Roles): Diferenciação entre ADMIN (dono da loja) e USER (vendedores).

PDV Integrado: Venda simplificada através de leitura de código de barras.

Baixa Automática: Controle de estoque em tempo real a cada venda realizada.

🛠️ Tecnologias Utilizadas
Java 17

Spring Boot 3

Spring Security (Autenticação e Autorização)

JWT (Auth0)

Spring Data JPA

PostgreSQL / MySQL (ou H2 para testes)

Lombok

🏗️ Como Rodar o Projeto
Clone o repositório:

Bash

git clone https://github.com/Codex-System/Sistema-de-estoque.git
Configure o banco de dados: No arquivo src/main/resources/application.properties, ajuste as credenciais do seu banco de dados.

Compile e rode:

Bash

mvn spring-boot:run
📖 Guia de API (Principais Rotas)
1. Cadastro e Autenticação
   POST /cadastro/loja: Cria uma nova loja e o usuário administrador.

POST /auth/login: Autentica e retorna os tokens JWT.

POST /auth/refresh: Renova o Access Token expirado.

2. Gestão de Estoque (Requer Login)
   GET /produtos: Lista todos os produtos da sua loja.

POST /produtos: Cadastra um novo produto (Vínculo automático com sua loja).

GET /produtos/{id}: Busca um produto pelo UUID.

3. Operação de PDV
   GET /pdv/produto/{codigoBarras}: Consulta informações do produto pelo código de barras.

POST /pdv/venda: Realiza a baixa no estoque enviando o codigoBarras e a quantidade.

4. Gestão de Usuários (Apenas ADMIN)
   POST /usuarios: O administrador da loja cria novos usuários (vendedores) para sua unidade.

🔒 Segurança e Fluxo de Dados
O sistema utiliza o contexto de segurança do Spring para injetar a loja do usuário em cada operação. Nunca é necessário passar o ID da Loja manualmente nas requisições de produto ou venda, pois o UsuarioService extrai essa informação diretamente do Token JWT validado.