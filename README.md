# Sistema de Controle de Estoque

## 📖 Sobre o Projeto

O Sistema de Controle de Estoque é uma API REST desenvolvida com Spring Boot para gerenciar produtos e controlar movimentações de estoque.

Além das operações básicas de CRUD, o sistema permite realizar entradas e saídas de produtos, registrando automaticamente um histórico de movimentações para auditoria e acompanhamento.

Este projeto foi desenvolvido com foco no aprendizado de conceitos fundamentais de desenvolvimento backend, como arquitetura em camadas, persistência de dados, relacionamentos entre entidades e regras de negócio.

---

## 👨‍💻 Autor
Leandro G. G. Eusébio

Projeto desenvolvido para fins de estudo e prática de desenvolvimento backend com Java e Spring Boot.


## 🚀 Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Insomnia (testes da API)

---

## 📂 Estrutura do Projeto

```text
src
├── controller
├── service
├── repository
├── model
├── dto
├── GlobalHandlerException
```

---

## 📦 Funcionalidades

### Produtos

* Cadastrar produto
* Listar todos os produtos
* Buscar produto por ID
* Atualizar produto
* Excluir produto

### Controle de Estoque

* Entrada de estoque
* Saída de estoque
* Validação de quantidade maior que zero
* Validação de estoque insuficiente

### Histórico de Movimentações

* Registro automático de entradas
* Registro automático de saídas
* Consulta de movimentações realizadas
* Consulta de movimentação por ID

---

## 🗄️ Modelo de Dados

### Produto

| Campo      | Tipo    |
| ---------- | ------- |
| id         | Long    |
| nome       | String  |
| preco      | Double  |
| quantidade | Integer |

### MovimentacaoEstoque

| Campo      | Tipo          |
| ---------- | ------------- |
| id         | Long          |
| tipo       | String        |
| quantidade | Integer       |
| dataHora   | LocalDateTime |
| produto    | Produto       |

---

## 🔗 Relacionamentos

```text
Produto (1)
    |
    |
    |---- (N) MovimentacaoEstoque
```

Um produto pode possuir várias movimentações de estoque.

---

## 📌 Endpoints

### Produtos

#### Criar Produto

```http
POST /produtos
```

Exemplo:

```json
{
  "nome": "Monitor Gamer",
  "preco": 350.0,
  "quantidade": 15
}
```

---

#### Listar Produtos

```http
GET /produtos
```

---

#### Buscar Produto por ID

```http
GET /produtos/{id}
```

---

#### Atualizar Produto

```http
PUT /produtos/{id}
```

Exemplo:

```json
{
  "nome": "Monitor Gamer",
  "preco": 399.90,
  "quantidade": 20
}
```

---

#### Excluir Produto

```http
DELETE /produtos/{id}
```

---

### Movimentação de Estoque

#### Entrada de Estoque

```http
POST /produtos/{id}/entrada
```

Exemplo:

```json
{
  "quantidade": 5
}
```

---

#### Saída de Estoque

```http
POST /produtos/{id}/saida
```

Exemplo:

```json
{
  "quantidade": 3
}
```

---

### Histórico de Movimentações

#### Listar Movimentações

```http
GET /movimentacoes
```

---

#### Buscar Movimentação por ID

```http
GET /movimentacoes/{id}
```

---

## ⚠️ Regras de Negócio

### Quantidade Inválida

Não é permitido movimentar:

```text
Quantidade <= 0
```

Exemplo de retorno:

```json
{
  "status": 400,
  "erro": "A quantidade deve ser maior que zero!"
}
```

---

### Estoque Insuficiente

Não é permitido retirar mais itens do que existem em estoque.

Exemplo:

```text
Estoque atual: 10
Saída solicitada: 15
```

Retorno:

```json
{
  "status": 400,
  "erro": "Quantidade insuficiente em estoque!"
}
```

---

## 🎯 Objetivos de Aprendizado

Durante o desenvolvimento deste projeto foram praticados os seguintes conceitos:

* Criação de APIs REST
* Arquitetura em camadas
* CRUD completo
* DTOs
* Tratamento global de exceções
* Relacionamentos JPA (@OneToMany e @ManyToOne)
* Regras de negócio
* Persistência de dados com MySQL
* Manipulação de datas com LocalDateTime

---

