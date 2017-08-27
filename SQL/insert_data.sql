INSERT INTO countries (country_id, country_name) VALUES(1, 'Ukraine');
INSERT INTO countries (country_id, country_name) VALUES(2, 'England');
INSERT INTO countries (country_id, country_name) VALUES(3, 'Germany');

INSERT INTO teams (team_id, name, country_id) VALUES(1, 'dinamo', 1);
INSERT INTO teams (team_id, name, country_id) VALUES(2, 'arsenal', 2);
INSERT INTO teams (team_id, name, country_id) VALUES(3, 'bavaria', 3);

INSERT INTO players (player_id, name, number, in_order, team_id) VALUES(1, 'misha', 12, 1, 1);
INSERT INTO players (player_id, name, number, in_order, team_id) VALUES(11, 'michael', 11, 2, 1);
INSERT INTO players (player_id, name, number, in_order, team_id) VALUES(111, 'kasya', 10, 3, 1);
INSERT INTO players (player_id, name, number, in_order, team_id) VALUES(1111, 'lolo', 9, 5, 1);
INSERT INTO players (player_id, name, number, in_order, team_id) VALUES(11111, 'mama', 8, 7, 1);
INSERT INTO players (player_id, name, number, in_order, team_id) VALUES(2, 'vova', 13, 10, 1);
INSERT INTO players (player_id, name, number, in_order, team_id) VALUES(3, 'vasya', 14, 11, 1);
INSERT INTO players (player_id, name, number, team_id) VALUES(4, 'four', 4, 2);
INSERT INTO players (player_id, name, number, team_id) VALUES(5, 'five', 5, 2);
INSERT INTO players (player_id, name, number, team_id) VALUES(6, 'six', 6, 2);
INSERT INTO players (player_id, name, number, team_id) VALUES(7, 'seven', 7, 3);
INSERT INTO players (player_id, name, number, team_id) VALUES(8, 'eight', 8, 3);
INSERT INTO players (player_id, name, number, team_id) VALUES(9, 'nine', 9, 3);

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
INSERT INTO cards (card_id, player_id, discriminator) VALUES(11, 6, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(12, 6, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(13, 7, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(14, 7, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(15, 8, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(16, 8, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(17, 9, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(18, 9, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(19, 11, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(20, 11, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(21, 111, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(22, 111, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(23, 1111, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(24, 1111, 'CARTOON');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(25, 11111, 'OFFICIAL');
INSERT INTO cards (card_id, player_id, discriminator) VALUES(26, 11111, 'CARTOON');

-- # INSERT INTO roles (role_id, role_name, description) VALUES(1, 'USER', 'user');
-- # INSERT INTO roles (role_id, role_name, description) VALUES(2, 'ADMIN', 'admin');

INSERT INTO users (user_id, role, user_name, pswrd_hash, reward_date) VALUES(1, "USER", 'user', "password", 1502311785886);
INSERT INTO users (user_id, role, user_name, pswrd_hash) VALUES(2, "USER", 'user2', "parol");
INSERT INTO users (user_id, role, user_name, pswrd_hash, reward_date) VALUES(3, "ADMIN", 'admin', "admin", 1502311785886);

INSERT INTO user_card (user_card_id, user_id, card_id, is_activated) VALUES(1, 1, 1, TRUE);
INSERT INTO user_card (user_card_id, user_id, card_id) VALUES(2, 1, 2);
INSERT INTO user_card (user_card_id, user_id, card_id) VALUES(3, 1, 3);
INSERT INTO user_card (user_card_id, user_id, card_id, is_activated) VALUES(4, 1, 4, TRUE);
INSERT INTO user_card (user_card_id, user_id, card_id) VALUES(5, 1, 5);
INSERT INTO user_card (user_card_id, user_id, card_id) VALUES(6, 2, 1);
INSERT INTO user_card (user_card_id, user_id, card_id, is_activated) VALUES(7, 2, 7, TRUE);
INSERT INTO user_card (user_card_id, user_id, card_id) VALUES(8, 2, 9);

# without ID
# INSERT INTO user_card (user_id, card_id, is_activated) VALUES( 1, 1, TRUE);
# INSERT INTO user_card ( user_id, card_id) VALUES( 1, 2);
# INSERT INTO user_card ( user_id, card_id) VALUES( 1, 3);
# INSERT INTO user_card ( user_id, card_id, is_activated) VALUES( 1, 4, TRUE);
# INSERT INTO user_card ( user_id, card_id) VALUES( 1, 5);
# INSERT INTO user_card ( user_id, card_id) VALUES( 2, 1);
# INSERT INTO user_card ( user_id, card_id, is_activated) VALUES( 2, 7, TRUE);
# INSERT INTO user_card ( user_id, card_id) VALUES( 2, 9);