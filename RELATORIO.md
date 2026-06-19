# Relatório curto — Catálogo API

## 1. Descrição do sistema

O projeto é uma API REST para cadastro de produtos e categorias. O domínio foi escolhido por ser simples e permitir demonstrar uma estrutura real de backend com cadastro, listagem, atualização, remoção e persistência em banco de dados.

## 2. Arquitetura

A aplicação segue uma arquitetura em camadas:

- **Controller:** recebe as requisições HTTP e define os endpoints REST.
- **Service:** concentra as regras de negócio, como buscar categoria antes de criar produto.
- **Repository:** faz a comunicação com o banco de dados usando Spring Data JPA.
- **Model:** representa as entidades do banco.
- **DTO:** define os dados recebidos nas requisições.
- **Exception Handler:** padroniza respostas de erro, como 404 e 400.

O cliente conversa com a API usando HTTP e JSON. A API processa os dados, acessa o PostgreSQL e devolve respostas também em JSON.

## 3. Modelo de dados

O sistema possui duas entidades principais:

### Categoria

Campos:

- `id`: identificador único.
- `nome`: nome da categoria.

### Produto

Campos:

- `id`: identificador único.
- `nome`: nome do produto.
- `preco`: preço do produto.
- `estoque`: quantidade disponível.
- `categoria`: relacionamento com uma categoria.

Relacionamento:

- Uma categoria pode ter vários produtos.
- Um produto pertence a uma categoria.

## 4. Endpoints REST

### Categorias

- `GET /categorias` — lista todas as categorias.
- `GET /categorias/{id}` — busca uma categoria por ID.
- `POST /categorias` — cria uma nova categoria.
- `PUT /categorias/{id}` — atualiza uma categoria existente.
- `DELETE /categorias/{id}` — remove uma categoria.

### Produtos

- `GET /produtos` — lista todos os produtos.
- `GET /produtos/{id}` — busca um produto por ID.
- `POST /produtos` — cria um novo produto.
- `PUT /produtos/{id}` — atualiza um produto existente.
- `DELETE /produtos/{id}` — remove um produto.

## 5. Códigos HTTP utilizados

- `200 OK`: usado em consultas e atualizações bem-sucedidas.
- `201 Created`: usado quando um recurso é criado.
- `204 No Content`: usado quando um recurso é removido.
- `400 Bad Request`: usado quando os dados enviados são inválidos.
- `404 Not Found`: usado quando produto ou categoria não existem.

## 6. Docker

A aplicação foi empacotada com Docker. O `Dockerfile` constrói a aplicação Java e gera uma imagem executável. O `docker-compose.yml` sobe dois containers:

- `api`: aplicação Spring Boot.
- `db`: banco PostgreSQL.

Com isso, o projeto pode rodar da mesma forma em qualquer máquina que tenha Docker instalado.

## 7. Integração contínua

O pipeline foi configurado com GitHub Actions no arquivo `.github/workflows/ci.yml`. A cada envio de código para a branch `main`, o pipeline:

1. Baixa o código do repositório.
2. Configura o Java 17.
3. Executa `mvn clean test`.

Isso ajuda a garantir que o projeto continua compilando e que os testes automatizados continuam passando.

## 8. Plano de implantação em nuvem

Para implantar em nuvem, eu usaria uma VPS simples, como AWS EC2, DigitalOcean, Render ou Railway. O processo seria:

1. Enviar o código para o GitHub.
2. Configurar variáveis de ambiente do banco em produção.
3. Subir a aplicação com Docker Compose ou usar uma plataforma que aceite container.
4. Usar um banco PostgreSQL gerenciado ou um container PostgreSQL com volume persistente.
5. Expor a API por uma URL pública.

Em um ambiente mais profissional, a imagem Docker poderia ser publicada em um registry, como Docker Hub ou GitHub Container Registry, e depois implantada automaticamente no servidor.

## 9. Monitoramento, logs e métricas

Em produção, os logs ajudam a entender erros e acompanhar o comportamento da aplicação. Por exemplo, se uma requisição falhar, os logs mostram onde ocorreu o problema.

As métricas ajudam a acompanhar a saúde do sistema, como uso de CPU, memória, quantidade de requisições, tempo de resposta e erros HTTP. Ferramentas como Prometheus, Grafana, logs do Docker ou painel da própria nuvem poderiam ser usadas para esse acompanhamento.
