CREATE TABLE tarefas (
    id VARCHAR(255) PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL,
    estado VARCHAR(255),
    descricao VARCHAR(255)
);