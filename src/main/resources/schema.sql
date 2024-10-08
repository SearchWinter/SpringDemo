--
-- CREATE DATABASE  IF NOT EXISTS `db_base_stat` ;
--
-- USE `db_demo`;
--
-- /*Table structure for table `t_group` */
--
-- CREATE TABLE  IF NOT EXISTS `t_group` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `group_id` varchar(255) DEFAULT NULL,
--   `group_name` varchar(255) DEFAULT NULL,
--   `status` int(11) DEFAULT NULL,
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `group` (`group_id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
--
--
-- /*Table structure for table `t_group_black` */
--
-- CREATE TABLE  IF NOT EXISTS `t_group_black` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `group_id` varchar(255) DEFAULT NULL,
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `group_id` (`group_id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
--
-- /*Table structure for table `t_group_user` */
--
-- CREATE TABLE  IF NOT EXISTS `t_group_user` (
--   `id` int(11) NOT NULL AUTO_INCREMENT,
--   `group_id` varchar(255) DEFAULT NULL,
--   `accounts` varchar(255) DEFAULT NULL,
--   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `group_user` (`group_id`,`accounts`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
--
--
