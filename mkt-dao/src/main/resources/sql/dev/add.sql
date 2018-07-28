--修改字段长度
ALTER TABLE `wx_contact` MODIFY COLUMN `attr_status`  bigint(11) NULL DEFAULT NULL AFTER `statues`;

--聊天记录添加昵称字段
ALTER TABLE `chat_records` ADD COLUMN `wx_nick_name`  varchar(256) NULL;