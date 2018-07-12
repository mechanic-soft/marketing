-- 系统用户表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
  id              BIGINT PRIMARY KEY NOT NULL, -- 用户ID
  username        VARCHAR(20)        NULL, -- 登录账户
  password        VARCHAR(255)       NOT NULL, -- 登录密码
  salt            VARCHAR(255)       NULL, -- 加密盐值
  real_name       VARCHAR(64)        NULL, -- 真实姓名
  wx_uin          BIGINT             NULL, -- 微信用户信息识别码(唯一)
  wx_username     VARCHAR(255)       NULL, -- 加密的微信号(唯一)
  wx_nickname     VARCHAR(255)       NULL, -- 微信昵称
  wx_head_img_url VARCHAR(2048)      NULL, -- 头像URL
  wx_sex          TINYINT            NULL, -- 微信性别
  wx_signature    VARCHAR(2048)      NULL, -- 微信签名
  status          TINYINT DEFAULT 1  NOT NULL, -- 状态 0:删除，1：正常
  create_user     VARCHAR(64)        NULL, -- 创建人主键编号
  create_time     TIMESTAMP          NULL, -- 创建时间
  update_user     VARCHAR(64)        NULL, -- 最后更新人主键编号
  update_time     TIMESTAMP          NULL-- 最后更新日期'
);

-- 系统角色表
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id          BIGINT PRIMARY KEY      NOT NULL, -- 角色ID
  name        VARCHAR(64)             NOT NULL, -- 角色名称
  description VARCHAR(1024)           NULL, -- 描述
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL-- 记录最后一次更新时间'
);

-- 系统权限表
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission
(
  id          BIGINT PRIMARY KEY, -- 权限ID
  name        VARCHAR(256)  NOT NULL, -- 权限名称
  endpoint    VARCHAR(1024) NULL, -- 资源地址
  action      VARCHAR(16)   NULL, -- 资源地址
  description VARCHAR(1024) NULL, -- 描述
  status      TINYINT       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT        NULL, -- 创建记录的用户编号
  create_time TIMESTAMP     NULL, -- 创建记录的时间
  update_user BIGINT        NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP     NULL-- 记录最后一次更新时间'
);

-- 用户角色关联表
DROP TABLE IF EXISTS rele_user_role;
CREATE TABLE rele_user_role
(
  id          BIGINT PRIMARY KEY, -- ID
  user_id     BIGINT    NOT NULL, -- 用户主键
  role_id     BIGINT    NOT NULL, -- 角色主键
  status      TINYINT   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT    NULL, -- 创建记录的用户编号
  create_time TIMESTAMP NULL, -- 创建记录的时间
  update_user BIGINT    NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP NULL -- 记录最后一次更新时间'
);

-- 角色权限关联表
DROP TABLE IF EXISTS rele_role_permission;
CREATE TABLE rele_role_permission
(
  id            BIGINT PRIMARY KEY, -- ID
  role_id       BIGINT    NOT NULL, -- 角色主键
  permission_id BIGINT    NOT NULL, -- 权限主键
  status        TINYINT   NOT NULL, -- 状态(0=删除,1=正常)
  create_user   BIGINT    NULL, -- 创建记录的用户编号
  create_time   TIMESTAMP NULL, -- 创建记录的时间
  update_user   BIGINT    NULL, -- 记录最后一次更新的用户编号
  update_time   TIMESTAMP NULL-- 记录最后一次更新时间'
);

-- 角色权限关联表
DROP TABLE IF EXISTS test_table;
CREATE TABLE test_table
(
  id   BIGINT PRIMARY KEY, -- ID
  name VARCHAR(64) NOT NULL -- 角色主键
);
