CREATE TABLE `meta_profile` (
`id` int(10) NOT NULL  COMMENT 'ID',
`broker` VARCHAR(100) NOT NULL  COMMENT '服务详细名',
`server` VARCHAR(30) NOT NULL  COMMENT '服务名',
`api_profile_id` VARCHAR(80) NOT NULL  COMMENT 'Profile ID',
`api_profile_name` VARCHAR(30) NOT NULL  COMMENT 'profile名',
`broker_timezone` VARCHAR(100) NOT NULL  COMMENT '时区',
`broker_dst_switch_timezone` VARCHAR(100) NOT NULL  COMMENT '服务时区',
`type` VARCHAR(64) DEFAULT NULL  COMMENT '类型',
`version` VARCHAR(1) DEFAULT NULL  COMMENT '版本',
`created_at` datetime DEFAULT NULL  COMMENT '创建时间',
`created_by` VARCHAR(32) DEFAULT NULL  COMMENT '创建者',
`updated_at` datetime DEFAULT NULL  COMMENT '最后更新时间',
`updated_by` VARCHAR(32) DEFAULT NULL  COMMENT '最后更新者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Meta服务器信息';
