-- ----------------------------
-- Table structure for wechat_config
-- ----------------------------
DROP TABLE IF EXISTS `wechat_config`;
CREATE TABLE `wechat_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信配置编号',
  `config_key` varchar(32) NOT NULL UNIQUE COMMENT '微信配置关键字',
  `config_text` varchar(64) COMMENT '微信配置文本值',
  `remark` varchar(64) COMMENT '微信配置备注',
  `create_date` timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `activity`.`wechat_config` (`config_key`, `config_text`, `remark`, `create_date`) VALUES ('APPID', 'wx84a8649ef7a1171d', '微信appid', '2018-01-01 00:00:00');
INSERT INTO `activity`.`wechat_config` (`config_key`, `config_text`, `remark`, `create_date`) VALUES ('SECRET', '7082b585c84f6b346fe9478ebfe7f382', '微信secret', '2018-01-01 00:00:00');
INSERT INTO `activity`.`wechat_config` (`config_key`, `config_text`, `remark`, `create_date`) VALUES ('TOKEN', 'weixin', '微信token', '2018-01-01 00:00:00');
INSERT INTO `activity`.`wechat_config` (`config_key`, `config_text`, `remark`, `create_date`) VALUES ('AESKEY', 'rKoI4DFAJZgYNv5ol2eHF6rvEY5uJbq8WHD7eVcPjHy', '微信aeskey', '2018-01-01 00:00:00');
-- INSERT INTO `activity`.`wechat_config` (`config_key`, `config_text`, `remark`, `create_date`) VALUES ('URI', 'http://tt.51meiy.com', '微信回调配置域名', '2018-01-01 00:00:00');
INSERT INTO `activity`.`wechat_config` (`config_key`, `config_text`, `remark`, `create_date`) VALUES ('URI', 'http%3a%2f%2ftt.51meiy.com', '使用urlEncode对微信回调配置域名处理结果', '2018-01-01 00:00:00');
