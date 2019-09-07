/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : service_db

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-09-07 09:53:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_label
-- ----------------------------
DROP TABLE IF EXISTS `base_label`;
CREATE TABLE `base_label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Records of base_label
-- ----------------------------
INSERT INTO `base_label` VALUES ('1', '测试标签1');
INSERT INTO `base_label` VALUES ('2', '测试标签2');
INSERT INTO `base_label` VALUES ('3', '测试标签3');
INSERT INTO `base_label` VALUES ('4', 'aaaaa');
INSERT INTO `base_label` VALUES ('5', '123');
INSERT INTO `base_label` VALUES ('6', '1231');
INSERT INTO `base_label` VALUES ('7', '231');
INSERT INTO `base_label` VALUES ('8', null);
INSERT INTO `base_label` VALUES ('9', null);

-- ----------------------------
-- Table structure for base_param
-- ----------------------------
DROP TABLE IF EXISTS `base_param`;
CREATE TABLE `base_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assessment_report_template_id` bigint(20) DEFAULT NULL,
  `assessment_report_xml_id` bigint(20) DEFAULT NULL,
  `evaluation_number` int(11) DEFAULT NULL,
  `is_basic_info` bit(1) DEFAULT NULL,
  `is_description` bit(1) DEFAULT NULL,
  `is_evaluation` bit(1) DEFAULT NULL,
  `is_test` bit(1) DEFAULT NULL,
  `manual_template_id` bigint(11) DEFAULT NULL,
  `manual_xml_id` bigint(11) DEFAULT NULL,
  `model_file_template_id` bigint(11) DEFAULT NULL,
  `model_file_xml_id` bigint(11) DEFAULT NULL,
  `test_number` int(11) DEFAULT NULL,
  `test_report_template_id` bigint(11) DEFAULT NULL,
  `test_report_xml_id` bigint(11) DEFAULT NULL,
  `resource_download_set` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKixp778xtdsithkwxhvurj0ysf` (`assessment_report_template_id`),
  KEY `FKemdgyjmb7mbr5uqmxkjmw7gax` (`assessment_report_xml_id`),
  KEY `FKox4n0vcl89x35mhsx4eo7j18j` (`manual_template_id`),
  KEY `FKu90lnte596baommxexvmsmwt` (`manual_xml_id`),
  KEY `FKk5a0sc4u4phpudhlu95oh4re8` (`model_file_template_id`),
  KEY `FK9c588b762eah2mbmwg1osl9mm` (`model_file_xml_id`),
  KEY `FKi21u5s4l8neo0ib0ilg1bwy2j` (`test_report_template_id`),
  KEY `FK6xds67pfai80n5qsl6qns0olk` (`test_report_xml_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_param
-- ----------------------------
INSERT INTO `base_param` VALUES ('1', null, null, '3', '', '\0', '\0', '', null, null, null, null, '2', null, null, '0');

-- ----------------------------
-- Table structure for data_permission
-- ----------------------------
DROP TABLE IF EXISTS `data_permission`;
CREATE TABLE `data_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `developer_information` bit(1) DEFAULT NULL,
  `down_load_model_file` bit(1) DEFAULT NULL,
  `download_model_description_file` bit(1) DEFAULT NULL,
  `download_parameter_file` bit(1) DEFAULT NULL,
  `permission_type` varchar(255) DEFAULT NULL,
  `resources_name` bit(1) DEFAULT NULL,
  `view_model_description` bit(1) DEFAULT NULL,
  `view_model_users` bit(1) DEFAULT NULL,
  `view_parameter_description` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_permission
-- ----------------------------
INSERT INTO `data_permission` VALUES ('1', '\0', '', '\0', '\0', 'A', '\0', '', '\0', '');
INSERT INTO `data_permission` VALUES ('2', '', '\0', '', '', 'B', '\0', '\0', '', '\0');
INSERT INTO `data_permission` VALUES ('3', '\0', '\0', null, null, 'C', '\0', '\0', null, '\0');
INSERT INTO `data_permission` VALUES ('4', '\0', '\0', '\0', '\0', 'D', '\0', '\0', '\0', '\0');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_name` varchar(50) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(100) DEFAULT NULL COMMENT '文件路径',
  `file_size` varchar(10) DEFAULT NULL COMMENT '文件尺寸',
  `file_description` varchar(200) DEFAULT NULL COMMENT '文件描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_userid` varchar(20) DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件';

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for flow_inst
-- ----------------------------
DROP TABLE IF EXISTS `flow_inst`;
CREATE TABLE `flow_inst` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `business_id` bigint(20) DEFAULT NULL COMMENT '业务ID',
  `flow_name` varchar(200) DEFAULT NULL COMMENT '流程实例名称',
  `flow_status` int(11) DEFAULT NULL COMMENT '流程状态[0:草稿;1:待审核;2审核通过;驳回]',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_userid` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  `update_userid` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `flow_type` int(11) DEFAULT '0' COMMENT '流程类型[0:模型审核，1：申请下载]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='流程实例';

-- ----------------------------
-- Records of flow_inst
-- ----------------------------
INSERT INTO `flow_inst` VALUES ('1', '73', '模型审核流程', '0', '2018-12-06 16:14:07', null, '9', '2018-12-06 16:14:07', null, null, '0');
INSERT INTO `flow_inst` VALUES ('2', '74', '2018-12-7普通用户提交的模型需要您的审核', '0', '2018-12-07 11:53:45', null, '9', '2018-12-07 11:53:44', null, null, '0');
INSERT INTO `flow_inst` VALUES ('3', '75', '2018-12-7普通用户提交的模型需要您的审核', '0', '2018-12-07 12:32:22', null, '9', '2018-12-07 12:32:22', null, null, '0');
INSERT INTO `flow_inst` VALUES ('4', '75', '2018-12-7普通用户提交的模型需要您的审核', '0', '2018-12-07 12:34:50', null, '9', '2018-12-07 12:34:50', null, null, '0');
INSERT INTO `flow_inst` VALUES ('5', '76', '2018-12-7普通用户提交的模型需要您的审核', '0', '2018-12-07 17:03:17', null, '9', '2018-12-07 17:03:16', null, null, '0');
INSERT INTO `flow_inst` VALUES ('6', '77', '2018-12-7普通用户提交的模型需要您的审核', '0', '2018-12-07 18:08:11', null, '9', '2018-12-07 18:08:11', null, null, '0');
INSERT INTO `flow_inst` VALUES ('7', '83', '2018-12-10普通用户提交的模型需要您的审核', '0', '2018-12-10 10:34:44', null, '9', '2018-12-10 10:34:44', null, null, '0');
INSERT INTO `flow_inst` VALUES ('8', '88', '2018-12-10普通用户提交的模型需要您的审核', '0', '2018-12-10 10:40:44', null, '9', '2018-12-10 10:40:44', null, null, '0');
INSERT INTO `flow_inst` VALUES ('9', '91', '2018-12-10普通用户提交的模型需要您的审核', '0', '2018-12-10 10:49:41', null, '9', '2018-12-10 10:49:41', null, null, '0');
INSERT INTO `flow_inst` VALUES ('10', '98', '2018-12-10普通用户提交的模型需要您的审核', '0', '2018-12-10 11:31:43', null, '9', '2018-12-10 11:31:42', null, null, '0');
INSERT INTO `flow_inst` VALUES ('11', '101', '2018-12-10普通用户提交的模型需要您的审核', '0', '2018-12-10 11:45:48', null, '9', '2018-12-10 11:45:47', null, null, '0');
INSERT INTO `flow_inst` VALUES ('12', '102', '2018-12-10普通用户提交的模型需要您的审核', '0', '2018-12-10 15:20:56', null, '9', '2018-12-10 15:20:55', null, null, '0');
INSERT INTO `flow_inst` VALUES ('13', '103', '2018-12-10普通用户提交的模型需要您的审核', '0', '2018-12-10 15:22:23', null, '9', '2018-12-10 15:22:22', null, null, '0');
INSERT INTO `flow_inst` VALUES ('14', '107', '2018-12-12普通用户提交的模型需要您的审核', '0', '2018-12-12 14:50:56', null, '9', '2018-12-12 14:50:55', null, null, '0');
INSERT INTO `flow_inst` VALUES ('15', '108', '2018-12-12普通用户提交的模型需要您的审核', '0', '2018-12-12 15:08:16', null, '9', '2018-12-12 15:08:15', null, null, '0');
INSERT INTO `flow_inst` VALUES ('16', '111', '2018-12-12普通用户提交的模型需要您的审核', '3', '2018-12-12 15:24:13', null, '9', '2018-12-12 16:59:03', null, null, '0');
INSERT INTO `flow_inst` VALUES ('17', '112', '2018-12-12普通用户提交的模型需要您的审核', '2', '2018-12-12 17:20:09', null, '9', '2018-12-12 17:22:19', null, null, '0');
INSERT INTO `flow_inst` VALUES ('18', '113', '2018-12-14普通用户提交的模型需要您的审核', '0', '2018-12-14 13:52:12', null, '9', '2018-12-14 13:52:11', null, null, '0');
INSERT INTO `flow_inst` VALUES ('19', '114', '2018-12-14普通用户提交的模型需要您的审核', '2', '2018-12-14 15:23:33', null, '9', '2018-12-14 17:15:03', null, null, '0');
INSERT INTO `flow_inst` VALUES ('20', '116', '2018-12-28普通用户提交的模型需要您的审核', '0', '2018-12-28 09:43:01', null, '9', '2018-12-28 09:43:00', null, null, '0');
INSERT INTO `flow_inst` VALUES ('21', '117', '2018-12-28普通用户提交的模型需要您的审核', '0', '2018-12-28 11:14:35', null, '9', '2018-12-28 11:14:34', null, null, '0');

-- ----------------------------
-- Table structure for flow_step
-- ----------------------------
DROP TABLE IF EXISTS `flow_step`;
CREATE TABLE `flow_step` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `flow_id` bigint(20) DEFAULT NULL COMMENT '流程id',
  `step_name` varchar(100) DEFAULT NULL COMMENT '步骤名称',
  `step_identification` int(11) DEFAULT NULL COMMENT '步骤标识【通过常亮标识】',
  `step_type` int(11) DEFAULT NULL COMMENT '步骤类型【0:开始节点;1:过程节点;2:结束节点】',
  `step_number` int(11) DEFAULT NULL COMMENT '步骤编号',
  `step_status` int(11) DEFAULT NULL COMMENT '审核状态【0:初始状态;1:审核通过;2:审核驳回】',
  `approval_opinion` varchar(100) DEFAULT NULL COMMENT '审批意见',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `do_with_user` varchar(20) DEFAULT NULL COMMENT '处理人',
  `do_with_userid` bigint(20) DEFAULT NULL COMMENT '处理人ID',
  `complete_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '完成时间',
  `wait_user_id` varchar(100) DEFAULT NULL COMMENT '待办人ID，可以多个，中间用,隔开',
  `wait_user` varchar(255) DEFAULT NULL COMMENT '代办人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='流程步骤';

-- ----------------------------
-- Records of flow_step
-- ----------------------------
INSERT INTO `flow_step` VALUES ('1', '1', '流程启动', null, null, '0', '2', '提交审核', '2018-12-06 16:14:07', 'user', '9', '2018-12-06 16:14:07', '9', 'user');
INSERT INTO `flow_step` VALUES ('2', '1', '测试审核', null, null, '1', '1', '', '2018-12-06 16:14:07', null, null, '2018-12-06 16:14:07', '11', 'aaaa');
INSERT INTO `flow_step` VALUES ('3', '1', '可信度评估审核', null, null, '2', '1', '', '2018-12-06 16:14:07', null, null, '2018-12-06 16:14:07', '11', 'aaaa');
INSERT INTO `flow_step` VALUES ('4', '2', '流程启动', null, null, '0', '2', '提交审核', '2018-12-07 11:53:45', 'user', '9', '2018-12-07 11:53:45', '9', 'user');
INSERT INTO `flow_step` VALUES ('5', '2', '测试审核', null, null, '1', '1', '', '2018-12-07 11:53:45', null, null, '2018-12-07 11:53:45', '12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('6', '2', '可信度评估审核', null, null, '2', '0', '', '2018-12-07 11:53:45', null, null, '2018-12-07 11:53:45', '12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('7', '2', '系统管理员入库', null, null, '4', '0', '', '2018-12-07 11:53:45', null, null, '2018-12-07 11:53:45', '11,', 'aaaa');
INSERT INTO `flow_step` VALUES ('8', '4', '流程启动', null, null, '0', '2', '提交审核', '2018-12-07 12:34:50', 'user', '9', '2018-12-07 12:34:50', '9', 'user');
INSERT INTO `flow_step` VALUES ('9', '4', '系统管理员入库', null, null, '4', '0', '', '2018-12-07 12:34:50', null, null, '2018-12-07 12:34:50', '11,', 'aaaa');
INSERT INTO `flow_step` VALUES ('10', '5', '流程启动', null, null, '0', '2', '提交审核', '2018-12-07 17:03:17', 'user', '9', '2018-12-07 17:03:17', '9', 'user');
INSERT INTO `flow_step` VALUES ('11', '5', '测试审核', null, null, '1', '1', '', '2018-12-07 17:03:17', null, null, '2018-12-07 17:03:16', ',11,', 'aaaa');
INSERT INTO `flow_step` VALUES ('12', '5', '可信度评估审核', null, null, '2', '0', '', '2018-12-07 17:03:17', null, null, '2018-12-07 17:03:16', ',11,', 'aaaa');
INSERT INTO `flow_step` VALUES ('13', '5', '系统管理员入库', null, null, '4', '0', '', '2018-12-07 17:03:17', null, null, '2018-12-07 17:03:16', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('14', '6', '流程启动', null, null, '0', '2', '提交审核', '2018-12-07 18:08:11', 'user', '9', '2018-12-07 18:08:11', '9', 'user');
INSERT INTO `flow_step` VALUES ('15', '6', '测试审核', null, null, '1', '1', '', '2018-12-07 18:08:11', null, null, '2018-12-07 18:08:11', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('16', '6', '可信度评估审核', null, null, '2', '0', '', '2018-12-07 18:08:11', null, null, '2018-12-07 18:08:11', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('17', '6', '系统管理员入库', null, null, '4', '0', '', '2018-12-07 18:08:11', null, null, '2018-12-07 18:08:11', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('18', '7', '流程启动', null, null, '0', '2', '提交审核', '2018-12-10 10:34:44', 'user', '9', '2018-12-10 10:34:44', '9', 'user');
INSERT INTO `flow_step` VALUES ('19', '7', '系统管理员入库', null, null, '4', '0', '', '2018-12-10 10:34:44', null, null, '2018-12-10 10:34:44', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('20', '8', '流程启动', null, null, '0', '2', '提交审核', '2018-12-10 10:40:44', 'user', '9', '2018-12-10 10:40:44', '9', 'user');
INSERT INTO `flow_step` VALUES ('21', '8', '测试审核', null, null, '1', '1', '', '2018-12-10 10:40:44', null, null, '2018-12-10 10:40:44', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('22', '8', '可信度评估审核', null, null, '2', '0', '', '2018-12-10 10:40:44', null, null, '2018-12-10 10:40:44', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('23', '8', '系统管理员入库', null, null, '4', '0', '', '2018-12-10 10:40:44', null, null, '2018-12-10 10:40:44', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('24', '9', '流程启动', null, null, '0', '2', '提交审核', '2018-12-10 10:49:41', 'user', '9', '2018-12-10 10:49:41', '9', 'user');
INSERT INTO `flow_step` VALUES ('25', '9', '测试审核', null, null, '1', '1', '', '2018-12-10 10:49:41', null, null, '2018-12-10 10:49:41', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('26', '9', '可信度评估审核', null, null, '2', '0', '', '2018-12-10 10:49:41', null, null, '2018-12-10 10:49:41', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('27', '9', '系统管理员入库', null, null, '4', '0', '', '2018-12-10 10:49:41', null, null, '2018-12-10 10:49:41', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('28', '10', '流程启动', null, null, '0', '2', '提交审核', '2018-12-10 11:31:43', 'user', '9', '2018-12-10 11:31:43', '9', 'user');
INSERT INTO `flow_step` VALUES ('29', '10', '测试审核', null, null, '1', '1', '', '2018-12-10 11:31:43', null, null, '2018-12-10 11:31:42', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('30', '10', '可信度评估审核', null, null, '2', '0', '', '2018-12-10 11:31:43', null, null, '2018-12-10 11:31:42', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('31', '10', '系统管理员入库', null, null, '4', '0', '', '2018-12-10 11:31:43', null, null, '2018-12-10 11:31:42', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('32', '11', '流程启动', null, null, '0', '2', '提交审核', '2018-12-10 11:45:48', 'user', '9', '2018-12-10 11:45:48', '9', 'user');
INSERT INTO `flow_step` VALUES ('33', '11', '测试审核', null, null, '1', '1', '', '2018-12-10 11:45:48', null, null, '2018-12-10 11:45:47', ',11,', 'aaaa');
INSERT INTO `flow_step` VALUES ('34', '11', '可信度评估审核', null, null, '2', '0', '', '2018-12-10 11:45:48', null, null, '2018-12-10 11:45:47', ',11,', 'aaaa');
INSERT INTO `flow_step` VALUES ('35', '11', '系统管理员入库', null, null, '4', '0', '', '2018-12-10 11:45:48', null, null, '2018-12-10 11:45:47', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('36', '12', '流程启动', null, null, '0', '2', '提交审核', '2018-12-10 15:20:56', 'user', '9', '2018-12-10 15:20:56', '9', 'user');
INSERT INTO `flow_step` VALUES ('37', '12', '测试审核', null, null, '1', '1', '', '2018-12-10 15:20:56', null, null, '2018-12-10 15:20:55', ',11,', 'aaaa');
INSERT INTO `flow_step` VALUES ('38', '12', '可信度评估审核', null, null, '2', '0', '', '2018-12-10 15:20:56', null, null, '2018-12-10 15:20:55', ',11,', 'aaaa');
INSERT INTO `flow_step` VALUES ('39', '12', '系统管理员入库', null, null, '4', '0', '', '2018-12-10 15:20:56', null, null, '2018-12-10 15:20:55', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('40', '13', '流程启动', null, null, '0', '2', '提交审核', '2018-12-10 15:22:23', 'user', '9', '2018-12-10 15:22:23', '9', 'user');
INSERT INTO `flow_step` VALUES ('41', '13', '测试审核', null, null, '1', '1', '', '2018-12-10 15:22:23', null, null, '2018-12-10 15:22:22', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('42', '13', '可信度评估审核', null, null, '2', '0', '', '2018-12-10 15:22:23', null, null, '2018-12-10 15:22:22', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('43', '13', '系统管理员入库', null, null, '4', '0', '', '2018-12-10 15:22:23', null, null, '2018-12-10 15:22:22', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('44', '14', '流程启动', null, null, '0', '2', '提交审核', '2018-12-12 14:50:56', 'user', '9', '2018-12-12 14:50:56', '9', 'user');
INSERT INTO `flow_step` VALUES ('45', '14', '测试审核', null, null, '1', '1', '', '2018-12-12 14:50:56', null, null, '2018-12-12 14:50:55', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('46', '14', '可信度评估审核', null, null, '2', '0', '', '2018-12-12 14:50:56', null, null, '2018-12-12 14:50:55', ',12,11,', 'abcdef,aaaa');
INSERT INTO `flow_step` VALUES ('47', '14', '系统管理员入库', null, null, '4', '0', '', '2018-12-12 14:50:56', null, null, '2018-12-12 14:50:55', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('48', '15', '流程启动', null, null, '0', '2', '提交审核', '2018-12-12 15:08:16', 'user', '9', '2018-12-12 15:08:16', '9', 'user');
INSERT INTO `flow_step` VALUES ('49', '15', '测试审核', null, null, '1', '1', '', '2018-12-12 15:08:16', null, null, '2018-12-12 15:08:15', ',13,', '测试');
INSERT INTO `flow_step` VALUES ('50', '15', '可信度评估审核', null, null, '2', '0', '', '2018-12-12 15:08:16', null, null, '2018-12-12 15:08:15', ',13,', '测试');
INSERT INTO `flow_step` VALUES ('51', '15', '系统管理员入库', null, null, '4', '0', '', '2018-12-12 15:08:16', null, null, '2018-12-12 15:08:15', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('52', '16', '流程启动', null, null, '0', '2', '提交审核', '2018-12-12 15:24:13', 'user', '9', '2018-12-12 15:24:13', '9', 'user');
INSERT INTO `flow_step` VALUES ('53', '16', '测试审核', null, null, '1', '2', 'aaaa', '2018-12-12 15:24:00', null, null, '2018-12-12 16:57:45', ',14,', '测试人员');
INSERT INTO `flow_step` VALUES ('54', '16', '可信度评估审核', null, null, '2', '3', 'bbbb', '2018-12-12 15:24:00', null, null, '2018-12-12 16:59:03', ',14,', '测试人员');
INSERT INTO `flow_step` VALUES ('55', '16', '系统管理员入库', null, null, '4', '0', '', '2018-12-12 15:24:13', null, null, '2018-12-12 15:24:13', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('56', '17', '流程启动', null, null, '0', '2', '提交审核', '2018-12-12 17:20:09', 'user', '9', '2018-12-12 17:20:09', '9', 'user');
INSERT INTO `flow_step` VALUES ('57', '17', '测试审核', null, null, '1', '2', 'aaa', '2018-12-12 17:20:00', null, null, '2018-12-12 17:21:10', ',14,', '测试人员');
INSERT INTO `flow_step` VALUES ('58', '17', '可信度评估审核', null, null, '2', '2', 'aaaa', '2018-12-12 17:20:00', null, null, '2018-12-12 17:21:41', ',14,', '测试人员');
INSERT INTO `flow_step` VALUES ('59', '17', '系统管理员入库', null, null, '4', '2', '', '2018-12-12 17:20:00', null, null, '2018-12-12 17:22:19', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('60', '18', '流程启动', null, null, '0', '2', '提交审核', '2018-12-14 13:52:12', 'user', '9', '2018-12-14 13:52:12', '9', 'user');
INSERT INTO `flow_step` VALUES ('61', '18', '测试审核', null, null, '1', '1', '', '2018-12-14 13:52:12', null, null, '2018-12-14 13:52:11', ',14,', '测试人员');
INSERT INTO `flow_step` VALUES ('62', '18', '可信度评估审核', null, null, '2', '0', '', '2018-12-14 13:52:12', null, null, '2018-12-14 13:52:11', ',14,', '测试人员');
INSERT INTO `flow_step` VALUES ('63', '18', '系统管理员入库', null, null, '4', '0', '', '2018-12-14 13:52:12', null, null, '2018-12-14 13:52:11', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('64', '19', '流程启动', null, null, '0', '2', '提交审核', '2018-12-14 15:23:33', 'user', '9', '2018-12-14 15:23:33', '9', 'user');
INSERT INTO `flow_step` VALUES ('65', '19', '测试审核', null, null, '1', '2', '12313', '2018-12-14 15:23:00', null, null, '2018-12-14 15:24:29', ',14,', '测试人员');
INSERT INTO `flow_step` VALUES ('66', '19', '可信度评估审核', null, null, '2', '2', '3123123', '2018-12-14 15:23:00', null, null, '2018-12-14 15:24:41', ',14,', '测试人员');
INSERT INTO `flow_step` VALUES ('67', '19', '系统管理员入库', null, null, '4', '2', 'AASAAA', '2018-12-14 15:23:00', null, null, '2018-12-14 17:15:03', ',12,', 'abcdef');
INSERT INTO `flow_step` VALUES ('68', '21', '流程启动', null, null, '0', '2', '提交审核', '2018-12-28 11:14:35', 'user', '9', '2018-12-28 11:14:35', '9', 'user');
INSERT INTO `flow_step` VALUES ('69', '21', '测试审核', null, null, '1', '1', '', '2018-12-28 11:14:35', null, null, '2018-12-28 11:14:34', ',18,', 'test123');
INSERT INTO `flow_step` VALUES ('70', '21', '系统管理员入库', null, null, '4', '0', '', '2018-12-28 11:14:35', null, null, '2018-12-28 11:14:34', ',7,', '管理员');

-- ----------------------------
-- Table structure for model_category
-- ----------------------------
DROP TABLE IF EXISTS `model_category`;
CREATE TABLE `model_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `file_id` bigint(20) DEFAULT NULL COMMENT '文件描述文件模板ID',
  `set_file` tinyint(1) DEFAULT '0' COMMENT '是否设置,默认为否.0:标识否,1:标示是',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标样式',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of model_category
-- ----------------------------
INSERT INTO `model_category` VALUES ('1', '作战模型', '0', null, '0', null);
INSERT INTO `model_category` VALUES ('2', '装备模型', '0', null, '0', null);
INSERT INTO `model_category` VALUES ('3', '环境模型', '0', null, '0', null);
INSERT INTO `model_category` VALUES ('4', '评估模型', '0', null, '0', null);
INSERT INTO `model_category` VALUES ('5', '支撑模型', '0', null, '0', null);
INSERT INTO `model_category` VALUES ('6', '支撑测试1', '5', null, '0', null);
INSERT INTO `model_category` VALUES ('8', '作战测试', '1', null, '0', null);
INSERT INTO `model_category` VALUES ('9', '平原', '1', null, '0', null);
INSERT INTO `model_category` VALUES ('10', '城市', '1', null, '0', null);
INSERT INTO `model_category` VALUES ('11', '测试1', '8', null, '0', null);
INSERT INTO `model_category` VALUES ('12', '测试2', '8', null, '0', null);
INSERT INTO `model_category` VALUES ('13', '沙漠', '9', null, '0', null);
INSERT INTO `model_category` VALUES ('14', '草原', '9', null, '0', null);
INSERT INTO `model_category` VALUES ('15', '广场', '10', null, '0', null);
INSERT INTO `model_category` VALUES ('16', '工厂', '10', null, '0', null);
INSERT INTO `model_category` VALUES ('17', '写字楼', '10', null, '0', null);
INSERT INTO `model_category` VALUES ('18', '手工工厂', '15', null, '0', null);
INSERT INTO `model_category` VALUES ('19', '化工厂', '15', null, '0', null);
INSERT INTO `model_category` VALUES ('20', '金属加工厂', '16', null, '0', null);

-- ----------------------------
-- Table structure for model_collection
-- ----------------------------
DROP TABLE IF EXISTS `model_collection`;
CREATE TABLE `model_collection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` bigint(20) DEFAULT NULL COMMENT '模型ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '人员ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '人员名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `collect_status` bit(1) DEFAULT NULL COMMENT '收藏状态[0,取消收藏;1:收藏]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='模型收藏';

-- ----------------------------
-- Records of model_collection
-- ----------------------------
INSERT INTO `model_collection` VALUES ('11', '112', '9', '普通用户', '2019-01-02 22:09:22', '');

-- ----------------------------
-- Table structure for model_down
-- ----------------------------
DROP TABLE IF EXISTS `model_down`;
CREATE TABLE `model_down` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` bigint(20) DEFAULT NULL COMMENT '模型ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '人员ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '人员名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模型下载';

-- ----------------------------
-- Records of model_down
-- ----------------------------

-- ----------------------------
-- Table structure for model_info
-- ----------------------------
DROP TABLE IF EXISTS `model_info`;
CREATE TABLE `model_info` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_name` varchar(50) DEFAULT NULL COMMENT '模型名称',
  `developer_id` bigint(20) DEFAULT NULL COMMENT '模型编写人ID',
  `contact_information` varchar(40) DEFAULT NULL COMMENT '联系方式',
  `category` bigint(4) DEFAULT NULL COMMENT '模型类型【1:装备;2:作战;3:环境;4:评估;5:支撑类】',
  `type` bigint(4) DEFAULT NULL COMMENT '模型小类',
  `developer` varchar(20) DEFAULT NULL COMMENT '模型编写人',
  `write_time` varchar(30) DEFAULT NULL COMMENT '模型编写时间',
  `model_department` varchar(40) DEFAULT NULL COMMENT '模型编写单位',
  `contact_information_isvisible` bit(1) DEFAULT b'0' COMMENT '联系方式下载者可见【0:不可见;1:可见】',
  `model_permissions` int(1) DEFAULT NULL COMMENT '模型开放权限[0:A级（所有用户都可见）;1:B级（注册用户均可下载）;2:C级（注册用户可预览，需要申请下载权限）;3:D级（仅管理员可以下载）]',
  `model_label` varchar(200) DEFAULT NULL COMMENT '标签',
  `pv` int(11) DEFAULT '0' COMMENT '阅读量',
  `download_count` int(11) DEFAULT '0' COMMENT '下载量',
  `model_file_id` bigint(20) DEFAULT NULL COMMENT '模型描述文件',
  `model_execute_type` varchar(20) DEFAULT NULL COMMENT '模型执行模式[Debug;Release;Debug/Release]',
  `model_developer_platform` varchar(30) DEFAULT NULL COMMENT '模型开发平台[visual studio2006;visual studio2008;visual studio2010;visual studio2015;其他]',
  `model_runtime_platform` varchar(30) DEFAULT NULL COMMENT '模型使用平台[win32;win64]',
  `model_manual_id` bigint(20) DEFAULT NULL COMMENT '模型使用手册',
  `model_test_report_id` bigint(20) DEFAULT NULL COMMENT '模型测试报告',
  `model_assessment_report_id` bigint(20) DEFAULT NULL COMMENT '模型可信度评估报告',
  `model_status` int(2) DEFAULT NULL COMMENT '模型状态【1,2,3,4,草稿的各个步骤，5:待审核;7:通过;8：入库，9:驳回】',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  `update_user_id` bigint(4) DEFAULT NULL COMMENT '更新人ID',
  `description` varchar(400) DEFAULT NULL COMMENT '模型描述',
  `model_code` varchar(255) DEFAULT NULL,
  `other_remark` varchar(255) DEFAULT NULL,
  `classification` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='模型基本信息';

-- ----------------------------
-- Records of model_info
-- ----------------------------
INSERT INTO `model_info` VALUES ('105', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '1', '标签1|标签2|标签3', null, null, '196', null, null, null, null, null, null, '3', '2018-12-11 14:43:52', '2018-12-11 14:43:52', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('106', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', '\0', '1', '标签1|标签2|标签3', null, null, '198', 'Debug', 'visual Studio2008', 'win64', '199', null, null, '3', '2018-12-12 10:33:22', '2018-12-12 10:33:22', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('107', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '1', '标签1|标签2|标签3', null, null, '201', 'Debug', 'visual Studio2006', 'win32', '202', '203', '204', '5', '2018-12-12 14:50:56', '2018-12-12 14:50:56', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('108', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '4', '标签1|标签2|标签3', null, null, '205', 'Debug', 'visual Studio2006', 'win32', '206', '207', '208', '5', '2018-12-12 15:08:16', '2018-12-12 15:08:16', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('109', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '3', '标签1|标签2|标签3', null, null, '210', 'Debug', 'visual Studio2006', 'win32', '211', null, null, '3', '2018-12-12 15:18:03', '2018-12-12 15:18:03', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('110', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '4', '标签1|标签2|标签3', null, null, '213', 'Debug', 'visual Studio2006', 'win32', '214', null, null, '3', '2018-12-12 15:21:56', '2018-12-12 15:21:56', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('111', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '4', '标签1|标签2|标签3', null, null, '215', 'Debug', 'visual Studio2006', 'win32', '216', '217', '218', '9', '2018-12-12 15:24:13', '2018-12-12 15:24:13', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('112', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '1', '标签1|标签2|标签3', null, null, '219', 'Debug', 'visual Studio2008', 'win32', '220', '221', '222', '8', '2018-12-12 17:20:09', '2018-12-12 17:20:09', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('113', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '2', '标签1|标签2|标签3', null, null, '224', 'Debug', 'visual Studio2008', 'linux', '225', '226', '227', '5', '2018-12-14 13:52:12', '2018-12-14 13:52:12', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('114', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', '', '4', '标签1|标签2|标签3', null, null, '229', 'Debug', 'visual Studio2006', 'win32', '230', '231', '232', '8', '2018-12-14 15:23:33', '2018-12-14 15:23:33', 'user', '9', '模型的功能、应用范围等描述', null, null, null);
INSERT INTO `model_info` VALUES ('115', '装备模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '4', '标签1|标签2|标签3', null, null, '233', 'Debug', '其他', '其他', '234', '235', null, '4', '2018-12-18 09:57:21', '2018-12-18 09:57:21', 'user', '9', '模型的功能、应用范围等描述', null, '测试', null);
INSERT INTO `model_info` VALUES ('116', '作战模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', '', '2', '标签1|标签2|标签3', null, null, '236', 'Debug', 'visual Studio2006', 'win32', '237', '238', null, '4', '2018-12-28 09:42:56', '2018-12-28 09:42:56', 'user', '9', '模型的功能、应用范围等描述', 'ZK01000001', null, null);
INSERT INTO `model_info` VALUES ('117', '作战模型描述文件模板', '9', null, '1', null, '张三', '2018-4-17', '北京仿真中心', null, '4', '标签1|标签2|标签3', null, null, '240', 'Debug', 'visual Studio2006', 'win32', '241', '242', '243', '5', '2018-12-28 11:14:35', '2018-12-28 11:14:35', 'user', '9', '模型的功能、应用范围等描述', 'ZK01000001', null, null);

-- ----------------------------
-- Table structure for model_pv
-- ----------------------------
DROP TABLE IF EXISTS `model_pv`;
CREATE TABLE `model_pv` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` bigint(20) DEFAULT NULL COMMENT '模型ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '人员ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '人员名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模型pv量';

-- ----------------------------
-- Records of model_pv
-- ----------------------------

-- ----------------------------
-- Table structure for model_user
-- ----------------------------
DROP TABLE IF EXISTS `model_user`;
CREATE TABLE `model_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` bigint(20) DEFAULT NULL COMMENT '模型ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '人员ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '人员名称',
  `user_type` tinyint(1) DEFAULT NULL COMMENT '用户类型[1:测试人员;0:评估人员]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='模型人员';

-- ----------------------------
-- Records of model_user
-- ----------------------------
INSERT INTO `model_user` VALUES ('1', '63', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('2', '63', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('3', '63', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('4', '63', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('5', '63', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('6', '63', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('7', '65', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('12', '67', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('13', '67', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('15', '67', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('16', '72', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('17', '72', '11', 'aaaa', '0');
INSERT INTO `model_user` VALUES ('18', '73', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('19', '73', '11', 'aaaa', '0');
INSERT INTO `model_user` VALUES ('20', '74', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('21', '74', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('22', '75', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('24', '76', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('25', '76', '11', 'aaaa', '0');
INSERT INTO `model_user` VALUES ('26', '77', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('27', '77', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('30', '88', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('31', '88', '11', 'aaaa', '0');
INSERT INTO `model_user` VALUES ('32', '89', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('33', '91', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('34', '91', '11', 'aaaa', '0');
INSERT INTO `model_user` VALUES ('35', '92', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('36', '92', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('37', '93', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('38', '98', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('39', '98', '11', 'aaaa', '0');
INSERT INTO `model_user` VALUES ('40', '101', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('41', '101', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('42', '102', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('43', '102', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('44', '103', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('45', '103', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('47', '106', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('48', '107', '12', 'abcdef', '1');
INSERT INTO `model_user` VALUES ('49', '107', '11', 'aaaa', '1');
INSERT INTO `model_user` VALUES ('50', '107', '12', 'abcdef', '0');
INSERT INTO `model_user` VALUES ('51', '108', '13', '测试', '1');
INSERT INTO `model_user` VALUES ('52', '108', '15', '评估人员', '0');
INSERT INTO `model_user` VALUES ('53', '111', '14', '测试人员', '1');
INSERT INTO `model_user` VALUES ('54', '111', '15', '评估人员', '0');
INSERT INTO `model_user` VALUES ('55', '112', '14', '测试人员', '1');
INSERT INTO `model_user` VALUES ('56', '112', '15', '评估人员', '0');
INSERT INTO `model_user` VALUES ('57', '113', '14', '测试人员', '1');
INSERT INTO `model_user` VALUES ('58', '113', '15', '评估人员', '0');
INSERT INTO `model_user` VALUES ('59', '114', '14', '测试人员', '1');
INSERT INTO `model_user` VALUES ('60', '114', '15', '评估人员', '0');
INSERT INTO `model_user` VALUES ('61', '115', '14', '测试人员', '1');

-- ----------------------------
-- Table structure for node_info
-- ----------------------------
DROP TABLE IF EXISTS `node_info`;
CREATE TABLE `node_info` (
  `id` bigint(20) NOT NULL,
  `host_name` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `cpu_count` bigint(20) DEFAULT NULL,
  `thread_count` bigint(20) DEFAULT NULL,
  `cpu_frequency` varchar(255) DEFAULT NULL,
  `memory_size` varchar(255) DEFAULT NULL,
  `disk_size` varchar(255) DEFAULT NULL,
  `deployed_services` varchar(255) DEFAULT NULL,
  `running_services` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of node_info
-- ----------------------------
INSERT INTO `node_info` VALUES ('1', 'wb', '10.10.255.255', '4', '5', '60', '80', '70', '100', '100');
INSERT INTO `node_info` VALUES ('2', 'wb', '12.12.12.12', '6', '7', '88', '66', '77', '28', '55');
INSERT INTO `node_info` VALUES ('3', '2', '2', '2', '2', '2', '2', '2', '2', '3');
INSERT INTO `node_info` VALUES ('4', '4', '4', '4', '4', '4', '4', '4', '4', '4');
INSERT INTO `node_info` VALUES ('5', '5', '5', '5', '5', '5', '5', '5', '5', '5');
INSERT INTO `node_info` VALUES ('6', '6', '6', '6', '6', '6', '6', '6', '6', '6');
INSERT INTO `node_info` VALUES ('7', '7', '7', '7', '7', '7', '7', '7', '7', '7');
INSERT INTO `node_info` VALUES ('8', '8', '8', '8', '8', '8', '8', '8', '8', '8');
INSERT INTO `node_info` VALUES ('9', '9', '9', '9', '9', '9', '9', '9', '9', '9');
INSERT INTO `node_info` VALUES ('11', '11', '1', '11', '1', '1', '1', '1', '1', '1');
INSERT INTO `node_info` VALUES ('12', '5', '8', '5', '8', '8', '8', '8', '8', '8');

-- ----------------------------
-- Table structure for parameter_info
-- ----------------------------
DROP TABLE IF EXISTS `parameter_info`;
CREATE TABLE `parameter_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parameter_code` varchar(50) DEFAULT NULL COMMENT '参数编号',
  `model_code` varchar(50) DEFAULT NULL COMMENT '模型编号',
  `model_id` bigint(20) DEFAULT NULL COMMENT '模型ID',
  `name` varchar(50) DEFAULT NULL COMMENT '参数名称',
  `developer_id` bigint(20) DEFAULT NULL COMMENT '参数编写人ID',
  `contact_information` varchar(40) DEFAULT NULL COMMENT '联系方式',
  `developer` bigint(20) DEFAULT NULL COMMENT '参数上传者',
  `write_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '参数编写时间',
  `department` varchar(40) DEFAULT NULL COMMENT '参数上传单位',
  `contact_information_isvisible` bit(1) DEFAULT b'0' COMMENT '联系方式下载者可见【0:不可见;1:可见】',
  `parameter_permissions` bit(1) DEFAULT NULL COMMENT '参数开放权限[0:A级（所有用户都可见）;1:B级（注册用户均可下载）;2:C级（注册用户可预览，需要申请下载权限）;3:D级（仅管理员可以下载）]',
  `parameter_label` varchar(200) DEFAULT NULL COMMENT '标签',
  `pv` int(11) DEFAULT NULL COMMENT '阅读量',
  `download_count` int(11) DEFAULT '0' COMMENT '下载量',
  `description_file_id` bigint(20) DEFAULT NULL COMMENT '参数描述文件',
  `parameter_status` varchar(30) DEFAULT NULL COMMENT '参数状态【0：草稿;1:待审核;2:通过;3:驳回】',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '更新人',
  `update_user_id` varchar(20) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数基本信息';

-- ----------------------------
-- Records of parameter_info
-- ----------------------------

-- ----------------------------
-- Table structure for restful_bean
-- ----------------------------
DROP TABLE IF EXISTS `restful_bean`;
CREATE TABLE `restful_bean` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `node` varchar(255) DEFAULT NULL,
  `service_id` varchar(255) DEFAULT NULL,
  `instance_count` varchar(255) DEFAULT NULL,
  `min_instance_count` varchar(255) DEFAULT NULL,
  `max_instance_count` varchar(255) DEFAULT NULL,
  `deployed_on_nodes` varchar(255) DEFAULT NULL,
  `running_on_nodes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of restful_bean
-- ----------------------------
INSERT INTO `restful_bean` VALUES ('1', 'wb', '1', '1', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for service_category
-- ----------------------------
DROP TABLE IF EXISTS `service_category`;
CREATE TABLE `service_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID[第一级父id统一为0]',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(40) DEFAULT NULL COMMENT '创建人',
  `create_userid` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(40) DEFAULT NULL COMMENT '更新人',
  `update_userid` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='服务分类';

-- ----------------------------
-- Records of service_category
-- ----------------------------
INSERT INTO `service_category` VALUES ('6', 'weqeq', null, '2019-01-08 00:23:31', null, null, '2019-01-08 00:23:31', null, null);
INSERT INTO `service_category` VALUES ('7', 'llok', null, '2019-01-08 00:25:34', null, null, '2019-01-08 00:25:34', null, null);
INSERT INTO `service_category` VALUES ('8', 'qweq', null, '2019-01-08 00:36:26', null, null, '2019-01-08 00:36:26', null, null);
INSERT INTO `service_category` VALUES ('9', 'eqewq', null, '2019-01-08 00:36:31', null, null, '2019-01-08 00:36:31', null, null);
INSERT INTO `service_category` VALUES ('10', '服务', null, '2019-01-08 12:05:45', null, null, '2019-01-08 12:05:45', null, null);
INSERT INTO `service_category` VALUES ('11', '12', null, '2019-01-08 22:41:26', null, null, '2019-01-08 22:41:26', null, null);
INSERT INTO `service_category` VALUES ('12', '123', null, '2019-01-08 23:37:41', null, null, '2019-01-08 23:37:41', null, null);
INSERT INTO `service_category` VALUES ('13', '123444', null, '2019-01-09 00:35:51', null, null, '2019-01-09 00:35:51', null, null);
INSERT INTO `service_category` VALUES ('14', '123', null, '2019-01-14 22:01:33', null, null, '2019-01-14 22:01:33', null, null);
INSERT INTO `service_category` VALUES ('15', '122', null, '2019-03-04 11:34:20', null, null, '2019-03-04 11:34:20', null, null);
INSERT INTO `service_category` VALUES ('16', '1111', null, '2019-03-04 11:34:23', null, null, '2019-03-04 11:34:23', null, null);
INSERT INTO `service_category` VALUES ('17', '2222', null, '2019-03-04 11:34:26', null, null, '2019-03-04 11:34:26', null, null);
INSERT INTO `service_category` VALUES ('18', '3333', null, '2019-03-04 11:34:28', null, null, '2019-03-04 11:34:28', null, null);
INSERT INTO `service_category` VALUES ('19', '444', null, '2019-03-04 11:34:32', null, null, '2019-03-04 11:34:32', null, null);
INSERT INTO `service_category` VALUES ('20', '5555', null, '2019-03-04 11:34:41', null, null, '2019-03-04 11:34:41', null, null);
INSERT INTO `service_category` VALUES ('21', '6666', null, '2019-03-04 11:34:46', null, null, '2019-03-04 11:34:46', null, null);
INSERT INTO `service_category` VALUES ('22', '6666', null, '2019-03-04 11:34:51', null, null, '2019-03-04 11:34:51', null, null);
INSERT INTO `service_category` VALUES ('23', '2222', null, '2019-03-04 11:42:09', null, null, '2019-03-04 11:42:09', null, null);
INSERT INTO `service_category` VALUES ('24', '5555', null, '2019-03-04 11:42:14', null, null, '2019-03-04 11:42:14', null, null);
INSERT INTO `service_category` VALUES ('25', '6666', null, '2019-03-04 11:42:18', null, null, '2019-03-04 11:42:18', null, null);
INSERT INTO `service_category` VALUES ('26', '111', null, '2019-09-02 22:00:25', null, null, '2019-09-02 22:00:25', null, null);
INSERT INTO `service_category` VALUES ('27', '222', null, '2019-09-02 22:00:28', null, null, '2019-09-02 22:00:28', null, null);

-- ----------------------------
-- Table structure for service_desploy
-- ----------------------------
DROP TABLE IF EXISTS `service_desploy`;
CREATE TABLE `service_desploy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `service_id` bigint(20) DEFAULT NULL COMMENT '服务ID',
  `node_id` varchar(40) DEFAULT NULL COMMENT '节点ID',
  `instance_count` int(11) DEFAULT NULL COMMENT '实例数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(40) DEFAULT NULL COMMENT '创建人',
  `create_userid` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `update_user` varchar(40) DEFAULT NULL COMMENT '更新人',
  `update_userid` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务部署';

-- ----------------------------
-- Records of service_desploy
-- ----------------------------

-- ----------------------------
-- Table structure for service_info
-- ----------------------------
DROP TABLE IF EXISTS `service_info`;
CREATE TABLE `service_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) DEFAULT NULL COMMENT '服务分类ID',
  `name` varchar(50) DEFAULT NULL COMMENT '服务名称',
  `service_label` varchar(200) DEFAULT NULL COMMENT '标签【中间用,隔开】',
  `designer` varchar(40) DEFAULT NULL COMMENT '设计人员名称',
  `designer_id` bigint(20) DEFAULT NULL COMMENT '设计人员ID',
  `designer_department` varchar(40) DEFAULT NULL COMMENT '设计人员单位',
  `contact_information` varchar(40) DEFAULT NULL COMMENT '联系方式',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `interface_description_file_id` bigint(20) DEFAULT NULL COMMENT '接口描述文件id',
  `developer` varchar(40) DEFAULT NULL COMMENT '开发人员',
  `developer_id` bigint(20) DEFAULT NULL COMMENT '开发人员ID',
  `designer_status` varchar(1) DEFAULT NULL COMMENT '设计状态0:草稿;1:提交',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `design_submission_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '设计提交时间',
  `design_submission_user` varchar(40) DEFAULT NULL COMMENT '设计提交人',
  `design_submission_user_id` bigint(20) DEFAULT NULL COMMENT '设计提交人ID',
  `service_implementation_file_id` bigint(20) DEFAULT NULL COMMENT '服务实现ID',
  `user_manual_file_id` bigint(20) DEFAULT NULL COMMENT '用户文档ID',
  `demo_file_id` bigint(20) DEFAULT NULL COMMENT '使用案例文档ID',
  `min_instance` int(11) DEFAULT NULL COMMENT '最小实例数',
  `max_instance` int(11) DEFAULT NULL COMMENT '最大实例数',
  `developer_submission_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开发提交时间',
  `developer_submission_user` varchar(40) DEFAULT NULL COMMENT '开发提交人',
  `developer_submission_user_id` bigint(20) DEFAULT NULL COMMENT '开发提交人ID',
  `developer_status` bit(1) DEFAULT NULL COMMENT '开发状态 0:草稿;1:提交',
  `deploy_submission_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '部署提交时间',
  `deploy_submission_user` varchar(40) DEFAULT NULL COMMENT '部署提交人',
  `deploy_submission_user_id` bigint(20) DEFAULT NULL COMMENT '部署提交人ID',
  `deploy_status` bit(1) DEFAULT NULL COMMENT '部署状态 0:草稿;1:提交',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='服务基本信息';

-- ----------------------------
-- Records of service_info
-- ----------------------------
INSERT INTO `service_info` VALUES ('53', '0', '21', '', '21', '0', '12', '12', '12', '1', '选项2', '0', '1', '2019-03-04 11:43:21', '2019-03-04 11:43:21', null, '0', '0', '0', '0', '0', '0', '2019-03-04 11:43:21', null, '0', '\0', '2019-03-04 11:43:21', null, '0', '\0');
INSERT INTO `service_info` VALUES ('54', '0', '2222222', '', '222', '0', '222222', '22', '222', '222', '选项2', '0', '1', '2019-03-04 13:10:46', '2019-03-04 13:10:46', null, '0', '0', '0', '0', '0', '0', '2019-03-04 13:10:46', null, '0', '\0', '2019-03-04 13:10:46', null, '0', '\0');
INSERT INTO `service_info` VALUES ('55', '0', '2222', '3333', '444', '0', '444', '555', '555', '66', '选项1', '0', '1', '2019-03-04 13:32:18', '2019-03-04 13:32:18', null, '0', '0', '0', '0', '0', '0', '2019-03-04 13:32:18', null, '0', '\0', '2019-03-04 13:32:18', null, '0', '\0');
INSERT INTO `service_info` VALUES ('56', '0', '111', '331', '223', '0', '13213', '131', '31231', '31231', '选项1', '0', '1', '2019-09-02 21:56:28', '2019-09-02 21:56:28', null, '0', '0', '0', '0', '0', '0', '2019-09-02 21:56:28', null, '0', '\0', '2019-09-02 21:56:28', null, '0', '\0');
INSERT INTO `service_info` VALUES ('57', '0', '', '', '', '0', '', '', '', '0', '', '0', '0', '2019-09-02 23:08:28', '2019-09-02 23:08:28', null, '0', '0', '0', '0', '0', '0', '2019-09-02 23:08:28', null, '0', '\0', '2019-09-02 23:08:28', null, '0', '\0');
INSERT INTO `service_info` VALUES ('58', '0', '222', '1321', '33', '0', '333', '33', '333', '33', '选项1', '0', '1', '2019-09-03 10:36:44', '2019-09-03 10:36:44', null, '0', '0', '0', '0', '0', '0', '2019-09-03 10:36:44', null, '0', '\0', '2019-09-03 10:36:44', null, '0', '\0');
INSERT INTO `service_info` VALUES ('60', '0', '1231231', '31321', '313', '0', '131', '31231', '312313', '12313', '选项1', '0', '1', '2019-09-03 10:44:24', '2019-09-03 10:44:24', null, '0', '0', '0', '0', '0', '0', '2019-09-03 10:44:24', null, '0', '\0', '2019-09-03 10:44:24', null, '0', '\0');

-- ----------------------------
-- Table structure for stats_bean
-- ----------------------------
DROP TABLE IF EXISTS `stats_bean`;
CREATE TABLE `stats_bean` (
  `id` int(11) NOT NULL,
  `stats_time` varchar(255) DEFAULT NULL,
  `total_service_count` int(11) DEFAULT NULL,
  `deployed_service_count` int(11) DEFAULT NULL,
  `running_service_count` int(11) DEFAULT NULL,
  `running_service_instance_count` int(11) DEFAULT NULL,
  `host_count` int(11) DEFAULT NULL,
  `used_host_count` int(11) DEFAULT NULL,
  `total_call_count` int(11) DEFAULT NULL,
  `up_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stats_bean
-- ----------------------------
INSERT INTO `stats_bean` VALUES ('1', null, '100', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for system_attachment
-- ----------------------------
DROP TABLE IF EXISTS `system_attachment`;
CREATE TABLE `system_attachment` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(4) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ext` varchar(20) DEFAULT NULL,
  `link` varchar(100) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `old_name` varchar(50) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `size` bigint(4) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_attachment
-- ----------------------------
INSERT INTO `system_attachment` VALUES ('182', null, '2018-12-10 14:41:16', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210144116643.xml', '20181210144116643.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181210\\20181210144116643.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('183', null, '2018-12-10 14:41:44', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210144144906.xml', '20181210144144906.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181210\\20181210144144906.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('184', null, '2018-12-10 14:50:11', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210145011962.xml', '20181210145011962.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181210\\20181210145011962.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('185', null, '2018-12-10 15:05:35', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210150535588.xml', '20181210150535588.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181210\\20181210150535588.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('186', null, '2018-12-10 15:20:11', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210152011599.xml', '20181210152011599.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181210\\20181210152011599.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('187', null, '2018-12-10 15:20:18', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210152018594.xml', '20181210152018594.xml', '装备模型描述文件模板(1).xml', 'e:\\work-data\\attachment\\20181210\\20181210152018594.xml', '2704', null);
INSERT INTO `system_attachment` VALUES ('188', null, '2018-12-10 15:20:28', 'png', 'http://localhost:8081/upload/attachment/20181210/2018121015202872.png', '2018121015202872.png', 'upload-background.png', 'e:\\work-data\\attachment\\20181210\\2018121015202872.png', '65114', null);
INSERT INTO `system_attachment` VALUES ('189', null, '2018-12-10 15:21:37', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210152137667.xml', '20181210152137667.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181210\\20181210152137667.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('190', null, '2018-12-10 15:22:06', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210152206755.xml', '20181210152206755.xml', '装备模型描述文件模板(1).xml', 'e:\\work-data\\attachment\\20181210\\20181210152206755.xml', '2704', null);
INSERT INTO `system_attachment` VALUES ('191', null, '2018-12-10 15:22:14', 'xml', 'http://localhost:8081/upload/attachment/20181210/2018121015221479.xml', '2018121015221479.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181210\\2018121015221479.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('192', null, '2018-12-10 15:22:19', 'xml', 'http://localhost:8081/upload/attachment/20181210/20181210152219453.xml', '20181210152219453.xml', '装备模型描述文件模板(1).xml', 'e:\\work-data\\attachment\\20181210\\20181210152219453.xml', '2704', null);
INSERT INTO `system_attachment` VALUES ('193', null, '2018-12-11 14:40:34', 'xml', 'http://localhost:8081/upload/attachment/20181211/20181211144034876.xml', '20181211144034876.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181211\\20181211144034876.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('194', null, '2018-12-11 14:41:30', 'xml', 'http://localhost:8081/upload/attachment/20181211/20181211144130188.xml', '20181211144130188.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181211\\20181211144130188.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('195', null, '2018-12-11 14:42:25', 'xml', 'http://localhost:8081/upload/attachment/20181211/20181211144225147.xml', '20181211144225147.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181211\\20181211144225147.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('196', null, '2018-12-11 14:42:31', 'xml', 'http://localhost:8081/upload/attachment/20181211/20181211144231615.xml', '20181211144231615.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181211\\20181211144231615.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('197', null, '2018-12-11 17:33:40', 'xml', 'http://localhost:8081/upload/attachment/20181211/20181211173340811.xml', '20181211173340811.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181211\\20181211173340811.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('198', null, '2018-12-12 10:05:57', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212100557618.xml', '20181212100557618.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181212\\20181212100557618.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('199', null, '2018-12-12 10:33:16', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212103316798.xml', '20181212103316798.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181212\\20181212103316798.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('200', null, '2018-12-12 14:50:00', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212145000356.xml', '20181212145000356.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212145000356.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('201', null, '2018-12-12 14:50:05', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212145005571.xml', '20181212145005571.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212145005571.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('202', null, '2018-12-12 14:50:24', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212145024252.xml', '20181212145024252.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212145024252.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('203', null, '2018-12-12 14:50:33', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212145033652.xml', '20181212145033652.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212145033652.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('204', null, '2018-12-12 14:50:54', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212145054454.xml', '20181212145054454.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212145054454.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('205', null, '2018-12-12 15:07:51', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212150751183.xml', '20181212150751183.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212150751183.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('206', null, '2018-12-12 15:08:00', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212150800305.xml', '20181212150800305.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212150800305.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('207', null, '2018-12-12 15:08:07', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212150807519.xml', '20181212150807519.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212150807519.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('208', null, '2018-12-12 15:08:11', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212150811624.xml', '20181212150811624.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212150811624.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('209', null, '2018-12-12 15:17:47', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212151747146.xml', '20181212151747146.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212151747146.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('210', null, '2018-12-12 15:17:51', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212151751308.xml', '20181212151751308.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212151751308.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('211', null, '2018-12-12 15:18:02', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212151802132.xml', '20181212151802132.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212151802132.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('212', null, '2018-12-12 15:21:40', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212152140950.xml', '20181212152140950.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212152140950.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('213', null, '2018-12-12 15:21:45', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212152145151.xml', '20181212152145151.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212152145151.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('214', null, '2018-12-12 15:21:54', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212152154986.xml', '20181212152154986.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212152154986.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('215', null, '2018-12-12 15:23:52', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212152352682.xml', '20181212152352682.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212152352682.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('216', null, '2018-12-12 15:24:02', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212152402245.xml', '20181212152402245.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212152402245.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('217', null, '2018-12-12 15:24:06', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212152406502.xml', '20181212152406502.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212152406502.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('218', null, '2018-12-12 15:24:11', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212152411658.xml', '20181212152411658.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181212\\20181212152411658.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('219', null, '2018-12-12 17:19:43', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212171943840.xml', '20181212171943840.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181212\\20181212171943840.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('220', null, '2018-12-12 17:19:53', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212171953371.xml', '20181212171953371.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181212\\20181212171953371.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('221', null, '2018-12-12 17:19:59', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212171959438.xml', '20181212171959438.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181212\\20181212171959438.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('222', null, '2018-12-12 17:20:07', 'xml', 'http://localhost:8081/upload/attachment/20181212/20181212172007186.xml', '20181212172007186.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181212\\20181212172007186.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('223', null, '2018-12-13 17:28:41', 'xml', 'http://localhost:8081/upload/attachment/20181213/2018121317284192.xml', '2018121317284192.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181213\\2018121317284192.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('224', null, '2018-12-14 13:50:34', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214135034589.xml', '20181214135034589.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181214\\20181214135034589.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('225', null, '2018-12-14 13:51:53', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214135153643.xml', '20181214135153643.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181214\\20181214135153643.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('226', null, '2018-12-14 13:52:02', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214135202435.xml', '20181214135202435.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181214\\20181214135202435.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('227', null, '2018-12-14 13:52:08', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214135208327.xml', '20181214135208327.xml', '装备模型描述文件模板(2).xml', 'e:\\work-data\\attachment\\20181214\\20181214135208327.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('228', null, '2018-12-14 15:22:52', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214152252359.xml', '20181214152252359.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181214\\20181214152252359.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('229', null, '2018-12-14 15:22:57', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214152257904.xml', '20181214152257904.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181214\\20181214152257904.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('230', null, '2018-12-14 15:23:19', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214152319237.xml', '20181214152319237.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181214\\20181214152319237.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('231', null, '2018-12-14 15:23:23', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214152323421.xml', '20181214152323421.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181214\\20181214152323421.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('232', null, '2018-12-14 15:23:31', 'xml', 'http://localhost:8081/upload/attachment/20181214/20181214152331338.xml', '20181214152331338.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181214\\20181214152331338.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('233', null, '2018-12-18 09:55:52', 'xml', 'http://localhost:8081/upload/attachment/20181218/2018121809555240.xml', '2018121809555240.xml', '装备模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181218\\2018121809555240.xml', '2632', null);
INSERT INTO `system_attachment` VALUES ('234', null, '2018-12-18 09:56:30', 'xml', 'http://localhost:8081/upload/attachment/20181218/20181218095630714.xml', '20181218095630714.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181218\\20181218095630714.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('235', null, '2018-12-18 09:57:13', 'xml', 'http://localhost:8081/upload/attachment/20181218/20181218095713242.xml', '20181218095713242.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181218\\20181218095713242.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('236', null, '2018-12-28 09:41:11', 'xml', 'http://localhost:8081/upload/attachment/20181228/20181228094111856.xml', '20181228094111856.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181228\\20181228094111856.xml', '2655', null);
INSERT INTO `system_attachment` VALUES ('237', null, '2018-12-28 09:42:26', 'xml', 'http://localhost:8081/upload/attachment/20181228/20181228094226539.xml', '20181228094226539.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181228\\20181228094226539.xml', '2655', null);
INSERT INTO `system_attachment` VALUES ('238', null, '2018-12-28 09:42:54', 'xml', 'http://localhost:8081/upload/attachment/20181228/20181228094254589.xml', '20181228094254589.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181228\\20181228094254589.xml', '2655', null);
INSERT INTO `system_attachment` VALUES ('239', null, '2018-12-28 09:42:59', 'xml', 'http://localhost:8081/upload/attachment/20181228/20181228094259789.xml', '20181228094259789.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181228\\20181228094259789.xml', '2655', null);
INSERT INTO `system_attachment` VALUES ('240', null, '2018-12-28 11:12:08', 'xml', 'http://localhost:8081/upload/attachment/20181228/20181228111208646.xml', '20181228111208646.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181228\\20181228111208646.xml', '2655', null);
INSERT INTO `system_attachment` VALUES ('241', null, '2018-12-28 11:14:24', 'xml', 'http://localhost:8081/upload/attachment/20181228/20181228111424111.xml', '20181228111424111.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181228\\20181228111424111.xml', '2655', null);
INSERT INTO `system_attachment` VALUES ('242', null, '2018-12-28 11:14:30', 'xml', 'http://localhost:8081/upload/attachment/20181228/20181228111430508.xml', '20181228111430508.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181228\\20181228111430508.xml', '2655', null);
INSERT INTO `system_attachment` VALUES ('243', null, '2018-12-28 11:14:33', 'xml', 'http://localhost:8081/upload/attachment/20181228/20181228111433942.xml', '20181228111433942.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20181228\\20181228111433942.xml', '2655', null);
INSERT INTO `system_attachment` VALUES ('244', null, '2019-01-04 14:53:11', 'xml', 'http://localhost:8081/upload/attachment/20190104/20190104145302483.xml', '20190104145302483.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20190104\\20190104145302483.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('245', null, '2019-01-04 14:53:58', 'xml', 'http://localhost:8081/upload/attachment/20190104/20190104145358972.xml', '20190104145358972.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20190104\\20190104145358972.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('246', null, '2019-01-04 15:03:48', 'xml', 'http://localhost:8081/upload/attachment/20190104/20190104150348807.xml', '20190104150348807.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20190104\\20190104150348807.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('247', null, '2019-01-06 15:52:01', 'xml', 'http://localhost:8080/upload/attachment/20190106/20190106155152551.xml', '20190106155152551.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20190106\\20190106155152551.xml', '2289', null);
INSERT INTO `system_attachment` VALUES ('248', null, '2019-01-06 15:52:21', 'xml', 'http://localhost:8080/upload/attachment/20190106/20190106155221646.xml', '20190106155221646.xml', '作战模型描述文件模板.xml', 'e:\\work-data\\attachment\\20190106\\20190106155221646.xml', '2289', null);

-- ----------------------------
-- Table structure for system_index
-- ----------------------------
DROP TABLE IF EXISTS `system_index`;
CREATE TABLE `system_index` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) DEFAULT NULL,
  `index` varchar(50) DEFAULT NULL,
  `role_id` bigint(4) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_index
-- ----------------------------
INSERT INTO `system_index` VALUES ('1', '2018-11-13 16:02:20', '用户列表', null, '1', '/user-list');
INSERT INTO `system_index` VALUES ('2', '2018-11-13 16:04:40', '管理员首页', null, '2', '/model-user-manage');
INSERT INTO `system_index` VALUES ('3', '2018-11-14 11:23:43', '模型查询', null, '3', '/model-custom-search');

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', 'dashboard', '2018-12-10 17:00:40', '首页', '0', 'el-icon-lx-home', '1');
INSERT INTO `system_menu` VALUES ('2', 'table', '2018-11-06 15:11:41', '基础表格', '0', 'el-icon-lx-cascades', '2');
INSERT INTO `system_menu` VALUES ('3', 'tabs', '2018-11-06 15:12:15', 'tab选项卡', '0', 'el-icon-lx-copy', '3');
INSERT INTO `system_menu` VALUES ('4', 'system-setting', '2018-12-10 17:00:45', '系统设置', '0', 'el-icon-setting', '2');
INSERT INTO `system_menu` VALUES ('5', 'user-list', '2018-11-19 12:06:45', '用户列表', '4', 'el-icon-lx-people', '3');
INSERT INTO `system_menu` VALUES ('6', 'role-list', '2018-11-19 12:11:21', '角色列表', '4', 'el-icon-lx-group', '3');
INSERT INTO `system_menu` VALUES ('7', 'menu-manager', '2018-12-10 17:01:52', '菜单管理', '4', 'el-icon-menu', '3');
INSERT INTO `system_menu` VALUES ('8', 'permission-manager', '2018-11-19 13:09:47', '权限管理', '4', 'el-icon-sm-authority', '3');
INSERT INTO `system_menu` VALUES ('9', 'about-form', '2018-11-06 15:13:17', '表单相关', '0', 'el-icon-lx-calendar', '2');
INSERT INTO `system_menu` VALUES ('10', 'form', '2018-11-06 15:13:43', '基本表单', '9', '', '3');
INSERT INTO `system_menu` VALUES ('11', '3-2', '2018-11-06 15:14:06', '三级表单', '9', '', '3');
INSERT INTO `system_menu` VALUES ('12', 'editor', '2018-11-06 15:14:26', '富文本编辑器', '11', '', '4');
INSERT INTO `system_menu` VALUES ('13', 'markdown', '2018-11-06 15:14:41', 'markdown编辑器', '11', '', '4');
INSERT INTO `system_menu` VALUES ('14', 'upload', '2018-11-26 15:16:44', '文件上传', '9', '', '3');
INSERT INTO `system_menu` VALUES ('15', 'icon', '2018-11-06 15:15:43', '自定义图标', '0', 'el-icon-lx-emoji', '2');
INSERT INTO `system_menu` VALUES ('16', 'charts', '2018-11-06 15:16:19', 'scharts图表', '0', 'el-icon-lx-favor', '2');
INSERT INTO `system_menu` VALUES ('17', 'drag', '2018-11-06 15:16:45', '拖拽列表', '0', 'el-icon-rank', '2');
INSERT INTO `system_menu` VALUES ('18', '6', '2018-11-06 15:17:15', '错误处理', '0', 'el-icon-lx-warn', '2');
INSERT INTO `system_menu` VALUES ('19', 'permission', '2018-11-06 15:17:45', '权限测试', '18', '', '3');
INSERT INTO `system_menu` VALUES ('20', '404', '2018-11-06 15:18:01', '404页面', '18', '', '3');
INSERT INTO `system_menu` VALUES ('21', 'model-manager', '2018-11-14 17:30:53', '模型管理', '0', 'el-icon-lx-present', '2');
INSERT INTO `system_menu` VALUES ('22', 'model-custom-search', '2018-11-15 13:21:07', '模型查询', '21', 'el-icon-lx-searchlist', '3');
INSERT INTO `system_menu` VALUES ('23', 'model-upload', '2018-11-23 11:26:39', '模型上传', '21', 'el-icon-lx-upload', '3');
INSERT INTO `system_menu` VALUES ('29', 'manage', '2018-12-10 17:02:01', '管理', '0', 'el-icon-menu', '2');
INSERT INTO `system_menu` VALUES ('30', 'setting', '2018-12-10 17:01:07', '设置', '0', 'el-icon-setting', '2');
INSERT INTO `system_menu` VALUES ('25', 'model-self-resource-upload', '2018-12-13 16:04:34', '我的资源', '21', 'el-icon-lx-notice', '3');
INSERT INTO `system_menu` VALUES ('26', 'test-model', '2018-11-19 14:54:00', '测试模块', '0', 'el-icon-t', '2');
INSERT INTO `system_menu` VALUES ('27', 'test-model-upload', '2018-11-19 14:54:34', '上传测试', '26', 'el-icon-upload', '3');
INSERT INTO `system_menu` VALUES ('31', 'model-user-manage', '2018-11-30 09:41:24', '用户管理', '29', '', '3');
INSERT INTO `system_menu` VALUES ('32', 'model-manage', '2018-11-27 10:08:07', '模型管理', '29', '', '3');
INSERT INTO `system_menu` VALUES ('33', 'parameter-manage', '2018-11-27 10:08:24', '参数管理', '29', '', '3');
INSERT INTO `system_menu` VALUES ('34', 'system-config', '2018-11-27 10:12:44', '系统设置', '30', '', '3');
INSERT INTO `system_menu` VALUES ('35', 'user-config', '2018-11-27 10:13:10', '人员设置', '30', '', '3');
INSERT INTO `system_menu` VALUES ('36', 'permission-config', '2018-11-27 10:13:30', '权限设置', '30', '', '3');
INSERT INTO `system_menu` VALUES ('37', '/category-manage', '2018-12-13 16:05:00', '分类管理', '30', '', '3');

-- ----------------------------
-- Table structure for system_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `system_menu_role`;
CREATE TABLE `system_menu_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK6fbbfiu8njr0yj2fip0drvfvt` (`menu_id`),
  KEY `FKi5bt12i0pjyfi95rfv8p6fet1` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_menu_role
-- ----------------------------
INSERT INTO `system_menu_role` VALUES ('1', '2', '1', '2018-11-05 15:34:55');
INSERT INTO `system_menu_role` VALUES ('2', '1', '1', '2018-11-05 15:34:55');
INSERT INTO `system_menu_role` VALUES ('4', '4', '1', '2018-11-05 15:34:55');
INSERT INTO `system_menu_role` VALUES ('5', '6', '1', '2018-11-05 15:34:55');
INSERT INTO `system_menu_role` VALUES ('7', '7', '1', '2018-11-05 16:41:50');
INSERT INTO `system_menu_role` VALUES ('8', '5', '1', '2018-11-05 16:41:50');
INSERT INTO `system_menu_role` VALUES ('9', '8', '1', '2018-11-05 16:41:50');
INSERT INTO `system_menu_role` VALUES ('10', '8', '1', '2018-11-05 16:43:20');
INSERT INTO `system_menu_role` VALUES ('11', '5', '1', '2018-11-05 16:43:20');
INSERT INTO `system_menu_role` VALUES ('12', '7', '1', '2018-11-05 16:43:20');
INSERT INTO `system_menu_role` VALUES ('59', '33', '2', '2018-11-27 10:17:10');
INSERT INTO `system_menu_role` VALUES ('60', '35', '2', '2018-11-27 10:17:10');
INSERT INTO `system_menu_role` VALUES ('58', '36', '2', '2018-11-27 10:17:10');
INSERT INTO `system_menu_role` VALUES ('18', '3', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('19', '9', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('20', '13', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('21', '10', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('22', '12', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('23', '15', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('24', '16', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('25', '20', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('26', '19', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('27', '18', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('28', '14', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('29', '11', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('30', '17', '1', '2018-11-06 15:45:44');
INSERT INTO `system_menu_role` VALUES ('42', '23', '1', '2018-11-14 15:13:40');
INSERT INTO `system_menu_role` VALUES ('43', '22', '1', '2018-11-14 15:13:40');
INSERT INTO `system_menu_role` VALUES ('41', '21', '1', '2018-11-14 15:13:40');
INSERT INTO `system_menu_role` VALUES ('39', '22', '3', '2018-11-14 13:39:42');
INSERT INTO `system_menu_role` VALUES ('40', '23', '3', '2018-11-14 13:39:42');
INSERT INTO `system_menu_role` VALUES ('62', '30', '2', '2018-11-27 10:17:56');
INSERT INTO `system_menu_role` VALUES ('64', '36', '2', '2018-11-27 10:17:56');
INSERT INTO `system_menu_role` VALUES ('63', '32', '2', '2018-11-27 10:17:56');
INSERT INTO `system_menu_role` VALUES ('48', '24', '3', '2018-11-15 10:45:24');
INSERT INTO `system_menu_role` VALUES ('49', '24', '1', '2018-11-15 13:43:06');
INSERT INTO `system_menu_role` VALUES ('50', '25', '1', '2018-11-15 13:43:06');
INSERT INTO `system_menu_role` VALUES ('61', '31', '2', '2018-11-27 10:17:56');
INSERT INTO `system_menu_role` VALUES ('52', '25', '3', '2018-11-15 13:43:25');
INSERT INTO `system_menu_role` VALUES ('53', '29', '2', '2018-11-27 10:17:10');
INSERT INTO `system_menu_role` VALUES ('54', '30', '2', '2018-11-27 10:17:10');
INSERT INTO `system_menu_role` VALUES ('55', '31', '2', '2018-11-27 10:17:10');
INSERT INTO `system_menu_role` VALUES ('56', '32', '2', '2018-11-27 10:17:10');
INSERT INTO `system_menu_role` VALUES ('57', '34', '2', '2018-11-27 10:17:10');
INSERT INTO `system_menu_role` VALUES ('65', '37', '2', '2018-12-13 16:05:16');

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pattern` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_permission
-- ----------------------------
INSERT INTO `system_permission` VALUES ('1', '/api/role**', '角色管理', 'ALL', '角色资源', '0', '2018-11-06 12:21:02', null);
INSERT INTO `system_permission` VALUES ('2', '/api/menu**', '菜单显示资源', 'ALL', '菜单资源', '0', '2018-11-06 12:21:15', null);
INSERT INTO `system_permission` VALUES ('3', '/api/permission**', '权限资源', 'ALL', '权限资源', '0', '2018-11-06 12:20:48', null);
INSERT INTO `system_permission` VALUES ('4', '/api/user**', '用户资源', 'ALL', '用户资源', '0', '2018-11-06 12:20:22', null);
INSERT INTO `system_permission` VALUES ('5', '/api/index**', '首页资源', 'ALL', '首页资源', '0', '2018-11-13 15:55:50', null);

-- ----------------------------
-- Table structure for system_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `system_permission_role`;
CREATE TABLE `system_permission_role` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(4) DEFAULT NULL,
  `permission_id` bigint(4) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKmoetrlqbjy17fsv7hrfo315e0` (`permission_id`),
  KEY `FKn2yup9v55hyy67kl0s5xlbrj9` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_permission_role
-- ----------------------------
INSERT INTO `system_permission_role` VALUES ('1', '1', '1', '2018-10-26 16:52:50');
INSERT INTO `system_permission_role` VALUES ('2', '1', '2', '2018-10-26 16:52:50');
INSERT INTO `system_permission_role` VALUES ('4', '2', '2', '2018-10-26 16:52:50');
INSERT INTO `system_permission_role` VALUES ('5', '2', '1', '2018-10-26 16:52:50');
INSERT INTO `system_permission_role` VALUES ('11', '1', '3', '2018-11-06 11:32:18');
INSERT INTO `system_permission_role` VALUES ('12', '2', '4', '2018-11-08 17:25:24');
INSERT INTO `system_permission_role` VALUES ('13', '2', '3', '2018-11-08 17:25:24');
INSERT INTO `system_permission_role` VALUES ('14', '1', '4', '2018-11-08 17:25:43');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `type` varchar(255) DEFAULT NULL,
  `layout` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', 'ROLE_ROOT', '2018-10-26 16:50:13', '超级管理员', '1', null, 'manage');
INSERT INTO `system_role` VALUES ('2', 'ROLE_ADMIN', '2018-10-26 16:50:13', '管理员', '1', null, 'manage');
INSERT INTO `system_role` VALUES ('3', 'ROLE_USER', '2018-10-26 16:50:13', 'user', '1', null, 'custom');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(100) NOT NULL DEFAULT '$2a$10$sTCvL8UAxl6nt38I9HoDN.fbzwOCAKLt5.u2tK7KWP/1DZRhOM31m',
  `username` varchar(50) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `department` varchar(255) DEFAULT NULL,
  `is_leader` bit(1) DEFAULT NULL COMMENT '0',
  `reason` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `contact_information` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT b'0',
  `is_evaluator` bit(1) DEFAULT b'0',
  `is_tester` bit(1) DEFAULT b'0',
  `evaluate_cambot` bit(1) DEFAULT b'0',
  `evaluate_environment` bit(1) DEFAULT b'0',
  `evaluate_equipment` bit(1) DEFAULT b'0',
  `evaluate_evaluation` bit(1) DEFAULT b'0',
  `evaluate_support` bit(1) DEFAULT b'0',
  `test_cambot` bit(1) DEFAULT b'0',
  `test_environment` bit(1) DEFAULT b'0',
  `test_equipment` bit(1) DEFAULT b'0',
  `test_evaluation` bit(1) DEFAULT b'0',
  `test_support` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_person_name` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', '2018-10-18 16:53:39', '$2a$10$5bG0fijstC7DGMqp4E5JB.v5EQoWB7dpdX6zBi6XJCQ6fuWYRUoo.', 'administrator', '2018-10-18 16:53:41', '1', null, null, null, '测试标签1', '内置管理员', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `system_user` VALUES ('3', '2018-10-18 16:54:01', '$2a$10$pKxqYR38/J6kgeByVATnguAX/tG.dCnDI/Zo/W0n2cHp6aC6Om9yK', 'root', '2018-10-18 16:54:03', '1', null, null, null, '测试标签2', '超级管理员', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `system_user` VALUES ('9', '2018-10-29 17:13:20', '$2a$10$f6F.sDuz8s9a5YJJk4wJpuolFh78krbi9R9oz4gwkWjTUbmq9Yp/e', 'user', '2018-10-29 17:13:18', '1', null, null, null, '测试标签3', '普通用户', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `system_user` VALUES ('7', '2018-10-29 16:39:00', '$2a$10$SmmB8YrQBEGmEsufHz82zuGOQlZzXoBoXWkn3ZXOFIX/XCuF2C3ya', 'admin', '2018-10-29 16:38:59', '1', null, null, null, null, '管理员', null, null, '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `system_user` VALUES ('18', '2018-12-28 10:29:59', '$2a$10$BEtJDuzxx4.XM2b7LEQ4LesHpOkfQ64fWcAxACsNcvINmjp/Awexi', 'test123', null, '1', '测试单位', '\0', '1', '测试标签3', '测试人员', null, '12321312', null, '\0', '', '\0', '\0', '\0', '\0', '\0', '', '', '', '', '');
INSERT INTO `system_user` VALUES ('19', '2018-12-28 11:09:54', '$2a$10$ahzFs/xhxZRuqj2ZQnUr3OUK4IGz9tXoCYZ7JURpbVzX8/neOY4u2', 'pinggu123', null, '1', '评估单位', '\0', '1', '测试标签1', '评估人员', null, '12312312', '\0', '', '\0', '', '', '', '', '', '\0', '\0', '\0', '\0', '\0');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role_id` bigint(4) DEFAULT NULL,
  `user_id` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnp61n3syn415rmbwvhnw87u3a` (`role_id`),
  KEY `FKkc6ik04bm9v9kldgbt3kkgfac` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('1', '2018-10-26 16:49:36', '2', '2');
INSERT INTO `system_user_role` VALUES ('20', '2018-11-07 17:07:38', '2', '7');
INSERT INTO `system_user_role` VALUES ('19', '2018-11-07 17:07:26', '3', '9');
INSERT INTO `system_user_role` VALUES ('17', '2018-11-07 17:07:01', '2', '1');
INSERT INTO `system_user_role` VALUES ('5', '2018-10-26 16:49:36', '1', '2');
INSERT INTO `system_user_role` VALUES ('18', '2018-11-07 17:07:15', '1', '3');
INSERT INTO `system_user_role` VALUES ('21', '2018-11-07 17:11:03', '0', '3');
INSERT INTO `system_user_role` VALUES ('22', '2018-12-11 17:41:19', '3', '13');
INSERT INTO `system_user_role` VALUES ('23', '2018-12-12 14:54:17', '3', '14');
INSERT INTO `system_user_role` VALUES ('24', '2018-12-12 14:56:34', '3', '15');
INSERT INTO `system_user_role` VALUES ('25', '2018-12-12 17:04:10', '2', '12');
INSERT INTO `system_user_role` VALUES ('26', '2018-12-28 10:23:48', '3', '16');
INSERT INTO `system_user_role` VALUES ('27', '2018-12-28 10:25:20', '3', '17');
INSERT INTO `system_user_role` VALUES ('28', '2018-12-28 10:30:00', '3', '18');
INSERT INTO `system_user_role` VALUES ('29', '2018-12-28 11:09:55', '3', '19');
