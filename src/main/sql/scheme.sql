/*DROP DATABASE IF EXISTS oris;
CREATE DATABASE oris;*/

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(100) UNIQUE NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(200)        NOT NULL,
    role     VARCHAR(10)         NOT NULL
);

CREATE TABLE IF NOT EXISTS categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO categories (name) VALUES ('math');
INSERT INTO categories (name) VALUES ('english');
INSERT INTO categories (name) VALUES ('physics');

CREATE TABLE IF NOT EXISTS questions
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(200) NOT NULL UNIQUE,
    description TEXT,
    id_user     INT,
    id_category INT,
    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (id_category) REFERENCES categories (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS answers
(
    id          SERIAL PRIMARY KEY,
    id_question INT,
    content     TEXT NOT NULL,
    id_user     INT,
    FOREIGN KEY (id_question) REFERENCES questions (id) ON DELETE CASCADE,
    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS favourites_answers
(
    id_user   INT,
    id_answer INT,
    PRIMARY KEY (id_user, id_answer),
    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (id_answer) REFERENCES answers (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rating (
    id_user INT,
    id_answer INT,
    PRIMARY KEY (id_user, id_answer),
    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (id_answer) REFERENCES answers (id) ON DELETE CASCADE,
    is_liked BOOLEAN NOT NULL
);
