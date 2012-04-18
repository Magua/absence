# Absence schema
 
# --- !Ups

INSERT INTO user (name) values('Dolores Claiborn');
INSERT INTO user (name) values('Sheldon Cooper');

INSERT INTO absence (userid, description, start, end) values (1, 'Vacation: Home gardening.', 1334911562576, 1335084362576);
INSERT INTO absence (userid, description, start, end) values (2, 'Comic-Con International 2012!', 1334738762576, 1335948362576);
 
# --- !Downs

DELETE from absence where userid = 1
DELETE from absence where userid = 2

DELETE from user where id = 1
DELETE from user where id = 2

