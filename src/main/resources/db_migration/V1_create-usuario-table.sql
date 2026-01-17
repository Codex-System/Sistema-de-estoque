CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE_TABLE(
    id UUID DEFAULT get_random_uuid() PRIMARY KEY,
    username VARCHAR(30) UNIQUE,
    password VARCHAR(150),
    role TEXT NOT NULL;

);