--修改字段长度
ALTER TABLE `wx_contact` MODIFY COLUMN `attr_status`  bigint(11) NULL DEFAULT NULL AFTER `statues`;