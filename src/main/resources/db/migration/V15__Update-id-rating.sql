select setval('SEQ_RATING', 200);
UPDATE booking SET status = 'IN_PROGRESS' WHERE status = 'IN_PROGESS';
INSERT INTO SETTING (ID, VERSION, KEY, VALUE1, VALUE2) VALUES
(4, 0, 'email.order', 'Quý khách đã đặt hàng thành công. <br/>Danh sách sản phẩm: <br/>$$product$$', 'V-barbershop Xác nhận đơn hàng'),
(5, 0, 'time.max.wait', '1', null);
