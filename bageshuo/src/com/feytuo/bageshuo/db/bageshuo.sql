/*
Navicat MySQL Data Transfer

Source Server         : hibernate
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : bageshuo

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2015-03-25 11:25:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `an_id` int(11) NOT NULL AUTO_INCREMENT,
  `an_location` varchar(255) NOT NULL,
  `an_time` datetime NOT NULL,
  `an_word` text,
  `an_voice` varchar(255) DEFAULT NULL,
  `an_praise_num` int(11) NOT NULL DEFAULT '0',
  `u_id` int(11) NOT NULL,
  `pr_id` int(11) NOT NULL,
  PRIMARY KEY (`an_id`),
  KEY `an_prid` (`pr_id`),
  KEY `an_uid` (`u_id`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE,
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`pr_id`) REFERENCES `problem` (`pr_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for `carousel`
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel` (
  `ca_id` int(11) NOT NULL AUTO_INCREMENT,
  `ca_theme` varchar(255) NOT NULL,
  `ca_pic` varchar(255) NOT NULL,
  `ca_time` datetime NOT NULL,
  `ca_content` varchar(255) NOT NULL,
  `ca_share_num` int(11) NOT NULL DEFAULT '0',
  `ca_praise_num` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ca_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carousel
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_location` varchar(255) NOT NULL,
  `com_time` datetime NOT NULL,
  `com_word` text,
  `com_voice` varchar(255) DEFAULT NULL,
  `u_id` int(11) NOT NULL,
  `inv_id` int(11) NOT NULL,
  PRIMARY KEY (`com_id`),
  KEY `com_uid` (`u_id`),
  KEY `com_invid` (`inv_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`inv_id`) REFERENCES `invitation` (`inv_id`) ON DELETE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `community`
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community` (
  `co_id` int(11) NOT NULL AUTO_INCREMENT,
  `co_name` varchar(255) NOT NULL,
  `co_title` varchar(255) DEFAULT NULL,
  `co_head` varchar(255) DEFAULT NULL,
  `co_intro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`co_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `community_user`
-- ----------------------------
DROP TABLE IF EXISTS `community_user`;
CREATE TABLE `community_user` (
  `co_u_id` int(11) NOT NULL AUTO_INCREMENT,
  `co_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`co_u_id`),
  KEY `cu_coid` (`co_id`),
  KEY `cu_uid` (`u_id`),
  CONSTRAINT `community_user_ibfk_1` FOREIGN KEY (`co_id`) REFERENCES `community` (`co_id`),
  CONSTRAINT `community_user_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of community_user
-- ----------------------------

-- ----------------------------
-- Table structure for `invitation`
-- ----------------------------
DROP TABLE IF EXISTS `invitation`;
CREATE TABLE `invitation` (
  `inv_id` int(11) NOT NULL AUTO_INCREMENT,
  `inv_location` varchar(255) NOT NULL,
  `inv_time` datetime NOT NULL,
  `inv_word` text,
  `inv_voice` varchar(255) DEFAULT NULL,
  `inv_share_num` int(11) NOT NULL DEFAULT '0',
  `inv_praise_num` int(11) NOT NULL DEFAULT '0',
  `inv_top` int(11) NOT NULL DEFAULT '0',
  `inv_outstanding` int(11) NOT NULL DEFAULT '0',
  `u_id` int(11) NOT NULL,
  `co_id` int(11) NOT NULL,
  PRIMARY KEY (`inv_id`),
  KEY `inv_uid` (`u_id`),
  KEY `inv_coid` (`co_id`),
  CONSTRAINT `invitation_ibfk_1` FOREIGN KEY (`co_id`) REFERENCES `community` (`co_id`),
  CONSTRAINT `invitation_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invitation
-- ----------------------------

-- ----------------------------
-- Table structure for `problem`
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `pr_id` int(11) NOT NULL AUTO_INCREMENT,
  `pr_location` varchar(255) NOT NULL,
  `pr_time` datetime NOT NULL,
  `pr_word` text NOT NULL,
  `pr_voice` varchar(255) DEFAULT NULL,
  `pr_native` varchar(255) NOT NULL,
  `pr_share_num` int(11) NOT NULL DEFAULT '0',
  `pr_praise_num` int(11) NOT NULL DEFAULT '0',
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`pr_id`),
  KEY `pr_uid` (`u_id`),
  CONSTRAINT `problem_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of problem
-- ----------------------------

-- ----------------------------
-- Table structure for `testuser`
-- ----------------------------
DROP TABLE IF EXISTS `testuser`;
CREATE TABLE `testuser` (
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testuser
-- ----------------------------
INSERT INTO `testuser` VALUES ('ttt', 'aaa', '3');

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `to_id` int(11) NOT NULL AUTO_INCREMENT,
  `to_theme` varchar(255) NOT NULL,
  `to_pic` varchar(255) NOT NULL,
  `to_time` datetime NOT NULL,
  `to_content` varchar(255) NOT NULL,
  `to_share_num` int(11) NOT NULL DEFAULT '0',
  `to_praise_num` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`to_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------

-- ----------------------------
-- Table structure for `topic_comment`
-- ----------------------------
DROP TABLE IF EXISTS `topic_comment`;
CREATE TABLE `topic_comment` (
  `to_com_id` int(11) NOT NULL AUTO_INCREMENT,
  `to_com_location` varchar(255) NOT NULL,
  `to_com_time` datetime NOT NULL,
  `to_com_word` text,
  `to_com_voice` varchar(255) DEFAULT NULL,
  `u_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  PRIMARY KEY (`to_com_id`),
  KEY `to_co_toid` (`to_id`),
  KEY `to_co_uid` (`u_id`),
  CONSTRAINT `topic_comment_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE,
  CONSTRAINT `topic_comment_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `topic` (`to_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(255) NOT NULL,
  `u_pwd` varchar(255) NOT NULL,
  `u_head` varchar(255) DEFAULT NULL,
  `u_nick` varchar(255) NOT NULL,
  `u_bage` varchar(255) NOT NULL,
  `u_sex` varchar(255) NOT NULL,
  `u_home` varchar(255) NOT NULL,
  `u_sign` varchar(255) DEFAULT NULL,
  `u_push_id` varchar(255) NOT NULL,
  `u_type` varchar(255) NOT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
