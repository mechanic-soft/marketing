-- 系统用户表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
  id          BIGINT PRIMARY KEY NOT NULL, -- COMMENT '用户ID',
  username    VARCHAR(20)        NULL, -- COMMENT '登录账户',
  password    VARCHAR(255)       NOT NULL, -- COMMENT '登录密码',
  salt        VARCHAR(255)       NULL, -- COMMENT '加密盐值',
  name        VARCHAR(64)        NULL, -- COMMENT '姓名',
  status      TINYINT DEFAULT 1  NOT NULL, -- COMMENT '状态 0:删除，1：正常',
  create_user VARCHAR(64)        NULL, -- COMMENT '创建人主键编号',
  create_time TIMESTAMP          NULL, -- COMMENT '创建时间',
  update_user VARCHAR(64)        NULL, -- COMMENT '最后更新人主键编号',
  update_time TIMESTAMP          NULL-- COMMENT '最后更新日期'
);

-- 系统角色表
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id          BIGINT PRIMARY KEY      NOT NULL, -- COMMENT '角色ID',
  name        VARCHAR(64)             NOT NULL, -- COMMENT '角色名称',
  description VARCHAR(1024)           NULL, -- COMMENT '描述',
  status      TINYINT DEFAULT 1       NOT NULL, -- COMMENT '状态(0=删除,1=正常)',
  create_user BIGINT                  NULL, -- COMMENT '创建记录的用户编号',
  create_time TIMESTAMP               NULL, -- COMMENT '创建记录的时间',
  update_user BIGINT                  NULL, -- COMMENT '记录最后一次更新的用户编号',
  update_time TIMESTAMP               NULL-- COMMENT '记录最后一次更新时间'
);

-- 系统权限表
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission
(
  id          BIGINT PRIMARY KEY, -- COMMENT '权限ID',
  name        VARCHAR(256)  NOT NULL, -- COMMENT '权限名称',
  endpoint    VARCHAR(1024) NULL, -- 资源地址
  action      VARCHAR(16)   NULL, -- 资源地址
  description VARCHAR(1024) NULL, -- COMMENT '描述',
  status      TINYINT       NOT NULL, -- COMMENT '状态(0=删除,1=正常)',
  create_user BIGINT        NULL, -- COMMENT '创建记录的用户编号',
  create_time TIMESTAMP     NULL, -- COMMENT '创建记录的时间',
  update_user BIGINT        NULL, -- COMMENT '记录最后一次更新的用户编号',
  update_time TIMESTAMP     NULL-- COMMENT '记录最后一次更新时间'
);

-- 用户角色关联表
DROP TABLE IF EXISTS rele_user_role;
CREATE TABLE rele_user_role
(
  id          BIGINT PRIMARY KEY, -- COMMENT 'ID',
  user_id     BIGINT    NOT NULL, -- COMMENT '用户主键',
  role_id     BIGINT    NOT NULL, -- COMMENT '角色主键',
  status      TINYINT   NOT NULL, -- COMMENT '状态(0=删除,1=正常)',
  create_user BIGINT    NULL, -- COMMENT '创建记录的用户编号',
  create_time TIMESTAMP NULL, -- COMMENT '创建记录的时间',
  update_user BIGINT    NULL, -- COMMENT '记录最后一次更新的用户编号',
  update_time TIMESTAMP NULL -- COMMENT '记录最后一次更新时间'
);

-- 角色权限关联表
DROP TABLE IF EXISTS rele_role_permission;
CREATE TABLE rele_role_permission
(
  id            BIGINT PRIMARY KEY, -- COMMENT 'ID',
  role_id       BIGINT    NOT NULL, -- COMMENT '角色主键',
  permission_id BIGINT    NOT NULL, -- COMMENT '权限主键',
  status        TINYINT   NOT NULL, -- COMMENT '状态(0=删除,1=正常)',
  create_user   BIGINT    NULL, -- COMMENT '创建记录的用户编号',
  create_time   TIMESTAMP NULL, -- COMMENT '创建记录的时间',
  update_user   BIGINT    NULL, -- COMMENT '记录最后一次更新的用户编号',
  update_time   TIMESTAMP NULL-- COMMENT '记录最后一次更新时间'
);