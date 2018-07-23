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


INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1, 'add_user', '/v1/sys/users', 'POST', '新增用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (2, 'update_user', '/v1/sys/users', 'PUT', '更新用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (3, 'delete_user', '/v1/sys/users', 'DELETE', '删除用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (4, 'get_user', '/v1/sys/users', 'GET', '获取多个用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (5, 'get_sign_user', '/v1/sys/users/{id}', 'GET', '获取单个用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (6, 'add_corp', '/v1/sys/corps', 'POST', '新增公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (7, 'update_corp', '/v1/sys/corps', 'PUT', '更新公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (8, 'delete_corp', '/v1/sys/corps', 'DELETE', '删除公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (9, 'get_sign_corp', '/v1/sys/corps/{id}', 'GET', '获取公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (10, 'get_task', '/v1/tasks', 'GET', '任务列表信息', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (11, 'get_sign_task', '/v1/tasks/{id}', 'GET', '获取任务详细信息', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (12, 'add_task', '/v1/tasks', 'POST', '新建任务信息', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (13, 'update_task', '/v1/tasks', 'PUT', '修改任务信息', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (14, 'task_today', '/v1/tasks/today', 'DELETE', '获取今日任务信息', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (15, 'get_wxcontacts', '/v1/wxcontacts', 'GET', '微信好友列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (16, 'sync_wxContacts', '/v1/customers/wxContacts', 'POST', '同步客户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (17, 'rele_customers', '/v1/customers', 'PATCH', '关联微信', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (18, 'delete_customers', '/v1/customers', 'DELETE', '删除客户信息', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());


INSERT INTO rele_user_role VALUES (1, 1, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (2, 2, 2, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (3, 3, 3, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
-- admin
INSERT INTO rele_role_permission VALUES (1, 1, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2, 1, 2, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3, 1, 3, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (4, 1, 4, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (5, 1, 5, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (6, 1, 6, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (7, 1, 7, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (8, 1, 8, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (9, 1, 9, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
-- manager
INSERT INTO rele_role_permission VALUES (10, 2, 10, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (11, 2, 11, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (12, 2, 12, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (13, 2, 13, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (14, 2, 14, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
-- user
INSERT INTO rele_role_permission VALUES (15, 3, 15, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (16, 3, 16, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (17, 3, 17, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (18, 3, 18, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_corp VALUES (1, 'corp1', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (2, 'corp2', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (3, 'corp3', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (4, 'corp4', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_corp VALUES (5, 'corp5', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());