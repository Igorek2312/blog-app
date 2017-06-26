CREATE TABLE attachment
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    file_url VARCHAR(255) NOT NULL,
    post_id INT NOT NULL
);