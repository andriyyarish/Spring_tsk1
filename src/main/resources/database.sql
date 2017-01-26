-- Table: accounts (own implementation)
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
#   user_id INT NOT NULL,
  ballance FLOAT

#   FOREIGN KEY (user_id) REFERENCES users(id)
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
#   password VARCHAR(255) NOT NULL,
  account_id INT NOT NULL,

  FOREIGN KEY (account_id) REFERENCES accounts(id)
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
  account_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (account_id) REFERENCES accounts (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (account_id, role_id)
)
  ENGINE = InnoDB;

DROp TABLE IF EXISTS events;
CREATE TABLE events (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(255) NOT NULL ,
  basePrice DOUBLE,
  eventRating VARCHAR(255)
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS auditoriums;
CREATE TABLE auditoriums(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(255) NOT NULL ,
  adress VARCHAR(255) NOT NULL ,
  seats INT NOT NULL
)
  ENGINE = InnoDB;
-- create tickets table
DROP TABLE IF EXISTS tickets;
CREATE TABLE tickets(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  seat INT NOT NULL ,
  price DOUBLE NOT NULL ,
  dateTime DATETIME,
  user_id INT NOT NULL ,

  FOREIGN KEY (user_id) REFERENCES users(id)
)
  ENGINE = InnoDB;

-- tickets to user relation
DROP TABLE IF EXISTS user_tickets;
CREATE TABLE user_tickets (
  user_id INT NOT NULL,
  ticket_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (ticket_id) REFERENCES tickets (id),

  UNIQUE (user_id, ticket_id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL
);

-- Insert data
INSERT INTO accounts VALUES (1,'encode','$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 0); -- 123456

INSERT INTO users VALUES (1,'script','script','script','1970-01-01 00:00:01',1);

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO account_roles VALUES (1, 2);

SET FOREIGN_KEY_CHECKS=1;