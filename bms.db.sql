/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50561
 Source Host           : localhost:3306
 Source Schema         : bms.db

 Target Server Type    : MySQL
 Target Server Version : 50561
 File Encoding         : 65001

 Date: 03/04/2021 20:35:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `stock` int(11) NOT NULL,
  `publishhouse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出版社',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for br
-- ----------------------------
DROP TABLE IF EXISTS `br`;
CREATE TABLE `br`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reader_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `borrowtime` datetime NOT NULL,
  `returntime` datetime NULL DEFAULT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `time` datetime NULL DEFAULT NULL COMMENT '日志时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `profession` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `type` enum('管理员','读者') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '读者' COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
