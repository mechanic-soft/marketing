DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_permission;
DROP TABLE IF EXISTS rele_user_role;
DROP TABLE IF EXISTS rele_role_permission;
DROP TABLE IF EXISTS sys_menu;
/*DROP TABLE IF EXISTS rele_menu_permission;*/
DROP TABLE IF EXISTS sys_corp;
DROP TABLE IF EXISTS sys_log;
DROP TABLE IF EXISTS sys_visiting_card;
/*DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS article_regular;
DROP TABLE IF EXISTS article_like;
DROP TABLE IF EXISTS article_read;
DROP TABLE IF EXISTS article_shared;
DROP TABLE IF EXISTS article_subscription;*/
drop table if exists chat_records;
drop table if exists customer;
drop table if exists customer_dynamic;
drop table if exists rele_customer_tag;
drop table if exists customer_lifecycle_event;
drop table if exists rule;
drop table if exists rele_rule_customer_label;
drop table if exists rule_trigger_action;
drop table if exists tag;
drop table if exists tag_type;
/*drop table if exists rele_tag_article;*/
drop table if exists task;
drop table if exists rele_user_task;
drop table if exists wx_contact;
drop table if exists mp_user;

/*==============================================================*/
/* Table: 系统用户表                                              */
/*==============================================================*/
CREATE TABLE sys_user (
  id                 BIGINT PRIMARY KEY NOT NULL, -- 用户ID
  username           VARCHAR(20)        NULL, -- 登录账户
  password           VARCHAR(255)       NOT NULL, -- 登录密码
  salt               VARCHAR(255)       NULL, -- 加密盐值
  real_name          VARCHAR(64)        NULL, -- 真实姓名
  wx_uin             BIGINT             NULL, -- 微信用户信息识别码(唯一)
  wx_username        VARCHAR(255)       NULL, -- 加密的微信号(唯一)
  wx_nickname        VARCHAR(255)       NULL, -- 微信昵称
  wx_head_img_url    VARCHAR(2048)      NULL, -- 头像URL
  wx_sex             TINYINT            NULL, -- 微信性别
  wx_signature       VARCHAR(2048)      NULL, -- 微信签名
  mp_openid          VARCHAR(255)       NULL, -- 服务号OPENID
  mp_uuid            VARCHAR(255)       NULL, -- 服务号UIUD
  callcenter_user_id VARCHAR(64)        NULL, -- 外呼平台用户ID
  corp_id            BIGINT             NULL, -- 公司ID
  status             TINYINT DEFAULT 1  NOT NULL, -- 状态 0:删除，1：正常
  create_user        BIGINT             NULL, -- 创建记录的用户编号
  create_time        TIMESTAMP          NULL, -- 创建记录的时间
  update_user        BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time        TIMESTAMP          NULL -- 记录最后一次更新时间
);


