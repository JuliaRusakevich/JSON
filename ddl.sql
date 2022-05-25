CREATE DATABASE university;

CREATE SCHEMA olympic_gamer;

CREATE TABLE university.olympic_gamer.student
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(124) NOT NULL,
    age           INTEGER      NOT NULL,
    score         DECIMAL      NOT NULL,
    olympic_gamer BOOLEAN      NOT NULL
);


CREATE TABLE university.olympic_gamer.group
(
    id         SERIAL PRIMARY KEY,
    group_name VARCHAR(124) NOT NULL UNIQUE
);

CREATE TABLE university.olympic_gamer.group_info
(
    group_id   INTEGER REFERENCES university.olympic_gamer.group (id),
    student_id INTEGER REFERENCES university.olympic_gamer.student (id) UNIQUE
);
