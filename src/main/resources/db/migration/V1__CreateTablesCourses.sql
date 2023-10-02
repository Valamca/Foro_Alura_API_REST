CREATE TABLE courses (
    id bigint NOT NULL AUTO_INCREMENT,
    nombre varchar(100) NOT NULL,
    categoria varchar(100) NOT NULL UNIQUE,

    PRIMARY KEY (id)
);