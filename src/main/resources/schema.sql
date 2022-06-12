create table items
(
    item_id integer AUTO_INCREMENT not null,
    item_name varchar(100) not null,
    price integer default 0,
    sale_price integer default 0,
    item_image varchar(50),
    desc_image varchar(50),
    item_desc varchar(500),
    parent_id integer,
    p_group_name varchar(50),
    limit_qty int,
    primary key(item_id)
);

create table options
(
    option_id integer not null,
    item_id integer,
    option_name varchar(100) not null,
    price integer default 0,
    primary key(option_id),
    foreign key (item_id) references items(item_id)
);

create table users
(
    user_id varchar(30) not null,
    user_pw varchar(30) not null,
    user_name varchar(30) not null,
    address1 varchar(100),
    address2 varchar(100),
    point integer default 0,
    primary key(user_id)
);

create table orders
(
    order_id varchar(30) not null,
    user_id varchar(30) not null,
    order_date date not null,
    use_point integer default 0,
    use_coupon integer default 0,
    coupon_seq integer default 0,
    new_point integer default 0,
    status integer, -- 기준정보 테이블 코드로 관리 (status)
    use_card boolean,
    v_account varchar(20), -- 입금받을 가상계좌
    primary key(order_id),
    foreign key (user_id) references users(user_id)
);

create table order_items
(
    item_seq integer not null,
    order_id varchar(30) not null,
    item_id integer not null,
    option_id integer not null,
    qty integer default 1,
    primary key(item_seq),
    foreign key (order_id) references orders(order_id),
    foreign key (item_id) references items(item_id),
    foreign key (option_id) references options(option_id)
);

create table coupons
(
    coupon_id integer AUTO_INCREMENT not null,
    coupon_name varchar(50) not null,
    exp_date integer not null,         --쿠폰만료일
    c_min integer default 0,          --쿠폰사용최소금액
    use_percent boolean,            --퍼센트쿠폰/할인쿠폰여부
    c_percent_limit integer default 0,--퍼센트쿠폰할인한도
    c_price integer default 0,        --쿠폰할인금액
    c_percent integer,              --쿠폰할인퍼센트
    primary key(coupon_id)
);

create table user_coupons
(
    seq integer AUTO_INCREMENT not null,
    coupon_id integer not null,
    user_id varchar(30) not null,
    exp_date date,
    used_flag boolean,
    primary key(seq),
    foreign key (coupon_id) references coupons(coupon_id),
    foreign key (user_id) references users(user_id)
);

create table points
(
    point_code integer not null,
    point_name varchar(50),
    point integer default 0,
    primary key(point_code)
);

create table point_his
(
    seq integer AUTO_INCREMENT not null,
    tran_date date not null,
    user_id varchar(30) not null,
    point_code int not null,
    point integer default 0,
    order_id varchar(30),
    primary key(seq),
    foreign key (point_code) references points(point_code),
    foreign key (user_id) references users(user_id),
    foreign key (order_id) references orders(order_id)
);

create table cards
(
    card_id varchar(16) not null,
    yy smallint not null,
    mm smallint not null,
    cvs smallint not null,
    pw smallint not null,
    usage_amount integer, --사용금액
    primary key(card_id)
);

create table card_his
(
    seq integer AUTO_INCREMENT not null,
    card_id varchar(16) not null,
    tran_date date not null,
    tran_amount integer default 0,
    usage_info varchar(100),
    primary key(seq),
    foreign key (card_id) references cards(card_id)
);

create table accounts
(
    account_id varchar(20) not null,
    valance integer default 0
);

create table account_his
(
    seq integer AUTO_INCREMENT not null,
    from_id varchar(20) not null,
    to_id varchar(20) not null,
    tran_date date not null,
    tran_amount integer default 0
);