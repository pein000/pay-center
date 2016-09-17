/*
Navicat MySQL Data Transfer

Source Server         : MyNative
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : pay-center

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-09-02 19:39:01
*/
use pay_center;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for center_channel_ali
-- ----------------------------
DROP TABLE IF EXISTS `center_channel_ali`;
CREATE TABLE `center_channel_ali` (
  `ID` varchar(32) NOT NULL,
  `PID` varchar(50) NOT NULL,
  `PARTNER_MD5` varchar(50) DEFAULT NULL COMMENT '合作伙伴MD5密钥',
  `PARTNER_RSA_PUB` varchar(400) DEFAULT NULL COMMENT '合作伙伴RSA(SHA1)秘钥_公钥',
  `PARTNER_RSA_PRI` varchar(2000) DEFAULT NULL COMMENT '合作伙伴RSA(SHA1)秘钥_私钥',
  `WIRE__MD5` varchar(50) DEFAULT NULL COMMENT '无线产品MD5密钥',
  `WIRE__RSA_PUB` varchar(400) DEFAULT NULL COMMENT '无线产品RSA(SHA1)秘钥_公钥',
  `WIRE__RSA_PRI` varchar(2000) DEFAULT NULL COMMENT '无线产品RSA(SHA1)秘钥_私钥',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for center_merchant_info
-- ----------------------------
DROP TABLE IF EXISTS `center_merchant_info`;
CREATE TABLE `center_merchant_info` (
  `ID` int(11) NOT NULL,
  `APP_KEY` varchar(32) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESC` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for center_operate_flow
-- ----------------------------
DROP TABLE IF EXISTS `center_operate_flow`;
CREATE TABLE `center_operate_flow` (
  `ID` varchar(50) NOT NULL,
  `OPERATE_STATUS` varchar(20) NOT NULL COMMENT '变更的状态',
  `OPERATE_STYLE` varchar(20) DEFAULT NULL,
  `TRADE_ID` varchar(50) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for center_trade_flow
-- ----------------------------
DROP TABLE IF EXISTS `center_trade_flow`;
CREATE TABLE `center_trade_flow` (
  `ID` varchar(50) NOT NULL,
  `PRO_SUBJECT` varchar(200) DEFAULT NULL,
  `PRO_DESC` varchar(200) DEFAULT NULL,
  `NOTIFY_URL` varchar(5000) DEFAULT NULL,
  `TRADE_TYPE` varchar(20) DEFAULT NULL,
  `TRADE_NO` varchar(50) DEFAULT NULL,
  `TRADE_STATES` varchar(20) NOT NULL,
  `OUT_REFUND_NO` varchar(50) DEFAULT NULL,
  `APP_KEY` varchar(50) NOT NULL,
  `INST_TRADE_NO` varchar(50) NOT NULL,
  `APP_ID` varchar(50) DEFAULT NULL,
  `MCH_ID` varchar(50) DEFAULT NULL,
  `AMOUNT` varchar(20) DEFAULT NULL,
  `CHANNEL_CODE` varchar(20) NOT NULL,
  `CHANNEL_URL` varchar(200) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
