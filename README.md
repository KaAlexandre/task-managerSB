# Task Manager

Um gerenciador de tarefas simples desenvolvido com Spring Boot.

## Pré-requisitos

- **JDK 17+**
- **Maven 3.6+**
- **PostgreSQL** (opcional, para o perfil `postgres`)
- **Docker** instalado
- **Docker Compose** (recomendado para uso com PostgreSQL)

---

## 🚀 Execução do Projeto

### Usando Maven

#### Perfil PostgreSQL (banco de dados persistente)
  
  ```bash```
```
 mvn spring-boot:run -Dspring-boot.run.profiles=postgres
``` 

### Perfil H2 (banco de dados em memória)
  
  ```bash```
``` 
mvn spring-boot:run -Dspring-boot.run.profiles=h2
``` 

### Acesso ao Console H2:

``` 
http://localhost:9090/h2-console
``` 

### Credenciais:
``` 
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: password
```

## 🐳 Usando Docker
### Método Básico
``` bash ```
```
# Construir imagem
docker build -t task-manager:latest .

# Executar container
docker run -p 9090:9090 task-manager:latest
```

  ```bash```
```
docker-compose up
```
### Configuração do Compose:

- Aplicação na porta 9090

- PostgreSQL na porta 5432

- Banco de dados: task_manager

- Usuário/senha: postgres/password

## 🔧 Configurações Técnicas

### Dockerfile
```
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/task-manager-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```
docker-compose.yml
```
version: '3'
services:
  app:
    build: .
    ports: ["9090:9090"]
    environment:
      SPRING_PROFILES_ACTIVE: postgres
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/task_manager
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: password
  db:
    image: postgres:13
    environment:
      POSTGRES_DB: task_manager
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
    ports: ["5432:5432"]
```

### 🐘 Configuração do PostgreSQL

## Crie o Banco de Dados

``` sql.data ```

```
CREATE DATABASE task_manager;
```
## Configure no application.yml
``` yaml ```
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/task_manager
    username: postgres
    password: sua_senha_segura
```
## 🐘 Acessando o PostgreSQL via pgAdmin (**Caso esteja usando o docker**)

### 1. Acesse o pgAdmin
Abra o navegador e acesse:
🔗 http://localhost:5050

### 2. Faça Login
Email: admin@exampl1.com

Senha: Admin@Secure123

### 3. Adicione o Servidor PostgreSQL
Clique em "Add New Server".

Na aba "General", dê um nome ao servidor (ex: Docker PostgreSQL).

Na aba "Connection", preencha:

Host name/address: db (nome do serviço no Docker Compose)

Port: 5432

Maintenance database: task_manager

Username: postgres

Password: password (senha definida no docker-compose.yml)

Clique em "Save".

### 4. Explore o Banco de Dados
Expanda o servidor > Databases > task_manager > Schemas > public > Tables.

Aqui você verá todas as tabelas criadas pela aplicação.


  ## 📡 Endpoints da API

  Criar Tarefa
  
```http```
```
POST /tarefas/adicionar
```
### Corpo:

```json```

```
{
  "nomeTarefa": "Nova Tarefa",
  "descricao": "Descrição da tarefa"
  "estado": "Estado da tarefa"
}
```

### Buscar Tarefa

```http```

```
GET /tarefas/{id}
```

### Listar Todas 

```http```

```
GET /tarefas/listAll
```


### Atualizar Tarefa

```http```
```
PUT /tarefas/tarefa/{id}
```

### Corpo:

```json```
```
{
  "nomeTarefa": "Título Atualizado",
  "descricao": "Nova descrição",
  "estado": "Novo estado"
}
```

### Excluir Tarefa

```http```

```DELETE /tarefas/{id}```


### Excluir Todas

```http```
```
DELETE /tarefas/deleteAll
```
## ⚙️ Variáveis de Ambiente

| Variável                     | Valor Padrão            | Descrição               |
|------------------------------|-------------------------|-------------------------|
| `SPRING_PROFILES_ACTIVE`     | `postgres`              | Perfil ativo do Spring  |
| `SPRING_DATASOURCE_URL`      | `jdbc:h2:mem:task_manager` | URL do banco (H2)    |
| `SPRING_DATASOURCE_URL`      | `jdbc:postgresql://host:port/db` | URL do banco (PG)    |
| `SPRING_DATASOURCE_USERNAME` | `sa` (H2) / `postgres` (PG) | Usuário do banco    |
| `SPRING_DATASOURCE_PASSWORD` | (vazio H2) / `password` (PG) | Senha do banco     |
