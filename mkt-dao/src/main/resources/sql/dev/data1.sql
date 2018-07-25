
INSERT INTO wx_contact(id,nickname,head_img_url) VALUES (1,'老腊肉','http://123.com');
INSERT INTO wx_contact(id,nickname,head_img_url) VALUES (2,'小鲜肉','http://1234.com');
INSERT INTO wx_contact(id,nickname,head_img_url) VALUES (3,'滚刀肉','http://12345.com');

INSERT INTO customer(ID,wx_contact_id,user_id) VALUES (1,1,1);
INSERT INTO customer(ID,wx_contact_id,user_id) VALUES (2,2,2);
INSERT INTO customer(ID,wx_contact_id,user_id) VALUES (3,3,3);

INSERT INTO customer_dynamic ( id, customer_id, event, event_date, article_id, article_title,
user_id, article_tag, read_time, is_full_read,status,create_user,create_time,update_user,update_time
) VALUES ( 1021731663924703234, 3, 0, '2018-07-24 12:08:12', 1, 'title1', 1, 'TAG2',
20, 0,1,NULL,'2018-07-24 12:08:12',NULL,'2018-07-24 12:08:12' );

INSERT INTO customer_dynamic ( id, customer_id, event, event_date, article_id, article_title,
user_id, article_tag, read_time, is_full_read,status,create_user,create_time,update_user,update_time
) VALUES ( 1021731663924703237, 2, 0, '2018-07-24 12:08:12', 2, 'title1', 1, 'TAG2',
30, 1,1,NULL,'2018-07-24 12:08:12',NULL,'2018-07-24 12:08:12' );

INSERT INTO customer_dynamic ( id, customer_id, event, event_date, article_id, article_title,
user_id, article_tag, read_time, is_full_read,status,create_user,create_time,update_user,update_time
) VALUES ( 1021731663924703235, 2, 1, '2018-07-24 12:08:12', 1, 'string', 1, 'TAG1',
30, 0,1,NULL,'2018-07-24 12:08:12',NULL,'2018-07-24 12:08:12' );

INSERT INTO customer_dynamic ( id, customer_id, event, event_date, article_id, article_title,
user_id, article_tag, read_time, is_full_read,status,create_user,create_time,update_user,update_time
) VALUES ( 1021731663924703236, 3, 2, '2018-07-24 12:08:12', 1, 'string', 1, 'TAG1',
30, 0,1,NULL,'2018-07-24 12:08:12',NULL,'2018-07-24 12:08:12' );

INSERT INTO customer_dynamic ( id, customer_id, event, event_date, article_id, article_title,
user_id, article_tag, read_time, is_full_read,status,create_user,create_time,update_user,update_time
) VALUES ( 1021731663924703238, 3, 2, '2018-07-24 12:08:12', 1, 'string', 1, 'TAG1',
30, 0,1,NULL,'2018-07-24 12:08:12',NULL,'2018-07-24 12:08:12' );

INSERT INTO customer_dynamic ( id, customer_id, event, event_date, article_id, article_title,
user_id, article_tag, read_time, is_full_read,status,create_user,create_time,update_user,update_time
) VALUES ( 1021731663924703239, 3, 2, '2018-07-24 12:08:12', 1, 'string', 2, 'TAG1',
30, 0,1,NULL,'2018-07-24 12:08:12',NULL,'2018-07-24 12:08:12' );
