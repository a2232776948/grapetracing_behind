/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : my_grape

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-04-22 14:54:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES ('1', '一号大棚', '广州市天河区五山路1号', '使用中');
INSERT INTO `area` VALUES ('2', '二号大棚', '广州市天河区五山路2号', '使用中');
INSERT INTO `area` VALUES ('3', '三号大棚', '广州市天河区五山路3号', '使用中');
INSERT INTO `area` VALUES ('4', '四号大棚', '广州市天河区五山路4号', '使用中');
INSERT INTO `area` VALUES ('5', '五号大棚', '广州市天河区五山路4号', '使用中');
INSERT INTO `area` VALUES ('6', '六号大棚', '广州市天河区五山路4号', '使用中');
INSERT INTO `area` VALUES ('13', '七号大棚', '广州市天河区五山路7号', '使用中');

-- ----------------------------
-- Table structure for farmop
-- ----------------------------
DROP TABLE IF EXISTS `farmop`;
CREATE TABLE `farmop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of farmop
-- ----------------------------
INSERT INTO `farmop` VALUES ('3', '3', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('4', '4', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('6', '6', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('7', '7', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('8', '8', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('9', '9', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('10', '10', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('11', '11', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('12', '11', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('13', '12', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('14', '13', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('15', '14', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('16', '15', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('17', '16', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('18', '17', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('19', '18', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('20', '19', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('21', '20', '2', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('22', '30', '3', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('24', '30', '3', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('25', '30', '3', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('26', '29', '3', '施肥', '钾肥5kg', '2020-04-06', '3');
INSERT INTO `farmop` VALUES ('27', '11', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('28', '12', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('29', '13', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('30', '14', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('31', '15', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('32', '16', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('33', '17', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('34', '18', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('35', '19', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('36', '20', '2', '除草', '', '2020-04-01', '3');
INSERT INTO `farmop` VALUES ('38', '31', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('39', '32', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('40', '33', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('41', '34', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('42', '35', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('43', '36', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('44', '37', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('45', '38', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('46', '39', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('47', '40', '4', '采摘', '', '2019-08-13', '4');
INSERT INTO `farmop` VALUES ('48', '1', '1', '施肥', '复合肥5kg', '2019-08-14', '3');
INSERT INTO `farmop` VALUES ('49', '2', '1', '施肥', '复合肥5kg', '2019-08-14', '3');
INSERT INTO `farmop` VALUES ('50', '3', '1', '施肥', '复合肥5kg', '2019-08-14', '3');
INSERT INTO `farmop` VALUES ('51', '4', '1', '施肥', '复合肥5kg', '2019-08-14', '3');
INSERT INTO `farmop` VALUES ('52', '5', '1', '施肥', '复合肥5kg', '2019-08-14', '3');
INSERT INTO `farmop` VALUES ('53', '6', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('54', '7', '1', '施肥', '复合肥5kg', '2019-08-14', '3');
INSERT INTO `farmop` VALUES ('55', '8', '1', '施肥', '磷肥5kg', '2020-04-04', '3');
INSERT INTO `farmop` VALUES ('56', '9', '1', '施肥', '复合肥5kg', '2019-08-14', '3');
INSERT INTO `farmop` VALUES ('57', '10', '1', '施肥', '复合肥5kg', '2019-08-14', '3');
INSERT INTO `farmop` VALUES ('58', '46', '13', '施肥', '施钾肥10kg', '2020-04-05', '3');
INSERT INTO `farmop` VALUES ('59', '47', '13', '施肥', '施钾肥10kg', '2020-04-05', '3');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '2', '2020-04-20');
INSERT INTO `goods` VALUES ('2', '12', '2020-04-07');
INSERT INTO `goods` VALUES ('3', '12', '2020-04-09');
INSERT INTO `goods` VALUES ('4', '2', '2019-02-03');
INSERT INTO `goods` VALUES ('5', '2', '2019-02-03');
INSERT INTO `goods` VALUES ('6', '2', '2019-02-03');
INSERT INTO `goods` VALUES ('7', '2', '2019-02-03');
INSERT INTO `goods` VALUES ('8', '2', '2019-02-03');
INSERT INTO `goods` VALUES ('9', '2', '2019-02-03');
INSERT INTO `goods` VALUES ('10', '3', '2020-04-21');
INSERT INTO `goods` VALUES ('11', '3', '2020-04-21');
INSERT INTO `goods` VALUES ('12', '3', '2020-04-21');
INSERT INTO `goods` VALUES ('13', '3', '2020-04-21');
INSERT INTO `goods` VALUES ('14', '3', '2020-04-21');
INSERT INTO `goods` VALUES ('15', '3', '2020-04-21');
INSERT INTO `goods` VALUES ('16', '3', '2020-04-21');
INSERT INTO `goods` VALUES ('17', '3', '2020-04-21');
INSERT INTO `goods` VALUES ('18', '11', '2020-04-21');
INSERT INTO `goods` VALUES ('19', '11', '2020-04-21');
INSERT INTO `goods` VALUES ('20', '11', '2020-04-21');
INSERT INTO `goods` VALUES ('21', '12', '2020-04-21');
INSERT INTO `goods` VALUES ('22', '12', '2020-04-21');
INSERT INTO `goods` VALUES ('23', '12', '2020-04-21');
INSERT INTO `goods` VALUES ('24', '13', '2020-04-21');
INSERT INTO `goods` VALUES ('25', '13', '2020-04-21');
INSERT INTO `goods` VALUES ('26', '13', '2020-04-21');
INSERT INTO `goods` VALUES ('27', '14', '2020-04-21');
INSERT INTO `goods` VALUES ('28', '14', '2020-04-21');
INSERT INTO `goods` VALUES ('29', '14', '2020-04-21');
INSERT INTO `goods` VALUES ('30', '15', '2020-04-21');
INSERT INTO `goods` VALUES ('31', '15', '2020-04-21');
INSERT INTO `goods` VALUES ('32', '15', '2020-04-21');
INSERT INTO `goods` VALUES ('33', '16', '2020-04-21');
INSERT INTO `goods` VALUES ('34', '16', '2020-04-21');
INSERT INTO `goods` VALUES ('35', '16', '2020-04-21');
INSERT INTO `goods` VALUES ('36', '17', '2020-04-21');
INSERT INTO `goods` VALUES ('37', '17', '2020-04-21');
INSERT INTO `goods` VALUES ('38', '17', '2020-04-21');
INSERT INTO `goods` VALUES ('39', '18', '2020-04-21');
INSERT INTO `goods` VALUES ('40', '18', '2020-04-21');
INSERT INTO `goods` VALUES ('41', '18', '2020-04-21');
INSERT INTO `goods` VALUES ('42', '19', '2020-04-21');
INSERT INTO `goods` VALUES ('43', '19', '2020-04-21');
INSERT INTO `goods` VALUES ('44', '19', '2020-04-21');
INSERT INTO `goods` VALUES ('45', '20', '2020-04-21');
INSERT INTO `goods` VALUES ('46', '20', '2020-04-21');
INSERT INTO `goods` VALUES ('47', '20', '2020-04-21');
INSERT INTO `goods` VALUES ('48', '44', '2020-04-21');
INSERT INTO `goods` VALUES ('49', '44', '2020-04-21');
INSERT INTO `goods` VALUES ('50', '44', '2020-04-21');
INSERT INTO `goods` VALUES ('51', '45', '2020-04-21');
INSERT INTO `goods` VALUES ('52', '45', '2020-04-21');
INSERT INTO `goods` VALUES ('53', '45', '2020-04-21');
INSERT INTO `goods` VALUES ('54', '0', '2020-04-21');
INSERT INTO `goods` VALUES ('55', '0', '2020-04-21');
INSERT INTO `goods` VALUES ('56', '12', '2020-04-21');
INSERT INTO `goods` VALUES ('57', '12', '2020-04-21');
INSERT INTO `goods` VALUES ('58', '12', '2020-04-21');
INSERT INTO `goods` VALUES ('59', '12', '2020-04-21');
INSERT INTO `goods` VALUES ('60', '21', '2020-04-21');
INSERT INTO `goods` VALUES ('61', '21', '2020-04-21');
INSERT INTO `goods` VALUES ('62', '22', '2020-04-21');
INSERT INTO `goods` VALUES ('63', '22', '2020-04-21');
INSERT INTO `goods` VALUES ('64', '23', '2020-04-21');
INSERT INTO `goods` VALUES ('65', '23', '2020-04-21');
INSERT INTO `goods` VALUES ('66', '24', '2020-04-21');
INSERT INTO `goods` VALUES ('67', '24', '2020-04-21');
INSERT INTO `goods` VALUES ('68', '25', '2020-04-21');
INSERT INTO `goods` VALUES ('69', '25', '2020-04-21');
INSERT INTO `goods` VALUES ('70', '26', '2020-04-21');
INSERT INTO `goods` VALUES ('71', '26', '2020-04-21');
INSERT INTO `goods` VALUES ('72', '27', '2020-04-21');
INSERT INTO `goods` VALUES ('73', '27', '2020-04-21');
INSERT INTO `goods` VALUES ('74', '28', '2020-04-21');
INSERT INTO `goods` VALUES ('75', '28', '2020-04-21');
INSERT INTO `goods` VALUES ('76', '29', '2020-04-21');
INSERT INTO `goods` VALUES ('77', '29', '2020-04-21');
INSERT INTO `goods` VALUES ('78', '30', '2020-04-21');
INSERT INTO `goods` VALUES ('79', '30', '2020-04-21');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/', null, null, '所有', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('2', '/', '/home', 'Home', '首页', null, null, '1', '1', '1');
INSERT INTO `menu` VALUES ('3', '/', '/plantSys', 'PlantSys', '植株信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('4', '/', '/areaSys', 'AreaSys', '农田信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('5', '/', '/farmBusinessSys', 'FarmBusinessSys', '农事信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('6', '/', '/qualitySys', 'QualitySys', '质检信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('7', '/', '/logisticSys', 'LogisticSys', '物流信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('8', '/', '/QRcodeSys', 'QRcodeSys', '二维码信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('9', '/', '/blockchainSys', 'BlockChainSys', '区块链信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('10', '/', '/userSys', 'UserSys', '用户权限', 'el-icon-grape', null, '1', '2', '1');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('1', '1', '1');
INSERT INTO `menu_role` VALUES ('2', '2', '1');
INSERT INTO `menu_role` VALUES ('3', '3', '1');
INSERT INTO `menu_role` VALUES ('4', '4', '1');
INSERT INTO `menu_role` VALUES ('5', '5', '1');
INSERT INTO `menu_role` VALUES ('6', '6', '1');
INSERT INTO `menu_role` VALUES ('7', '7', '1');
INSERT INTO `menu_role` VALUES ('8', '8', '1');
INSERT INTO `menu_role` VALUES ('9', '9', '1');
INSERT INTO `menu_role` VALUES ('10', '1', '2');
INSERT INTO `menu_role` VALUES ('11', '2', '2');
INSERT INTO `menu_role` VALUES ('12', '3', '2');
INSERT INTO `menu_role` VALUES ('13', '4', '2');
INSERT INTO `menu_role` VALUES ('14', '5', '2');
INSERT INTO `menu_role` VALUES ('15', '6', '2');
INSERT INTO `menu_role` VALUES ('16', '7', '2');
INSERT INTO `menu_role` VALUES ('17', '8', '2');
INSERT INTO `menu_role` VALUES ('18', '9', '2');
INSERT INTO `menu_role` VALUES ('19', '10', '2');

-- ----------------------------
-- Table structure for quality
-- ----------------------------
DROP TABLE IF EXISTS `quality`;
CREATE TABLE `quality` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of quality
-- ----------------------------
INSERT INTO `quality` VALUES ('1', '1', '农药残留', '达标', '2020-04-01 00:00:00', '3');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_zh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_operator', '农事操作员');
INSERT INTO `role` VALUES ('2', 'ROLE_admin', '系统管理员');

-- ----------------------------
-- Table structure for tree
-- ----------------------------
DROP TABLE IF EXISTS `tree`;
CREATE TABLE `tree` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `area_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `plant_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tree
-- ----------------------------
INSERT INTO `tree` VALUES ('1', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('2', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('3', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('4', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('5', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('6', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('7', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('8', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('9', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('10', '1', '黑加仑', '2016-09-01', '存活');
INSERT INTO `tree` VALUES ('11', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('12', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('13', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('14', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('15', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('16', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('17', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('18', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('19', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('20', '2', '红提', '2016-10-01', '存活');
INSERT INTO `tree` VALUES ('21', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('22', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('23', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('24', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('25', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('26', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('27', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('28', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('29', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('30', '3', '青提', '2016-11-01', '存活');
INSERT INTO `tree` VALUES ('31', '0', '黑加仑', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('32', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('33', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('34', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('35', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('36', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('37', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('38', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('39', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('40', '4', '红提', '2020-02-04', '存活');
INSERT INTO `tree` VALUES ('41', '1', '黑加仑', '2020-04-05', '存活');
INSERT INTO `tree` VALUES ('42', '1', '黑加仑', '2020-04-05', '存活');
INSERT INTO `tree` VALUES ('43', '1', '黑加仑', '2020-04-05', '存活');
INSERT INTO `tree` VALUES ('44', '2', '黑加仑', '2020-03-29', '存活');
INSERT INTO `tree` VALUES ('45', '2', '黑加仑', '2020-03-29', '存活');
INSERT INTO `tree` VALUES ('46', '13', '红提', '2020-03-29', '存活');
INSERT INTO `tree` VALUES ('47', '13', '红提', '2020-03-29', '存活');
INSERT INTO `tree` VALUES ('48', '4', '红提', '2019-02-03', '存活');
INSERT INTO `tree` VALUES ('49', '4', '红提', '2019-02-03', '存活');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `userface` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '系统管理员', 'admin', '$2a$10$/grM.NgL3hajog4ARXwLu.EqWSeQCbjHaHBeVJ9E7vfUodh98RH0q', '15699999999', '女', '2020-03-31 00:00:00', '99', 'group1/M00/00/00/rBAACl6cSNaAfiOkAAT7lV5u67E477.jpg', '系统管理员', null, '1');
INSERT INTO `user` VALUES ('3', '农民一号', 'farm1', '$2a$10$iWdx6HFM33xFVLyDgRwsduggpfDz/.8kbzxxOvfTUHRHuq0C8GESC', '12312341234', '男', '2020-04-04 00:00:00', '35', null, '葡萄采摘工', '0x123165461316546549846a1af6a1df65a', '1');
INSERT INTO `user` VALUES ('4', '农民二号', 'farm2', '$2a$10$CXEkP0w1jgjCusf9pCWrm.Ee4.EFmqYH4zT51VfQQzsrA5Ls5i.UK', '12312311231', '男', '2020-04-07 00:00:00', '50', null, '', '', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '3', '1');
INSERT INTO `user_role` VALUES ('6', '1', '2');
INSERT INTO `user_role` VALUES ('7', '4', '1');

-- ----------------------------
-- Procedure structure for insertMany
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertMany`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertMany`(IN `area_id` int,IN `categry` varchar(255),IN `plant_date` date,IN `status` varchar(255),IN `num` int)
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
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertManyForGoodsByTree
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertManyForGoodsByTree`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertManyForGoodsByTree`(IN `tree_id`  int , IN `date` date , IN `num` int)
BEGIN
	#Routine body goes here...
DECLARE i INT;
SET i = 0;
WHILE i < `num` DO
insert into goods(tree_id, date) values(`tree_id`, `date`);
set i = i+1;						
END WHILE;

END
;;
DELIMITER ;
