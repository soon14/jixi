/*
 Navicat Premium Data Transfer

 Source Server         : 本地-mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : pigxx_smartff

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 17/09/2020 20:34:05
*/
USE pigxx_smartff;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sf_education
-- ----------------------------
DROP TABLE IF EXISTS `sf_education`;
CREATE TABLE `sf_education`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题(课程名称)',
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容（文本）',
  `thumbnail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图id',
  `file_type` tinyint(1) NULL DEFAULT NULL COMMENT '文件类型 1-文件，2-图片，3-视频',
  `file_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hits` int(11) NULL DEFAULT 0 COMMENT '浏览次数',
  `issue_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教育培训表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_education
-- ----------------------------
INSERT INTO `sf_education` VALUES ('80e968aa2c417d7d932e8b7de0b8439e', '测试', '<p>测试</p>', 'lengleng-5793b08318f649f09f71f646962d6e0d.jpg', NULL, 'lengleng-VID20190926173106.mp4', 9, '2019-09-26 14:39:31', '2019-09-01 00:00:00', '2019-09-28 00:00:00');

-- ----------------------------
-- Table structure for sf_fire_maintenance
-- ----------------------------
DROP TABLE IF EXISTS `sf_fire_maintenance`;
CREATE TABLE `sf_fire_maintenance`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维保单编号',
  `company_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维保公司',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维保公司名称',
  `contact_way` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `contact_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `produce_cate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类别',
  `produce_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `factor_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂家ID',
  `brand_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌ID',
  `area` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域编码',
  `area_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `device_code` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `network_unit_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位id',
  `build_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑id',
  `build_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑名称',
  `county_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼层id',
  `county_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼层名称',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在位置',
  `fault_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '故障描述',
  `apply_org_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请机构ID',
  `apply_org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请机构名称',
  `handle_result` tinyint(2) NULL DEFAULT NULL COMMENT '处理结果',
  `handle_status` tinyint(2) NULL DEFAULT NULL COMMENT '处理状态',
  `alarm_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报警类型',
  `source` tinyint(2) NULL DEFAULT NULL COMMENT '维保来源',
  `alarm_time` datetime(0) NULL DEFAULT NULL COMMENT '报警时间',
  `images` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称(多个以逗号相分隔)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人id',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人姓名',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人id',
  `update_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消防维保表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_fire_maintenance
-- ----------------------------
INSERT INTO `sf_fire_maintenance` VALUES ('111', '1111', '11', '11', '11', '11', '1', '1', '1', '1', '1', '1', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '11', '1', '2019-12-03 16:33:21', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sf_fire_report
-- ----------------------------
DROP TABLE IF EXISTS `sf_fire_report`;
CREATE TABLE `sf_fire_report`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告id',
  `unitid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位id',
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `reporttype` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报告类型',
  `r_year` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年份',
  `monthorweek` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '月份/周数',
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `device_total` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消防设备总数',
  `device_intact_rate` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备完好率',
  `device_should` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应装设备',
  `device_electri_fire` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电器火灾设备',
  `device_alarm` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备报警',
  `misreport` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '误报',
  `processed` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已处理',
  `compared` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同比',
  `fire_drill` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消防演练',
  `fire_training` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消防培训',
  `video_learning` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频学习',
  `culture` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章宣传',
  `inspection_times` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检次数',
  `hidden_trouble_rate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存在隐患率',
  `maintenance_times` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备维保总次数',
  `repair_times` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修次数',
  `replace_times` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更换数量',
  `structure_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结构类型',
  `fire_pool` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消防水池',
  `fire_exit` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消防通道',
  `fire_rating` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消防安全评分',
  `assessment_level` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评估等级 ',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评估人',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '评估时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消防报告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_fire_report
