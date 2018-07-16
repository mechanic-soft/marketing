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
  id          BIGINT PRIMARY KEY, -- 权限ID
  name        VARCHAR(256)  NOT NULL, -- 权限名称
  endpoint    VARCHAR(1024) NULL, -- 资源地址
  action      VARCHAR(16)   NULL, -- 资源地址
  description VARCHAR(1024) NULL, -- 描述
  status      TINYINT       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT        NULL, -- 创建记录的用户编号
  create_time TIMESTAMP     NULL, -- 创建记录的时间
  update_user BIGINT        NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP     NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 用户角色关联表                                          */
/*==============================================================*/
CREATE TABLE rele_user_role
(
  id          BIGINT PRIMARY KEY, -- ID
  user_id     BIGINT    NOT NULL, -- 用户主键
  role_id     BIGINT    NOT NULL, -- 角色主键
  status      TINYINT   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT    NULL, -- 创建记录的用户编号
  create_time TIMESTAMP NULL, -- 创建记录的时间
  update_user BIGINT    NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 角色权限关联表                                          */
/*==============================================================*/
CREATE TABLE rele_role_permission
(
  id            BIGINT PRIMARY KEY, -- ID
  role_id       BIGINT    NOT NULL, -- 角色主键
  permission_id BIGINT    NOT NULL, -- 权限主键
  status        TINYINT   NOT NULL, -- 状态(0=删除,1=正常)
  create_user   BIGINT    NULL, -- 创建记录的用户编号
  create_time   TIMESTAMP NULL, -- 创建记录的时间
  update_user   BIGINT    NULL, -- 记录最后一次更新的用户编号
  update_time   TIMESTAMP NULL -- 记录最后一次更新时间
);

-- 公司信息表
/*==============================================================*/
/* Table: 日志信息表                                              */
/*==============================================================*/
CREATE TABLE sys_corp
(
  id          BIGINT PRIMARY KEY, -- ID
  name        VARCHAR(64) NOT NULL, -- 角色主键
  status      TINYINT     NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT      NULL, -- 创建记录的用户编号
  create_time TIMESTAMP   NULL, -- 创建记录的时间
  update_user BIGINT      NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP   NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 日志信息表                                              */
/*==============================================================*/
CREATE TABLE sys_log
(
  id          BIGINT PRIMARY KEY, -- ID
  real_name   TINYINT       NOT NULL, -- 用户姓名
  menu        VARCHAR(64)   NULL, -- 菜单
  url         VARCHAR(2048) NULL, -- URL
  remark      VARCHAR(2048) NULL, -- 备注
  status      TINYINT       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT        NULL, -- 创建记录的用户编号
  create_time TIMESTAMP     NULL, -- 创建记录的时间
  update_user BIGINT        NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP     NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 名片表                                                 */
/*==============================================================*/
CREATE TABLE sys_namecard
(
  id          BIGINT PRIMARY KEY, -- ID
  user_id     BIGINT        NOT NULL, -- 用户ID
  real_name   TINYINT       NOT NULL, -- 用户姓名
  phone       VARCHAR(32)   NULL, -- 手机号
  position    VARCHAR(64)   NULL, -- 职位
  address     VARCHAR(2048) NULL, -- 地址
  qucode_url  VARCHAR(2048) NULL, -- 微信二维码路径
  status      TINYINT       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT        NULL, -- 创建记录的用户编号
  create_time TIMESTAMP     NULL, -- 创建记录的时间
  update_user BIGINT        NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP     NULL -- 记录最后一次更新时间
);


/*==============================================================*/
/* Table: chat_records                                          */
/*==============================================================*/
create table chat_records
(
  id           bigint                  not null,
  customer_id  bigint                  not null,
  user_id      varchar(256),
  event_date   date,
  content      varchar(1024),
  msg_type     bigint,
  url          varchar(256),
  is_input_msg tinyint,
  status       TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user  BIGINT                  NULL, -- 创建记录的用户编号
  create_time  TIMESTAMP               NULL, -- 创建记录的时间
  update_user  BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time  TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: custom_label                                          */
/*==============================================================*/
create table custom_label
(
  id            bigint                  not null,
  customer_id   bigint                  not null,
  laber_name    varchar(256),
  laber_type_id bigint,
  user_id       bigint,
  add_time      datetime,
  status        TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user   BIGINT                  NULL, -- 创建记录的用户编号
  create_time   TIMESTAMP               NULL, -- 创建记录的时间
  update_user   BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time   TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer
(
  id                  bigint                  not null,
  call_date           datetime,
  customer_code       varchar(256),
  is_agree_add_wechat tinyint,
  wechat_id           varchar(64),
  phone               varchar(32),
  remark              varchar(256),
  customer_maturity   int,
  is_wechat_contact   tinyint,
  is_open_account     tinyint,
  wechat_contact_id   bigint,
  status              TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user         BIGINT                  NULL, -- 创建记录的用户编号
  create_time         TIMESTAMP               NULL, -- 创建记录的时间
  update_user         BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time         TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: customer_dynamic                                      */
/*==============================================================*/
create table customer_dynamic
(
  id          bigint                  not null,
  customer_id bigint                  not null,
  action      varchar(256),
  event_date  date,
  article_id  bigint,
  user_id     bigint,
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: customer_label                                        */
/*==============================================================*/
create table customer_label
(
  id          bigint                  not null,
  customer_id bigint                  not null,
  label_id    bigint,
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: customer_lifecycle_event                              */
/*==============================================================*/
create table customer_lifecycle_event
(
  id          bigint                  not null,
  customer_id bigint                  not null,
  event       varchar(256),
  event_date  date,
  user_id     bigint,
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: rule                                                  */
/*==============================================================*/
create table rule
(
  id          bigint                  not null,
  title       varchar(256),
  content     varchar(256),
  start_date  date,
  end_date    date,
  user_id     bigint,
  add_time    datetime,
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: rule_customer_label                                   */
/*==============================================================*/
create table rule_customer_label
(
  id          bigint                  not null,
  rule_id     bigint                  not null,
  label_id    bigint,
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: rule_trigger_action                                   */
/*==============================================================*/
create table rule_trigger_action
(
  id          bigint                  not null,
  rule_id     bigint                  not null,
  action      varchar(32),
  "condition" varchar(4),
  frequency   int,
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: system_label                                          */
/*==============================================================*/
create table system_label
(
  id             bigint                  not null,
  name           varchar(64),
  label_type     varchar(64)             not null,
  sub_label_type varchar(64),
  label_src      varchar(64),
  status         TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user    BIGINT                  NULL, -- 创建记录的用户编号
  create_time    TIMESTAMP               NULL, -- 创建记录的时间
  update_user    BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time    TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: task                                                  */
/*==============================================================*/
create table task
(
  id          bigint                  not null,
  title       varchar(256),
  content     varchar(256),
  user_id     bigint,
  add_time    datetime,
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: user_task                                             */
/*==============================================================*/
create table user_task
(
  id          bigint                  not null,
  task_id     bigint,
  user_id     bigint,
  status      TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                  NULL, -- 创建记录的用户编号
  create_time TIMESTAMP               NULL, -- 创建记录的时间
  update_user BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: wechat_contact                                        */
/*==============================================================*/
create table wechat_contact
(
  id                  bigint primary key      not null,
  uin                 int,
  user_name           varchar(256),
  nick_name           varchar(256),
  head_img_url        varchar(256),
  contact_flag        int,
  member_count        int,
  member_list         varchar(256),
  remark_name         varchar(256),
  hide_input_bar_flag int,
  sex                 tinyint,
  signature           varchar(256),
  verify_flag         int,
  owner_uin           int,
  py_initial          varchar(256),
  py_quan_pin         varchar(256),
  remark_py_initial   varchar(256),
  remark_py_quan_pin  varchar(256),
  star_friend         int,
  app_account_flag    int,
  statues             int,
  attrStatus          int,
  province            varchar(256),
  cty                 varchar(256),
  alias               varchar(256),
  sns_flag            int,
  uni_friend          int,
  display_name        varchar(256),
  chat_room_id        int,
  keyword             varchar(256),
  wncry_chat_room_id  varchar(256),
  user_id             bigint,
  status              TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user         BIGINT                  NULL, -- 创建记录的用户编号
  create_time         TIMESTAMP               NULL, -- 创建记录的时间
  update_user         BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time         TIMESTAMP               NULL -- 记录最后一次更新时间
);

