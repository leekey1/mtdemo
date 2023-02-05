CREATE TABLE `client_balence_history` (
`id` int(32) NOT NULL  COMMENT 'ID',
`mt_tickett` VARCHAR(16) NOT NULL  COMMENT '订单号',
`price` int NOT NULL  COMMENT '价格',
`type` VARCHAR(10) NOT NULL  COMMENT '类型',
`before_balence` int NOT NULL  COMMENT '账户金额前',
`after_after` int NOT NULL  COMMENT '账户金额后',
`email` VARCHAR(32) NOT NULL  COMMENT '用户邮箱',
`login` int(15) NOT NULL  COMMENT '交易账户',
`meta_profile_id` VARCHAR(80) DEFAULT NULL  COMMENT 'profileID',
`created_at` datetime DEFAULT NULL  COMMENT '创建时间',
`created_by` VARCHAR(32) DEFAULT NULL  COMMENT '创建者',
`updated_at` datetime DEFAULT NULL  COMMENT '最后更新时间',
`updated_by` VARCHAR(32) DEFAULT NULL  COMMENT '最后更新者',
`version` int(11) DEFAULT NULL  COMMENT '版本',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易账户余额充值提现信息';
