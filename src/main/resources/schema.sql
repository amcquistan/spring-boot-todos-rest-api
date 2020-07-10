
CREATE TABLE IF NOT EXISTS users (
    id          SERIAL  PRIMARY KEY,
    username    VARCHAR(50) NOT NULL UNIQUE,
    password    VARCHAR(256) NOT NULL,
    enabled     BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS todos (
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description TEXT NULL,
    status      VARCHAR(50) NOT NULL,
    user_id     INTEGER REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS authorities (
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER NOT NULL REFERENCES users(id),
    authority   VARCHAR(50) NOT NULL
);
