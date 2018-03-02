SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `title` varchar(256) NOT NULL COMMENT '活动标题',
  `district_id` int(11) NOT NULL COMMENT '活动区域编号',
  `address` varchar(256) NOT NULL COMMENT '活动地址',
  `begin_time` timestamp NOT NULL COMMENT '开始时间',
  `end_time` timestamp NOT NULL COMMENT '结束时间',
  `max_limit` int(11) DEFAULT NULL COMMENT '人数限制',
  `upload_file_id` int(11) DEFAULT NULL COMMENT '封面文件编号',
  `active` bit(1) NOT NULL COMMENT '有效性',
  `create_date` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_REFERENCE_DISTRICT` (`district_id`),
  KEY `FK_ACTIVITY_REFERENCE_UPLOAD_F` (`upload_file_id`),
  CONSTRAINT `FK_ACTIVITY_REFERENCE_DISTRICT` FOREIGN KEY (`district_id`) REFERENCES `activity_district` (`id`),
  CONSTRAINT `FK_ACTIVITY_REFERENCE_UPLOAD_F` FOREIGN KEY (`upload_file_id`) REFERENCES `upload_file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_course
-- ----------------------------
DROP TABLE IF EXISTS `activity_course`;
CREATE TABLE `activity_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动课程编号',
  `activity_id` int(11) NOT NULL COMMENT '活动编号',
  `name` varchar(64) NOT NULL COMMENT '课程名称',
  `begin_time` timestamp NOT NULL COMMENT '课程开始时间',
  `end_time` timestamp NOT NULL COMMENT '课程结束时间',
  `active` bit(1) NOT NULL COMMENT '有效性',
  `create_date` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_COURSE_REFERENCE_ACTIVITY` (`activity_id`),
  CONSTRAINT `FK_ACTIVITY_COURSE_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_course_signin
-- ----------------------------
DROP TABLE IF EXISTS `activity_course_signin`;
CREATE TABLE `activity_course_signin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程签到编号',
  `course_id` int(11) NOT NULL COMMENT '课程编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `sign_in_time` timestamp NOT NULL COMMENT '签到时间',
  PRIMARY KEY (`id`),
  KEY `FK_COURSE_SIGNIN_REFERENCE_ACTIVITY` (`course_id`),
  KEY `FK_COURSE_SIGNIN_REFERENCE_USERS` (`user_id`),
  CONSTRAINT `FK_COURSE_SIGNIN_REFERENCE_ACTIVITY` FOREIGN KEY (`course_id`) REFERENCES `activity_course` (`id`),
  CONSTRAINT `FK_COURSE_SIGNIN_REFERENCE_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_description
-- ----------------------------
DROP TABLE IF EXISTS `activity_description`;
CREATE TABLE `activity_description` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动简介编号',
  `activity_id` int(11) NOT NULL COMMENT '活动编号',
  `description` text COMMENT '简介',
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_DESC_REFERENCE_ACTIVITY` (`activity_id`),
  CONSTRAINT `FK_ACTIVITY_DESC_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_district
-- ----------------------------
DROP TABLE IF EXISTS `activity_district`;
CREATE TABLE `activity_district` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域编号',
  `name` varchar(32) NOT NULL COMMENT '区域名称',
  `active` bit(1) NOT NULL COMMENT '有效性',
  `create_date` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_tag
-- ----------------------------
DROP TABLE IF EXISTS `activity_tag`;
CREATE TABLE `activity_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `activity_id` int(11) NOT NULL COMMENT '活动编号',
  `use_name` bit(1) DEFAULT NULL COMMENT '是否使用名称',
  `use_sex` bit(1) DEFAULT NULL COMMENT '是否使用性别',
  `use_phone` bit(1) DEFAULT NULL COMMENT '是否使用联系方式',
  `use_political` bit(1) DEFAULT NULL COMMENT '是否使用政治面貌',
  `use_company` bit(1) DEFAULT NULL COMMENT '是否使用工作单位',
  `use_job` bit(1) DEFAULT NULL COMMENT '是否使用职务',
  `use_card` bit(1) DEFAULT NULL COMMENT '是否使用证件',
  `use_profile` bit(1) DEFAULT NULL COMMENT '是否使用个人简介',
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_REFERENCE_ACTIVITY` (`activity_id`),
  CONSTRAINT `FK_ACTIVITY_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_thumbup
