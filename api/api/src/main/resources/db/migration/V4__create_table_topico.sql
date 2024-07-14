CREATE TABLE topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    estado_id BIGINT,
    autor_id BIGINT,
    curso_id BIGINT,
    FOREIGN KEY (estado_id) REFERENCES estado(id),
    FOREIGN KEY (autor_id) REFERENCES autor(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);
