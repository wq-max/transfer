/*
Navicat MySQL Data Transfer

Source Server         : QiQi
Source Server Version : 50735
Source Host           : localhost:3306
Source Database       : bulletin

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2021-12-13 15:18:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of bulletin
-- ----------------------------
INSERT INTO `bulletin` VALUES ('1', '下午一点，怡红院集合');
INSERT INTO `bulletin` VALUES ('2', '疫情原因，一点活动取消');
INSERT INTO `bulletin` VALUES ('3', '明天，所有人原地休息一天');

-- ----------------------------
-- Table structure for ub
-- ----------------------------
DROP TABLE IF EXISTS `ub`;
CREATE TABLE `ub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '0表示未读，1表示已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ub
-- ----------------------------
INSERT INTO `ub` VALUES ('1', '21172111', '1', '1');
INSERT INTO `ub` VALUES ('2', '21172111', '2', '1');
INSERT INTO `ub` VALUES ('3', '21172111', '3', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `pwd` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '0表示未登录，1表示已登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21172123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('21172111', '齐齐', '123', '0');
INSERT INTO `user` VALUES ('21172122', '苗苗', '456', '0');
