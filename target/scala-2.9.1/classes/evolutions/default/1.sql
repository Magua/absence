# Absence schema
 
# --- !Ups

CREATE SEQUENCE sprint_seq start with 1000;
CREATE TABLE sprint (
    id bigint not null primary key,
    sprint_nr bigint not null,
    start bigint,
    end bigint
);

CREATE SEQUENCE user_id_seq;
CREATE TABLE user (
    id integer NOT NULL DEFAULT nextval('user_id_seq'),
    name varchar(128),
    avatar blob,
    PRIMARY KEY (id)
);

CREATE SEQUENCE absence_id_seq;
CREATE TABLE absence (
    id integer DEFAULT nextval('absence_id_seq'),
    userid integer NOT NULL,
    description varchar(255),
    start bigint,
    end bigint,
    PRIMARY KEY (id),
    FOREIGN KEY(userid) REFERENCES user(id)
);
 
# --- !Downs

DROP TABLE user;
DROP SEQUENCE user_id_seq;
 
DROP TABLE absence;
DROP SEQUENCE absence_id_seq;

DROP TABLE if exists sprint;
DROP SEQUENCE if exists sprint_seq;
