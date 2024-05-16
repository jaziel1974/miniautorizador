CREATE TABLE IF NOT EXISTS cards (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    numero_cartao VARCHAR(255) NOT NULL,
    senha VARCHAR(255),
    CONSTRAINT unique_card UNIQUE (numero_cartao)
);