/*
 Navicat Premium Data Transfer

 Source Server         : 47.112.206.61
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 47.112.206.61:3306
 Source Schema         : StudentInfoManagement

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 21/06/2020 21:06:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Class
-- ----------------------------
DROP TABLE IF EXISTS `Class`;
CREATE TABLE `Class`  (
  `Clno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级编号',
  `Clname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级名称',
  `Dno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '院系编号',
  PRIMARY KEY (`Clno`) USING BTREE,
  INDEX `Class_ibfk_1`(`Dno`) USING BTREE,
  CONSTRAINT `Class_ibfk_1` FOREIGN KEY (`Dno`) REFERENCES `Department` (`Dno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Class
-- ----------------------------
INSERT INTO `Class` VALUES ('211211', '计算1811', '211');
INSERT INTO `Class` VALUES ('211212', '计算1812', '211');
INSERT INTO `Class` VALUES ('211231', '网络1811', '211');
INSERT INTO `Class` VALUES ('211232', '网络1812', '211');

-- ----------------------------
-- Table structure for Course
-- ----------------------------
DROP TABLE IF EXISTS `Course`;
CREATE TABLE `Course`  (
  `Cno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程编号',
  `Cname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名',
  `Credit` decimal(2, 1) NOT NULL COMMENT '学分',
  `Term` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学期',
  `Hours` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学时',
  `Tno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任课教师编号',
  PRIMARY KEY (`Cno`) USING BTREE,
  INDEX `Course_ibfk_1`(`Tno`) USING BTREE,
  CONSTRAINT `Course_ibfk_1` FOREIGN KEY (`Tno`) REFERENCES `Teacher` (`Tno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Course
-- ----------------------------
INSERT INTO `Course` VALUES ('3-105', '计算机导论', 3.0, '2019-2020学年第二学期', '32', 'T001');
INSERT INTO `Course` VALUES ('3-235', '数据结构', 4.0, '2019-2020学年第二学期', '32', 'T003');
INSERT INTO `Course` VALUES ('3-236', '离散数学', 4.0, '2019-2020学年第二学期', '32', 'T004');
INSERT INTO `Course` VALUES ('3-245', '操作系统', 3.0, '2019-2020学年第二学期', '32', 'T002');

-- ----------------------------
-- Table structure for Department
-- ----------------------------
DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department`  (
  `Dno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '院系编号',
  `Dname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '院系名称',
  PRIMARY KEY (`Dno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Department
-- ----------------------------
INSERT INTO `Department` VALUES ('210', '轮机工程学院');
INSERT INTO `Department` VALUES ('211', '计算机工程学院');
INSERT INTO `Department` VALUES ('330', '海外教育学院');

-- ----------------------------
-- Table structure for Score
-- ----------------------------
DROP TABLE IF EXISTS `Score`;
CREATE TABLE `Score`  (
  `Sno` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `Cno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程号',
  `Grade` decimal(4, 1) NOT NULL COMMENT '成绩',
  PRIMARY KEY (`Sno`, `Cno`) USING BTREE,
  INDEX `Score_ibfk_1`(`Cno`) USING BTREE,
  CONSTRAINT `Score_ibfk_1` FOREIGN KEY (`Cno`) REFERENCES `Course` (`Cno`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `Score_ibfk_2` FOREIGN KEY (`Sno`) REFERENCES `Student` (`Sno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Score
-- ----------------------------
INSERT INTO `Score` VALUES ('201821121006', '3-235', 1.0);
INSERT INTO `Score` VALUES ('201821121007', '3-235', 52.0);
INSERT INTO `Score` VALUES ('201821121009', '3-235', 81.0);
INSERT INTO `Score` VALUES ('201821121010', '3-235', 57.0);
INSERT INTO `Score` VALUES ('201821121011', '3-235', 49.0);
INSERT INTO `Score` VALUES ('201821121012', '3-235', 86.0);
INSERT INTO `Score` VALUES ('201821121013', '3-235', 90.0);
INSERT INTO `Score` VALUES ('201821121014', '3-235', 75.0);
INSERT INTO `Score` VALUES ('201821121015', '3-235', 15.0);
INSERT INTO `Score` VALUES ('201821121016', '3-235', 68.0);
INSERT INTO `Score` VALUES ('201821121017', '3-236', 58.0);
INSERT INTO `Score` VALUES ('201821121018', '3-236', 59.0);
INSERT INTO `Score` VALUES ('201821121019', '3-236', 47.0);
INSERT INTO `Score` VALUES ('201821121020', '3-236', 37.0);
INSERT INTO `Score` VALUES ('201821121021', '3-236', 47.0);
INSERT INTO `Score` VALUES ('201821121022', '3-236', 9.0);
INSERT INTO `Score` VALUES ('201821121023', '3-236', 68.0);
INSERT INTO `Score` VALUES ('201821121024', '3-236', 89.0);
INSERT INTO `Score` VALUES ('201821121025', '3-236', 91.0);
INSERT INTO `Score` VALUES ('201821121026', '3-236', 49.0);
INSERT INTO `Score` VALUES ('201821123001', '3-105', 68.0);
INSERT INTO `Score` VALUES ('201821123002', '3-105', 51.0);
INSERT INTO `Score` VALUES ('201821123003', '3-105', 54.0);
INSERT INTO `Score` VALUES ('201821123004', '3-105', 90.0);
INSERT INTO `Score` VALUES ('201821123005', '3-105', 82.0);
INSERT INTO `Score` VALUES ('201821123006', '3-105', 17.0);
INSERT INTO `Score` VALUES ('201821123007', '3-105', 34.0);
INSERT INTO `Score` VALUES ('201821123009', '3-105', 32.0);
INSERT INTO `Score` VALUES ('201821123010', '3-105', 74.0);
INSERT INTO `Score` VALUES ('201821123011', '3-105', 98.0);
INSERT INTO `Score` VALUES ('201821123012', '3-245', 36.0);
INSERT INTO `Score` VALUES ('201821123013', '3-245', 77.0);
INSERT INTO `Score` VALUES ('201821123014', '3-245', 32.0);
INSERT INTO `Score` VALUES ('201821123015', '3-245', 63.0);
INSERT INTO `Score` VALUES ('201821123016', '3-245', 35.0);
INSERT INTO `Score` VALUES ('201821123017', '3-245', 43.0);
INSERT INTO `Score` VALUES ('201821123019', '3-245', 25.0);
INSERT INTO `Score` VALUES ('201821123020', '3-245', 87.0);
INSERT INTO `Score` VALUES ('201821123021', '3-245', 79.0);
INSERT INTO `Score` VALUES ('201821123022', '3-245', 55.0);

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student`  (
  `Sno` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `Sname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `Ssex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `Sbirthday` date NOT NULL COMMENT '生日',
  `Sid` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号码',
  `Snation` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '民族',
  `Clno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级编号',
  PRIMARY KEY (`Sno`) USING BTREE,
  INDEX `Student_ibfk_1`(`Clno`) USING BTREE,
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`Clno`) REFERENCES `Class` (`Clno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Student
-- ----------------------------
INSERT INTO `Student` VALUES ('201821121006', '郑兰艳', '男', '1900-01-19', '352229199911153515', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121007', '黄思婷', '女', '1900-01-20', '350121200007030119', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121009', '滕婉', '女', '1900-01-19', '350322199909292558', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121010', '朱晓萌', '女', '1900-01-20', '350623200012096614', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121011', '林雅静', '男', '1900-01-20', '360502200001113639', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121012', '刘明慧', '男', '1900-01-19', '152826199912010416', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121013', '蔡金宇', '女', '1900-01-19', '510302199912120030', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121014', '何逸雨', '女', '1900-01-20', '331003200006103979', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121015', '郑鹭辉', '男', '1900-01-20', '450421200009136515', '汉', '211211');
INSERT INTO `Student` VALUES ('201821121016', '吴涵阳', '女', '1900-01-20', '522130200005073617', '苗', '211211');
INSERT INTO `Student` VALUES ('201821121017', '刘晓丹', '男', '1900-01-20', '35052420000706052X', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121018', '王静雯', '女', '1900-01-19', '522401199909155745', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121019', '李佳玟', '女', '1900-01-20', '522130200001110425', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121020', '汪明华', '女', '1900-01-20', '522325200006250021', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121021', '林沛欣', '女', '1900-01-19', '622323199901104421', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121022', '陈丽玟', '女', '1900-01-20', '350122200001116217', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121023', '陈楠', '男', '1900-01-20', '350521200001310031', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121024', '王玉', '女', '1900-01-20', '350623200005174119', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121025', '李甜甜', '男', '1900-01-20', '350702200006177411', '汉', '211212');
INSERT INTO `Student` VALUES ('201821121026', '朱玲玲', '男', '1900-01-19', '35082319991005231X', '汉', '211212');
INSERT INTO `Student` VALUES ('201821123001', '林文龙', '女', '1900-01-20', '520103200004125613', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123002', '薛洪财', '女', '1900-01-20', '362334200012110013', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123003', '江雄鹏', '女', '1900-01-20', '350822200002140014', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123004', '陈煜', '女', '1900-01-19', '350322199706210815', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123005', '许德阳', '女', '1900-01-20', '350322200107283022', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123006', '吕煜华', '男', '1900-01-20', '350321200001052246', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123007', '陈泽镕', '女', '1900-01-19', '150202199907100622', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123009', '危文涛', '女', '1900-01-20', '511302200002130746', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123010', '李钻鑫', '男', '1900-01-20', '632121200008290026', '锡伯', '211231');
INSERT INTO `Student` VALUES ('201821123011', '张一鸣', '女', '1900-01-20', '520322200003089825', '汉', '211231');
INSERT INTO `Student` VALUES ('201821123012', '吴永锋', '男', '1900-01-20', '620111200005170524', '汉', '211232');
INSERT INTO `Student` VALUES ('201821123013', '王丕杰', '男', '1900-01-19', '350824199911061118', '侗', '211232');
INSERT INTO `Student` VALUES ('201821123014', '陈韵', '女', '1900-01-20', '350181200002151831', '汉', '211232');
INSERT INTO `Student` VALUES ('201821123015', '那宝龙', '男', '1900-01-20', '350304200008073115', '汉', '211232');
INSERT INTO `Student` VALUES ('201821123016', '张杰', '男', '1900-01-20', '350322200009267117', '汉', '211232');
INSERT INTO `Student` VALUES ('201821123017', '徐永兴', '男', '1900-01-20', '350425200010070017', '汉', '211232');
INSERT INTO `Student` VALUES ('201821123019', '万大明', '男', '1900-01-19', '350524199905305511', '汉', '211232');
INSERT INTO `Student` VALUES ('201821123020', '江磊', '女', '1900-01-19', '350582199911304535', '汉', '211232');
INSERT INTO `Student` VALUES ('201821123021', '程开', '男', '1900-01-19', '350628199905082013', '汉', '211232');
INSERT INTO `Student` VALUES ('201821123022', '陈诗苗', '男', '1900-01-19', '35082219991221331X', '汉', '211232');

-- ----------------------------
-- Table structure for Teacher
-- ----------------------------
DROP TABLE IF EXISTS `Teacher`;
CREATE TABLE `Teacher`  (
  `Tno` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教师编号',
  `Tname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `Tsex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`Tno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Teacher
-- ----------------------------
INSERT INTO `Teacher` VALUES ('T001', '李诚', '男');
INSERT INTO `Teacher` VALUES ('T002', '张旭', '男');
INSERT INTO `Teacher` VALUES ('T003', '王萍', '女');
INSERT INTO `Teacher` VALUES ('T004', '李丽丽', '女');

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`  (
  `Username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of User
-- ----------------------------
INSERT INTO `User` VALUES ('admin', 'admin', 'admin');
INSERT INTO `User` VALUES ('T001', '111', 'teacher');
INSERT INTO `User` VALUES ('T002', '111', 'teacher');
INSERT INTO `User` VALUES ('T003', '111', 'teacher');
INSERT INTO `User` VALUES ('T004', '111', 'teacher');

SET FOREIGN_KEY_CHECKS = 1;
