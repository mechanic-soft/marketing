DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_permission;
DROP TABLE IF EXISTS rele_user_role;
DROP TABLE IF EXISTS rele_role_permission;
DROP TABLE IF EXISTS sys_corp;
DROP TABLE IF EXISTS sys_log;
DROP TABLE IF EXISTS sys_namecard;
drop table if exists chat_records;
drop table if exists custom_label;
drop table if exists customer;
drop table if exists customer_dynamic;
drop table if exists customer_label;
drop table if exists customer_lifecycle_event;
drop table if exists rule;
drop table if exists rule_customer_label;
drop table if exists rule_trigger_action;
drop table if exists system_label;
drop table if exists task;
drop table if exists user_task;
drop table if exists wechat_contact;

/*==============================================================*/
/* Table: 系统用户表                                              */
/*==============================================================*/
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
  mp_openid       VARCHAR(255)       NULL, -- 服务号OPENID
  mp_uuid         VARCHAR(255)       NULL, -- 服务号UIUD
  status          TINYINT DEFAULT 1  NOT NULL, -- 状态 0:删除，1：正常
  create_user     BIGINT             NULL, -- 创建记录的用户编号
  create_time     TIMESTAMP          NULL, -- 创建记录的时间
  update_user     BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time     TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 系统角色表                                              */
