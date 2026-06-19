# Catálogo API

API REST simples para cadastro de categorias e produtos.

## Tecnologias

- Java 17
- Spring Boot
- PostgreSQL
- Docker e Docker Compose
- GitHub Actions

## Como executar do zero

### 1. Pré-requisitos

Instale:

- Git
- Docker Desktop

### 2. Baixar o projeto

```bash
git clone https://github.com/Augusto-code-dev/catalogo-api.git
cd catalogo-api
```

### 3. Subir API e banco

```bash
docker compose up --build
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Endpoints principais

### Categorias

Criar categoria:

```bash
curl -X POST http://localhost:8080/categorias \
-H "Content-Type: application/json" \
-d '{"nome":"Eletrônicos"}'
```

Listar categorias:

```bash
curl http://localhost:8080/categorias
```

Buscar categoria por ID:

```bash
curl http://localhost:8080/categorias/1
```

Atualizar categoria:

```bash
curl -X PUT http://localhost:8080/categorias/1 \
-H "Content-Type: application/json" \
-d '{"nome":"Informática"}'
```

Deletar categoria:

```bash
curl -X DELETE http://localhost:8080/categorias/1
```

### Produtos

Criar produto:

```bash
curl -X POST http://localhost:8080/produtos \
-H "Content-Type: application/json" \
-d '{"nome":"Mouse Gamer","preco":99.90,"estoque":10,"categoriaId":1}'
```

Listar produtos:

```bash
curl http://localhost:8080/produtos
```

Buscar produto por ID:

```bash
curl http://localhost:8080/produtos/1
```

Atualizar produto:

```bash
curl -X PUT http://localhost:8080/produtos/1 \
-H "Content-Type: application/json" \
-d '{"nome":"Mouse Gamer RGB","preco":119.90,"estoque":8,"categoriaId":1}'
```

Deletar produto:

```bash
curl -X DELETE http://localhost:8080/produtos/1
```

## Rodar testes

```bash
mvn clean test
```

## Pipeline CI

O arquivo `.github/workflows/ci.yml` executa automaticamente a cada push na branch `main`.
Ele baixa o código, configura Java 17 e roda:

```bash
mvn clean test
```
