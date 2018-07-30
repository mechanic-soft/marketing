/*==============================================================*/
/* 已执行SQL脚本                                                 */
/*==============================================================*/

/*==============================================================*/
/* 更新说明：增加权限表字段                                      */
/* 更新作者：梁天利                                              */
/*==============================================================*/
alter table sys_permission add pid bigint not null default 0;

INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1000, 0, 'users',null,null, '用户管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid,name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1100, 0, 'roles', null, null, '角色管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1200, 0, 'gpermissions', null, null, '权限管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1300, 0, 'corp', null, null, '公司管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1400, 0, 'tasks', null, null, '任务管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1500, 0, 'rules', null, null, '规则管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1600, 0, 'customers', null, null, '客户管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1700, 0, 'customerDynamics', null, null, '客户动态管理', 1, 1, CURRENT_TIMESTAMP(), 1,CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1800, 0, 'chatRecords', null, null, '聊天管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (1900, 0, 'tags', null, null, '标签管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (2000, 0, 'logs', null, null, '日志管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO sys_permission (id, pid, name, endpoint, method, description, status, create_user, create_time, update_user, update_time)
VALUES (3000, 0, 'menus', null, null, '菜单管理', 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

update sys_permission set pid = 1000 where endpoint like '/v1/sys/users%';
update sys_permission set pid = 1100 where endpoint like '/v1/sys/roles%';
update sys_permission set pid = 1200 where endpoint like '/v1/sys/permissions%';
update sys_permission set pid = 1300 where endpoint like '/v1/sys/corps%';
update sys_permission set pid = 1400 where endpoint like '/v1/tasks%';
update sys_permission set pid = 1500 where endpoint like '/v1/rules%';
update sys_permission set pid = 1600 where endpoint like '/v1/customers%';
update sys_permission set pid = 1700 where endpoint like '/v1/customerDynamics%';
update sys_permission set pid = 1800 where endpoint like '/v1/chatRecords%';
update sys_permission set pid = 1900 where endpoint like '/v1/tags%';
update sys_permission set pid = 2000 where endpoint like '/v1/sys/logs%';
update sys_permission set pid = 3000 where endpoint like '/v1/sys/menus%';

/*==============================================================*/
/* 更新说明：权限角色关联                                        */
/* 更新作者：梁天利                                              */
/*==============================================================*/

update rele_role_permission set id = 3019 where id = 2019;

INSERT INTO rele_role_permission VALUES (1063, 1, 1000, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1064, 1, 1100, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1065, 1, 1200, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1066, 1, 1300, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1067, 1, 1400, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1068, 1, 1500, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1069, 1, 1600, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1070, 1, 1700, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1071, 1, 1800, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1072, 1, 1900, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1073, 1, 2000, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (1074, 1, 3000, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO rele_role_permission VALUES (2018, 2, 1400, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2019, 2, 1500, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2020, 2, 1900, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (2021, 2, 3000, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO rele_role_permission VALUES (3020, 3, 1400, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3021, 3, 1600, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3022, 3, 1700, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3023, 3, 1800, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_role_permission VALUES (3024, 3, 3000, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());