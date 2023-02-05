CREATE TABLE `client_challenge` (
`id` int(12) NOT NULL  COMMENT 'ID',
`email` VARCHAR(50) NOT NULL  COMMENT '用户邮箱',
`start_dt` datetime(10) NOT NULL  COMMENT '开始日期',
`end_dt` datetime(10) NOT NULL  COMMENT '结束日期',
`challenge_level` VARCHAR(3) DEFAULT NULL  COMMENT '挑战级别',
`result` VARCHAR(32) NOT NULL  COMMENT '结果',
`trading_days` VARCHAR(2) DEFAULT NULL  COMMENT '交易天数',
`daily_max_loss` int(10) DEFAULT NULL  COMMENT '每日最大损失',
`max_loss` int(10) DEFAULT NULL  COMMENT '最大损失',
`profit_target` int(10) DEFAULT NULL  COMMENT '当前盈利',
`created_at` datetime NOT NULL  COMMENT '创建时间',
`created_by` VARCHAR(32) DEFAULT NULL  COMMENT '创建者',
`updated_at` datetime NOT NULL  COMMENT '最后更新时间',
`updated_by` VARCHAR(32) DEFAULT NULL  COMMENT '最后更新者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户挑战';
