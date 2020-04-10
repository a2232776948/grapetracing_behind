/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : my_grape

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 08/04/2020 15:34:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES (1, '一号大棚', '广州市天河区五山路1号', '使用中');
INSERT INTO `area` VALUES (2, '二号大棚', '广州市天河区五山路2号', '使用中');
INSERT INTO `area` VALUES (3, '三号大棚', '广州市天河区五山路3号', '使用中');
INSERT INTO `area` VALUES (4, '四号大棚', '广州市天河区五山路4号', '使用中');
INSERT INTO `area` VALUES (5, '五号大棚', '广州市天河区五山路4号', '使用中');
INSERT INTO `area` VALUES (6, '六号大棚', '广州市天河区五山路4号', '使用中');
INSERT INTO `area` VALUES (13, '七号大棚', '广州市天河区五山路7号', '使用中');

-- ----------------------------
-- Table structure for farmop
-- ----------------------------
DROP TABLE IF EXISTS `farmop`;
CREATE TABLE `farmop`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `tree_id` int(0) NULL DEFAULT NULL,
  `area_id` int(0) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of farmop
-- ----------------------------
INSERT INTO `farmop` VALUES (3, 3, 1, '施肥', '磷肥5kg', '2020-04-04', 3);
INSERT INTO `farmop` VALUES (4, 4, 1, '施肥', '磷肥5kg', '2020-04-04', 3);
INSERT INTO `farmop` VALUES (5, 5, 1, '施肥', '磷肥5kg', '2020-04-04', 3);
INSERT INTO `farmop` VALUES (6, 6, 1, '施肥', '磷肥5kg', '2020-04-04', 3);
INSERT INTO `farmop` VALUES (7, 7, 1, '施肥', '磷肥5kg', '2020-04-04', 3);
INSERT INTO `farmop` VALUES (8, 8, 1, '施肥', '磷肥5kg', '2020-04-04', 3);
INSERT INTO `farmop` VALUES (9, 9, 1, '施肥', '磷肥5kg', '2020-04-04', 3);
INSERT INTO `farmop` VALUES (10, 10, 1, '施肥', '磷肥5kg', '2020-04-04', 3);
INSERT INTO `farmop` VALUES (11, 11, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (12, 11, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (13, 12, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (14, 13, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (15, 14, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (16, 15, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (17, 16, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (18, 17, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (19, 18, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (20, 19, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (21, 20, 2, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (22, 30, 3, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (24, 30, 3, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (25, 30, 3, '施肥', '钾肥5kg', '2020-04-06', 3);
INSERT INTO `farmop` VALUES (26, 29, 3, '施肥', '钾肥5kg', '2020-04-06', 3);
/*
INSERT INTO `farmop` VALUES (27, 11, 2, '除草', "", '2020-04-01', 3);
INSERT INTO `farmop` VALUES (28, 12, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (29, 13, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (30, 14, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (31, 15, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (32, 16, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (33, 17, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (34, 18, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (35, 19, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (36, 20, 2, '除草', '', '2020-04-01', 3);
INSERT INTO `farmop` VALUES (38, 31, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (39, 32, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (40, 33, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (41, 34, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (42, 35, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (43, 36, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (44, 37, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (45, 38, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (46, 39, 4, '采摘', '', '2019-08-13', 4);
INSERT INTO `farmop` VALUES (47, 40, 4, '采摘', '', '2019-08-13', 4);
*/
INSERT INTO `farmop` VALUES (48, 1, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (49, 2, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (50, 3, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (51, 4, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (52, 5, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (53, 6, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (54, 7, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (55, 8, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (56, 9, 1, '施肥', '复合肥5kg', '2019-08-14', 3);
INSERT INTO `farmop` VALUES (57, 10, 1, '施肥', '复合肥5kg', '2019-08-14', 3);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tree_id` int(0) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL,
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `iconCls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `keepAlive` tinyint(1) NULL DEFAULT NULL,
  `requireAuth` tinyint(1) NULL DEFAULT NULL,
  `parentId` int(0) NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '/', NULL, NULL, '所有', NULL, NULL, NULL, NULL, 1);
INSERT INTO `menu` VALUES (2, '/', '/home', 'Home', '首页', NULL, NULL, 1, 1, 1);
INSERT INTO `menu` VALUES (3, '/', '/plantSys', 'PlantSys', '植株信息', 'el-icon-grape', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (4, '/', '/areaSys', 'AreaSys', '农田信息', 'el-icon-grape', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (5, '/', '/farmBusinessSys', 'FarmBusinessSys', '农事信息', 'el-icon-grape', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (6, '/', '/qualitySys', 'QualitySys', '质检信息', 'el-icon-grape', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (7, '/', '/logisticSys', 'LogisticSys', '物流信息', 'el-icon-grape', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (8, '/', '/QRcodeSys', 'QRcodeSys', '二维码信息', 'el-icon-grape', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (9, '/', '/blockchainSys', 'BlockChainSys', '区块链信息', 'el-icon-grape', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (10, '/', '/userSys', 'UserSys', '用户权限', 'el-icon-grape', NULL, 1, 2, 1);

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `id` int(0) NOT NULL,
  `menu_id` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (1, 1, 1);
INSERT INTO `menu_role` VALUES (2, 2, 1);
INSERT INTO `menu_role` VALUES (3, 3, 1);
INSERT INTO `menu_role` VALUES (4, 4, 1);
INSERT INTO `menu_role` VALUES (5, 5, 1);
INSERT INTO `menu_role` VALUES (6, 6, 1);
INSERT INTO `menu_role` VALUES (7, 7, 1);
INSERT INTO `menu_role` VALUES (8, 8, 1);
INSERT INTO `menu_role` VALUES (9, 9, 1);
INSERT INTO `menu_role` VALUES (10, 1, 2);
INSERT INTO `menu_role` VALUES (11, 2, 2);
INSERT INTO `menu_role` VALUES (12, 3, 2);
INSERT INTO `menu_role` VALUES (13, 4, 2);
INSERT INTO `menu_role` VALUES (14, 5, 2);
INSERT INTO `menu_role` VALUES (15, 6, 2);
INSERT INTO `menu_role` VALUES (16, 7, 2);
INSERT INTO `menu_role` VALUES (17, 8, 2);
INSERT INTO `menu_role` VALUES (18, 9, 2);
INSERT INTO `menu_role` VALUES (19, 10, 2);

-- ----------------------------
-- Table structure for quality
-- ----------------------------
DROP TABLE IF EXISTS `quality`;
CREATE TABLE `quality`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(0) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quality
-- ----------------------------
INSERT INTO `quality` VALUES (1, 1, '农药残留', '达标', '2020-04-01 00:00:00', 3);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_operator', '农事操作员');
INSERT INTO `role` VALUES (2, 'ROLE_admin', '系统管理员');

-- ----------------------------
-- Table structure for tree
-- ----------------------------
DROP TABLE IF EXISTS `tree`;
CREATE TABLE `tree`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `area_id` int(0) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `plant_date` date NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tree
-- ----------------------------
INSERT INTO `tree` VALUES (1, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (2, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (3, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (4, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (5, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (6, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (7, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (8, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (9, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (10, 1, '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES (11, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (12, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (13, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (14, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (15, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (16, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (17, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (18, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (19, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (20, 2, '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES (21, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (22, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (23, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (24, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (25, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (26, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (27, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (28, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (29, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (30, 3, '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES (31, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (32, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (33, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (34, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (35, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (36, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (37, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (38, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (39, 4, '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES (40, 4, '红提', '2020-02-04', '存活');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `userface` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '系统管理员', 'admin', '$2a$10$/grM.NgL3hajog4ARXwLu.EqWSeQCbjHaHBeVJ9E7vfUodh98RH0q', '15699999999', '女', '2020-03-31 00:00:00', 99, 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '系统管理员', NULL, 1);
INSERT INTO `user` VALUES (3, '农民一号', 'farm1', '$2a$10$iWdx6HFM33xFVLyDgRwsduggpfDz/.8kbzxxOvfTUHRHuq0C8GESC', '12312341234', '男', '2020-04-04 00:00:00', 35, NULL, '葡萄采摘工', '0x123165461316546549846a1af6a1df65a', 1);
INSERT INTO `user` VALUES (4, '农民二号', 'farm2', '$2a$10$CXEkP0w1jgjCusf9pCWrm.Ee4.EFmqYH4zT51VfQQzsrA5Ls5i.UK', '12312311231', '男', '2020-04-07 00:00:00', 50, NULL, '', '', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 3, 1);
INSERT INTO `user_role` VALUES (6, 1, 2);
INSERT INTO `user_role` VALUES (7, 4, 1);

-- ----------------------------
-- Procedure structure for insertMany
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertMany`;
delimiter ;;
CREATE PROCEDURE `insertMany`(IN `area_id` int,IN `categry` varchar(255),IN `plant_date` date,IN `status` varchar(255),IN `num` int)
BEGIN
	#Routine body goes here...
DECLARE i INT;
SET i = 0;
WHILE i < `num` DO
insert into tree(area_id, category, plant_date, status) values(`area_id`, `categry`, `plant_date`, `status`);
set i = i+1;						
END WHILE;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
