/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : heterogeneous1

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 13/07/2021 11:30:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_admin_1
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_1`;
CREATE TABLE `sys_admin_1` (
  `id` bigint NOT NULL,
  `cid` bigint DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='后台用户表';

-- ----------------------------
-- Table structure for sys_admin_2
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_2`;
CREATE TABLE `sys_admin_2` (
  `id` bigint NOT NULL,
  `cid` bigint DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='后台用户表';

-- ----------------------------
-- Table structure for sys_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_login_log`;
CREATE TABLE `sys_admin_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint NOT NULL,
  `admin_id` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=523997263127642114 DEFAULT CHARSET=utf8mb3 COMMENT='后台用户登录日志表';

-- ----------------------------
-- Table structure for sys_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role_relation`;
CREATE TABLE `sys_admin_role_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb3 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '公司用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '公司创建、用户注册时候的密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_department_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_department_info`;
CREATE TABLE `sys_department_info` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_remote_database_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_remote_database_info`;
CREATE TABLE `sys_remote_database_info` (
  `id` bigint NOT NULL,
  `cid` bigint NOT NULL COMMENT '公司id',
  `db_url` varchar(100) DEFAULT NULL COMMENT '数据源url',
  `db_user` varchar(30) DEFAULT NULL COMMENT '数据源用户名',
  `db_password` varchar(30) DEFAULT NULL COMMENT '数据源密码',
  `db_description` varchar(50) DEFAULT NULL COMMENT '描述信息',
  `type_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='所需集成数据源表';

-- ----------------------------
-- Table structure for sys_remote_database_panal
-- ----------------------------
DROP TABLE IF EXISTS `sys_remote_database_panal`;
CREATE TABLE `sys_remote_database_panal` (
  `id` bigint NOT NULL,
  `cid` bigint NOT NULL COMMENT '公司id',
  `state` int NOT NULL COMMENT '是或否在主页显示(1为在）',
  `db_id` bigint DEFAULT NULL COMMENT '数据源id，用于获取数据源对应的所有面板',
  `panal_type_id` bigint DEFAULT NULL COMMENT '面板类型',
  `sql_words` varchar(1000) DEFAULT NULL COMMENT '执行的sql',
  `description` varchar(50) DEFAULT NULL COMMENT '描述信息',
  `x` varchar(255) DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='监控信息';

-- ----------------------------
-- Table structure for sys_remote_database_predict
-- ----------------------------
DROP TABLE IF EXISTS `sys_remote_database_predict`;
CREATE TABLE `sys_remote_database_predict` (
  `id` bigint NOT NULL,
  `cid` bigint DEFAULT NULL,
  `panal_id` bigint DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL COMMENT '单位（/h）',
  `phone` varchar(255) DEFAULT NULL,
  `threshold` double(100,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_remote_database_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_remote_database_type`;
CREATE TABLE `sys_remote_database_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL COMMENT '数据库类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8mb3 COMMENT='支持的数据库类型';

-- ----------------------------
-- Table structure for sys_remote_influx_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_remote_influx_info`;
CREATE TABLE `sys_remote_influx_info` (
  `id` bigint NOT NULL,
  `cid` bigint NOT NULL COMMENT '公司ID',
  `username` varchar(255) DEFAULT NULL COMMENT 'influx用户名',
  `password` varchar(255) DEFAULT NULL COMMENT 'influx密码',
  `url` varchar(255) DEFAULT NULL COMMENT 'influx的URL+端口',
  `db_name` varchar(255) DEFAULT NULL COMMENT 'influx数据库名字',
  `source_name` varchar(255) DEFAULT NULL COMMENT '数据源的昵称(方便鉴别）',
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_remote_influx_panal
-- ----------------------------
DROP TABLE IF EXISTS `sys_remote_influx_panal`;
CREATE TABLE `sys_remote_influx_panal` (
  `id` bigint NOT NULL,
  `cid` bigint NOT NULL COMMENT '公司ID',
  `db_id` bigint DEFAULT NULL COMMENT '数据库ID，标记请求时候的数据源',
  `sql_statement` varchar(255) DEFAULT NULL COMMENT 'SQL查询语句',
  `panal_type_id` bigint DEFAULT NULL COMMENT '面板类型',
  `panal_name` varchar(255) DEFAULT NULL COMMENT '面板名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_remote_panal_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_remote_panal_type`;
CREATE TABLE `sys_remote_panal_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '面板ID',
  `panal_type` varchar(255) DEFAULT NULL COMMENT '面板类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` bigint DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3 COMMENT='后台资源表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='后台用户角色表';

-- ----------------------------
-- Table structure for sys_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource_relation`;
CREATE TABLE `sys_role_resource_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8mb3 COMMENT='后台角色资源关系表';

SET FOREIGN_KEY_CHECKS = 1;
