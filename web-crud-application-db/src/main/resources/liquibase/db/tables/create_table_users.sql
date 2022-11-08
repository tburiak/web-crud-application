CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    gender CHAR(1) NOT NULL
    );