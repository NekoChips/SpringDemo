drop table if exists `vip_card`;
create table `vip_card`
(
    `id`           int          not null auto_increment comment 'id',
    `card_no`      varchar(2048) not null comment '卡号',
    `name`         varchar(2048) not null comment '用户名',
    `id_number`    varchar(2048) not null comment '身份证号',
    `phone_number` varchar(2048) not null comment '手机号',
    `create_time`  datetime default current_timestamp comment '创建时间',
    `update_time`  datetime comment '更新时间',
    primary key (`id`)
) comment '会员卡表'
    engine = InnoDB
    default charset = utf8;

insert into vip_card
(card_no, name, id_number, phone_number)
values ('10000000000', '张三', '11010119900307221X', '13812345678');