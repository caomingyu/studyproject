/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql8
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : study_spring_security

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/02/2022 16:21:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sc_permission
-- ----------------------------
DROP TABLE IF EXISTS `sc_permission`;
CREATE TABLE `sc_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sc_role
-- ----------------------------
DROP TABLE IF EXISTS `sc_role`;
CREATE TABLE `sc_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_role
-- ----------------------------
INSERT INTO `sc_role` VALUES (1, 'admin', '管理员', '1');
INSERT INTO `sc_role` VALUES (2, 'guest', '游客', '1');

-- ----------------------------
-- Table structure for sc_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sc_role_permission`;
CREATE TABLE `sc_role_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NULL DEFAULT NULL,
  `permission_id` int(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sc_user
-- ----------------------------
DROP TABLE IF EXISTS `sc_user`;
CREATE TABLE `sc_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `age` int(0) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `regis_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_user
-- ----------------------------
INSERT INTO `sc_user` VALUES (1, 'cmy', '123456', 25, '1', '1');
INSERT INTO `sc_user` VALUES (2, 'guest', '111', 2, '1', '1');

-- ----------------------------
-- Table structure for sc_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sc_user_role`;
CREATE TABLE `sc_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_user_role
-- ----------------------------
INSERT INTO `sc_user_role` VALUES (1, 1, 1, '1');
INSERT INTO `sc_user_role` VALUES (2, 2, 2, '1');

SET FOREIGN_KEY_CHECKS = 1;
