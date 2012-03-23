# Absence schema
 
# --- !Ups

CREATE SEQUENCE absence_id_seq;
CREATE TABLE absence (
    id integer NOT NULL DEFAULT nextval('absence_id_seq'),
    description varchar(255),
    start bigint,
    end bigint
);
 
# --- !Downs
 
DROP TABLE absence;
DROP SEQUENCE absence_id_seq;
