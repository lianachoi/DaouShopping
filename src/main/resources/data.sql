-- 상품

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10001, '사각 접이식 캠핑 의자', 42000, 0, '10001-1.jpg', '10001-2.jpg',
        '', 10001, '', 0);

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10002, '가방 White', 2000, 0, '', '',
        '', 10001, '가방', 0);

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10003, '가방 Black', 2000, 0, '', '',
        '', 10001, '가방', 0);

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10004, '컵걸이 White', 1000, 0, '', '',
        '', 10001, '컵걸이', 0);

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10005, '컵걸이 Black', 1000, 0, '', '',
        '', 10001, '컵걸이', 0);

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10006, 'LED 캠핑 랜턴', 36000, 0, '10006-1.jpg', '10006-2.jpg',
        '부가기능	LED전구, 생활방수, 미끄럼방지, 충격방지, 충전식, 점멸', 10006, '', 0);

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10007, '2600mAh', 6000, 0, '', '',
        '', 10006, '배터리 선택', 0);

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10008, '3000mAh', 9000, 0, '', '',
        '', 10006, '배터리 선택', 0);

insert into items(item_id, item_name, price, sale_price, item_image, desc_image,
                  item_desc, parent_id, p_group_name, limit_qty)
values (10009, '휴대용 포충기', 17000, 0, '', '',
        '', 10006, '추가 캠핑용품', 0);
-- 옵션

insert into options(option_id, item_id, option_name, price)
values (10001, 10001, '다크 브라운', 0);

insert into options(option_id, item_id, option_name, price)
values (10002, 10001, '밀크 브라운', 0);

insert into options(option_id, item_id, option_name, price)
values (10003, 10001, '화이트 베이지', 0);

insert into options(option_id, item_id, option_name, price)
values (10004, 10006, '랜턴 21년형(배터리 미포함)', 0);

-- 유저

insert into users(user_id, user_pw, user_name, address1,
                  address2, point)
values ('daou', 'daou22!', '다우', '16878 경기도 용인시 수지구 디지털벨리로 81',
        '다우디지털스퀘어 6층', 5000);

insert into users(user_id, user_pw, user_name, address1,
                  address2, point)
values ('sunyoung', 'sy2022!', '최선영', '서울시 광진구 123-45',
        '303호', 5000);

-- 쿠폰

insert into coupons(coupon_id, coupon_name, exp_date, c_min, use_percent,
                    c_percent_limit, c_price, c_percent)
values (1, '5,000(신규회원 쿠폰, 1만원 이상 구매시)', 30, 10000, false,
    0, 5000, 0);

insert into coupons(coupon_id, coupon_name, exp_date, c_min, use_percent,
                    c_percent_limit, c_price, c_percent)
values (2, '30%(1만 5천원 이상 구매 시 최대 1만원 할인)', 10, 15000, true,
    10000, 0, 30);

-- 유저 쿠폰 부여

insert into user_coupons(seq, coupon_id, user_id, exp_date, used_flag)
values (1, 1, 'daou',TIMESTAMPADD(DAY, 30, NOW()), false);

insert into user_coupons(seq, coupon_id, user_id, exp_date, used_flag)
values (2, 2, 'daou',TIMESTAMPADD(DAY, 10, NOW()), false);

insert into user_coupons(seq, coupon_id, user_id, exp_date, used_flag)
values (3, 1, 'sunyoung',TIMESTAMPADD(DAY, 30, NOW()), false);

insert into user_coupons(seq, coupon_id, user_id, exp_date, used_flag)
values (4, 2, 'sunyoung',TIMESTAMPADD(DAY, 10, NOW()), false);

-- 입금계좌, 사용자 계좌

insert into accounts(account_id, valance) values ('1002-958-955-866', 0);
insert into accounts(account_id, valance) values ('1234-56789-12345', 1000000);
insert into accounts(account_id, valance) values ('1234', 1000000);
