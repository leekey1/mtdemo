
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `loan_id` varchar(32) DEFAULT NULL COMMENT '进件ID',
  `demo_opprator` varchar(64) DEFAULT NULL COMMENT '操作人',
  `demo_opprator_type` varchar(64) DEFAULT NULL COMMENT '操作类型',
  `demo_opprator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `demo_money` decimal(14,2) DEFAULT NULL COMMENT '金额',
  `demo_rate` decimal(14,4) DEFAULT NULL COMMENT '比列',
  `demo_month` int(3) DEFAULT NULL COMMENT '借款月',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `updated_at` datetime DEFAULT NULL COMMENT '最后更新时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '最后更新者',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='demo表';
