CREATE TABLE post
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    content MEDIUMTEXT NOT NULL,
    date_time_published DATETIME NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT post_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE comment
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(300) NOT NULL,
    date_time_commented DATETIME NOT NULL,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    CONSTRAINT table_name_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT table_name_post_id_fk FOREIGN KEY (post_id) REFERENCES post (id)
);