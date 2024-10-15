CREATE TABLE IF NOT EXISTS roles
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO roles(id, name) VALUES
(1, 'Head Director'),
(2, 'Administrator'),
(3, 'Project Manager'),
(4, 'Analyst'),
(5, 'Developer'),
(6, 'Registered  User');

GRANT ALL ON TABLE roles TO postgres WITH GRANT OPTION;