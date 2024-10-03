CREATE TABLE IF NOT EXISTS projects_table
(
    project_id BIGSERIAL PRIMARY KEY ,
    project_name  VARCHAR(100) NOT NULL ,
    project_description VARCHAR(254) NOT NULL ,
);

GRANT ALL PRIVILEGES ON DATABASE projects_table TO admin;