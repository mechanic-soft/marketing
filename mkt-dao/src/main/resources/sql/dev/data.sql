INSERT INTO sys_user VALUES (
  1,
  'admin',
  /*  '4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8',*/
  '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse',
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
  /*  '4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8',*/
  '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse',
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
  /*  '4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8',*/
  '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse',
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
  NULL,
  1,
  1,
  CURRENT_TIMESTAMP(),
  1,
  CURRENT_TIMESTAMP());


INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (1, 'ADMIN', '系统管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (2, 'MANAGER', '管理者', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (3, 'USER', '理财经理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());


INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (1, 'add_user', '/v1/sys/users', 'POST', '新增用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (2, 'update_user', '/v1/sys/users', 'PUT', '更新用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (3, 'delete_user', '/v1/sys/users', 'DELETE', '删除用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (4, 'get_user', '/v1/sys/users', 'GET', '获取多个用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (5, 'get_user', '/v1/sys/users/{id}', 'GET', '获取单个用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (6, 'add_corp', '/v1/sys/corps/*', 'POST', '新增公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (7, 'update_corp', '/v1/sys/corps/*', 'PUT', '更新公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (8, 'delete_corp', '/v1/sys/corps', 'DELETE', '删除公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, action, description, status, create_user, create_time, update_user, update_time)
VALUES (9, 'get_corp', '/v1/sys/corps/*', 'GET', '获取公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO rele_user_role VALUES (1, 1, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (2, 2, 2, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (3, 3, 3, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO rele_role_permission VALUES (1, 1, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2, 1, 2, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3, 1, 3, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (4, 1, 4, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO rele_role_permission VALUES (5, 2, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (6, 2, 4, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO rele_role_permission VALUES (7, 3, 4, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (8, 3, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (9, 3, 7, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (10, 3, 8, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_corp VALUES (1, 'corp1', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (2, 'corp2', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (3, 'corp3', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (4, 'corp4', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (5, 'corp5', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());