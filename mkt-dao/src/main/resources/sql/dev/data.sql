
INSERT INTO sys_user VALUES (
  1,
  'admin',
  '4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8',
  'e5a721750f676a48ee77f3fe8c5a58e9',
  '管理员',
  '1185887460',
  '@42dc1e0cb92c1eb6eda3cb65c0ddbf64',
  '格物致知',
  '/cgi-bin/mmwebwx-bin/webwxgeticon?seq=1856357848&username=@42dc1e0cb92c1eb6eda3cb65c0ddbf64&skey=@crypt_69a1cdd6_64f03a7c1eff37e99cee69ef9f93a70c',
  1,
  '一代鲜肉替腊肉，终究风干无人识。',
  '',
  '',
  '',
  NULL,
  1,
  1,
  CURRENT_TIMESTAMP(),
  1,
  CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (
  2,
  'manager',
  '4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8',
  'e5a721750f676a48ee77f3fe8c5a58e9',
  '管理者1',
  NULL,
  '',
  '',
  '',
  NULL,
  '',
  '',
  '',
  '',
  NULL,
  1,
  1,
  CURRENT_TIMESTAMP(),
  1,
  CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (
  3,
  'user',
  '4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8',
  'e5a721750f676a48ee77f3fe8c5a58e9',
  '理财经理1',
  NULL,
  '',
  '',
  '',
  NULL,
  '',
  '',
  '',
  '',
  NULL ,
  1,
  1,
  CURRENT_TIMESTAMP(),
  1,
  CURRENT_TIMESTAMP());


INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (1, '系统管理', '拥有所有权限的系统管理员', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (2, '理财经理', '理财经理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (3, '管理者', '理财经理的上级管理人员', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());


INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (1, 'add_user', '/users', 'POST', '新增用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (2, 'update_user', '/users', 'PUT', '更新用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (3, 'delete_user', '/users', 'DELETE', '删除用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (4, 'get_user', '/users', 'GET', '获取用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());


INSERT INTO rele_user_role VALUES (1, 1, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (2, 2, 2, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (3, 2, 3, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (4, 2, 4, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (5, 3, 5, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO rele_role_permission VALUES (1, 1, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2, 1, 2, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3, 1, 3, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (4, 1, 4, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (5, 2, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (6, 2, 4, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (7, 3, 4, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (8, 3, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_corp VALUES (1, 'corp1', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (2, 'corp2', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (3, 'corp3', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (4, 'corp4', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (5, 'corp5', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());