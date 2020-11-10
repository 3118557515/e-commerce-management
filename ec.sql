/*
Navicat MySQL Data Transfer

Source Server         : xiangmu
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : ec

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2020-11-11 01:05:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `admin_root` varchar(20) NOT NULL COMMENT '管理员账号',
  `admin_password` varchar(20) NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`admin_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '肖记超', '1433223');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `commodity_ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `commodity_name` varchar(20) NOT NULL COMMENT '商品名称',
  `commodity_Price` int(11) NOT NULL COMMENT '商品价格',
  `commodity_stock` int(11) NOT NULL COMMENT '商品库存',
  PRIMARY KEY (`commodity_ID`),
  KEY `commodity_name` (`commodity_name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', 'T恤', '51', '11');
INSERT INTO `commodity` VALUES ('2', '毛衣', '45', '34');
INSERT INTO `commodity` VALUES ('3', '严爱', '52', '65');
INSERT INTO `commodity` VALUES ('4', '手机', '2000', '366');
INSERT INTO `commodity` VALUES ('5', '窗帘', '542', '5452');
INSERT INTO `commodity` VALUES ('6', '白板', '5999', '2585');
INSERT INTO `commodity` VALUES ('7', '记号笔', '52', '9511');
INSERT INTO `commodity` VALUES ('8', '鼠标', '299', '96666');
INSERT INTO `commodity` VALUES ('9', '灯管', '62', '985');
INSERT INTO `commodity` VALUES ('10', '灯泡', '51', '591');
INSERT INTO `commodity` VALUES ('11', '假发', '51', '562');
INSERT INTO `commodity` VALUES ('12', '键盘', '54', '2158');
INSERT INTO `commodity` VALUES ('13', '主板', '5499', '659842');
INSERT INTO `commodity` VALUES ('14', 'CPU', '3999', '5152');
INSERT INTO `commodity` VALUES ('15', '显卡', '9999', '564');
INSERT INTO `commodity` VALUES ('16', '内存', '1299', '5411');
INSERT INTO `commodity` VALUES ('17', '机箱', '1299', '2152');
INSERT INTO `commodity` VALUES ('18', '风扇', '622', '9515');
INSERT INTO `commodity` VALUES ('19', 'rgb灯条', '69', '1545');
INSERT INTO `commodity` VALUES ('20', '显示器', '9999', '5412');
INSERT INTO `commodity` VALUES ('21', 'LED灯柱', '299', '5412');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `order_user` varchar(20) NOT NULL COMMENT '下单用户的用户名',
  `order_datetime` datetime NOT NULL COMMENT '下单时间',
  `order_commodity` varchar(20) NOT NULL COMMENT '订单商品名称',
  `order_num` int(11) NOT NULL COMMENT '订单商品的数量',
  `order_address` varchar(40) NOT NULL COMMENT '订单送货地址',
  `order_TelephoneNumber` varchar(20) NOT NULL COMMENT '订单电话号码',
  PRIMARY KEY (`order_ID`),
  KEY `ForeignKey01` (`order_commodity`),
  KEY `ForeignKey02` (`order_user`),
  CONSTRAINT `ForeignKey01` FOREIGN KEY (`order_commodity`) REFERENCES `commodity` (`commodity_name`),
  CONSTRAINT `ForeignKey02` FOREIGN KEY (`order_user`) REFERENCES `user` (`user_root`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '肖记超', '2020-10-13 15:04:06', 'T恤', '2', '湖南生物几点职业技术学院', '0');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `num` int(11) NOT NULL,
  `sumMoney` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `shoppingcart_ibfk_1` (`user_id`),
  CONSTRAINT `shoppingcart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('3', 'T恤', '1', '51', '4');
INSERT INTO `shoppingcart` VALUES ('5', '严爱', '1', '52', '4');
INSERT INTO `shoppingcart` VALUES ('17', '严爱', '1', '52', '4');
INSERT INTO `shoppingcart` VALUES ('18', '灯泡', '1', '51', '4');
INSERT INTO `shoppingcart` VALUES ('19', '内存', '1', '1299', '4');
INSERT INTO `shoppingcart` VALUES ('20', 'CPU', '1', '3999', '4');
INSERT INTO `shoppingcart` VALUES ('21', 'LED灯柱', '1', '299', '4');
INSERT INTO `shoppingcart` VALUES ('22', '严爱', '1', '52', '4');
INSERT INTO `shoppingcart` VALUES ('23', '假发', '1', '51', '2');
INSERT INTO `shoppingcart` VALUES ('28', '手机', '1', '2000', '2');
INSERT INTO `shoppingcart` VALUES ('29', '严爱', '1', '52', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_root` varchar(20) NOT NULL COMMENT '用户账号',
  `user_password` varchar(20) NOT NULL COMMENT '用户密码',
  `user_TelephoneNumber` varchar(11) NOT NULL COMMENT '用户注册时的电话号码',
  `user_email` varchar(20) NOT NULL COMMENT '用户注册时的邮箱',
  `user_address` varchar(30) NOT NULL,
  PRIMARY KEY (`user_ID`),
  KEY `user_root` (`user_root`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '肖记超', '857857', '0', '', '湖南生物机电职业技术学院');
INSERT INTO `user` VALUES ('2', '小舜舜', '123123', '8208208820', '857857@qq.com', '湖南生物机电职业技术学院');
INSERT INTO `user` VALUES ('3', '小航航', '000000', '321123', '123@qq.com', '');
INSERT INTO `user` VALUES ('4', '', '', '', '', '');
INSERT INTO `user` VALUES ('5', '4515', '22222', '5312315', '231324', '');
