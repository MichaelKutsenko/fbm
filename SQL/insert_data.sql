INSERT INTO countries (country_id, country_name) VALUES(1, 'Ukraine');
INSERT INTO countries (country_id, country_name) VALUES(2, 'England');
INSERT INTO countries (country_id, country_name) VALUES(3, 'Germany');

INSERT INTO teams (team_id, name, country_id) VALUES(1, 'dinamo', 1);
INSERT INTO teams (team_id, name, country_id) VALUES(2, 'arsenal', 2);
INSERT INTO teams (team_id, name, country_id) VALUES(3, 'bavaria', 3);

INSERT INTO players (player_id, name, number, team_id) VALUES(1, 'misha', 12, 1);
INSERT INTO players (player_id, name, number, team_id) VALUES(2, 'vova', 13, 1);
INSERT INTO players (player_id, name, number, team_id) VALUES(3, 'vasya', 14, 1);
INSERT INTO players (player_id, name, number, team_id) VALUES(4, 'vasya', 4, 2);
INSERT INTO players (player_id, name, number, team_id) VALUES(5, 'vasya', 5, 2);
INSERT INTO players (player_id, name, number, team_id) VALUES(6, 'vasya', 6, 2);

INSERT INTO cards (card_id, player_id, discriminator) VALUES(1, 1, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(2, 1, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(3, 2, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(4, 2, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(5, 3, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(6, 3, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(7, 4, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(8, 4, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(9, 5, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(10, 5, 'CARTOON');

INSERT INTO roles (role_id, role_name, description) VALUES(1, 'USER', 'user');
INSERT INTO roles (role_id, role_name, description) VALUES(2, 'ADMIN', 'admin');

INSERT INTO users (user_id, role_id, user_name, pswrd_hash) VALUES(1, 1, 'user', "password");
INSERT INTO users (user_id, role_id, user_name, pswrd_hash) VALUES(2, 1, 'user2', "parol");
INSERT INTO users (user_id, role_id, user_name, pswrd_hash) VALUES(3, 1, 'admin', "admin");

INSERT INTO user_card (user_id, card_id) VALUES(1, 1);
INSERT INTO user_card (user_id, card_id) VALUES(1, 2);
INSERT INTO user_card (user_id, card_id) VALUES(1, 3);
INSERT INTO user_card (user_id, card_id) VALUES(1, 4);
INSERT INTO user_card (user_id, card_id) VALUES(1, 5);
INSERT INTO user_card (user_id, card_id) VALUES(2, 1);
INSERT INTO user_card (user_id, card_id) VALUES(2, 7);
INSERT INTO user_card (user_id, card_id) VALUES(2, 9);