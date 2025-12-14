-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO authorities(id,authority) VALUES (1,'ADMIN');
INSERT INTO appusers(id,username,password,authority) VALUES (1,'admin1','$2a$10$nMmTWAhPTqXqLDJTag3prumFrAJpsYtroxf0ojesFYq0k4PmcbWUS',1);

-- Ten player users, named player1 with passwor 0wn3r
INSERT INTO authorities(id,authority) VALUES (2,'PLAYER');
INSERT INTO appusers(id,username,password,authority) VALUES (4,'player1','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (5,'player2','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (6,'player3','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (7,'player4','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (8,'player5','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (9,'player6','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (10,'player7','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (11,'player8','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (12,'player9','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);
INSERT INTO appusers(id,username,password,authority) VALUES (13,'player10','$2a$10$DaS6KIEfF5CRTFrxIoGc7emY3BpZZ0.fVjwA3NiJ.BjpGNmocaS3e',2);


-- Data for testing the complex query:
INSERT INTO chess_board (id, creator_turn, on_check, current_turn_start) VALUES (15, 0, 0, null);
INSERT INTO chess_match(id, name, white_player_id, black_player_id, board_id, start, finish, turn_duration) VALUES 
                       (15, 'new', 4, 5, 15, '2025-10-20 12:00:00', null, 600);

INSERT INTO Position(id, x, y) VALUES
    (1, 1, 1),
    (2, 2, 1),
    (3, 3, 1),
    (4, 4, 1),
    (5, 5, 1),
    (6, 6, 1),
    (7, 7, 1),
    (8, 8, 1),
    (9, 1, 2),
    (10, 2, 2),
    (11, 3, 2),
    (12, 4, 2),
    (13, 5, 2),
    (14, 6, 2),
    (15, 7, 2),
    (16, 8, 2),
    (17, 1, 3),
    (18, 2, 3),
    (19, 3, 3),
    (20, 4, 3),
    (21, 5, 3),
    (22, 6, 3),
    (23, 7, 3),
    (24, 8, 3),
    (25, 1, 4),
    (26, 2, 4),
    (27, 3, 4),
    (28, 4, 4),
    (29, 5, 4),
    (30, 6, 4),
    (31, 7, 4),
    (32, 8, 4),
    (33, 1, 5),
    (34, 2, 5),
    (35, 3, 5),
    (36, 4, 5),
    (37, 5, 5),
    (38, 6, 5),
    (39, 7, 5),
    (40, 8, 5),
    (41, 1, 6),
    (42, 2, 6),
    (43, 3, 6),
    (44, 4, 6),
    (45, 5, 6),
    (46, 6, 6),
    (47, 7, 6),
    (48, 8, 6),
    (49, 1, 7),
    (50, 2, 7),
    (51, 3, 7),
    (52, 4, 7),
    (53, 5, 7),
    (54, 6, 7),
    (55, 7, 7),
    (56, 8, 7),
    (57, 1, 8),
    (58, 2, 8),
    (59, 3, 8),
    (60, 4, 8),
    (61, 5, 8),
    (62, 6, 8),
    (63, 7, 8),
    (64, 8, 8);

INSERT INTO piece_type (name, points) VALUES
  ('KING',   1000),
  ('QUEEN',  9),
  ('ROOK',   5),
  ('BISHOP', 3),
  ('KNIGHT', 3),
  ('PAWN',   1);

INSERT INTO Piece(id, type_name, color, position_id, board_id) VALUES 
    (123, 'ROOK', 'WHITE', 1, 15),
    (124, 'KNIGHT', 'WHITE', 9, 15),
    (125, 'BISHOP', 'WHITE', 17, 15),
    (126, 'QUEEN', 'WHITE', 25, 15),
    (127, 'KING', 'WHITE', 33, 15),
    (128, 'BISHOP', 'WHITE', 41, 15),
    (129, 'KNIGHT', 'WHITE', 49, 15),
    (130, 'ROOK', 'WHITE', 57, 15),
    (131, 'PAWN', 'WHITE', 2, 15),
    (132, 'PAWN', 'WHITE', 10, 15),
    (133, 'PAWN', 'WHITE', 18, 15),
    (134, 'PAWN', 'WHITE', 26, 15),
    (135, 'PAWN', 'WHITE', 34, 15),
    (136, 'PAWN', 'WHITE', 42, 15),
    (137, 'PAWN', 'WHITE', 50, 15),
    (138, 'PAWN', 'WHITE', 58, 15),
    (139, 'ROOK', 'BLACK', 8, 15),
    (140, 'KNIGHT', 'BLACK', 16, 15),
    (141, 'BISHOP', 'BLACK', 24, 15),
    (142, 'QUEEN', 'BLACK', 32, 15),
    (143, 'KING', 'BLACK', 40, 15),
    (144, 'BISHOP', 'BLACK', 48, 15),
    (145, 'KNIGHT', 'BLACK', 56, 15),
    (146, 'ROOK', 'BLACK', 64, 15),
    (147, 'PAWN', 'BLACK', 7, 15),
    (148, 'PAWN', 'BLACK', 15, 15),
    (149, 'PAWN', 'BLACK', 23, 15),
    (150, 'PAWN', 'BLACK', 31, 15),
    (151, 'PAWN', 'BLACK', 39, 15),
    (152, 'PAWN', 'BLACK', 47, 15),
    (153, 'PAWN', 'BLACK', 55, 15),
    (154, 'PAWN', 'BLACK', 63, 15);

-- Community
INSERT INTO community (id, name, location, max_players)
VALUES (1, 'Ajedrez Sevilla', 'Sevilla', 500);

INSERT INTO season (id, name, start_date, end_date, community_id) VALUES
  (1, 'Juegos Municipales 2024-25', '2024-09-01', '2025-05-31', 1),
  (2, 'Temporada Oficial 2025-26', '2025-10-15', '2026-06-15', 1);

INSERT INTO rating (id, elo, wins, losses, draws, season_id, player_id) VALUES
  (1, 1560, 4, 2, 1, 1, 4),
  (2, 1250, 2, 2, 0, 1, 5),
  (3, 1000, 0, 0, 4, 1, 6),
  (4, 2080, 8, 1, 2, 2, 4),
  (5, 1310, 4, 6, 1, 2, 5),
  (6, 1567, 2, 0, 0, 2, 6);

-- ==========================================================
-- PARTIDA 1:
-- ==========================================================
INSERT INTO chess_board (id, creator_turn, current_turn_start, on_check)
VALUES (101, TRUE, CURRENT_TIMESTAMP(), false);

INSERT INTO chess_match (id, name, start, finish, turn_duration,  board_id, white_player_id, black_player_id, season_id)
VALUES (1, 'Partida 1', CURRENT_TIMESTAMP(), NULL, 300,  101, 4, 5, 1);


-- ==========================================================
-- PARTIDA 2: 
-- ==========================================================
INSERT INTO chess_board (id, creator_turn, current_turn_start, on_check)
VALUES (102, true, CURRENT_TIMESTAMP(), false);

INSERT INTO chess_match (id, name, start, finish, turn_duration,  board_id, white_player_id, black_player_id, season_id)
VALUES (2, 'Partida 2', CURRENT_TIMESTAMP(), NULL, 300, 102, 5, 4, 1);


-- ==========================================================
-- PARTIDA 3:
-- ==========================================================
INSERT INTO chess_board (id, creator_turn, current_turn_start, on_check)
VALUES (103, TRUE, CURRENT_TIMESTAMP(), false);

INSERT INTO chess_match (id, name, start, finish, turn_duration,  board_id, white_player_id, black_player_id, season_id)
VALUES (3, 'Partida 3', CURRENT_TIMESTAMP(), NULL, 300,  103, 5, 6, 1);


-- ==========================================================
-- PARTIDA 4: 
-- ==========================================================
INSERT INTO chess_board (id, creator_turn, current_turn_start, on_check)
VALUES (104, true, CURRENT_TIMESTAMP(), false);

INSERT INTO chess_match (id, name, start, finish, turn_duration,  board_id, white_player_id, black_player_id, season_id)
VALUES (4, 'Partida 4', CURRENT_TIMESTAMP(), NULL, 300, 104, 4, 5, 2);


-- ==========================================================
-- PARTIDA 5:
-- ==========================================================
INSERT INTO chess_board (id, creator_turn, current_turn_start, on_check)
VALUES (105, TRUE, CURRENT_TIMESTAMP(), false);

INSERT INTO chess_match (id, name, start, finish, turn_duration,  board_id, white_player_id, black_player_id, season_id)
VALUES (5, 'Partida 5', CURRENT_TIMESTAMP(), NULL, 300, 105, 5, 4, 2);


-- ==========================================================
-- PARTIDA 6: 
-- ==========================================================
INSERT INTO chess_board (id, creator_turn, current_turn_start, on_check)
VALUES (106, true, CURRENT_TIMESTAMP(), false);

INSERT INTO chess_match (id, name, start, finish, turn_duration,  board_id, white_player_id, black_player_id, season_id)
VALUES (6, 'Partida 6', CURRENT_TIMESTAMP(), NULL, 300, 106, 5, 6, 2);



