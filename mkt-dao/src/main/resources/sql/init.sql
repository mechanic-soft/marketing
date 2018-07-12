CREATE DATABASE IF NOT EXISTS marketing DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'marketing'@'%' IDENTIFIED BY 'marketing#2018';

GRANT ALL PRIVILEGES ON marketing.* TO 'marketing'@'%' IDENTIFIED BY 'marketing#2018' WITH GRANT OPTION;


create table article
(
  id bigint not null comment '主键'
    primary key,
  origin_url varchar(500) default '' null comment '转载文章的原链接
  url varchar(500) default '' null comment '生成自己的链接
  title varchar(512) default '' not null comment '标题
  icon varchar(512) null comment '文章的icon
  content varchar(1024) default '' null comment '文章内容
  `desc` varchar(512) default '' null comment '文章的描述
  user_id bigint not null comment '作者userId
  state int(1) default '0' not null comment '状态：0：正常，2：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
;

create table article_info_regular
(
  id bigint not null
    primary key,
  domain varchar(100) null comment '网站域名
  title_reg varchar(500) null comment 'title的正则提取规则
  icon_url_reg varchar(500) null comment 'icon_url的正则提取规则
  desc_reg varchar(500) null comment 'desc的正则提取规则
  create_id bigint null comment '创建者id
  create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间
  modify_id bigint null comment '修改人id
  modify_time int null comment '修改时间
  state int(1) default '0' null comment '状态：0：正常，2：删除'
)
  comment '非微信文章信息正则规则'
;

create table article_like
(
  id bigint auto_increment comment '主键'
    primary key,
  article_info_id bigint not null comment '文章id
  user_id bigint not null comment '用户
  `like` int(1) default '0' not null comment '喜欢状态，0：默认，1,：喜欢
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
;

create table article_read
(
  id bigint auto_increment comment '主键'
    primary key,
  shared_info_id bigint null comment '分享信息的id
  article_info_id bigint not null comment '文章id
  user_id bigint not null comment '读者id
  start_time timestamp null comment '开始阅读时间
  end_time timestamp null comment '结束阅读时间
  reading_time int default '0' null comment '阅读时长
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP not null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
;

create table article_shared
(
  id bigint auto_increment comment '主键'
    primary key,
  parent_id bigint null comment '上游分享人
  article_info_id bigint not null comment '文章id
  user_id bigint not null comment '分享人
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  share_status tinyint default '0' null comment '分享状态 0：未分享成功，1：已经分享
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
;

create table article_subscription
(
  id bigint auto_increment comment '主键'
    primary key,
  subscription_user_id bigint not null comment '被订阅的人id
  user_id bigint not null comment '普通用户id
  subscription int(1) default '0' not null comment '订阅状态，0：默认，1：订阅
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
;

create table module_info
(
  id bigint auto_increment comment '主键'
    primary key,
  name varchar(255) default '' not null comment '模块名
  label varchar(255) default '' not null comment '模块标签
  remark varchar(255) default '' not null comment '备注
  parent_id bigint(1) default '0' not null comment '父级id
  user_id bigint not null comment '创建人
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
  comment '模块表'
;

create table role_info
(
  id bigint auto_increment comment '主键'
    primary key,
  name varchar(255) default '' not null comment '角色名
  remark varchar(255) default '' not null comment '备注
  user_id bigint not null comment '创建人
  role_level int(1) not null comment '角色级别 1：超级管理员 ，2:管理员，3：普通理财经理
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
  comment '角色表'
;

create table role_module_rel
(
  id bigint auto_increment comment '主键'
    primary key,
  role_id bigint not null comment '角色id
  module_id bigint not null comment '模块id
  ramark varchar(255) default '' not null comment '备注
  user_id bigint not null comment '创建人
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
  comment '角色模块关系表'
;

create table tag_article
(
  id bigint auto_increment comment '主键'
    primary key,
  article_info_id bigint not null comment '标签所属的文章id
  tag_id bigint not null comment '标签id
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
  comment '用户标签表'
;

create table tag_info
(
  id bigint auto_increment comment '主键'
    primary key,
  user_id bigint not null comment '标签创建人
  name varchar(255) default '' not null comment '标签名
  type tinyint(1) default '0' null comment '类别：1：文章标签，2：人的标签
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
  comment '标签表'
;

create table tag_user
(
  id bigint auto_increment comment '主键'
    primary key,
  user_id bigint not null comment '标签所属的人id
  tag_id bigint not null comment '标签id
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
  comment '用户标签表'
;

create table template_info
(
  id bigint auto_increment comment '主键'
    primary key,
  first_msg varchar(255) not null comment 'first描述
  content varchar(255) not null comment '内容
  remark_msg varchar(255) default '' not null comment 'remark描述
  url varchar(520) default '' null comment '超链接
  title varchar(250) default '' null comment '标题
  user_id bigint not null comment '更新人id
  state tinyint(1) default '0' null comment '状态，0：正常，1：删除
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间
  modify_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
;

create table sys_user
(
  id bigint not null comment '主键
  wechat_user_id bigint not null comment '对应微信用户表
  phone varchar(11) default '' null comment '手机号码
  user_name varchar(20) default '' null comment '用户名字
  account_name varchar(255) default '' not null comment '登录账号
  password varchar(255) default '' not null comment '登录密码
  salt varchar(255) default '' not null comment '加密盐值
  remark varchar(500) default '' null comment '备注
  state int(1) default '0' null comment '状态：0：是正常，2是删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
  my_phone varchar(11) default '' null comment '手机号码
  my_user_name varchar(20) default '' null comment '用户名字
  primary key (id, wechat_user_id)
)
  comment '用户个人信息'
;

create table wx_user
(
  id bigint not null comment '主键'
    primary key,
  open_id varchar(64) not null comment '微信号在公众号的唯一标识
  union_id varchar(64) default '' null comment '第三方的绑定标识
  nick_name varchar(128) default '' null comment '微信昵称
  sex int(1) default '0' null comment '性别标识，0：未知，1：男性，2女性
  head_img_url varchar(1000) default '' null comment '微信头像url
  country varchar(32) default '' null comment '县
  province varchar(32) default '' null comment '省
  city varchar(32) default '' null comment '城市
  address varchar(255) default '' null comment '地址
  subscribe tinyint default '0' null comment '是否关注，0：未关注，1：关注
  state bigint default '0' null comment '状态：0：是正常，2是删除
  create_time timestamp default CURRENT_TIMESTAMP null,
  update_time timestamp default CURRENT_TIMESTAMP null,
  owner_user_id bigint null comment '所属的理财经理id
  role_id bigint null comment '角色id'
)
  comment '微信个人信息'
;

