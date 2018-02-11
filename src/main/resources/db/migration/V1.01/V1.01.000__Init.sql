/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50638
Source Host           : 127.0.0.1:3306
Source Database       : activity

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-02-11 10:34:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for acitvity_enroll
-- ----------------------------
DROP TABLE IF EXISTS `acitvity_enroll`;
CREATE TABLE `acitvity_enroll` (
  `id` int(11) NOT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `openid` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `political` varchar(32) DEFAULT NULL,
  `company` varchar(64) DEFAULT NULL,
  `job` varchar(64) DEFAULT NULL,
  `card_face` int(11) DEFAULT NULL,
  `card_back` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACITVITY_ENROLL_FACE_REFERENCE_UPLOAD_F` (`card_face`),
  KEY `FK_ACITVITY_ENROLL_REFERENCE_ACTIVITY` (`activity_id`),
  KEY `FK_ACITVITY_ENROLL_REFERENCE_WECHAT_U` (`openid`),
  KEY `FK_ACITVITY_ENROLL_BACK_REFERENCE_UPLOAD_F` (`card_back`),
  CONSTRAINT `FK_ACITVITY_ENROLL_BACK_REFERENCE_UPLOAD_F` FOREIGN KEY (`card_back`) REFERENCES `upload_file` (`id`),
  CONSTRAINT `FK_ACITVITY_ENROLL_FACE_REFERENCE_UPLOAD_F` FOREIGN KEY (`card_face`) REFERENCES `upload_file` (`id`),
  CONSTRAINT `FK_ACITVITY_ENROLL_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_ACITVITY_ENROLL_REFERENCE_WECHAT_U` FOREIGN KEY (`openid`) REFERENCES `wechat_user` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `title` varchar(256) NOT NULL,
  `district_id` int(11) NOT NULL,
  `begin_time` date NOT NULL,
  `end_time` date NOT NULL,
  `max_limit` int(11) DEFAULT NULL,
  `upload_file_id` int(11) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `create_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_REFERENCE_ACTIVITY` (`district_id`),
  KEY `FK_ACTIVITY_REFERENCE_UPLOAD_F` (`upload_file_id`),
  CONSTRAINT `FK_ACTIVITY_REFERENCE_ACTIVITY` FOREIGN KEY (`district_id`) REFERENCES `activity_district` (`id`),
  CONSTRAINT `FK_ACTIVITY_REFERENCE_UPLOAD_F` FOREIGN KEY (`upload_file_id`) REFERENCES `upload_file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_course
-- ----------------------------
DROP TABLE IF EXISTS `activity_course`;
CREATE TABLE `activity_course` (
  `id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `begin_time` date NOT NULL,
  `end_time` date NOT NULL,
  `active` bit(1) NOT NULL,
  `create_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_COURSE_REFERENCE_ACTIVITY` (`activity_id`),
  CONSTRAINT `FK_ACTIVITY_COURSE_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_course_signin
-- ----------------------------
DROP TABLE IF EXISTS `activity_course_signin`;
CREATE TABLE `activity_course_signin` (
  `id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `openid` varchar(32) NOT NULL,
  `sign_in_time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_COURSE_SIGNIN_REFERENCE_ACTIVITY` (`course_id`),
  KEY `FK_ACTIVITY_COURSE_SIGNIN_REFERENCE_WECHAT_U` (`openid`),
  CONSTRAINT `FK_ACTIVITY_COURSE_SIGNIN_REFERENCE_ACTIVITY` FOREIGN KEY (`course_id`) REFERENCES `activity_course` (`id`),
  CONSTRAINT `FK_ACTIVITY_COURSE_SIGNIN_REFERENCE_WECHAT_U` FOREIGN KEY (`openid`) REFERENCES `wechat_user` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_description
-- ----------------------------
DROP TABLE IF EXISTS `activity_description`;
CREATE TABLE `activity_description` (
  `id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_DESCRIPTION_REFERENCE_ACTIVITY` (`activity_id`),
  CONSTRAINT `FK_ACTIVITY_DESCRIPTION_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_district
-- ----------------------------
DROP TABLE IF EXISTS `activity_district`;
CREATE TABLE `activity_district` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `create_date` date NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_tag
-- ----------------------------
DROP TABLE IF EXISTS `activity_tag`;
CREATE TABLE `activity_tag` (
  `id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `use_name` bit(1) DEFAULT NULL,
  `use_sex` bit(1) DEFAULT NULL,
  `use_phone` bit(1) DEFAULT NULL,
  `use_political` bit(1) DEFAULT NULL,
  `use_company` bit(1) DEFAULT NULL,
  `use_job` bit(1) DEFAULT NULL,
  `use_card_face` bit(1) DEFAULT NULL,
  `use_card_back` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_TAG_REFERENCE_ACTIVITY` (`activity_id`),
  CONSTRAINT `FK_ACTIVITY_TAG_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_thumbup_log
-- ----------------------------
DROP TABLE IF EXISTS `activity_thumbup_log`;
CREATE TABLE `activity_thumbup_log` (
  `id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `openid` varchar(32) NOT NULL,
  `up_time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_THUMBUP_LOG_REFERENCE_ACTIVITY` (`activity_id`),
  KEY `FK_ACTIVITY_THUMBUP_LOG_REFERENCE_WECHAT_U` (`openid`),
  CONSTRAINT `FK_ACTIVITY_THUMBUP_LOG_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_ACTIVITY_THUMBUP_LOG_REFERENCE_WECHAT_U` FOREIGN KEY (`openid`) REFERENCES `wechat_user` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for activity_watch_log
-- ----------------------------
DROP TABLE IF EXISTS `activity_watch_log`;
CREATE TABLE `activity_watch_log` (
  `id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `openid` varchar(32) NOT NULL,
  `watch_time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ACTIVITY_WATCH_LOG_REFERENCE_ACTIVITY` (`activity_id`),
  KEY `FK_ACTIVITY_WATCH_LOG_REFERENCE_WECHAT_U` (`openid`),
  CONSTRAINT `FK_ACTIVITY_WATCH_LOG_REFERENCE_ACTIVITY` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_ACTIVITY_WATCH_LOG_REFERENCE_WECHAT_U` FOREIGN KEY (`openid`) REFERENCES `wechat_user` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for adsense
-- ----------------------------
DROP TABLE IF EXISTS `adsense`;
CREATE TABLE `adsense` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `upload_file_id` int(11) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `page_name` varchar(32) NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ADSENSE_REFERENCE_UPLOAD_F` (`upload_file_id`),
  CONSTRAINT `FK_ADSENSE_REFERENCE_UPLOAD_F` FOREIGN KEY (`upload_file_id`) REFERENCES `upload_file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for carousel_figure
-- ----------------------------
DROP TABLE IF EXISTS `carousel_figure`;
CREATE TABLE `carousel_figure` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `upload_file_id` int(11) NOT NULL,
  `url` varchar(256) NOT NULL,
  `active` bit(1) NOT NULL,
  `create_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CAROUSEL_FIGURE_REFERENCE_UPLOAD_F` (`upload_file_id`),
  CONSTRAINT `FK_CAROUSEL_FIGURE_REFERENCE_UPLOAD_F` FOREIGN KEY (`upload_file_id`) REFERENCES `upload_file` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file` (
  `id` int(11) NOT NULL,
  `file_name` varchar(256) NOT NULL,
  `real_name` varchar(32) NOT NULL,
  `file_path` varchar(32) NOT NULL,
  `file_type` varchar(16) NOT NULL,
  `create_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_score_log
-- ----------------------------
DROP TABLE IF EXISTS `user_score_log`;
CREATE TABLE `user_score_log` (
  `id` int(11) NOT NULL,
  `openid` varchar(32) NOT NULL,
  `score` int(11) NOT NULL,
  `reason` varchar(128) DEFAULT NULL,
  `create_time` date NOT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_SCORE_LOG_REFERENCE_WECHAT_U` (`openid`),
  CONSTRAINT `FK_USER_SCORE_LOG_REFERENCE_WECHAT_U` FOREIGN KEY (`openid`) REFERENCES `wechat_user` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE `wechat_user` (
  `openid` varchar(32) NOT NULL,
  `nickname` varchar(32) NOT NULL,
  `subscribe` int(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `city` varchar(32) DEFAULT NULL,
  `province` varchar(32) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL,
  `headingurl` varchar(128) DEFAULT NULL,
  `subscribe_time` date DEFAULT NULL,
  `unionid` varchar(32) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `create_date` date NOT NULL,
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
