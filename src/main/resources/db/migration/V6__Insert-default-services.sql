DELETE FROM SERVICE WHERE ID < 21;
DELETE FROM SERVICE_TYPE WHERE ID < 6;

ALTER TABLE SERVICE_TYPE ALTER COLUMN VERSION SET DEFAULT 0;
INSERT INTO SERVICE_TYPE(id, "name") VALUES
(1, 'Combo'),
(2, 'Uốn'),
(3, 'Nhuộm'),
(4, 'Dưỡng'),
(5, 'Chăm sóc da');

ALTER TABLE SERVICE ALTER COLUMN VERSiON SET DEFAULT 0;
INSERT INTO SERVICE(id, description, duration, "name", price, service_type_id, image) VALUES
(1, 'SHINE COMBO +4 MAX TỎA SÁNG', 120, 'SHINE COMBO +4 MAX TỎA SÁNG', 185000, 1,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FCOMBO1.jpg?alt=media&token=46eda02e-4c72-49a6-8052-0e4915329ec0'),
(2, 'SHINE COMBO +3 DETOX TOÀN DIỆN', 90, 'SHINE COMBO +3 DETOX TOÀN DIỆN', 165000, 1,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FCOMBO2.jpg?alt=media&token=46eda02e-4c72-49a6-8052-0e4915329ec0'),
(3, 'SHINE COMBO +3 SẠCH MỤN', 90, 'SHINE COMBO +3 SẠCH MỤN', 165000, 1,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FCOMBO3.jpg?alt=media&token=46eda02e-4c72-49a6-8052-0e4915329ec0'),
(4, 'SHINE COMBO +2 CHĂM SÓC DA CƠ BẢN', 90, 'SHINE COMBO +3 CHĂM SÓC DA CƠ BẢN', 140000, 1,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FCOMBO4.jpg?alt=media&token=46eda02e-4c72-49a6-8052-0e4915329ec0'),
(5, 'SHINE COMBO', 60, 'SHINE COMBO', 100000, 1,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FCOMBO5.jpg?alt=media&token=46eda02e-4c72-49a6-8052-0e4915329ec0'),
(6, 'KID COMBO', 40, 'KID COMBO', 80000, 1,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FCOMBO6.jpg?alt=media&token=46eda02e-4c72-49a6-8052-0e4915329ec0'),
(7, 'UỐN PHỒNG TIÊU CHUẨN: Tạo kiểu dễ dàng vào nếp nhanh chóng', 60, 'UỐN PHỒNG TIÊU CHUẨN', 234000, 2,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FUONG1.jpg?alt=media&token=23390c00-7186-4408-860e-4271a76765ea'),
(8, 'TẠO PHỒNG: Hiệu quả trong 15 ngày, thay đổi kiểu tóc linh hoạt', 30, 'TẠO PHỒNG', 99000, 2,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FUONG2.jpg?alt=media&token=23390c00-7186-4408-860e-4271a76765ea'),
(9, 'UỐN CAO CẤP: Bổ sung collagen, keratin suôn mượt, không amoniac, tóc phồng hoàn hảo', 60, 'UỐN CAO CẤP', 315000, 2,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FUONG3.jpg?alt=media&token=23390c00-7186-4408-860e-4271a76765ea'),
(10, 'PREMLOCK: Hot trend 2020, dẫn đầu xu hướng với kiểu tóc nhà vô địch', 60, 'PREMLOCK', 799000, 2,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FUONG4.jpg?alt=media&token=23390c00-7186-4408-860e-4271a76765ea'),
(11, 'NHUỘM TIÊU CHUẨN: Lên màu cực nhanh, giữ màu cực lâu', 60, 'NHUỘM TIÊU CHUẨN', 162000, 3,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FNHUOM1.jpg?alt=media&token=a1ef50da-fb31-433a-a000-50230fa709a2'),
(12, 'TẨY TÓC: Bước nền hoàn hảo cho màu tóc sáng', 30, 'TẨY TÓC', 90000, 3,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FNHUOM2.jpg?alt=media&token=a1ef50da-fb31-433a-a000-50230fa709a2'),
(13, 'NHUỘM CAO CẤP KIỂU Ý: Tinh chất dưỡng dầu dừa, sáp ong, vitamin C, không gây hư tổn, màu chuẩn tuyệt đối', 60, 'NHUỘM CAO CẤP KIỂU Ý', 260000, 3,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FNHUOM3.jpg?alt=media&token=a1ef50da-fb31-433a-a000-50230fa709a2'),
(14, 'NHUỘM HIGHLIGHT: Kiểu tóc dành cho dân chơi', 30, 'NHUỘM HIGHLIGHT', 70000, 3,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FNHUOM4.png?alt=media&token=fe2763dc-a8bd-4b37-aefa-d31afec873ba'),
(15, 'HẤP DƯỠNG OLIU', 60, 'HẤP DƯỠNG OLIU', 119000, 4,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FDUONG1.png?alt=media&token=35a6fded-9c58-430a-a705-cb075fa85d98'),
(16, 'PHỤC HỒI NANO', 60, 'PHỤC HỒI NANO', 159000, 4,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FDUONG2.jpg?alt=media&token=35a6fded-9c58-430a-a705-cb075fa85d98'),
(17, 'MẶT NẠ LẠNH COOL SHINE', 15, 'MẶT NẠ LẠNH COOL SHINE', 30000, 5,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FDA1.jpg?alt=media&token=7a8ca747-4e21-42b2-bb0d-524b0fbac43a'),
(18, 'LẤY MỤN MŨI CHUYÊN SÂU', 15, 'LẤY MỤN MŨI CHUYÊN SÂU', 30000, 5,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FDA2.png?alt=media&token=a1ef50da-fb31-433a-a000-50230fa709a2'),
(19, 'TẨY DA CHẾT SỦI BỌT', 15, 'TẨY DA CHẾT SỦI BỌT', 30000, 5,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FDA3.png?alt=media&token=a1ef50da-fb31-433a-a000-50230fa709a2'),
(20, 'DETOX DA ĐẦU', 15, 'DETOX DA ĐẦU', 30000, 5,
'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2FDA4.png?alt=media&token=a1ef50da-fb31-433a-a000-50230fa709a2');

ALTER TABLE SERVICE ADD COLUMN IF NOT EXISTS BOOKING_IMAGE VARCHAR;
ALTER TABLE SERVICE ADD COLUMN IF NOT EXISTS BOOKING_RECOMMEND_IMAGE VARCHAR;
ALTER TABLE SERVICE ADD COLUMN IF NOT EXISTS IS_RECOMMEND BOOLEAN DEFAULT FALSE;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_COMBO1.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00',
BOOKING_RECOMMEND_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_RECOMMEND_COMBO1.jpg?alt=media&token=a8e39396-9153-4778-b6ed-2bf16f9996ac',
IS_RECOMMEND = TRUE
WHERE ID = 1;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_COMBO2.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00'
WHERE ID = 2;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_COMBO3.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00'
WHERE ID = 3;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_COMBO4.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00'
WHERE ID = 4;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_COMBO5.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00',
BOOKING_RECOMMEND_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_RECOMMEND_COMBO5.jpg?alt=media&token=a8e39396-9153-4778-b6ed-2bf16f9996ac',
IS_RECOMMEND = TRUE
WHERE ID = 5;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_COMBO6.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00'
WHERE ID = 6;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_UONG1.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00'
WHERE ID = 7;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_UONG2.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00'
WHERE ID = 8;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_UONG3.jpg?alt=media&token=b74d0deb-9aea-42fd-be01-14f95ff78e00',
BOOKING_RECOMMEND_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_RECOMMEND_UONG3.jpg?alt=media&token=a8e39396-9153-4778-b6ed-2bf16f9996ac',
IS_RECOMMEND = TRUE
WHERE ID = 9;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_UONG4.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 10;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_NHUOM1.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 11;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_NHUOM2.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 12;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_NHUOM3.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 13;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_NHUOM4.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 14;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_DUONG1.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 15;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_DUONG2.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 16;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_DA1.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 17;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_DA2.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 18;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_DA3.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 19;

UPDATE SERVICE SET
BOOKING_IMAGE = 'https://firebasestorage.googleapis.com/v0/b/sms-fe.appspot.com/o/service%2Fbooking%2FBOOKING_DA4.jpg?alt=media&token=175de380-da35-432a-8a91-bcc22a4577da'
WHERE ID = 20;
