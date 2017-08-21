#1 create 'countries'
CREATE TABLE IF NOT EXISTS countries (
  country_id   BIGINT      NOT NULL,
  country_name VARCHAR(32) NOT NULL,
  PRIMARY KEY (country_id)
)ENGINE InnoDB;

#2 create 'teams'
CREATE TABLE IF NOT EXISTS teams (
  team_id    BIGINT      NOT NULL,
  name       VARCHAR(32) NOT NULL,
  country_id BIGINT      NOT NULL,
  chance     TINYINT DEFAULT 100,
  PRIMARY KEY (team_id),
  FOREIGN KEY (country_id) REFERENCES countries (country_id)
)ENGINE InnoDB;

#3 create 'players'
CREATE TABLE IF NOT EXISTS players (
  player_id BIGINT      NOT NULL,
  name      VARCHAR(32) NOT NULL,
  number    DECIMAL     NOT NULL,
  team_id   BIGINT      NOT NULL,
  in_order  TINYINT DEFAULT 100,
  chance    TINYINT DEFAULT 100,
  PRIMARY KEY (player_id),
  FOREIGN KEY (team_id) REFERENCES teams (team_id)
)ENGINE InnoDB;

#4 create 'cards'
CREATE TABLE IF NOT EXISTS cards (
  card_id       BIGINT NOT NULL,
  player_id     BIGINT NOT NULL,
  photo         BLOB    DEFAULT NULL,
  chance        TINYINT DEFAULT 100,
  discriminator VARCHAR(16),
  PRIMARY KEY (card_id),
  FOREIGN KEY (player_id) REFERENCES players (player_id)
)ENGINE InnoDB;

-- # #5 create 'role'
-- # CREATE TABLE IF NOT EXISTS roles (
-- #   role_id    BIGINT      NOT NULL,
-- #   role_name  VARCHAR(20) NOT NULL,
-- #   description VARCHAR(200),
-- #   PRIMARY KEY (role_id)
-- # )ENGINE InnoDB;

#6 create 'users'
CREATE TABLE IF NOT EXISTS users (
  user_id     BIGINT      NOT NULL,
  role        VARCHAR(16) NOT NULL,
  user_name   VARCHAR(16) NOT NULL,
  pswrd_hash  VARCHAR(64) NOT NULL,
  email       VARCHAR(32) DEFAULT NULL,
  reward_date BIGINT      DEFAULT 0,
  PRIMARY KEY (user_id)
)ENGINE InnoDB;

#7 create join table 'user_card'
CREATE TABLE IF NOT EXISTS user_card (
  user_card_id BIGINT NOT NULL,
  user_id   BIGINT  NOT NULL,
  card_id   BIGINT  NOT NULL,
  is_activated BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (user_card_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (card_id) REFERENCES cards (card_id)
)ENGINE InnoDB;