-- ----------------------------
INSERT INTO `sf_fire_report` VALUES ('1', '66be114fe695993908827b6cbc68a97c', 'ef752e4cfb2881a84d7b1908cd0ea28b', '1', '2019', '1', NULL, '400', '70%', '700', '30', '30', '3', '20', '10%', '10', '5', '2', '2', '12', '20%', '30', '10', '20', '混凝土结构', '5', '10', '68', '合格', '1', '2019-09-18 13:19:49');
INSERT INTO `sf_fire_report` VALUES ('2', '66be114fe695993908827b6cbc68a97c', 'ef752e4cfb2881a84d7b1908cd0ea28b', '1', '2019', '2', NULL, '500', '60%', '800', '40', '40', '4', '30', '20%', '20', '10', '4', '4', '12', '10%', '40', '20', '30', '混凝土结构', '10', '20', '71', '合格', '1', '2019-09-18 13:19:49');
INSERT INTO `sf_fire_report` VALUES ('3', '66be114fe695993908827b6cbc68a97c', 'ef752e4cfb2881a84d7b1908cd0ea28b', '1', '2019', '3', NULL, '450', '90%', '750', '30', '30', '3', '20', '10%', '10', '20', '8', '8', '12', '20%', '30', '10', '20', '混凝土结构', '20', '10', '81', '良好', '1', '2019-09-18 13:19:49');

-- ----------------------------
-- Table structure for sf_fire_resources
-- ----------------------------
DROP TABLE IF EXISTS `sf_fire_resources`;
CREATE TABLE `sf_fire_resources`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `res_cate` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源类别',
  `res_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物资名称',
  `expiry_date` datetime(0) NULL DEFAULT NULL COMMENT '有效期',
  `res_quantity` decimal(10, 0) NULL DEFAULT NULL COMMENT '物资储量',
  `res_unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '储量单位',
  `unit_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位id',
  `unit_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位名称',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人id',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `isRemoved` int(10) NULL DEFAULT NULL COMMENT '是否删除 0-正常；1-被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消防物资表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_fire_resources
-- ----------------------------
INSERT INTO `sf_fire_resources` VALUES (1, '1', '灭火器', '2020-01-31 11:56:32', 20, '个', '0b78cffad98d89dfde1aaeda241a6bab', '中国联通', '1', '2020-01-08 11:57:22', '1', '2020-01-08 11:57:12', '111', 0);
INSERT INTO `sf_fire_resources` VALUES (2, '2', '卷帘门', '2020-01-08 12:00:18', 5, '个', '0b78cffad98d89dfde1aaeda241a6bab', '中国联通', '1', '2020-01-08 12:00:37', '1', '2020-01-08 12:00:41', NULL, 0);
INSERT INTO `sf_fire_resources` VALUES (3, '1', '灭火瓶', '2020-01-31 13:10:53', 100, '个', '0b78cffad98d89dfde1aaeda241a6bab', '中国联通', '1', '2020-01-08 13:11:25', '1', '2020-01-08 13:11:31', NULL, 0);

-- ----------------------------
-- Table structure for sf_inspection_execute
-- ----------------------------
DROP TABLE IF EXISTS `sf_inspection_execute`;
CREATE TABLE `sf_inspection_execute`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `taskid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务id',
  `taskname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `org_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联网单位ID',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位名称',
  `buildid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑id',
  `tour_build_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑名称',
  `tourlist` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '巡检点Id集合',
  `persons` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '巡检人员集合',
  `begin_time` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(1) NOT NULL COMMENT '任务状态:巡检状态（1,进行中;2,已完成;3,逾期完成，4逾期未完成）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '巡检执行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_inspection_execute
-- ----------------------------
INSERT INTO `sf_inspection_execute` VALUES ('19d9d1f071102ec0c71087b0015e6e51', 'a19cb557d160704ec25995a4470d38bb', '周一测试', '66be114fe695993908827b6cbc68a97c', '北京东霖消防科技有限公司', '3f9198c1fd625bec726111380cf6c383', 'OBE E4', '2310802cc811ec91961dc8489ff6b78c,4560802cc811ec91961dc8489ff6b78c', '5b53c89e721cb13053333bb980ee4547,97530a6e0ba7a9594ca304dec7afc4f9', '2019-08-19', '2019-08-19', 4, NULL, NULL, '2019-08-19 09:19:36', '003');
INSERT INTO `sf_inspection_execute` VALUES ('1fdd9d1f071102ec0c71087b00wertyu', '900982969be36b537fe790c0a56880f8', '巡检任务测试', '66be114fe695993908827b6cbc68a97', 'dsfsdfsdfs', 'fdsfsfsfdsfs', 'fdsfsdfsdf', NULL, 'fdsfsfds', '2019-08-19', '2019-08-24', 1, NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_execute` VALUES ('8a2f1e65a3c0c389ce8c30df6f5096d9', '395fada51c7f49b037390fd24eb2ea71', '20191014', '66be114fe695993908827b6cbc68a97c', '北京东霖消防科技有限公司', 'a1ff19d9f266f3d267705b218ea3884e', '4号楼', '22470ab5fb1005faae83f1ac4267fc2c,42671af790dd551d02c83245cef37f2a,4560802cc811ec91961dc8489ff6b78c', 'd9891cc778b353b2af783245cb5c8b3a', '2019-10-14', '2019-10-16', 1, NULL, NULL, '2019-10-14 09:59:08', 'test');

