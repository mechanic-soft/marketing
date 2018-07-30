-- 修改字段长度
ALTER TABLE `wx_contact` MODIFY COLUMN `attr_status`  bigint(11) NULL DEFAULT NULL AFTER `statues`;

-- 聊天记录添加昵称字段
ALTER TABLE `chat_records` ADD COLUMN `wx_nick_name`  varchar(256) NULL;

-- 规则行为表的frequency字段的tinyin类型改为INT类型。
alter table rule_trigger_action MODIFY column frequency INT ;

-- 微信联系人状态字段长度
ALTER TABLE `wx_contact` MODIFY COLUMN `attr_status`  bigint(20) NULL DEFAULT NULL AFTER `statues`;

-- 标签表添加user_id 字段（不知道是谁在tag实体中添加了userId，但是数据库并没有）
ALTER TABLE `tag` ADD COLUMN `user_id`  bigint(20) NULL;