DROP TABLE if EXISTS activity_enroll;
CREATE TABLE `activity_enroll` (
  `id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL COMMENT '活动编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `political` varchar(32) DEFAULT NULL COMMENT '政治面貌',
  `company` varchar(64) DEFAULT NULL COMMENT '工作单位',
  `job` varchar(64) DEFAULT NULL COMMENT '职务',
  `card_face` int(11) DEFAULT NULL COMMENT '证件正面',
  `card_back` int(11) DEFAULT NULL COMMENT '证件反面',
  `active` bit(1) NOT NULL COMMENT '有效性',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_REFERENCE_UPLOAD_FACE` (`card_face`),
  KEY `FK_ENROLL_REFERENCE_ACTIVITY` (`activity_id`),
  KEY `FK_ENROLL_REFERENCE_USERS_ID` (`user_id`),
  KEY `FK_ENROLL_REFERENCE_UPLOAD_BACK` (`card_back`),
  CONSTRAINT `FK_ACTIVITY_REFERENCE_UPLOAD_FACE` FOREIGN KEY (`card_face`) REFERENCES `upload_file` (`id`),
  CONSTRAINT `FK_ENROLL_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_ENROLL_REFERENCE_UPLOAD_BACK` FOREIGN KEY (`card_back`) REFERENCES `upload_file` (`id`),
  CONSTRAINT `FK_ENROLL_REFERENCE_USERS_ID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