-- ----------------------------
-- Table structure for sf_inspection_execute_detail
-- ----------------------------
DROP TABLE IF EXISTS `sf_inspection_execute_detail`;
CREATE TABLE `sf_inspection_execute_detail`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `taskid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务id',
  `executeid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行id',
  `point_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点id',
  `point_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点名称',
  `point_build_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑ID',
  `point_build_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑名称',
  `point_region_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点位置ID',
  `point_region_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点位置名称',
  `devicetype` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `devicenum` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号',
  `deviceaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备地址',
  `point_time` datetime(0) NULL DEFAULT NULL COMMENT '巡检时间',
  `point_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检人',
  `point_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检人姓名',
  `point_image_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点照片ID',
  `is_inspection` tinyint(2) NULL DEFAULT NULL COMMENT '是否巡检，0-未巡检，1-已巡检',
  `point_floor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点所属楼层',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位名称',
  `org_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '巡检执行情况详细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_inspection_execute_detail
-- ----------------------------
INSERT INTO `sf_inspection_execute_detail` VALUES ('0d69f7d24a2270da6a6409b42b53ddd9', 'a19cb557d160704ec25995a4470d38bb', '19d9d1f071102ec0c71087b0015e6e51', '4560802cc811ec91961dc8489ff6b78c', '七小路', 'ef6a0087e5a2f9c88fcc737859ca6cd0', '五禾公寓', '78945655', '四楼研发', '2', '456859fhgdfk5494fbgjhfgh', 'fsdfsfs', '2019-09-18 16:05:14', '1', NULL, 'lengleng-4accfd6af8824782b04c60bb866a4115.png,', 1, NULL, 'tttt', '66be114fe695993908827b6cbc68a97c', NULL);
INSERT INTO `sf_inspection_execute_detail` VALUES ('365dd8ff3f089af70164f8b97b225046', 'a19cb557d160704ec25995a4470d38bb', '19d9d1f071102ec0c71087b0015e6e51', '2310802cc811ec91961dc8489ff6b78c', '朱辛庄', '434', 'e世界', '12345434', '三楼拐角', '3', 'dsf759fhgdfk5494fbgjhfgh', 'testaddr', '2019-08-27 10:35:06', NULL, NULL, 'e76b85f7c5614d7da9cc97a21d410e51timg.jpg,eb4c21c10ed9460ea134e477d84f1669zm001.jpeg', 0, NULL, 'test', '1234', NULL);
INSERT INTO `sf_inspection_execute_detail` VALUES ('7dfab8f43a5815f62c674c4d5ade94ac', '395fada51c7f49b037390fd24eb2ea71', '8a2f1e65a3c0c389ce8c30df6f5096d9', '4560802cc811ec91961dc8489ff6b78c', '七小路', 'ef6a0087e5a2f9c88fcc737859ca6cd0', '五禾公寓', '78945655', '四楼研发', '2', '456859fhgdfk5494fbgjhfgh', 'fsdfsfs', NULL, NULL, NULL, NULL, NULL, NULL, 'tttt', '66be114fe695993908827b6cbc68a97c', NULL);
INSERT INTO `sf_inspection_execute_detail` VALUES ('bf58bbda7d3f3a7c514decea7ccb95ca', '395fada51c7f49b037390fd24eb2ea71', '8a2f1e65a3c0c389ce8c30df6f5096d9', '22470ab5fb1005faae83f1ac4267fc2c', '东霖消防通道', '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, '6', 'a001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '66be114fe695993908827b6cbc68a97c', NULL);
INSERT INTO `sf_inspection_execute_detail` VALUES ('e5a5d39f196594fccb442e5494db6ccd', '395fada51c7f49b037390fd24eb2ea71', '8a2f1e65a3c0c389ce8c30df6f5096d9', '42671af790dd551d02c83245cef37f2a', '东霖消防通道', '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, '6', 'a001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '66be114fe695993908827b6cbc68a97c', NULL);

