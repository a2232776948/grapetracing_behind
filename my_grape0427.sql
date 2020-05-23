/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : my_grape0427

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-05-23 10:35:57
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
-- Table structure for bcacc
-- ----------------------------
DROP TABLE IF EXISTS `bcacc`;
CREATE TABLE `bcacc` (
  `hash` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hash`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bcacc
-- ----------------------------
INSERT INTO `bcacc` VALUES ('0x852cc83147a33adf725469b798b336329c6da4a5', '123456');
INSERT INTO `bcacc` VALUES ('0x88cb37399eed06c3873d0e38b5af7541a33be7c8', '123456');
INSERT INTO `bcacc` VALUES ('0x93ddacc80bd2e7af90587eb9606ffeebbcdd8885', '123456');
INSERT INTO `bcacc` VALUES ('0xa47dc1cf76bdc61ea08a55cae4fbd8f6c5212d17', '123456');
INSERT INTO `bcacc` VALUES ('0xbee6d5f98ae1aa27a3a39b891ff6f70269adb07a', '123456');
INSERT INTO `bcacc` VALUES ('0xc8066c01b7f22c71b4a29028345eaf04b2984a3a', '123456');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `company_name` varchar(255) NOT NULL,
  PRIMARY KEY (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('珠海市致兴大农科技有限公司');

-- ----------------------------
-- Table structure for express
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `express_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of express
-- ----------------------------
INSERT INTO `express` VALUES ('2', '22', '789564648915789', '签收', '2020-04-21');
INSERT INTO `express` VALUES ('12', '23', '234235235235', '暂无物流信息', '2020-05-11');
INSERT INTO `express` VALUES ('13', '24', 'YT4448863027564', '签收', '2020-05-01');

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
  `editable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of farmop
-- ----------------------------
INSERT INTO `farmop` VALUES ('3', '3', '1', '施肥', '磷肥5kg', '2020-04-25', '3', '0');
INSERT INTO `farmop` VALUES ('4', '4', '1', '施肥', '磷肥5kg', '2020-04-25', '3', '0');
INSERT INTO `farmop` VALUES ('5', '5', '1', '施肥', '磷肥5kg', '2020-04-25', '3', '0');
INSERT INTO `farmop` VALUES ('6', '6', '1', '施肥', '磷肥5kg', '2020-04-25', '3', '0');
INSERT INTO `farmop` VALUES ('7', '7', '1', '施肥', '磷肥5kg', '2020-04-04', '3', '0');
INSERT INTO `farmop` VALUES ('8', '8', '1', '施肥', '磷肥5kg', '2020-04-04', '3', '0');
INSERT INTO `farmop` VALUES ('9', '9', '1', '施肥', '磷肥5kg', '2020-04-04', '3', '0');
INSERT INTO `farmop` VALUES ('10', '10', '1', '施肥', '磷肥5kg', '2020-04-04', '3', '0');
INSERT INTO `farmop` VALUES ('11', '11', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('12', '11', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('13', '12', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('14', '13', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('15', '14', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('16', '15', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('17', '16', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('18', '17', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('19', '18', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('20', '19', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('21', '20', '2', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('22', '30', '3', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('24', '30', '3', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('25', '30', '3', '施肥', '钾肥5kg', '2020-04-06', '3', '0');
INSERT INTO `farmop` VALUES ('26', '29', '3', '施肥', '钾肥5kg', '2020-05-19', '3', '0');
INSERT INTO `farmop` VALUES ('27', '11', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('28', '12', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('29', '13', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('30', '14', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('31', '15', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('32', '16', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('33', '17', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('34', '18', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('35', '19', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('36', '20', '2', '除草', '', '2020-04-01', '3', '0');
INSERT INTO `farmop` VALUES ('38', '31', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('39', '32', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('40', '33', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('41', '34', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('42', '35', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('43', '36', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('44', '37', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('45', '38', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('46', '39', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('47', '40', '4', '采摘', '', '2019-08-13', '4', '0');
INSERT INTO `farmop` VALUES ('48', '1', '1', '施肥', '复合肥5kg', '2019-08-14', '3', '0');
INSERT INTO `farmop` VALUES ('49', '2', '1', '施肥', '复合肥5kg', '2019-08-14', '3', '0');
INSERT INTO `farmop` VALUES ('50', '3', '1', '施肥', '磷肥5kg', '2020-04-25', '3', '0');
INSERT INTO `farmop` VALUES ('51', '4', '1', '施肥', '磷肥5kg', '2020-04-25', '3', '0');
INSERT INTO `farmop` VALUES ('52', '5', '1', '施肥', '磷肥5kg', '2020-04-25', '3', '0');
INSERT INTO `farmop` VALUES ('53', '6', '1', '施肥', '磷肥5kg', '2020-04-25', '3', '0');
INSERT INTO `farmop` VALUES ('54', '7', '1', '施肥', '复合肥5kg', '2019-08-14', '3', '0');
INSERT INTO `farmop` VALUES ('55', '8', '1', '施肥', '复合肥5kg', '2019-08-14', '3', '0');
INSERT INTO `farmop` VALUES ('56', '9', '1', '施肥', '复合肥5kg', '2019-08-14', '3', '0');
INSERT INTO `farmop` VALUES ('57', '10', '1', '施肥', '复合肥5kg', '2019-08-14', '3', '0');
INSERT INTO `farmop` VALUES ('58', '21', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('59', '22', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('60', '23', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('61', '24', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('62', '25', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('63', '26', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('64', '27', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('65', '28', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('66', '29', '3', '施肥', '钾肥5kg', '2020-05-19', '3', '0');
INSERT INTO `farmop` VALUES ('67', '30', '3', '其他', '浇水', '2020-04-01', '4', '0');
INSERT INTO `farmop` VALUES ('68', '144', '13', '除草', '测试', '2020-04-02', '5', '0');
INSERT INTO `farmop` VALUES ('69', '144', '13', '除草', '测试', '2020-04-02', '5', '0');
INSERT INTO `farmop` VALUES ('70', '148', '0', '采摘', '测试4', '2020-05-21', '5', '0');
INSERT INTO `farmop` VALUES ('71', '148', '0', '采摘', '测试4', '2020-05-21', '5', '0');
INSERT INTO `farmop` VALUES ('72', '148', '0', '采摘', '测试4', '2020-05-21', '5', '0');
INSERT INTO `farmop` VALUES ('73', '148', '3', '采摘', '测试4', '2020-05-21', '5', '0');
INSERT INTO `farmop` VALUES ('74', '144', '3', '施肥', '钾肥5kg', '2020-05-19', '3', '0');
INSERT INTO `farmop` VALUES ('75', '145', '3', '施肥', '钾肥5kg', '2020-05-19', '3', '0');
INSERT INTO `farmop` VALUES ('76', '144', '3', '施肥', '钾肥5kg', '2020-05-19', '3', '0');
INSERT INTO `farmop` VALUES ('77', '143', '3', '施肥', '钾肥5kg', '2020-05-28', '3', '0');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `tracking_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('21', '2', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000021');
INSERT INTO `goods` VALUES ('22', '2', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000022');
INSERT INTO `goods` VALUES ('27', '3', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000027');
INSERT INTO `goods` VALUES ('28', '3', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000028');
INSERT INTO `goods` VALUES ('29', '21', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000029');
INSERT INTO `goods` VALUES ('30', '21', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000030');
INSERT INTO `goods` VALUES ('31', '22', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000031');
INSERT INTO `goods` VALUES ('32', '22', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000032');
INSERT INTO `goods` VALUES ('33', '23', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000033');
INSERT INTO `goods` VALUES ('34', '23', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000034');
INSERT INTO `goods` VALUES ('35', '24', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000035');
INSERT INTO `goods` VALUES ('36', '24', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000036');
INSERT INTO `goods` VALUES ('37', '25', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000037');
INSERT INTO `goods` VALUES ('38', '25', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000038');
INSERT INTO `goods` VALUES ('39', '26', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000039');
INSERT INTO `goods` VALUES ('40', '26', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000040');
INSERT INTO `goods` VALUES ('41', '27', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000041');
INSERT INTO `goods` VALUES ('42', '27', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000042');
INSERT INTO `goods` VALUES ('43', '28', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000043');
INSERT INTO `goods` VALUES ('44', '28', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000044');
INSERT INTO `goods` VALUES ('45', '29', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000045');
INSERT INTO `goods` VALUES ('46', '29', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000046');
INSERT INTO `goods` VALUES ('47', '30', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000047');
INSERT INTO `goods` VALUES ('48', '30', '新鲜葡萄', '现摘葡萄', '2020-04-27', 'goods100000000000048');

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
INSERT INTO `menu` VALUES ('9', '/', '/blockchainSys', 'BlockChainSys', '区块链信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('10', '/', '/goodsSys', 'GoodsSys', '葡萄信息', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('11', '/', '/userSys', 'UserSys', '用户权限', 'el-icon-grape', null, '1', '2', '1');
INSERT INTO `menu` VALUES ('12', '/', '/statistics', 'StatisticsSys', '数据统计', 'el-icon-grape', null, '1', '2', '1');

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
INSERT INTO `menu_role` VALUES ('20', '11', '2');
INSERT INTO `menu_role` VALUES ('21', '12', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of quality
-- ----------------------------
INSERT INTO `quality` VALUES ('1', '1', '农药残留', '达标', '2020-04-01 00:00:00', '4');
INSERT INTO `quality` VALUES ('2', '22', '农药残留', '达标', '2019-06-04 15:17:56', '3');
INSERT INTO `quality` VALUES ('3', '23', '农药残留', '达标', '2020-05-28 15:18:53', '3');

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
  `hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tree
-- ----------------------------
INSERT INTO `tree` VALUES ('21', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('22', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('23', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('24', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('25', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('26', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('27', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('28', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('29', '3', '青提', '2016-11-01', '存活', '');
INSERT INTO `tree` VALUES ('30', '3', '青提', '2016-11-01', '存活', null);
INSERT INTO `tree` VALUES ('31', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('32', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('33', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('34', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('35', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('36', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('37', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('38', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('39', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('40', '4', '红提', '2020-02-04', '存活', null);
INSERT INTO `tree` VALUES ('41', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('42', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('43', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('44', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('45', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('46', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('47', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('48', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('49', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('51', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('52', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('53', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('54', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('55', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('56', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('57', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('58', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('59', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('60', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('61', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('62', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('63', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('64', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('65', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('66', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('67', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('68', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('69', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('70', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('71', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('72', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('73', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('74', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('75', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('76', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('77', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('78', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('79', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('80', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('81', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('82', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('83', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('84', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('85', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('86', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('87', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('88', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('89', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('90', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('91', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('92', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('93', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('94', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('95', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('96', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('97', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('98', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('99', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('100', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('101', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('102', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('103', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('104', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('105', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('106', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('107', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('108', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('109', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('110', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('111', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('112', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('113', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('114', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('115', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('116', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('117', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('118', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('119', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('120', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('121', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('122', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('123', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('124', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('125', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('126', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('127', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('128', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('129', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('130', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('131', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('132', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('133', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('134', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('135', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('136', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('137', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('138', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('139', '13', '黑加仑', '2020-04-30', '存活', null);
INSERT INTO `tree` VALUES ('141', '13', '测试', '2020-04-25', '存活', null);
INSERT INTO `tree` VALUES ('142', '13', '测试', '2020-04-25', '存活', null);
INSERT INTO `tree` VALUES ('143', '13', '测试', '2020-04-25', '存活', '0xf98c116ed008155aae9d2c0666264e51b20339d97461f2e4aae12d5adf33740e');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '系统管理员', 'admin', '$2a$10$/grM.NgL3hajog4ARXwLu.EqWSeQCbjHaHBeVJ9E7vfUodh98RH0q', '15699999998', '女', '2020-03-31 00:00:00', '99', 'http://193.112.178.124:81/group1/M00/00/00/rBAACl7FJwmAWUSFAAB6KMCe7R4524.jpg', '系统管理员', '0x88cb37399eed06c3873d0e38b5af7541a33be7c8', '1');
INSERT INTO `user` VALUES ('3', '农民一号', 'farm1', '$2a$10$iWdx6HFM33xFVLyDgRwsduggpfDz/.8kbzxxOvfTUHRHuq0C8GESC', '12312341234', '男', '2020-04-04 00:00:00', '35', null, '葡萄采摘工', '0x93ddacc80bd2e7af90587eb9606ffeebbcdd8885', '1');
INSERT INTO `user` VALUES ('4', '农民二号', 'farm2', '$2a$10$CXEkP0w1jgjCusf9pCWrm.Ee4.EFmqYH4zT51VfQQzsrA5Ls5i.UK', '12312311231', '男', '2020-04-07 00:00:00', '50', null, '', '0x852cc83147a33adf725469b798b336329c6da4a5', '1');
INSERT INTO `user` VALUES ('5', '农民三号', 'farm3', '$2a$10$Z0y2NIsL3MO0rV9sPH9y1uP.pZtD/doHnVmNHJx/8tK6p/5y8KS7C', '12345698798', '男', '2020-04-09 00:00:00', '55', null, '132132', '0xc8066c01b7f22c71b4a29028345eaf04b2984a3a', '1');
INSERT INTO `user` VALUES ('6', '农民四号', 'farm4', '$2a$10$84Ornwo/ctvs69eFzV9byOW6jybXiveYQS/3HyRTZ31jCjlmQtNDq', '12345671234', '男', '2020-04-26 00:00:00', '56', null, '可怜的农民', '0xa47dc1cf76bdc61ea08a55cae4fbd8f6c5212d17', '1');
INSERT INTO `user` VALUES ('7', '农民五号', 'farm5', '$2a$10$ITKhsXE70FcXN50TPwgNduiOEJMOaGELFRRwB80iCjiYF03E0eg6a', '15245789665', '男', '2020-05-18 00:00:00', '45', null, '密码1234567', '0xbee6d5f98ae1aa27a3a39b891ff6f70269adb07a', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '3', '1');
INSERT INTO `user_role` VALUES ('6', '1', '2');
INSERT INTO `user_role` VALUES ('7', '4', '1');
INSERT INTO `user_role` VALUES ('8', '5', '1');
INSERT INTO `user_role` VALUES ('9', '6', '1');
INSERT INTO `user_role` VALUES ('10', '7', '1');

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
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertManyForGoodsByTree`(IN `tree_id`  int , IN `category` varchar(255) ,IN `desc` varchar(255),IN `date` date ,IN `num` int)
BEGIN
	#Routine body goes here...
DECLARE i INT;
DECLARE tra_num VARCHAR(256);
DECLARE tra_num2 VARCHAR(256);
DECLARE tra_num3 VARCHAR(256);
SET i = 0;
WHILE i < `num` DO
insert into goods(tree_id,category,`desc`,date) values(tree_id,category,`desc`,date);
SELECT MAX(id) into tra_num from goods ;
set tra_num3 = '100000000000000'+tra_num;
select concat('goods',tra_num3) into tra_num2 ;
UPDATE goods set `tracking_number`=tra_num2 where id=tra_num;
set i = i+1;						
END WHILE;

END
;;
DELIMITER ;