-- ----------------------------
DROP TABLE IF EXISTS `activity_thumbup`;
CREATE TABLE `activity_thumbup` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动点赞记录编号',
  `activity_id` int(11) NOT NULL COMMENT '活动编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `up_time` timestamp NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_THUMBUP_REFERENCE_ACTIVITY` (`activity_id`),
  KEY `FK_ACTIVITY_THUMBUP_REFERENCE_USERS` (`user_id`),
  CONSTRAINT `FK_ACTIVITY_THUMBUP_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_ACTIVITY_THUMBUP_REFERENCE_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_watched
-- ----------------------------
DROP TABLE IF EXISTS `activity_watched`;
CREATE TABLE `activity_watched` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动查看记录编号',
  `activity_id` int(11) NOT NULL COMMENT '活动编号',
  `user_id` int(11) NULL COMMENT '用户编号',
  `watch_time` timestamp NOT NULL COMMENT '查看时间',
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_WATCHED_REFERENCE_ACTIVITY` (`activity_id`),
  KEY `FK_ACTIVITY_WATCHED_REFERENCE_USERS` (`user_id`),
  CONSTRAINT `FK_ACTIVITY_WATCHED_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_ACTIVITY_WATCHED_REFERENCE_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for adsense
-- ----------------------------
DROP TABLE IF EXISTS `adsense`;
CREATE TABLE `adsense` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(256) NOT NULL COMMENT '图片名称名称',
  `upload_file_id` int(11) NOT NULL COMMENT '图片文件编号',
  `url` varchar(256) DEFAULT NULL COMMENT '跳转链接',
  `page_name` varchar(32) NULL COMMENT '对应页面',
  `type` varchar(32) NOT NULL COMMENT '类型：轮播图/广告图',
  `active` bit(1) NOT NULL COMMENT '有效性',
  `create_date` timestamp NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_ADSENSE_REFERENCE_UPLOAD_F` (`upload_file_id`),
  CONSTRAINT `FK_ADSENSE_REFERENCE_UPLOAD_F` FOREIGN KEY (`upload_file_id`) REFERENCES `upload_file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '上传文件编号',
  `file_name` varchar(256) NOT NULL COMMENT '文件名称',
  `real_name` varchar(64) NOT NULL COMMENT '存放名称',
  `file_path` varchar(512) NOT NULL COMMENT '文件路径',
  `file_type` varchar(32) NOT NULL COMMENT '文件类型',
  `create_date` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `name` varchar(32) NOT NULL COMMENT '用户名称',
  `sex` varchar(2) DEFAULT NULL COMMENT '用户性别',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `province` varchar(32) DEFAULT NULL COMMENT '省份',
  `country` varchar(32) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(256) DEFAULT NULL COMMENT '头像',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `active` bit(1) NOT NULL COMMENT '有效性',
  `create_date` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users_score
-- ----------------------------
DROP TABLE IF EXISTS `users_score`;
CREATE TABLE `users_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户积分编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `score` int(11) NOT NULL COMMENT '积分',
  `reason` varchar(128) DEFAULT NULL COMMENT '原因',
  `activity_id` int(11) DEFAULT NULL COMMENT '有效性',
  `course_id` int(11) DEFAULT NULL COMMENT '课程编号',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_USERS_SCORE_REFERENCE_USERS` (`user_id`),
  CONSTRAINT `FK_USERS_SCORE_REFERENCE_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `openid` varchar(32) DEFAULT NULL UNIQUE COMMENT '微信标识',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `subscribe` bit NOT NULL COMMENT '关注状态',
  `union_id` varchar(32) DEFAULT NULL COMMENT '唯一标识',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `group_id` int(11) DEFAULT NULL COMMENT '分组编号',
  `create_date` timestamp NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_WECHAT_U_REFERENCE_USERS` (`user_id`),
  CONSTRAINT `FK_WECHAT_U_REFERENCE_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '用户姓名',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `active` bit(1) NOT NULL COMMENT '有效性',
  `create_date` timestamp NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_enroll
-- ----------------------------
DROP TABLE if EXISTS `activity_enroll`;
CREATE TABLE `activity_enroll` (
  `id` int(11) NOT NULL AUTO_INCREMENT  COMMENT '编号',
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
  `profile` varchar(256) DEFAULT NULL COMMENT '个人简介',
  `active` bit(1) NOT NULL COMMENT '有效性',
  `create_date` timestamp NOT NULL COMMENT '创建日期',
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


-- ----------------------------
-- Table structure for etl_task_activity
-- ----------------------------
DROP TABLE if EXISTS `etl_task_activity`;
CREATE TABLE `etl_task_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '定时任务实体类名',
  `success_time` timestamp NOT NULL COMMENT '成功时间',
  `success` bit(1) NOT NULL COMMENT '是否成功',
  `created_date` timestamp NOT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