-- ----------------------------
-- Table structure for sf_inspection_point
-- ----------------------------
DROP TABLE IF EXISTS `sf_inspection_point`;
CREATE TABLE `sf_inspection_point`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `org_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位ID',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位名称',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级单位ID',
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级单位名称',
  `point_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点名称',
  `point_bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一维码',
  `point_rfid_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'RFID',
  `point_qr_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码',
  `binding` tinyint(1) NULL DEFAULT NULL COMMENT '绑定状态 0-未绑定  1-已绑定',
  `point_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点区域类型ID',
  `point_build_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑ID',
  `point_build_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑名称',
  `point_region_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点位置ID',
  `point_floor` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点所属楼层',
  `point_region_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点位置名称',
  `point_image_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '巡检点照片ID',
  `devicetype` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `deviceaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备地址',
  `devicenum` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号',
  `produce_date` date NULL DEFAULT NULL COMMENT '生产日期',
  `expirationdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保质期',
  `isRemoved` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除: 0-正常；1-已删除',
  `isScrap` tinyint(1) NULL DEFAULT NULL COMMENT '是否报废: 0-正常；1-已报废',
  `longitude` decimal(10, 7) NULL DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10, 7) NULL DEFAULT NULL COMMENT '纬度',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `modify_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `modify_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '巡检点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_inspection_point
-- ----------------------------
INSERT INTO `sf_inspection_point` VALUES ('0fdb97835331fe48240cda45936bc7d8', 'f83d8e86e21158605e0e8486ccf50b4d', NULL, NULL, NULL, 'vcxzvcz', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '1', NULL, 'vcxzvzcvzx', '2019-09-19', NULL, 0, NULL, NULL, NULL, NULL, NULL, '2019-09-18 15:27:24', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('22470ab5fb1005faae83f1ac4267fc2c', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:43', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('2310802cc811ec91961dc8489ff6b78c', '1234', 'test', '123443', 'tesst', '朱辛庄', '34324', '43432424', 'e364c57f0fdd4d57aa1fd312e280988f', 0, '5', '434', 'e世界', '12345434', NULL, '三楼拐角', 'dsds', '3', 'testaddr', 'dsf759fhgdfk5494fbgjhfgh', '2019-07-19', '100', 0, 0, 116.6145000, 39.8657800, '1', 'admin', '2019-08-06 16:05:53', '1', 'admin', '2019-08-06 16:06:09', '测试数据');
INSERT INTO `sf_inspection_point` VALUES ('2416c7ba562862e7aeb0b98935750a1c', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:39:00', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('2a42914d3005594623b8d55a5aee3bf2', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:55', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('3419d0944e9be5e41f163d57dcfea00b', '66be114fe695993908827b6cbc68a97', NULL, NULL, NULL, 'zmtest', NULL, NULL, NULL, NULL, NULL, 'c28249c0637ff68eec55db3302de9b37', '', NULL, NULL, NULL, NULL, '1', NULL, '1234567890-=', '2020-10-07', NULL, 0, NULL, NULL, NULL, NULL, NULL, '2019-10-23 16:02:05', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('42671af790dd551d02c83245cef37f2a', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:42', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('4560802cc811ec91961dc8489ff6b78c', '66be114fe695993908827b6cbc68a97c', 'tttt', '888654', 'yyyy', '七小路', '54851', '78978989', '08b69dfd35a74a4fa9fd38d308e7f4b3', 1, '5', 'ef6a0087e5a2f9c88fcc737859ca6cd0', '五禾公寓', '78945655', NULL, '四楼研发', '5222', '2', 'fsdfsfs', '456859fhgdfk5494fbgjhfgh', '2019-08-10', '30', 0, 0, 116.6145000, 39.8657800, '1', 'admin', '2019-08-10 16:56:44', '1', 'admin', '2019-08-14 16:49:58', '测试数据2');
INSERT INTO `sf_inspection_point` VALUES ('4bcb6392ac28b48e7b13ac62b937c565', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '巡检点', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '11', '2019-08-30', NULL, 0, NULL, NULL, NULL, NULL, NULL, '2019-08-29 11:08:07', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('500b5e9a21d3e3a9dfead5a248291e4e', '2b0f99f7dd3cc60d1ac0eff667157c9d', NULL, NULL, NULL, 'test001', NULL, NULL, NULL, NULL, NULL, 'ef6a0087e5a2f9c88fcc737859ca6cd0', '', NULL, NULL, NULL, NULL, '4', NULL, 'ty', '2019-08-19', NULL, 0, NULL, NULL, NULL, NULL, NULL, '2019-08-27 16:13:09', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('5b32d115d8931e349fb2f8c79967217a', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:55', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('6458585101d5505da92c9aac52604bb3', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:43', NULL, NULL, '2019-08-24 10:53:01', NULL);
INSERT INTO `sf_inspection_point` VALUES ('67bd470994c22a69dc12b53866bf0182', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:56', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('6b3aee35a47ff1dd556ab4eebb1c8df2', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:42', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('6dd5d0ce354c5bd83985c83af2ea1d98', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:55', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('7089d3b724f160ee7073a63d6ffa0a4f', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:43', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('7141efc785fcce962c89e3f8ec159493', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:41', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('7f5f0791e41fee670d19ee95d987617c', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:55', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('90917ac16262c9fe08cd19a7f8cede67', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:54', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('974f6b3d73efc9dbe5e8d39402c715b4', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:44', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('9daa37ad5f0718c53290f7b2cb94d405', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:55', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('9df23d548efb2da6b53700461aeb362f', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:42', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('a215a4f7b359115a333d8027d5c56c03', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:44', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('bd77e23231f22ff87156f8d10f90a07d', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:55', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('c152bca1db3d78c468823d721448fbe1', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:54', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('c1d0fe3413011d633b12bae1eb921713', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:41', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('ca95ab52dd49be725f5df3ecefd34654', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:41', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('cc092c6ba3ad2eabfbc40dd1c3d2a261', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '20191014', NULL, NULL, NULL, NULL, NULL, 'a1ff19d9f266f3d267705b218ea3884e', '', NULL, NULL, NULL, NULL, '2', NULL, 'NUHY6789989', '2024-10-09', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-10-14 09:57:59', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('d293252e7e0720dd70b06fb69695b67f', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '20191014', NULL, NULL, NULL, NULL, NULL, 'a1ff19d9f266f3d267705b218ea3884e', '', NULL, NULL, NULL, NULL, '2', NULL, 'NUHY6789989', '2024-10-09', NULL, 0, NULL, NULL, NULL, NULL, NULL, '2019-10-14 09:57:56', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('d57c60d6212f51f984335be2724e319c', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'bbbb', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '3', NULL, '44', '2019-08-28', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:55', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('d7c60edb8099b8289c6574f6e811ec25', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:32', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('e14db74e039cace2a3796d3f785d2150', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:41', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('e3e62e3656009939a5b6ab80bf32d1aa', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:43', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('e81cc9b88345a58d32ecea64af86d3a1', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:43', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('e96f9f2745ebc6390b89a0f6fbde6fe8', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, '东霖消防通道', NULL, NULL, NULL, NULL, NULL, '3f9198c1fd625bec726111380cf6c383', '', NULL, NULL, NULL, NULL, '6', NULL, 'a001', '2019-08-30', NULL, 0, NULL, NULL, NULL, NULL, NULL, '2019-08-23 18:09:43', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('ecae52c87cca6914de2600d30eb4c0f2', '', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:38', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('ecb7077ccb44fc177bb2c163346fe092', '', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-29 11:07:42', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('ed8b6b6d6cf03d7218f58f622b0a52e5', '', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, '', '', NULL, NULL, NULL, NULL, '', NULL, '', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, '2019-08-27 09:38:33', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_point` VALUES ('f4cb6815e76933cfa9fc7d1d96df195a', '66be114fe695993908827b6cbc68a97c', NULL, NULL, NULL, 'vcxzvzcxv', NULL, NULL, NULL, NULL, NULL, '382c3e065479b64b352bbc0ee072a14d', '', NULL, NULL, NULL, NULL, '2', NULL, 'vczxvczxv', '2019-10-29', NULL, 0, NULL, NULL, NULL, NULL, NULL, '2019-10-11 09:50:45', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sf_inspection_task
-- ----------------------------
DROP TABLE IF EXISTS `sf_inspection_task`;
CREATE TABLE `sf_inspection_task`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `taskcode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务编码',
  `taskname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `taskcontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务说明',
  `tasktype` tinyint(1) NULL DEFAULT NULL COMMENT '任务类型: 1-临时配发  2-定时配发',
  `tourlist` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '巡检点集合',
  `kaishi` int(3) NULL DEFAULT NULL COMMENT '开始日(1-31的数值)',
  `finish` int(3) NULL DEFAULT NULL COMMENT '结束日(1-31的数值)',
  `begin_time` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `org_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联网单位ID',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位名称',
  `buildid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑id',
  `build_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑名称',
  `persons` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配置巡检人员集合',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `modify_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `modifytime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '巡检任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_inspection_task
-- ----------------------------
INSERT INTO `sf_inspection_task` VALUES ('37507ff5c1c9865d433109b7dcee0fca', '', '', NULL, NULL, '22470ab5fb1005faae83f1ac4267fc2c22470ab5fb1005faae83f1ac4267fc2c', NULL, NULL, NULL, NULL, '66be114fe695993908827b6cbc68a97c', '北京东霖消防科技有限公司', '3f9198c1fd625bec726111380cf6c383', 'OBE E4', NULL, NULL, NULL, '2019-08-24 10:55:30', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_task` VALUES ('395fada51c7f49b037390fd24eb2ea71', '20191014', '20191014', NULL, 1, '22470ab5fb1005faae83f1ac4267fc2c,42671af790dd551d02c83245cef37f2a,4560802cc811ec91961dc8489ff6b78c', NULL, NULL, '2019-10-14', '2019-10-16', '66be114fe695993908827b6cbc68a97c', '北京东霖消防科技有限公司', 'a1ff19d9f266f3d267705b218ea3884e', '4号楼', 'd9891cc778b353b2af783245cb5c8b3a', NULL, NULL, '2019-10-14 09:59:08', NULL, NULL, NULL, 'test');
INSERT INTO `sf_inspection_task` VALUES ('3f05726d1f5ad3e644dc9f8577f367df', '2342134123', '巡检任务', NULL, 1, '4560802cc811ec91961dc8489ff6b78c,4560802cc811ec91961dc8489ff6b78c,7433cbf76abed0ae3089c635cdf06085', NULL, NULL, '2019-08-04', '2019-08-31', '66be114fe695993908827b6cbc68a97c', '北京东霖消防科技有限公司', '3f9198c1fd625bec726111380cf6c383', 'OBE E4', '1,5b53c89e721cb13053333bb980ee4547,8599cab4777ae9018b76e28b61eda674,8d0573c20d9b19932e33116bbd172b27,97530a6e0ba7a9594ca304dec7afc4f9,ef752e4cfb2881a84d7b1908cd0ea28b1,5b53c89e721cb13053333bb980ee4547,8599cab4777ae9018b76e28b61eda674,8d0573c20d9b19932e33116bbd172b27,97530a6e0ba7a9594ca304dec7afc4f9,ef752e4cfb2881a84d7b1908cd0ea28b', NULL, NULL, '2019-08-16 09:12:27', NULL, NULL, NULL, '23421424');
INSERT INTO `sf_inspection_task` VALUES ('408ccc7e8046f8792b4ccd50511d3ec3', '1', '1', NULL, NULL, '22470ab5fb1005faae83f1ac4267fc2c', NULL, NULL, NULL, NULL, '66be114fe695993908827b6cbc68a97c', '北京东霖消防科技有限公司', '3f9198c1fd625bec726111380cf6c383', 'OBE E4', NULL, NULL, NULL, '2019-08-27 09:44:16', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_task` VALUES ('823335d5fc449b1ff51dced065b40297', 'vcxzvzxc', '测试3', NULL, NULL, '2310802cc811ec91961dc8489ff6b78c,4560802cc811ec91961dc8489ff6b78c,7433cbf76abed0ae3089c635cdf06085', NULL, NULL, NULL, NULL, '66be114fe695993908827b6cbc68a97c', '北京东霖消防科技有限公司', '3f9198c1fd625bec726111380cf6c383', 'OBE E4', NULL, NULL, NULL, '2019-08-19 09:16:16', NULL, NULL, NULL, NULL);
INSERT INTO `sf_inspection_task` VALUES ('900982969be36b537fe790c0a56880f8', '测试', '巡检任务测试', NULL, 2, '310802cc811ec91961dc8489ff6b78c,4560802cc811ec91961dc8489ff6b78c,7433cbf76abed0ae3089c635cdf06085', NULL, NULL, NULL, NULL, '66be114fe695993908827b6cbc68a97c', '北京东霖消防科技有限公司', '3f9198c1fd625bec726111380cf6c383', 'OBE E4', '', NULL, NULL, '2019-08-17 16:02:49', NULL, NULL, NULL, 'DS');
INSERT INTO `sf_inspection_task` VALUES ('a19cb557d160704ec25995a4470d38bb', '002', '周一测试', NULL, 1, '2310802cc811ec91961dc8489ff6b78c,4560802cc811ec91961dc8489ff6b78c', NULL, NULL, '2019-08-19', '2019-08-21', '66be114fe695993908827b6cbc68a97', '北京东霖消防科技有限公司', '3f9198c1fd625bec726111380cf6c383', 'OBE E4', '5b53c89e721cb13053333bb980ee4547,97530a6e0ba7a9594ca304dec7afc4f9', NULL, NULL, '2019-08-19 09:19:36', NULL, NULL, NULL, '003');

-- ----------------------------
-- Table structure for sf_taskplan
-- ----------------------------
DROP TABLE IF EXISTS `sf_taskplan`;
CREATE TABLE `sf_taskplan`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `plancode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案编码',
  `planname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案名称',
  `plandesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案说明',
  `orgid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位id',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联网单位名称',
  `buildid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑ID',
  `build_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑名称',
  `tourlist` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '巡检点集合',
  `person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案经办人',
  `person_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方案经办人姓名',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人ID',
  `modify_user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `modifytime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '巡检方案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sf_taskplan
-- ----------------------------
INSERT INTO `sf_taskplan` VALUES ('2310802cc811ec91961dc8487834yhfg', 'TEST001', '突击检查', '从里到外', '123456', '北七家', '67890', '万科Tvb', '金，木，水，火，土', 'admin', 'admin', '123456', 'admin', '2019-08-06 16:38:39', '123546', 'admin', '2019-08-07 16:38:56', '七小路从南向北');

SET FOREIGN_KEY_CHECKS = 1;
