# 📦 Sistema de Estoque - CodexSystem

Este projeto é uma API REST robusta para gerenciamento de estoque, focada em práticas modernas de desenvolvimento Java. O sistema permite o controle de produtos e a gestão de usuários com autenticação segura.

## 🛠️ Tecnologias e Conceitos Aplicados

* **Java 17 & Spring Boot 3**: Núcleo do projeto.
* **Spring Security & JWT**: Autenticação stateless com tokens para proteção de rotas.
* **Padrão DTO (Data Transfer Object)**: Utilizado para desacoplar a camada de persistência da camada de apresentação e proteger dados sensíveis.
* **Spring Data JPA**: Abstração de banco de dados e persistência.
* **Lombok**: Redução de código boilerplate.

## 🏗️ Arquitetura do Projeto

O projeto segue uma estrutura de camadas bem definida:
1.  **Model**: Entidades que representam as tabelas do banco de dados (Ex: `Usuario`, `Produto`).
2.  **DTO**: Objetos de transferência para entrada de dados (`LoginRequestDTO`) e saída (`UsuarioResponseDTO`).
3.  **Repository**: Interfaces para comunicação com o banco de dados.
4.  **Service**: Camada de lógica de negócio e conversão Entidade ↔ DTO.
5.  **Controller**: Endpoints da API que gerenciam as requisições HTTP.



## 🔐 Segurança

A segurança foi implementada utilizando:
* **BCrypt**: Para criptografia de senhas antes de salvar no banco.
* **JWT Auth Filter**: Um filtro personalizado (`OncePerRequestFilter`) que valida o token em cada requisição.
* **Proteção de Endpoints**: Apenas a rota `/auth/**` é pública; todas as outras exigem um token válido.

## 🚀 Como testar a API

### 1. Registro de Usuário
Envie um `POST` para `/auth/register` com o corpo:
```json
{
  "username": "admin",
  "password": "123"
}
```


A resposta não incluirá a senha, graças ao uso do UsuarioResponseDTO.


2. Login
   Envie um POST para /auth/login. O sistema retornará um token.

3. Gestão de Produtos
   Utilize o token no Header Authorization como Bearer <seu_token> para acessar:

GET /produtos: Lista todos os itens.

POST /produtos/adiciona: Cadastra novo produto.

DELETE /produtos/{id}: Remove um produto.

