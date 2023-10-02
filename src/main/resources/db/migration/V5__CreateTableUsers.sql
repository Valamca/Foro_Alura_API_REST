CREATE TABLE users (
    id bigint NOT NULL AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    activo tinyint NOT NULL,
    
    PRIMARY KEY (id)
);