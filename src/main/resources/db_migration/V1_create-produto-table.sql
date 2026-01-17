CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE_TABLE(
    id UUID DEFAULT get_random_uuid() PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade INTEGER NOT NULL,
    valorCompra DECIMAL(precision, scale) NOT NULL,
    valorVenda DECIMAL(precision, scale) NOT NULL,
    data TIMESTAMP

);