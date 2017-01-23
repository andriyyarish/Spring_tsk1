-- Table: accounts (own implementation)
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  ballance FLOAT
)
  ENGINE = InnoDB;

-- Table: users (according to task description)
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  dateOfBirth DATETIME ,
  password VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

-- Table: roles
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL

)
  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
DROP TABLE IF EXISTS account_roles;
CREATE TABLE account_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES accounts (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
)
  ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE IF NOT EXISTS persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL
);

  -- Insert data

INSERT INTO accounts VALUES (1, 'andy', 'root');
INSERT INTO accounts VALUES (3,'encode','$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.'); -- 123456

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO account_roles VALUES (1, 2);