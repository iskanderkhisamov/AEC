DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    firstName VARCHAR(50),
    secondName VARCHAR(100),
    middleName VARCHAR(100),
    group INT,
    status INT
);

DROP TABLE IF EXISTS groups;
CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    groups VARCHAR(20)
);

DROP TABLE IF EXISTS statuses;
CREATE TABLE statuses (
    id SERIAL PRIMARY KEY,
    status VARCHAR(20)
);

DROP TABLE IF EXISTS questions;
CREATE TABLE questions (
    id SERIAL PRIMARY KEY,
    question TEXT,
    answer INT
);

DROP TABLE IF EXISTS answers;
CREATE TABLE answers (
    id SERIAL PRIMARY KEY,
    answer TEXT
);

ALTER TABLE users ADD CONSTRAINT group_fk FOREIGN KEY (group) REFERENCES groups (id);
ALTER TABLE users ADD CONSTRAINT status_fk FOREIGN KEY (status) REFERENCES statuses (id);
ALTER TABLE questions ADD CONSTRAINT answer_fk FOREIGN KEY (answer) REFERENCES answers (id);
