CREATE TABLE `client` (
`id` int(12) NOT NULL  COMMENT 'ID',
`frist_name` VARCHAR(25) NOT NULL  COMMENT '姓',
`second_name` VARCHAR(25) NOT NULL  COMMENT '名',
`title` VARCHAR(3) DEFAULT NULL  COMMENT '性别',
`email` VARCHAR(32) NOT NULL  COMMENT '邮箱',
`password` VARCHAR(44) NOT NULL  COMMENT '密码',
`adress` VARCHAR(100) NOT NULL  COMMENT '地址',
`city` VARCHAR(30) NOT NULL  COMMENT '城市',
`country` VARCHAR(30) NOT NULL  COMMENT '国家',
`zip` VARCHAR(20) DEFAULT NULL  COMMENT '邮编号码',
`phone` VARCHAR(20) DEFAULT NULL  COMMENT '电话',
`time_zone` VARCHAR(100) NOT NULL  COMMENT '时区',
`created_at` datetime DEFAULT NULL  COMMENT '创建时间',
`created_by` VARCHAR(32) DEFAULT NULL  COMMENT '创建者',
`updated_at` datetime DEFAULT NULL  COMMENT '最后更新时间',
`updated_by` VARCHAR(32) DEFAULT NULL  COMMENT '最后更新者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';
