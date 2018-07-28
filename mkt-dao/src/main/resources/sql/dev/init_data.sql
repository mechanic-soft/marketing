/*==============================================================*/
/* Data: 系统用户                                                */
/*==============================================================*/
INSERT INTO sys_user VALUES
  (1, 'admin', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9',
      '管理员', '1185887460', '@42dc1e0cb92c1eb6eda3cb65c0ddbf64', '格物致知',
      '/cgi-bin/mmwebwx-bin/webwxgeticon?seq=1856357848&username=@42dc1e0cb92c1eb6eda3cb65c0ddbf64&skey=@crypt_69a1cdd6_64f03a7c1eff37e99cee69ef9f93a70c',
      1, '一代鲜肉替腊肉，终究风干无人识。', '', '', '', NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (2, 'manager', /*  '4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8',*/
                                '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse',
                                'e5a721750f676a48ee77f3fe8c5a58e9', '管理者1', NULL, '', '', '', NULL, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (3, 'user', /*  '4a79d0a37b1bf2ded4b72f1372c5dec9a0b36520a77c4846a9accacb527d91c8',*/
                                '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse',
                                'e5a721750f676a48ee77f3fe8c5a58e9', '理财经理1', NULL, '', '', '', NULL, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

/*==============================================================*/
/* Data: 系统角色                                                */
/*==============================================================*/
INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (1, 'ADMIN', '系统管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (2, 'MANAGER', '管理者', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_role (id, name, description, status, create_user, create_time, update_user, update_time)
VALUES (3, 'USER', '理财经理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

/*==============================================================*/
/* Data: 系统权限                                                */
/*==============================================================*/
-- 用户
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1001, 'get_users', '/v1/sys/users', 'GET', '用户列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1002, 'get_users_id', '/v1/sys/users/{id}', 'GET', '用户详情', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1003, 'post_users', '/v1/sys/users', 'POST', '新增用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1004, 'put_users', '/v1/sys/users', 'PUT', '更新用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1005, 'delete_user', '/v1/sys/users', 'DELETE', '删除用户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

-- 角色
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1101, 'get_roles', '/v1/sys/roles', 'GET', '角色列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1102, 'get_roles_id', '/v1/sys/roles/{id}', 'GET', '角色详情', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1103, 'post_roles', '/v1/sys/roles', 'POST', '新增角色', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1104, 'put_roles_id', '/v1/sys/roles', 'PUT', '更新角色', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1105, 'delete_roles', '/v1/sys/roles', 'DELETE', '删除角色', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

-- 权限
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES
  (1201, 'get_permissions', '/v1/sys/permissions', 'GET', '权限列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1202, 'get_permissions_id', '/v1/sys/permissions/{id}', 'GET', '权限详情', 1, 1, CURRENT_TIMESTAMP(), 1,
        CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES
  (1203, 'post_permissions', '/v1/sys/permissions', 'POST', '新增权限', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES
  (1204, 'put_permissions_id', '/v1/sys/permissions', 'PUT', '更新权限', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1205, 'delete_permissions', '/v1/sys/permissions', 'DELETE', '删除权限', 1, 1, CURRENT_TIMESTAMP(), 1,
        CURRENT_TIMESTAMP());

-- 公司
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1301, 'get_corp', '/v1/sys/corps', 'GET', '公司列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1302, 'get_corp_id', '/v1/sys/corps/{id}', 'GET', '公司详情', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1303, 'post_corp', '/v1/sys/corps', 'POST', '新增公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1304, 'put_corp', '/v1/sys/corps', 'PUT', '更新公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1305, 'delete_corp', '/v1/sys/corps', 'DELETE', '删除公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

-- 任务
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1401, 'get_tasks', '/v1/tasks', 'GET', '任务列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1402, 'get_tasks_today', '/v1/tasks/today', 'GET', '今日任务', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1403, 'get_rules_remindings', '/v1/rules/remindings', 'GET', '今日提醒', 1, 1, CURRENT_TIMESTAMP(), 1,
        CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1404, 'post_tasks', '/v1/tasks', 'POST', '新建任务', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1405, 'get_tasks_id', '/v1/tasks/{id}', 'GET', '任务详情', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1406, 'put_tasks', '/v1/tasks/{id}', 'PUT', '修改任务', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

-- 规则
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1501, 'get_rules', '/v1/rules', 'GET', '规则列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1502, 'post_rules', '/v1/rules', 'POST', '新建规则', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1503, 'get_rules_id', '/v1/rules/{id}', 'GET', '规则详情', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1504, 'put_rules', '/v1/rules/{id}', 'PUT', '修改规则', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

-- 客户
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1601, 'get_customers', '/v1/customers', 'GET', '客户列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1602, 'delete_customers', '/v1/customers', 'DELETE', '删除客户', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1603, 'patch_customers', '/v1/customers', 'PATCH', '关联微信', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1604, 'get_wxcontacts', '/v1/wxcontacts', 'GET', '微信好友列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1605, 'post_customers_wxcontacts', '/v1/customers/wxcontacts', 'POST', '同步客户', 1, 1, CURRENT_TIMESTAMP(), 1,
        CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1606, 'get_customers_tag', '/v1/customers/{id}/tags', 'GET', '客户标签列表', 1, 1, CURRENT_TIMESTAMP(), 1,
        CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1607, 'post_customers_tag', '/v1/customers/{customerId}/tags', 'POST', '新增客户标签', 1, 1, CURRENT_TIMESTAMP(), 1,
        CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES
  (1608, 'get_customers_lifecycles', '/v1/customers/{id}/lifecycles', 'GET', '客户生命周期事件列表', 1, 1, CURRENT_TIMESTAMP(), 1,
   CURRENT_TIMESTAMP());

-- 客户动态
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1701, 'get_customerDynamics', '/v1/customerDynamics', 'GET', '客户动态列表', 1, 1, CURRENT_TIMESTAMP(), 1,
        CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1702, 'get_customerDynamics_statistics', '/v1/customerDynamics/statistics', 'GET', '客户行为统计', 1, 1,
        CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1703, 'get_customerDynamics_acticles', '/v1/customerDynamics/statistics/acticles', 'GET', '文章阅读动态统计', 1, 1,
        CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES
  (1704, 'get_customerDynamics_statistics_interaction', '/v1/customerDynamics/statistics/interaction', 'GET', '客户互动统计',
   1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1705, 'post_customerDynamics', '/v1/customerDynamics', 'POST', '添加客户动态', 1, 1, CURRENT_TIMESTAMP(), 1,
        CURRENT_TIMESTAMP());

-- 聊天
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1801, 'get_chatRecords', '/v1/chatRecords', 'GET', '聊天记录', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1802, 'get_consumers', '/v1/consumers', 'GET', '加微客户列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES
  (1803, 'post_chatRecords', '/v1/chatRecords', 'POST', '添加聊天记录', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

-- 标签
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1901, 'post_tags', '/v1/tags', 'POST', '新增标签', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1902, 'post_tagTypes', '/v1/tagTypes', 'POST', '新增类别', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1903, 'get_tags', '/v1/tags', 'GET', '标签列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1904, 'put_tags', '/v1/tags/{id}', 'PUT', '修改标签', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1905, 'delete_tags', '/v1/tags', 'DELETE', '删除标签', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1906, 'get_tagsTypes', '/v1/tagTypes', 'GET', '标签类别', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

-- 日志
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (2001, 'get_logs', '/v1/sys/logs', 'GET', '日志列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (2002, 'get_logs_id', '/v1/sys/logs/{id}', 'GET', '日志详情', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (2003, 'post_logs', '/v1/sys/logs', 'POST', '新增日志', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (2004, 'put_logs', '/v1/sys/logs', 'PUT', '更新日志', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (2005, 'delete_logs', '/v1/sys/logs', 'DELETE', '删除日志', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

-- 菜单
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (3001, 'get_menus', '/v1/sys/menus/', 'GET', '菜单列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (3002, 'get_menus_id', '/v1/sys/menus/{id}', 'GET', '菜单详情', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (3003, 'post_menus', '/v1/sys/menus', 'POST', '新增菜单', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (3004, 'put_menus', '/v1/sys/menus', 'PUT', '更新菜单', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (3005, 'delete_menus', '/v1/sys/menus/{id}', 'DELETE', '删除菜单', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (3006, 'get_menus_user_menus', '/v1/sys/menus/user_menus', 'GET', '当前用户菜单列表', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

/*==============================================================*/
/* Data: 用户角色关联                                              */
/*==============================================================*/
INSERT INTO rele_user_role VALUES (1, 1, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (2, 2, 2, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (3, 3, 3, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

/*==============================================================*/
/* Data: 角色权限关联                                             */
/*==============================================================*/
-- admin(系统管理员)
INSERT INTO rele_role_permission VALUES (1001, 1, 1001, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1002, 1, 1002, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1003, 1, 1003, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1004, 1, 1004, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1005, 1, 1005, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1006, 1, 1101, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1007, 1, 1102, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1008, 1, 1103, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1009, 1, 1104, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1000, 1, 1105, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1011, 1, 1201, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1012, 1, 1202, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1013, 1, 1203, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1014, 1, 1204, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1015, 1, 1205, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1016, 1, 1301, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1017, 1, 1302, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1018, 1, 1303, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1019, 1, 1304, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (10191, 1, 1305, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1020, 1, 1401, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1021, 1, 1402, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1022, 1, 1403, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1023, 1, 1404, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1024, 1, 1405, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1025, 1, 1406, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1026, 1, 1501, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1027, 1, 1502, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1028, 1, 1503, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1029, 1, 1504, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1030, 1, 1601, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1031, 1, 1602, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1032, 1, 1603, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1033, 1, 1604, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1034, 1, 1605, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1035, 1, 1606, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1036, 1, 1607, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1037, 1, 1608, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1038, 1, 1701, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1039, 1, 1702, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1040, 1, 1703, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1041, 1, 1704, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1042, 1, 1705, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1043, 1, 1801, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1044, 1, 1802, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1045, 1, 1803, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1046, 1, 1901, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1047, 1, 1902, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1048, 1, 1903, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1049, 1, 1904, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1050, 1, 1905, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1051, 1, 1906, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1052, 1, 2001, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1053, 1, 2002, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1054, 1, 2003, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1055, 1, 2004, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1056, 1, 2005, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1057, 1, 3001, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1058, 1, 3002, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1059, 1, 3003, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1060, 1, 3004, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1061, 1, 3005, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1062, 1, 3006, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
-- manager(客户经理上级)
INSERT INTO rele_role_permission VALUES (2001, 2, 1401, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2002, 2, 1402, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2003, 2, 1403, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2004, 2, 1404, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2005, 2, 1405, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2006, 2, 1406, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2007, 2, 1501, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2008, 2, 1502, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2009, 2, 1503, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2010, 2, 1504, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2011, 2, 1901, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2012, 2, 1902, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2013, 2, 1903, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2014, 2, 1904, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2015, 2, 1905, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2016, 2, 1906, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2017, 2, 3006, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
-- user(客户经理)
INSERT INTO rele_role_permission VALUES (3001, 3, 1402, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3002, 3, 1403, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3003, 3, 1601, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3004, 3, 1602, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3005, 3, 1603, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3006, 3, 1604, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3007, 3, 1605, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3008, 3, 1606, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3009, 3, 1607, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3010, 3, 1608, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3011, 3, 1701, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3012, 3, 1702, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3013, 3, 1703, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3014, 3, 1704, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3015, 3, 1705, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3016, 3, 1801, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3017, 3, 1802, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3018, 3, 1803, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2019, 3, 3006, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

/*==============================================================*/
/* Data: 系统菜单                                                */
/*==============================================================*/
INSERT INTO sys_menu VALUES (1,  0, '任务代办', '', 1401, 1, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (2,  0, '新建任务', '/task', 1401, 2, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (3,  0, '客户管理', '/clientManage', 1601, 3, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (4,  0, '客户动态', '/clientDynamic', 1701, 4, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (5,  0, '聊天会话', '/chatSession', 1802, 5, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (7,  0, '标签管理', '/tagManage', 1903, 6, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (8,  0, '系统管理', '', 1101, 7, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (9,  1, '我的代办', '/dynamics', 1402, 1, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (10, 1, '新建规则', '/rule', 1501, 2, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (11, 8, '公司注册', '/company', 1305, 1, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (12, 8, '用户管理', '/user', 1004, 5, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (13, 8, '权限管理', '/permissionList', 1101, 4, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (14, 8, '日志管理', '/logList', 2001, 3, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);
INSERT INTO sys_menu VALUES (15, 14, 'xxxxx',  '', 2001, 2, 1, 1, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP);

/*==============================================================*/
/* Data: 公司                                                    */
/*==============================================================*/
INSERT INTO sys_corp VALUES (1, '安信证券佛山分公司', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());