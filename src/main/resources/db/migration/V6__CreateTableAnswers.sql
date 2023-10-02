CREATE TABLE answers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    topic_id BIGINT NOT NULL,
    answer TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    answerDate DATETIME NOT NULL,
    active BOOLEAN NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topics (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
