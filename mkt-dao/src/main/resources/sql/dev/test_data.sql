INSERT INTO wx_contact (id, nick_name, head_img_url) VALUES (1, '老腊肉', 'http://123.com');
INSERT INTO wx_contact (id, nick_name, head_img_url) VALUES (2, '小鲜肉', 'http://1234.com');
INSERT INTO wx_contact (id, nick_name, head_img_url) VALUES (3, '滚刀肉', 'http://12345.com');

INSERT INTO customer (ID, wx_contact_id, user_id) VALUES (1, 1, 1);
INSERT INTO customer (ID, wx_contact_id, user_id) VALUES (2, 2, 2);
INSERT INTO customer (ID, wx_contact_id, user_id) VALUES (3, 3, 3);

INSERT INTO customer_dynamic (id, customer_id, event, event_date, article_id, article_title, user_id, article_tag, read_time, is_full_read, status, create_user, create_time, update_user, update_time)
VALUES (1021731663924703234, 3, 0, '2018-07-24 12:08:12', 1, 'title1', 1, 'TAG2', 20, 0, 1, NULL, '2018-07-24 12:08:12',
        NULL, '2018-07-24 12:08:12');

INSERT INTO customer_dynamic (id, customer_id, event, event_date, article_id, article_title, user_id, article_tag, read_time, is_full_read, status, create_user, create_time, update_user, update_time)
VALUES (1021731663924703237, 2, 0, '2018-07-24 12:08:12', 2, 'title1', 1, 'TAG2', 30, 1, 1, NULL, '2018-07-24 12:08:12',
        NULL, '2018-07-24 12:08:12');

INSERT INTO customer_dynamic (id, customer_id, event, event_date, article_id, article_title, user_id, article_tag, read_time, is_full_read, status, create_user, create_time, update_user, update_time)
VALUES (1021731663924703235, 2, 1, '2018-07-24 12:08:12', 1, 'string', 1, 'TAG1', 30, 0, 1, NULL, '2018-07-24 12:08:12',
        NULL, '2018-07-24 12:08:12');

INSERT INTO customer_dynamic (id, customer_id, event, event_date, article_id, article_title, user_id, article_tag, read_time, is_full_read, status, create_user, create_time, update_user, update_time)
VALUES (1021731663924703236, 3, 2, '2018-07-24 12:08:12', 1, 'string', 1, 'TAG1', 30, 0, 1, NULL, '2018-07-24 12:08:12',
        NULL, '2018-07-24 12:08:12');

INSERT INTO customer_dynamic (id, customer_id, event, event_date, article_id, article_title, user_id, article_tag, read_time, is_full_read, status, create_user, create_time, update_user, update_time)
VALUES (1021731663924703238, 3, 2, '2018-07-24 12:08:12', 1, 'string', 1, 'TAG1', 30, 0, 1, NULL, '2018-07-24 12:08:12',
        NULL, '2018-07-24 12:08:12');

INSERT INTO customer_dynamic (id, customer_id, event, event_date, article_id, article_title, user_id, article_tag, read_time, is_full_read, status, create_user, create_time, update_user, update_time)
VALUES (1021731663924703239, 3, 2, '2018-07-24 12:08:12', 1, 'string', 2, 'TAG1', 30, 0, 1, NULL, '2018-07-24 12:08:12',
        NULL, '2018-07-24 12:08:12');


INSERT INTO sys_user VALUES (4, 'yj', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '杨健', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (5, 'lmm', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '林媚媚', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (6, 'sgm', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '苏冠铭', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (7, 'swh', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '史伟华', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (8, 'wtf', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '王庭飞', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (9, 'xzh', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '谢泽宏', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (10, 'pgc', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '潘根成', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (11, 'ltl', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '梁天利', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (12, 'yml', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', '杨名良', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (13, 'ld', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', 'iPhone测试机的领导', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO sys_user VALUES (14, 'lcjl', '$2a$10$F6aBE8pDiiKnGG3jWk9vre24qvKYRBlozuGDGHHF3qpYfgREVqCse', 'e5a721750f676a48ee77f3fe8c5a58e9', 'iPhone测试机(理财经理)', NULL, '', '', '', 1, '', '', '', '',
                             NULL, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());

INSERT INTO rele_user_role VALUES (4, 4, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (5, 5, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (6, 6, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (7, 7, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (8, 8, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (9, 9, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (10, 10, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (11, 11, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (12, 12, 1, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (13, 13, 2, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());
INSERT INTO rele_user_role VALUES (14, 14, 3, 1, 1, CURRENT_TIMESTAMP(), 1, CURRENT_TIMESTAMP());