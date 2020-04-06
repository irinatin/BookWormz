-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS family;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS friends;

CREATE TABLE family (
  family_id SERIAL NOT NULL,
  family_name varchar(100) NOT NULL,

  CONSTRAINT pk_family_id PRIMARY KEY(family_id)
);


CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  friend_id int,
  family_id int NOT NULL,

  CONSTRAINT fk_family_id FOREIGN KEY (family_id) REFERENCES family (family_id)
);


CREATE TABLE friends (
  user_id integer NOT NULL,
  friend_id integer NOT NULL
);



COMMIT;