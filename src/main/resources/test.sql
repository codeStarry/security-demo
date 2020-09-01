/*
 Navicat Premium Data Transfer

 Source Server         : lsy_aliyun_mysql
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 112.124.15.176:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 01/09/2020 15:32:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级资源ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url',
  `resources` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源编码',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `level` int(11) NULL DEFAULT NULL COMMENT '资源级别',
  `sort_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源图标',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 menu、button',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-权限资源表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES (93, '0', NULL, 'systemManager', '系统管理', 0, 1, NULL, 'menu', '系统管理', '2020-08-27 11:44:45', '2020-08-27 11:44:47');
INSERT INTO `t_sys_menu` VALUES (94, '93', '/menu/save', 'sys:menu:save', '添加权限菜单', 1, 2, NULL, 'menu', '添加权限菜单', '2020-08-27 11:48:44', '2020-08-27 11:48:46');
INSERT INTO `t_sys_menu` VALUES (95, '93', '/menu/update', 'sys:menu:update', '修改权限菜单', 2, 3, NULL, 'menu', '修改权限菜单', '2020-08-27 11:49:43', '2020-08-27 11:49:44');
INSERT INTO `t_sys_menu` VALUES (96, '93', '/menu/del', 'sys:menu:del', '删除权限菜单', 3, 4, NULL, 'menu', '删除权限菜单', '2020-08-27 11:50:33', '2020-08-27 11:50:35');
INSERT INTO `t_sys_menu` VALUES (97, '93', '/menu/page', 'sys:menu:page', '分页查询权限菜单', 4, 5, NULL, 'menu', '分页查询权限菜单', '2020-08-27 11:51:24', '2020-08-27 11:51:26');
INSERT INTO `t_sys_menu` VALUES (98, '0', NULL, 'userManager', '用户管理', 5, 6, NULL, 'menu', '用户管理', '2020-08-27 11:53:03', '2020-08-27 11:53:05');
INSERT INTO `t_sys_menu` VALUES (99, '98', '/user/page', 'sys:user:page', '分页查询用户', 6, 7, NULL, 'menu', '分页查询用户', '2020-08-27 11:53:54', '2020-08-27 11:53:56');
INSERT INTO `t_sys_menu` VALUES (100, '98', '/user/save', 'sys:user:save', '分配账号', 7, 8, NULL, 'button', '分配账号', '2020-08-27 11:54:58', '2020-08-27 11:55:00');
INSERT INTO `t_sys_menu` VALUES (101, '98', '/user/register', 'sys:user:register', '用户注册', 8, 9, NULL, 'button', '用户注册', '2020-08-27 11:55:29', '2020-08-27 11:55:31');
INSERT INTO `t_sys_menu` VALUES (102, '98', '/user/login', 'sys:user:login', '用户登录', 9, 10, NULL, 'button', '用户登录', '2020-08-27 11:59:33', '2020-08-27 11:59:35');
INSERT INTO `t_sys_menu` VALUES (103, '0', NULL, 'noAuth', '游客', 10, 11, NULL, 'menu', '游客', '2020-08-27 12:02:58', '2020-08-27 12:03:00');
INSERT INTO `t_sys_menu` VALUES (104, '103', '/user/list', 'no:auth:user:list', '查询所有用户', 11, 12, NULL, 'button', '查询所有用户', '2020-08-27 12:04:04', '2020-08-27 12:04:05');
INSERT INTO `t_sys_menu` VALUES (105, '103', '/menu/list', 'no:auth:menu:list', '查询所有权限菜单', 12, 13, NULL, 'button', '查询所有权限菜单', '2020-08-27 12:04:48', '2020-08-27 12:04:50');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-角色表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, 'admin', '系统管理员', '系统管理员', '2019-03-28 15:51:56', '2019-03-28 15:51:59');
INSERT INTO `t_sys_role` VALUES (2, 'visitor', '访客', '访客', '2019-03-28 20:17:04', '2019-09-09 16:32:15');
INSERT INTO `t_sys_role` VALUES (5, 'user', '普通用户', '普通用户', '2020-08-27 11:05:57', '2020-08-27 11:05:59');

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(10) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(10) NULL DEFAULT NULL COMMENT '菜单ID',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1642 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理 - 角色-权限资源关联表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES (1636, 1, 93, '2020-08-27 12:00:26', '2020-08-27 12:00:28');
INSERT INTO `t_sys_role_menu` VALUES (1637, 1, 98, '2020-08-27 12:01:02', '2020-08-27 12:01:04');
INSERT INTO `t_sys_role_menu` VALUES (1638, 2, 103, '2020-08-27 12:05:03', '2020-08-27 12:05:05');
INSERT INTO `t_sys_role_menu` VALUES (1639, 5, 99, '2020-08-27 12:05:16', '2020-08-27 12:05:18');
INSERT INTO `t_sys_role_menu` VALUES (1640, 5, 101, '2020-08-27 12:05:52', '2020-08-27 12:05:54');
INSERT INTO `t_sys_role_menu` VALUES (1641, 5, 102, '2020-08-27 12:06:02', '2020-08-27 12:06:04');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0:男 1:女',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `flag` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `token` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  `qq_oppen_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ 第三方登录Oppen_ID唯一标识',
  `pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '明文密码',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理-用户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (4, 'lsy_starry', '$2a$10$In22ZkKj9Ex9j/nB7eaoRewPfoMb54zv0TavCyIyKt5kZlymvRyRm', NULL, '0', NULL, NULL, NULL, 1, '$2a$10$In22ZkKj9Ex9j/nB7eaoRe', NULL, NULL, '123456', '2020-08-26 21:10:10', '2020-08-26 21:10:10');
INSERT INTO `t_sys_user` VALUES (5, 'lsy', '$2a$10$zveEH7zU.sXZNCif3Zzyk..oFy3BR944cbLDPwyHlgIASYzTvqYnG', NULL, '0', NULL, NULL, NULL, 1, '$2a$10$zveEH7zU.sXZNCif3Zzyk.', NULL, NULL, '123456', '2020-08-31 15:52:34', '2020-08-31 15:52:34');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(10) NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户ID',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理 - 用户角色关联表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES (12, 1, 1, '2019-08-21 10:49:41', '2019-08-21 10:49:41');
INSERT INTO `t_sys_user_role` VALUES (27, 2, 2, '2019-09-07 21:50:33', '2019-09-07 21:50:33');
INSERT INTO `t_sys_user_role` VALUES (30, 5, 4, '2020-08-27 12:08:44', '2020-08-27 12:08:46');

SET FOREIGN_KEY_CHECKS = 1;