/*==============================================================*/
CREATE TABLE sys_role (
  id          BIGINT PRIMARY KEY      NOT NULL, -- 角色ID
  name        VARCHAR(64)             NOT NULL, -- 角色名称
  description VARCHAR(1024)           NULL, -- 描述
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 系统权限表                                              */
/*==============================================================*/
CREATE TABLE sys_permission
(
  id          BIGINT PRIMARY KEY               NOT NULL, -- 权限ID
  name        VARCHAR(256)                     NOT NULL, -- 权限名称
  endpoint    VARCHAR(1024)                    NULL, -- 资源地址
  action      VARCHAR(16)                      NULL, -- 资源地址
  description VARCHAR(1024)                    NULL, -- 描述
  status      TINYINT                          NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                           NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                        NULL, -- 创建记录的时间
  update_user BIGINT                           NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                        NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 用户角色关联表                                          */
/*==============================================================*/
CREATE TABLE rele_user_role
(
  id          BIGINT PRIMARY KEY               NOT NULL, -- ID
  user_id     BIGINT                           NOT NULL, -- 用户主键
  role_id     BIGINT                           NOT NULL, -- 角色主键
  status      TINYINT                          NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                           NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                        NULL, -- 创建记录的时间
  update_user BIGINT                           NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                        NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 角色权限关联表                                          */
/*==============================================================*/
CREATE TABLE rele_role_permission
(
  id            BIGINT PRIMARY KEY               NOT NULL, -- ID
  role_id       BIGINT                           NOT NULL, -- 角色主键
  permission_id BIGINT                           NOT NULL, -- 权限主键
  status        TINYINT                          NOT NULL, -- 状态(0=删除,1=正常)
  create_user   BIGINT                           NULL, -- 创建记录的用户编号
  create_time   TIMESTAMP                        NULL, -- 创建记录的时间
  update_user   BIGINT                           NULL, -- 记录最后一次更新的用户编号
  update_time   TIMESTAMP                        NULL -- 记录最后一次更新时间
);

-- 公司信息表
/*==============================================================*/
/* Table: 日志信息表                                              */
/*==============================================================*/
CREATE TABLE sys_corp
(
  id          BIGINT PRIMARY KEY               NOT NULL, -- ID
  name        VARCHAR(64)                      NOT NULL, -- 角色主键
  status      TINYINT                          NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                           NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                        NULL, -- 创建记录的时间
  update_user BIGINT                           NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                        NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 日志信息表                                              */
/*==============================================================*/
CREATE TABLE sys_log
(
  id          BIGINT PRIMARY KEY               NOT NULL, -- ID
  real_name   TINYINT                          NOT NULL, -- 用户姓名
  menu        VARCHAR(64)                      NULL, -- 菜单
  url         VARCHAR(2048)                    NULL, -- URL
  remark      VARCHAR(2048)                    NULL, -- 备注
  status      TINYINT                          NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                           NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                        NULL, -- 创建记录的时间
  update_user BIGINT                           NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                        NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 名片表                                                 */
/*==============================================================*/
CREATE TABLE sys_namecard
(
  id          BIGINT PRIMARY KEY               NOT NULL, -- ID
  user_id     BIGINT                           NOT NULL, -- 用户ID
  real_name   TINYINT                          NOT NULL, -- 用户姓名
  phone       VARCHAR(32)                      NULL, -- 手机号
  position    VARCHAR(64)                      NULL, -- 职位
  address     VARCHAR(2048)                    NULL, -- 地址
  qucode_url  VARCHAR(2048)                    NULL, -- 微信二维码路径
  status      TINYINT                          NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                           NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                        NULL, -- 创建记录的时间
  update_user BIGINT                           NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                        NULL -- 记录最后一次更新时间
);


/*==============================================================*/
/* Table: chat_records                                          */
/*==============================================================*/
CREATE TABLE chat_records
(
  id           BIGINT PRIMARY KEY               NOT NULL,
  customer_id  BIGINT                           NOT NULL,
  user_id      VARCHAR(256),
  event_date   DATE,
  content      VARCHAR(1024),
  msg_type     BIGINT,
  url          VARCHAR(256),
  is_input_msg TINYINT,
  status       TINYINT DEFAULT 1                NOT NULL, -- 状态(0=删除,1=正常)
  create_user  BIGINT                           NULL, -- 创建记录的用户编号
  create_time  TIMESTAMP                        NULL, -- 创建记录的时间
  update_user  BIGINT                           NULL, -- 记录最后一次更新的用户编号
  update_time  TIMESTAMP                        NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: custom_label                                          */
/*==============================================================*/
CREATE TABLE custom_label
(
  id            BIGINT PRIMARY KEY                 NOT NULL,
  customer_id   BIGINT                             NOT NULL,
  laber_name    VARCHAR(256),
  laber_type_id BIGINT,
  user_id       BIGINT,
  add_time      TIMESTAMP,
  status        TINYINT DEFAULT 1                  NOT NULL, -- 状态(0=删除,1=正常)
  create_user   BIGINT                             NULL, -- 创建记录的用户编号
  create_time   TIMESTAMP                          NULL, -- 创建记录的时间
  update_user   BIGINT                             NULL, -- 记录最后一次更新的用户编号
  update_time   TIMESTAMP                          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
CREATE TABLE customer
(
  id                  BIGINT PRIMARY KEY                NOT NULL,
  call_date           TIMESTAMP,
  customer_code       VARCHAR(256),
  is_agree_add_wechat TINYINT,
  wechat_id           VARCHAR(64),
  phone               VARCHAR(32),
  remark              VARCHAR(256),
  customer_maturity   TINYINT,
  is_wechat_contact   TINYINT,
  is_open_account     TINYINT,
  wechat_contact_id   BIGINT,
  status              TINYINT DEFAULT 1                 NOT NULL, -- 状态(0=删除,1=正常)
  create_user         BIGINT                            NULL, -- 创建记录的用户编号
  create_time         TIMESTAMP                         NULL, -- 创建记录的时间
  update_user         BIGINT                            NULL, -- 记录最后一次更新的用户编号
  update_time         TIMESTAMP                         NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: customer_dynamic                                      */
/*==============================================================*/
CREATE TABLE customer_dynamic
(
  id          BIGINT PRIMARY KEY                  NOT NULL,
  customer_id BIGINT                              NOT NULL,
  action      VARCHAR(256),
  event_date  DATE,
  article_id  BIGINT,
  user_id     BIGINT,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: customer_label                                        */
/*==============================================================*/
CREATE TABLE customer_label
(
  id          BIGINT PRIMARY KEY                  NOT NULL,
  customer_id BIGINT                              NOT NULL,
  label_id    BIGINT,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: customer_lifecycle_event                              */
/*==============================================================*/
CREATE TABLE customer_lifecycle_event
(
  id          BIGINT PRIMARY KEY                  NOT NULL,
  customer_id BIGINT                              NOT NULL,
  event       VARCHAR(256),
  event_date  DATE,
  user_id     BIGINT,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: rule                                                  */
/*==============================================================*/
CREATE TABLE rule
(
  id          BIGINT PRIMARY KEY                  NOT NULL,
  title       VARCHAR(256),
  content     VARCHAR(256),
  start_date  DATE,
  end_date    DATE,
  user_id     BIGINT,
  add_time    TIMESTAMP,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: rule_customer_label                                   */
/*==============================================================*/
CREATE TABLE rule_customer_label
(
  id          BIGINT PRIMARY KEY                  NOT NULL,
  rule_id     BIGINT                              NOT NULL,
  label_id    BIGINT,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: rule_trigger_action                                   */
/*==============================================================*/
CREATE TABLE rule_trigger_action
(
  id          BIGINT PRIMARY KEY                  NOT NULL,
  rule_id     BIGINT                              NOT NULL,
  action      VARCHAR(32),
  "condition" VARCHAR(4),
  frequency   TINYINT,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: system_label                                          */
/*==============================================================*/
CREATE TABLE system_label
(
  id             BIGINT PRIMARY KEY                 NOT NULL,
  name           VARCHAR(64),
  label_type     VARCHAR(64)                        NOT NULL,
  sub_label_type VARCHAR(64),
  label_src      VARCHAR(64),
  status         TINYINT DEFAULT 1                  NOT NULL, -- 状态(0=删除,1=正常)
  create_user    BIGINT                             NULL, -- 创建记录的用户编号
  create_time    TIMESTAMP                          NULL, -- 创建记录的时间
  update_user    BIGINT                             NULL, -- 记录最后一次更新的用户编号
  update_time    TIMESTAMP                          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: task                                                  */
/*==============================================================*/
CREATE TABLE task
(
  id          BIGINT PRIMARY KEY                  NOT NULL,
  title       VARCHAR(256),
  content     VARCHAR(256),
  user_id     BIGINT,
  add_time    TIMESTAMP,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: user_task                                             */
/*==============================================================*/
CREATE TABLE user_task
(
  id          BIGINT PRIMARY KEY                  NOT NULL,
  task_id     BIGINT,
  user_id     BIGINT,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: wechat_contact                                        */
/*==============================================================*/
CREATE TABLE wechat_contact
(
  id                  BIGINT PRIMARY KEY      NOT NULL,
  uin                 TINYINT,
  user_name           VARCHAR(256),
  nick_name           VARCHAR(256),
  head_img_url        VARCHAR(256),
  contact_flag        TINYINT,
  member_count        TINYINT,
  member_list         VARCHAR(256),
  remark_name         VARCHAR(256),
  hide_input_bar_flag TINYINT,
  sex                 TINYINT,
  signature           VARCHAR(256),
  verify_flag         TINYINT,
  owner_uin           TINYINT,
  py_initial          VARCHAR(256),
  py_quan_pin         VARCHAR(256),
  remark_py_initial   VARCHAR(256),
  remark_py_quan_pin  VARCHAR(256),
  star_friend         TINYINT,
  app_account_flag    TINYINT,
  statues             TINYINT,
  attrStatus          TINYINT,
  province            VARCHAR(256),
  cty                 VARCHAR(256),
  alias               VARCHAR(256),
  sns_flag            TINYINT,
  uni_friend          TINYINT,
  display_name        VARCHAR(256),
  chat_room_id        TINYINT,
  keyword             VARCHAR(256),
  wncry_chat_room_id  VARCHAR(256),
  user_id             BIGINT,
  status              TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user         BIGINT                  NULL, -- 创建记录的用户编号
  create_time         TIMESTAMP               NULL, -- 创建记录的时间
  update_user         BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time         TIMESTAMP               NULL -- 记录最后一次更新时间
);

