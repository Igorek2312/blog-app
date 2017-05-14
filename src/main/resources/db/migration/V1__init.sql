CREATE TABLE user
(
  id             INT          NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  activated      BIT          NOT NULL,
  activation_key VARCHAR(36)  NULL,
  email          VARCHAR(100) NULL,
  first_name     VARCHAR(50)  NOT NULL,
  image_url      VARCHAR(256) NULL,
  last_name      VARCHAR(50)  NOT NULL,
  password_hash  VARCHAR(60)  NOT NULL,
  reset_key      VARCHAR(36)  NULL,
  username       VARCHAR(100) NOT NULL,
  CONSTRAINT UK_ob8kqyqqgmefl0aco34akdtpe
  UNIQUE (email),
  CONSTRAINT UK_sb8bbouer5wak8vyiiy4pf2bx
  UNIQUE (username)
);

CREATE TABLE role
(
  id   INT         NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(50) NULL
);

CREATE TABLE user_role
(
  account_id INT NOT NULL,
  role_id    INT NOT NULL,
  PRIMARY KEY (account_id, role_id),
  CONSTRAINT FK2qlw2xsga98pfrfuaiaju1je4
  FOREIGN KEY (account_id) REFERENCES user (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y
  FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE INDEX FKa68196081fvovjhkek5m97n3y
  ON user_role (role_id);




