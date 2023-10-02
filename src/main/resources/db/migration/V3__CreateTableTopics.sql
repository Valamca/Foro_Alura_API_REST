CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    date_creation DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    course_id BIGINT,
    
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
