CREATE TABLE sys_user (
  id BIGINT PRIMARY KEY ,
  username VARCHAR(64),
  password VARCHAR(256)
);

CREATE TABLE sys_role (
  id BIGINT PRIMARY KEY ,
  name VARCHAR(64)
);

CREATE TABLE sys_user_role (
  id BIGINT PRIMARY KEY ,
  user_id BIGINT,
  role_id BIGINT
);