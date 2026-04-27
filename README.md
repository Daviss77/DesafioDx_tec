# ⚽ Desafio Técnico - Sistema de Escalação de Times

## 📌 Descrição

Este projeto foi desenvolvido como solução para um desafio técnico com foco na construção de uma **API REST utilizando Spring Boot**.

A aplicação permite o gerenciamento de jogadores (integrantes) e a montagem de times, incluindo regras de negócio para análise de dados ao longo do tempo.

Também foi desenvolvida uma interface web simples para facilitar a interação com a API.

---

## 🚀 Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database (em memória)
* Maven
* HTML, CSS, JavaScript
* Bootstrap

---

## 🧱 Arquitetura do projeto

O projeto segue uma arquitetura em camadas:

### 📦 `controller`

Responsável por expor os endpoints da API.

* Recebe requisições HTTP
* Converte DTO → entidade
* Retorna respostas formatadas

---

### 📦 `service`

Camada de regras de negócio.

Responsável por:

* Criar times com associação de integrantes
* Aplicar regras analíticas
* Processar dados
* Converter entidades em DTOs

---

### 📦 `repository`

Interfaces que estendem `JpaRepository`.

* Responsáveis pelo acesso ao banco de dados

---

### 📦 `model`

Entidades JPA:

* `Time`
* `Integrante`
* `ComposicaoTime`

---

### 📦 `dto`

Objetos de transferência de dados:

* `TimeDto` → entrada (criação de time)
* `TimeResponseDto` → saída formatada

---

### 🧪 Testes

Foram implementados testes unitários com foco na camada de **service**, garantindo:

* Correta criação de times
* Associação de integrantes
* Funcionamento das regras de negócio

---

## ▶️ Como executar o projeto

### ✅ Pré-requisitos

* Java 17+
* Maven

---

### 🚀 Executando

```bash
git clone https://github.com/Daviss77/DesafioDx_tec.git
cd DesafioDx_tec
mvn spring-boot:run
```

---

## 🌐 Acesso

Após iniciar a aplicação:

```
http://localhost:8080
```

---

## 🗄️ Banco de dados

O projeto utiliza o **H2 Database (em memória)**:

* Não requer instalação
* Os dados são reiniciados ao subir a aplicação

Console do banco:

```
http://localhost:8080/h2-console
```

---

## 🔗 Endpoints da API

### 👤 Integrantes

#### ➕ Criar integrante

```
POST /integrantes
```

Body:

```json
{
  "nome": "Neymar",
  "funcao": "Atacante"
}
```

---

#### 📋 Listar integrantes

```
GET /integrantes
```

---

## ⚽ Times

### ➕ Criar time

```
POST /times
```

Body:

```json
{
  "nomeDoClube": "Palmeiras",
  "data": "2026-04-26",
  "idsIntegrantes": [1, 2, 3]
}
```

---

### 📋 Listar todos os times

```
GET /times
```

---

### 🔍 Buscar time por ID

```
GET /times/{id}
```

---

### ❌ Deletar time

```
DELETE /times/{id}
```

---

## 📊 Consultas (Regras de Negócio)

### 🔹 1. Time da data

```
GET /api/time-da-data?data=2021-01-15
```

✔️ Retorna o time correspondente à data informada

---

### 🔹 2. Integrantes do time mais recorrente

```
GET /api/integrantes-time-mais-recorrente?dataInicial=2021-01-01&dataFinal=2021-12-31
```

✔️ Retorna lista de nomes dos integrantes

---

### 🔹 3. Integrante mais utilizado

```
GET /api/integrante-mais-usado?dataInicial=2021-01-01&dataFinal=2021-12-31
```

✔️ Retorna o nome do integrante mais utilizado

---

### 🔹 4. Função mais recorrente

```
GET /api/funcao-mais-recorrente
```

✔️ Pode ser executado sem parâmetros

---

### 🔹 5. Clube mais recorrente

```
GET /api/clube-mais-recorrente
```

---

### 🔹 6. Contagem de clubes

```
GET /api/contagem-clubes
```

✔️ Exemplo de retorno:

```json
{
  "Falcons": 2,
  "FURIA": 1
}
```

---

### 🔹 7. Contagem por função

```
GET /api/contagem-funcoes
```

✔️ Exemplo de retorno:

```json
{
  "Atacante": 3,
  "Defensor": 2
}
```

---

## 🧪 Testando com Postman

Fluxo recomendado:

1. Criar integrantes (`POST /integrantes`)
2. Listar integrantes (`GET /integrantes`)
3. Criar times (`POST /times`)
4. Executar consultas analíticas (`/api/...`)
5. Validar os resultados

---

## 💻 Interface Web

Rotas disponíveis:

* `/` → Página inicial
* `/cadastro-integrante` → Cadastro de jogadores
* `/montar-time` → Montagem de times

---

## 🎯 Decisões técnicas

* Uso de DTOs para desacoplamento
* Separação em camadas (Controller, Service, Repository)
* Separação dos testes unitários por responsabilidade (Fácil legibilidade)
* Banco em memória para facilitar execução
* Frontend simples com foco na API

---

## 🚀 Melhorias futuras

* Implementar autenticação (Spring Security)
* Utilizar banco real (MySQL/PostgreSQL)
* Melhorar interface (ex: escalação visual)
* Adicionar validações mais robustas

---

## 👨‍💻 Autor

Desenvolvido por **Davi Santana**