/*==============================================================*/
/* Table: 系统角色表                                              */
/*==============================================================*/
CREATE TABLE sys_role (
  id          BIGINT PRIMARY KEY NOT NULL, -- 角色ID
  name        VARCHAR(64)        NOT NULL, -- 角色名称
  description VARCHAR(1024)      NULL, -- 描述
  status      TINYINT DEFAULT 1  NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP          NULL, -- 创建记录的时间
  update_user BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 系统权限表                                              */
/*==============================================================*/
CREATE TABLE sys_permission (
  id          BIGINT PRIMARY KEY NOT NULL, -- 权限ID
  name        VARCHAR(256)       NOT NULL, -- 权限名称
  endpoint    VARCHAR(1024)      NULL, -- 资源地址
  method      VARCHAR(16)        NULL, -- HTTP事件
  description VARCHAR(1024)      NULL, -- 描述
  status      TINYINT            NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP          NULL, -- 创建记录的时间
  update_user BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 用户角色关联表                                          */
/*==============================================================*/
CREATE TABLE rele_user_role (
  id          BIGINT PRIMARY KEY NOT NULL, -- ID
  user_id     BIGINT             NOT NULL, -- 用户主键
  role_id     BIGINT             NOT NULL, -- 角色主键
  status      TINYINT            NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP          NULL, -- 创建记录的时间
  update_user BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 角色权限关联表                                          */
/*==============================================================*/
CREATE TABLE rele_role_permission (
  id            BIGINT PRIMARY KEY NOT NULL, -- ID
  role_id       BIGINT             NOT NULL, -- 角色主键
  permission_id BIGINT             NOT NULL, -- 权限主键
  status        TINYINT            NOT NULL, -- 状态(0=删除,1=正常)
  create_user   BIGINT             NULL, -- 创建记录的用户编号
  create_time   TIMESTAMP          NULL, -- 创建记录的时间
  update_user   BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time   TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 系统菜单表                                              */
/*==============================================================*/
CREATE TABLE sys_menu (
  id            BIGINT PRIMARY KEY       NOT NULL, -- ID
  parent_id     BIGINT                   NOT NULL, -- 父级id
  name          VARCHAR(255)             NOT NULL, -- 菜单名
  url           VARCHAR(1024)            NOT NULL, -- URL
  permission_id BIGINT                   NOT NULL, -- 权限ID
  sort          TINYINT                  NOT NULL, -- 排序(升序)
  status        TINYINT                  NOT NULL, -- 状态(0=删除,1=正常)
  create_user   BIGINT                   NULL, -- 创建记录的用户编号
  create_time   TIMESTAMP                NULL, -- 创建记录的时间
  update_user   BIGINT                   NULL, -- 记录最后一次更新的用户编号
  update_time   TIMESTAMP                NULL -- 记录最后一次更新时间
);
/*==============================================================*/
/* Table: 菜单权限关联表                                          */
/*==============================================================*/
/*CREATE TABLE rele_menu_permission (
  id          BIGINT PRIMARY KEY       NOT NULL, -- ID
  menu_id     BIGINT                   NOT NULL, -- 父级id
  permission  BIGINT                   NOT NULL, -- 菜单名
  status      TINYINT                  NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                   NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                NULL, -- 创建记录的时间
  update_user BIGINT                   NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                NULL -- 记录最后一次更新时间
);*/

/*==============================================================*/
/* Table: 公司信息表                                              */
/*==============================================================*/
CREATE TABLE sys_corp (
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
CREATE TABLE sys_log (
  id          BIGINT PRIMARY KEY NOT NULL, -- ID
  username    TINYINT            NOT NULL, -- 用户名
  menu        VARCHAR(64)        NULL, -- 菜单
  url         VARCHAR(2048)      NULL, -- URL
  remark      VARCHAR(2048)      NULL, -- 备注
  status      TINYINT            NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP          NULL, -- 创建记录的时间
  update_user BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 名片表                                                 */
/*==============================================================*/
CREATE TABLE sys_visiting_card (
  id          BIGINT PRIMARY KEY NOT NULL, -- ID
  user_id     BIGINT             NOT NULL, -- 用户ID
  real_name   TINYINT            NOT NULL, -- 用户姓名
  phone       VARCHAR(32)        NULL, -- 手机号
  position    VARCHAR(64)        NULL, -- 职位
  address     VARCHAR(2048)      NULL, -- 地址
  qucode_url  VARCHAR(2048)      NULL, -- 微信二维码路径
  corp_id     BIGINT             NULL, -- 公司ID
  status      TINYINT            NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP          NULL, -- 创建记录的时间
  update_user BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 文章表                                                 */
/*==============================================================*/
/*CREATE TABLE article (
  id                   BIGINT PRIMARY KEY NOT NULL,
  source_url           VARCHAR(1024)      NULL, -- 转载文章的原始链接
  target_url           VARCHAR(1024)      NULL, -- 生成的自己的链接
  title                VARCHAR(512)       NULL, -- 标题
  icon                 varchar(512)       NULL, -- 图标
  content              varchar(1024)      NULL, -- 文章内容
  is_link              INTEGER            NULL, -- 是否转载链接的文章。0=否(手工编辑)，1=是(转载链接)
  is_send              INTEGER            NULL, -- 是否发送。0=否，1=是
  send_to_mp           INTEGER            NULL, -- 发送到服务号或微信。1=服务号，2=微信好友
  is_add_visiting_card INTEGER            NULL, -- 是否添加名片。0=否，1=是
  user_id              BIGINT             NOT NULL, -- 作者userId
  corp_id              BIGINT             NULL, -- 公司ID
  status               TINYINT DEFAULT 1  NOT NULL, -- 状态(0=删除,1=正常)
  create_user          BIGINT             NULL, -- 创建记录的用户编号
  create_time          TIMESTAMP          NULL, -- 创建记录的时间
  update_user          BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time          TIMESTAMP          NULL -- 记录最后一次更新时间
);*/

/*==============================================================*/
/* Table: 非微信文章信息正则规则                                   */
/*==============================================================*/
/*CREATE TABLE article_regular (
  id           BIGINT PRIMARY KEY NOT NULL,
  domain       varchar(100)       NULL, -- 网站域名
  title_reg    varchar(500)       NULL, -- title的正则提取规则
  icon_url_reg varchar(500)       NULL, -- icon_url的正则提取规则
  desc_reg     varchar(500)       NULL, -- desc的正则提取规则
  status       TINYINT DEFAULT 1  NOT NULL, -- 状态(0=删除,1=正常)
  create_user  BIGINT             NULL, -- 创建记录的用户编号
  create_time  TIMESTAMP          NULL, -- 创建记录的时间
  update_user  BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time  TIMESTAMP          NULL -- 记录最后一次更新时间
);*/

/*==============================================================*/
/* Table: 文章喜欢状态表                                          */
/*==============================================================*/
/*CREATE TABLE article_like (
  id              BIGINT PRIMARY KEY  NOT NULL,
  article_info_id BIGINT              NOT NULL, -- 文章id
  user_id         BIGINT              NOT NULL, -- 用户
  is_like         TINYINT default '0' NOT NULL, -- 喜欢状态，0：默认，1,：喜欢
  status          TINYINT DEFAULT 1   NOT NULL, -- 状态(0=删除,1=正常)
  create_user     BIGINT              NULL, -- 创建记录的用户编号
  create_time     TIMESTAMP           NULL, -- 创建记录的时间
  update_user     BIGINT              NULL, -- 记录最后一次更新的用户编号
  update_time     TIMESTAMP           NULL -- 记录最后一次更新时间
);*/

/*==============================================================*/
/* Table: 文章阅读表                                              */
/*==============================================================*/
/*CREATE TABLE article_read (
  id              BIGINT PRIMARY KEY NOT NULL,
  shared_info_id  BIGINT             NULL, -- 分享信息的id
  article_info_id BIGINT             NOT NULL, -- 文章id
  user_id         BIGINT             NOT NULL, -- 读者id
  start_time      timestamp          NULL, -- 开始阅读时间
  end_time        timestamp          NULL, -- 结束阅读时间
  reading_time    int default '0'    NULL, -- 阅读时长
  status          TINYINT DEFAULT 1  NOT NULL, -- 状态(0=删除,1=正常)
  create_user     BIGINT             NULL, -- 创建记录的用户编号
  create_time     TIMESTAMP          NULL, -- 创建记录的时间
  update_user     BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time     TIMESTAMP          NULL -- 记录最后一次更新时间
);
*/
/*==============================================================*/
/* Table: 文章分享表                                              */
/*==============================================================*/
/*CREATE TABLE article_shared (
  id              BIGINT PRIMARY KEY NOT NULL,
  parent_id       BIGINT             NULL, -- 上游分享人
  article_info_id BIGINT             NOT NULL, -- 文章id
  user_id         BIGINT             NOT NULL, -- 分享人
  status          TINYINT DEFAULT 1  NOT NULL, -- 状态(0=删除,1=正常)
  create_user     BIGINT             NULL, -- 创建记录的用户编号
  create_time     TIMESTAMP          NULL, -- 创建记录的时间
  update_user     BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time     TIMESTAMP          NULL -- 记录最后一次更新时间
);*/

/*==============================================================*/
/* Table: 文章订阅表                                              */
/*==============================================================*/
/*CREATE TABLE article_subscription (
  id                   BIGINT PRIMARY KEY  NOT NULL,
  subscription_user_id BIGINT              NOT NULL, -- 被订阅的人id
  user_id              BIGINT              NOT NULL, -- 普通用户id
  subscription         TINYINT default '0' NOT NULL, -- 订阅状态，0：默认，1：订阅
  status               TINYINT DEFAULT 1   NOT NULL, -- 状态(0=删除,1=正常)
  create_user          BIGINT              NULL, -- 创建记录的用户编号
  create_time          TIMESTAMP           NULL, -- 创建记录的时间
  update_user          BIGINT              NULL, -- 记录最后一次更新的用户编号
  update_time          TIMESTAMP           NULL -- 记录最后一次更新时间
);*/

/*==============================================================*/
/* Table: 聊天记录表                                             */
/*==============================================================*/
CREATE TABLE chat_records (
  id          BIGINT PRIMARY KEY NOT NULL,
  customer_id BIGINT             NOT NULL,
  wx_username VARCHAR(256)       NULL,
  msg_type    TINYINT DEFAULT 0  NULL, -- 消息类型(0=文本,1=图片,2=表情,3=文件)
  content     VARCHAR(1024)      NULL,
  url         VARCHAR(256)       NULL,
  is_send     TINYINT            NULL, -- 状态(0=接收,1=发送)
  send_time   TIMESTAMP          NULL,
  status      TINYINT DEFAULT 1  NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP          NULL, -- 创建记录的时间
  update_user BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP          NULL -- 记录最后一次更新时间
);


/*==============================================================*/
/* Table: 自定义标签表                                            */
/*==============================================================*/
/* CREATE TABLE custom_tag
 (
   id          BIGINT PRIMARY KEY                 NOT NULL,
   customer_id BIGINT                             NOT NULL,
   name        VARCHAR(256),
   type_id     BIGINT,
   corp_id     BIGINT                             NULL, -- 公司ID
   status      TINYINT DEFAULT 1                  NOT NULL, -- 状态(0=删除,1=正常)
   create_user BIGINT                             NULL, -- 创建记录的用户编号
   create_time TIMESTAMP                          NULL, -- 创建记录的时间
   update_user BIGINT                             NULL, -- 记录最后一次更新的用户编号
   update_time TIMESTAMP                          NULL -- 记录最后一次更新时间
 );
*/
/*==============================================================*/
/* Table: 客户表                                                 */
/*==============================================================*/
CREATE TABLE customer (
  id                 BIGINT PRIMARY KEY NOT NULL,
  wx_contact_id      BIGINT             NULL, -- 微信联系人ID
  mp_user_id         BIGINT             NULL, -- 服务号用户ID
  call_time          TIMESTAMP          NULL, -- 呼叫时间(同步)
  customer_code      VARCHAR(256)       NULL, -- 用户编码(同步)
  is_agree_add_wx    TINYINT            NULL, -- 是否同意添加微信(同步)
  wx_id              VARCHAR(128)       NULL, -- 微信号(同步)
  phone              VARCHAR(32)        NULL, -- 手机号(同步)
  remark             VARCHAR(256)       NULL, -- 备注(同步)
  maturity           TINYINT            NULL, -- 客户成熟度
  is_open_account    TINYINT            NULL, -- 是否开户
  callcenter_user_id VARCHAR(64)        NULL, -- 外呼平台用户ID(同步)理财经理ID
  user_id            BIGINT             NULL, -- 客户id
  corp_id            BIGINT             NULL, -- 公司ID
  status             TINYINT DEFAULT 1  NOT NULL, -- 状态(0=删除,1=正常)
  create_user        BIGINT             NULL, -- 创建记录的用户编号
  create_time        TIMESTAMP          NULL, -- 创建记录的时间
  update_user        BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time        TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 客户动态表                                              */
/*==============================================================*/
CREATE TABLE customer_dynamic (
  id            BIGINT PRIMARY KEY                  NOT NULL,
  customer_id   BIGINT                              NOT NULL, -- 客户ID
  event         TINYINT                             NULL, -- 事件(0=阅读,1=订阅,2=联系,3=转发)
  event_date    TIMESTAMP                           NULL, -- 发生日期
  article_id    BIGINT                              NULL, -- 文章ID
  article_title VARCHAR(512)                        NULL, -- 文章标题
  article_tag   VARCHAR(512)                        NULL, -- 文章标签
  read_time     INTEGER                             NULL, -- 阅读时长
  is_full_read  TINYINT                             NULL, -- 是否阅读全文
  user_id       BIGINT                              NULL, -- 用户ID
  status        TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user   BIGINT                              NULL, -- 创建记录的用户编号
  create_time   TIMESTAMP                           NULL, -- 创建记录的时间
  update_user   BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time   TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 客户标签关联表                                          */
/*==============================================================*/
CREATE TABLE rele_customer_tag (
  id          BIGINT PRIMARY KEY                  NOT NULL,
  customer_id BIGINT                              NOT NULL,
  tag_id      BIGINT                              NOT NULL, -- 系统标签ID
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 客户生命周期事件表                                       */
/*==============================================================*/
CREATE TABLE customer_lifecycle_event (
  id          BIGINT PRIMARY KEY                  NOT NULL,
  customer_id BIGINT                              NOT NULL, -- 客户ID
  event       TINYINT DEFAULT 0                   NULL, -- 事件(0=阅读,1=订阅,2=联系,3=转发,4=加微,5=开户,6=呼叫)
  event_date  TIMESTAMP                           NULL, -- 事件日期
  user_id     BIGINT                              NULL, -- 用户ID
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 规则表                                                 */
/*==============================================================*/
CREATE TABLE rule (
  id          BIGINT PRIMARY KEY NOT NULL,
  title       VARCHAR(256)       NOT NULL,
  content     VARCHAR(256)       NULL,
  start_date  TIMESTAMP          NULL, -- 开始日期
  end_date    TIMESTAMP          NULL, -- 结束日期
  status      TINYINT DEFAULT 1  NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP          NULL, -- 创建记录的时间
  update_user BIGINT             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 规则客户标签关联表                                       */
/*==============================================================*/
CREATE TABLE rele_rule_customer_label (
  id          BIGINT PRIMARY KEY                  NOT NULL,
  rule_id     BIGINT                              NOT NULL,
  tag_id      BIGINT                              NULL,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 规则触发行为表                                          */
/*==============================================================*/
CREATE TABLE rule_trigger_action (
  id          BIGINT PRIMARY KEY                  NOT NULL,
  rule_id     BIGINT                              NOT NULL,
  action      TINYINT DEFAULT 1                   NULL, -- 行为(0=阅读,1=订阅,3=聊天)
  `condition` TINYINT DEFAULT 1                   NULL, -- 条件(0= >,1= < ,2= >=,3= <=,4= =)
  frequency   TINYINT                             NULL, -- 次数
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 标签表                                             */
/*==============================================================*/
CREATE TABLE tag (
  id          BIGINT PRIMARY KEY                     NOT NULL,
  path        VARCHAR(256)                           NULL, -- 层级路径
  name        VARCHAR(64)                            NULL, -- 名称
  tag_type_id BIGINT                                 NOT NULL, -- 类型ID
  tag_src     TINYINT DEFAULT 0                      NULL, -- 标签来源(0=阅读,1=外呼,2=聊天)
  is_sys      TINYINT DEFAULT 0                      NULL, -- 是否系统标签(0=系统标签，1=自定义标签)
  status      TINYINT DEFAULT 1                      NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                                 NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                              NULL, -- 创建记录的时间
  update_user BIGINT                                 NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                              NULL -- 记录最后一次更新时间
);


/*==============================================================*/
/* Table: 标签类别表                                            */
/*==============================================================*/
CREATE TABLE tag_type (
  id          BIGINT PRIMARY KEY                 NOT NULL,
  parent_id   BIGINT default '0'                 NULL, -- 父级ID
  name        VARCHAR(256)                       NULL, -- 标签名称
  code        VARCHAR(256)                       NULL, -- 标签编码(仅数据处理用)
  status      TINYINT DEFAULT 1                  NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                          NULL, -- 创建记录的时间
  update_user BIGINT                             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                          NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 标签文章关联表                                            */
/*==============================================================*/
/*
CREATE TABLE rele_tag_article
(
  id          BIGINT PRIMARY KEY                 NOT NULL,
  tag_id      BIGINT                             NULL, -- 标签ID
  article_id  BIGINT                             NULL, -- 文章ID
  status      TINYINT DEFAULT 1                  NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                             NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                          NULL, -- 创建记录的时间
  update_user BIGINT                             NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                          NULL -- 记录最后一次更新时间
);
*/
/*==============================================================*/
/* Table: 任务表                                                 */
/*==============================================================*/
CREATE TABLE task (
  id          BIGINT PRIMARY KEY                  NOT NULL,
  title       VARCHAR(256)                        NULL, -- 标题
  content     VARCHAR(1024)                       NULL, -- 内容
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 用户任务关联表                                          */
/*==============================================================*/
CREATE TABLE rele_user_task (
  id          BIGINT PRIMARY KEY                  NOT NULL,
  task_id     BIGINT                              NULL,
  user_id     BIGINT                              NULL,
  status      TINYINT DEFAULT 1                   NOT NULL, -- 状态(0=删除,1=正常)
  create_user BIGINT                              NULL, -- 创建记录的用户编号
  create_time TIMESTAMP                           NULL, -- 创建记录的时间
  update_user BIGINT                              NULL, -- 记录最后一次更新的用户编号
  update_time TIMESTAMP                           NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 微信联系人                                              */
/*==============================================================*/
CREATE TABLE wx_contact (
  id                  BIGINT PRIMARY KEY      NOT NULL,
  user_id             BIGINT                  NULL,
  uin                 BIGINT                  NULL, -- 微信用户信息识别码(唯一)
  user_name           VARCHAR(255)            NULL, -- 加密的微信号(唯一)
  nick_name           VARCHAR(255)            NULL, -- 微信昵称
  head_img_url        VARCHAR(2048)           NULL, -- 头像URL
  contact_flag        INT,
  member_count        INT,
  member_list         VARCHAR(256),
  remark_name         VARCHAR(256),
  hide_input_bar_flag INT,
  sex                 INT                     NULL, -- 微信性别
  signature           VARCHAR(2048)           NULL, -- 微信签名
  verify_flag         INT,
  owner_uin           INT,
  py_initial          VARCHAR(256),
  py_quan_pin         VARCHAR(256),
  remark_py_initial   VARCHAR(256),
  remark_py_quan_pin  VARCHAR(256),
  star_friend         INT,
  app_account_flag    INT,
  statues             INT,
  attr_status         INT,
  province            VARCHAR(256),
  city                VARCHAR(256),
  alias               VARCHAR(256),
  sns_flag            INT,
  uni_friend          INT,
  display_name        VARCHAR(256),
  chat_room_id        INT,
  key_word            VARCHAR(256),
  wncry_chat_room_id  VARCHAR(256),
  encry_chat_room_id  VARCHAR(256),
  is_sync             TINYINT, -- 是否同步(0=未同步，1=已同步)
  status              TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user         BIGINT                  NULL, -- 创建记录的用户编号
  create_time         TIMESTAMP               NULL, -- 创建记录的时间
  update_user         BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time         TIMESTAMP               NULL -- 记录最后一次更新时间
);

/*==============================================================*/
/* Table: 服务号用户                                              */
/*==============================================================*/
/*CREATE TABLE mp_user (
  id              BIGINT PRIMARY KEY      NOT NULL,
  mp_nickname     VARCHAR(256)            NULL, -- 昵称
  mp_head_img_url VARCHAR(1024)           NULL, -- 头像URL
  mp_openid       VARCHAR(255)            NULL, -- 服务号OPENID
  mp_uuid         VARCHAR(255)            NULL, -- 服务号UIUD
  mp_province     VARCHAR(32)             NULL, -- 省
  mp_city         VARCHAR(32)             NULL, -- 市
  mp_country      VARCHAR(32)             NULL, -- 县
  mp_address      VARCHAR(255)            NULL, -- 地址
  mp_subscribe    tinyint                 null, -- 是否关注，0：未关注，1：关注
  status          TINYINT DEFAULT 1       NOT NULL, -- 状态(0=删除,1=正常)
  create_user     BIGINT                  NULL, -- 创建记录的用户编号
  create_time     TIMESTAMP               NULL, -- 创建记录的时间
  update_user     BIGINT                  NULL, -- 记录最后一次更新的用户编号
  update_time     TIMESTAMP               NULL -- 记录最后一次更新时间
);*/
/*==============================================================*/
/* Table: 外呼信息表                                              */
/*==============================================================*/
CREATE TABLE external_call (
  id                   bigint(20) PRIMARY KEY NOT NULL COMMENT '主键',
  item_code            varchar(150)           NULL COMMENT '项目编码',
  item_name            varchar(255)           NULL COMMENT '项目名称',
  task_code            varchar(255)           NULL COMMENT '任务编码',
  task_name            varchar(255)           NULL COMMENT '任务名称',
  business_hall_code   varchar(255)           NULL COMMENT '营业厅编码',
  business_hall_name   varchar(255)           NULL COMMENT '营业厅名称',
  call_time            timestamp              NULL COMMENT '呼叫时间',
  user_code            varchar(255)           NULL COMMENT '用户编码',
  customer_id          varchar(255)           NULL COMMENT '坐席工号（客户经理id）',
  customer_name        varchar(255)           NULL COMMENT '坐席工号名称（客户经理名称）',
  call_time_start      timestamp              NULL COMMENT '呼叫开始时间',
  call_time_answer     timestamp              NULL COMMENT '呼叫应答时间',
  call_time_end        timestamp              NULL COMMENT '呼叫结束时间',
  talk_time            bigint(20)             NULL COMMENT '通话时长',
  ring_time            bigint(20)             NULL COMMENT '振铃时长',
  line_up_time         bigint(20)             NULL COMMENT '排队时长',
  is_answer            int(5)                 NULL COMMENT '是否接通',
  call_result          varchar(30)            NULL COMMENT '呼叫结果(用户挂机、用户未接通)',
  call_type            varchar(5)             NULL COMMENT '呼叫类别(1呼、2呼、3呼)',
  call_in_out          varchar(20)            NULL COMMENT '呼叫方向(呼出、呼入)',
  is_send_msg          varchar(15)            NULL COMMENT '是否下发短信',
  msg                  varchar(1000)          NULL COMMENT '短信内容',
  record_msg           varchar(1000)          NULL COMMENT '录音内容',
  reserved_field_one   varchar(255)           NULL COMMENT '预留1',
  reserved_field_two   varchar(255)           NULL COMMENT '预留2',
  reserved_field_three varchar(255)           NULL COMMENT '预留3',
  reserved_field_four  varchar(255)           NULL COMMENT '预留4',
  reserved_field_five  varchar(255)           NULL COMMENT '预留5',
  remark               varchar(255)           NULL COMMENT '备注',
  status               tinyint(4)             NOT NULL COMMENT '状态(0=删除,1=正常)',
  create_user          bigint(20)             NULL COMMENT '创建记录的用户编号',
  create_time          timestamp              NULL COMMENT '创建记录的时间',
  update_user          bigint(20)             NULL COMMENT '记录最后一次更新的用户编号',
  update_time          timestamp              NULL COMMENT '记录最后一次更新的时间'
);

/*==============================================================*/
/* Table: 问卷记录表                                              */
/*==============================================================*/
CREATE TABLE questionnaire (
  id              bigint(40) PRIMARY KEY NOT NULL COMMENT '主键',
  sequence_number bigint(40)             NULL COMMENT '问卷序号',
  is_not_null     bigint(5)              NULL COMMENT '是否必填',
  content         varchar(1500)          NULL COMMENT '问卷内容',
  result          varchar(1000)          NULL COMMENT '问卷结果',
  remark          varchar(500)           NULL COMMENT '备注',
  status          tinyint(4)             NOT NULL COMMENT '状态(0=删除,1=正常)',
  create_user     bigint(20)             NULL COMMENT '创建记录的用户编号',
  create_time     timestamp              NULL COMMENT '创建记录的时间',
  update_user     bigint(20)             NULL COMMENT '记录最后一次更新的用户编号',
  update_time     timestamp              NULL COMMENT '记录最后一次更新的时间'
);

/*==============================================================*/
/* Table: 外呼信息与问卷记录关联关系表                                              */
/*==============================================================*/
CREATE TABLE rele_external_call_questionnaire (
  id               bigint(35) PRIMARY KEY NOT NULL DEFAULT '0' COMMENT 'id',
  external_call_id bigint(35)             NOT NULL COMMENT '外呼信息id',
  questionnaire_id bigint(35)             NOT NULL COMMENT '问卷记录id',
  status           tinyint(4)             NOT NULL DEFAULT '1' COMMENT '状态(0=删除,1=正常)',
  create_user      bigint(20)             NULL COMMENT '创建记录的用户编号',
  create_time      timestamp              NULL COMMENT '创建记录的时间',
  update_user      bigint(20)             NULL COMMENT '记录最后一次更新的用户编号',
  update_time      timestamp              NULL COMMENT '记录最后一次更新的时间'
);

