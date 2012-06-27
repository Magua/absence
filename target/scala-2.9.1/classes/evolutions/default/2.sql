# Absence schema
 
# --- !Ups

INSERT INTO user (name) values('Dolores Claiborn');
INSERT INTO user (name) values('Sheldon Cooper');

INSERT INTO absence (userid, description, start, end) values (1, 'Vacation: Home gardening.', 1337251933496, 1337255652120);
INSERT INTO absence (userid, description, start, end) values (2, 'Comic-Con International 2012!', 1337165533496, 1337169252120);
 
# --- !Downs

DELETE from absence where userid = 1
DELETE from absence where userid = 2

DELETE from user where id = 1
DELETE from user where id = 2

