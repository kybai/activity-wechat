DROP TABLE if EXISTS etl_task_activity;
CREATE TABLE `etl_task_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '定时任务实体类名',
  `success_time` timestamp NOT NULL COMMENT '成功时间',
  `success` bit(1) NOT NULL COMMENT '是否成功',
  `created_date` timestamp NOT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

