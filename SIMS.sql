/*
 Navicat MySQL Data Transfer

 Source Server         : 47.112.206.61
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 47.112.206.61:3306
 Source Schema         : studentinfomanagement

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 26/06/2020 16:56:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bar
-- ----------------------------
DROP TABLE IF EXISTS `bar`;
CREATE TABLE `bar`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bar
-- ----------------------------
INSERT INTO `bar` VALUES ('优秀', 30);
INSERT INTO `bar` VALUES ('良', 60);
INSERT INTO `bar` VALUES ('合格', 20);
INSERT INTO `bar` VALUES ('不及格', 10);

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `clno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级编号',
  `clname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级名称',
  `dno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '院系编号',
  PRIMARY KEY (`clno`) USING BTREE,
  INDEX `Class_ibfk_1`(`dno`) USING BTREE,
  CONSTRAINT `Class_ibfk_1` FOREIGN KEY (`dno`) REFERENCES `department` (`dno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('211231', '网络1811', '211');
INSERT INTO `class` VALUES ('211232', '网络1812', '211');
INSERT INTO `class` VALUES ('211233', '网络1813', '211');
INSERT INTO `class` VALUES ('211234', '网络1814', '211');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程编号',
  `cname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名',
  `credit` decimal(2, 1) NOT NULL COMMENT '学分',
  `term` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学期',
  `hours` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学时',
  `tno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任课教师编号',
  PRIMARY KEY (`cno`) USING BTREE,
  INDEX `Course_ibfk_1`(`tno`) USING BTREE,
  CONSTRAINT `Course_ibfk_1` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '1', 1.0, '1', '1', 'T001');
INSERT INTO `course` VALUES ('3-105', '计算机导论', 3.0, '2019-2020学年第二学期', '32', 'T001');
INSERT INTO `course` VALUES ('3-235', '数据结构', 4.0, '2019-2020学年第二学期', '32', 'T003');
INSERT INTO `course` VALUES ('3-236', '离散数学', 4.0, '2019-2020学年第二学期', '32', 'T004');
INSERT INTO `course` VALUES ('3-245', '操作系统', 3.0, '2019-2020学年第二学期', '32', 'T002');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '院系编号',
  `dname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '院系名称',
  PRIMARY KEY (`dno`) USING BTREE,
  INDEX `dno`(`dno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('210', '轮机工程学院');
INSERT INTO `department` VALUES ('211', '计算机工程学院');
INSERT INTO `department` VALUES ('330', '海外教育学院');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `sno` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `cno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程号',
  `grade` decimal(4, 1) NOT NULL COMMENT '成绩',
  PRIMARY KEY (`sno`, `cno`) USING BTREE,
  INDEX `Score_ibfk_1`(`cno`) USING BTREE,
  CONSTRAINT `Score_ibfk_1` FOREIGN KEY (`cno`) REFERENCES `course` (`cno`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `Score_ibfk_2` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('201821123001', '3-105', 66.0);
INSERT INTO `score` VALUES ('201821123002', '3-105', 28.0);
INSERT INTO `score` VALUES ('201821123003', '3-105', 47.0);
INSERT INTO `score` VALUES ('201821123004', '3-105', 59.0);
INSERT INTO `score` VALUES ('201821123005', '3-105', 8.0);
INSERT INTO `score` VALUES ('201821123007', '3-105', 20.0);
INSERT INTO `score` VALUES ('201821123009', '3-105', 42.0);
INSERT INTO `score` VALUES ('201821123010', '3-105', 1.0);
INSERT INTO `score` VALUES ('201821123011', '3-105', 25.0);
INSERT INTO `score` VALUES ('201821123012', '3-105', 9.0);
INSERT INTO `score` VALUES ('201821123013', '3-105', 82.0);
INSERT INTO `score` VALUES ('201821123014', '3-105', 85.0);
INSERT INTO `score` VALUES ('201821123015', '3-105', 88.0);
INSERT INTO `score` VALUES ('201821123016', '3-105', 67.0);
INSERT INTO `score` VALUES ('201821123017', '3-105', 86.0);
INSERT INTO `score` VALUES ('201821123019', '3-105', 41.0);
INSERT INTO `score` VALUES ('201821123021', '3-105', 57.0);
INSERT INTO `score` VALUES ('201821123022', '3-105', 72.0);
INSERT INTO `score` VALUES ('201821123023', '3-105', 94.0);
INSERT INTO `score` VALUES ('201821123024', '3-105', 59.0);
INSERT INTO `score` VALUES ('201821123025', '3-105', 69.0);
INSERT INTO `score` VALUES ('201821123026', '3-105', 89.0);
INSERT INTO `score` VALUES ('201821123027', '3-105', 42.0);
INSERT INTO `score` VALUES ('201821123028', '3-105', 28.0);
INSERT INTO `score` VALUES ('201821123029', '3-105', 33.0);
INSERT INTO `score` VALUES ('201821123030', '3-105', 28.0);
INSERT INTO `score` VALUES ('201821123031', '3-235', 61.0);
INSERT INTO `score` VALUES ('201821123032', '3-235', 44.0);
INSERT INTO `score` VALUES ('201821123033', '3-235', 87.0);
INSERT INTO `score` VALUES ('201821123034', '3-235', 85.0);
INSERT INTO `score` VALUES ('201821123035', '3-235', 16.0);
INSERT INTO `score` VALUES ('201821123036', '3-235', 4.0);
INSERT INTO `score` VALUES ('201821123037', '3-235', 40.0);
INSERT INTO `score` VALUES ('201821123038', '3-235', 53.0);
INSERT INTO `score` VALUES ('201821123039', '3-235', 83.0);
INSERT INTO `score` VALUES ('201821123040', '3-235', 11.0);
INSERT INTO `score` VALUES ('201821123041', '3-235', 66.0);
INSERT INTO `score` VALUES ('201821123042', '3-235', 93.0);
INSERT INTO `score` VALUES ('201821123043', '3-235', 62.0);
INSERT INTO `score` VALUES ('201821123044', '3-235', 85.0);
INSERT INTO `score` VALUES ('201821123045', '3-235', 13.0);
INSERT INTO `score` VALUES ('201821123046', '3-235', 78.0);
INSERT INTO `score` VALUES ('201821123047', '3-235', 81.0);
INSERT INTO `score` VALUES ('201821123048', '3-235', 70.0);
INSERT INTO `score` VALUES ('201821123049', '3-235', 56.0);
INSERT INTO `score` VALUES ('201821123050', '3-235', 40.0);
INSERT INTO `score` VALUES ('201821123051', '3-235', 89.0);
INSERT INTO `score` VALUES ('201821123052', '3-235', 99.0);
INSERT INTO `score` VALUES ('201821123053', '3-235', 96.0);
INSERT INTO `score` VALUES ('201821123054', '3-235', 32.0);
INSERT INTO `score` VALUES ('201821123055', '3-235', 49.0);
INSERT INTO `score` VALUES ('201821123056', '3-235', 44.0);
INSERT INTO `score` VALUES ('201821123057', '3-235', 60.0);
INSERT INTO `score` VALUES ('201821123058', '3-235', 41.0);
INSERT INTO `score` VALUES ('201821123059', '3-235', 54.0);
INSERT INTO `score` VALUES ('201821123060', '3-235', 99.0);
INSERT INTO `score` VALUES ('201821123061', '3-236', 10.0);
INSERT INTO `score` VALUES ('201821123062', '3-236', 98.0);
INSERT INTO `score` VALUES ('201821123063', '3-236', 38.0);
INSERT INTO `score` VALUES ('201821123064', '3-236', 71.0);
INSERT INTO `score` VALUES ('201821123065', '3-236', 95.0);
INSERT INTO `score` VALUES ('201821123066', '3-236', 46.0);
INSERT INTO `score` VALUES ('201821123067', '3-236', 36.0);
INSERT INTO `score` VALUES ('201821123068', '3-236', 36.0);
INSERT INTO `score` VALUES ('201821123069', '3-236', 33.0);
INSERT INTO `score` VALUES ('201821123070', '3-236', 64.0);
INSERT INTO `score` VALUES ('201821123071', '3-236', 87.0);
INSERT INTO `score` VALUES ('201821123072', '3-236', 86.0);
INSERT INTO `score` VALUES ('201821123073', '3-236', 52.0);
INSERT INTO `score` VALUES ('201821123074', '3-236', 34.0);
INSERT INTO `score` VALUES ('201821123075', '3-236', 21.0);
INSERT INTO `score` VALUES ('201821123076', '3-236', 4.0);
INSERT INTO `score` VALUES ('201821123077', '3-236', 100.0);
INSERT INTO `score` VALUES ('201821123078', '3-236', 36.0);
INSERT INTO `score` VALUES ('201821123079', '3-236', 72.0);
INSERT INTO `score` VALUES ('201821123080', '3-236', 38.0);
INSERT INTO `score` VALUES ('201821123081', '3-236', 46.0);
INSERT INTO `score` VALUES ('201821123082', '3-236', 76.0);
INSERT INTO `score` VALUES ('201821123083', '3-236', 38.0);
INSERT INTO `score` VALUES ('201821123084', '3-236', 77.0);
INSERT INTO `score` VALUES ('201821123085', '3-236', 94.0);
INSERT INTO `score` VALUES ('201821123086', '3-236', 72.0);
INSERT INTO `score` VALUES ('201821123087', '3-236', 80.0);
INSERT INTO `score` VALUES ('201821123088', '3-236', 7.0);
INSERT INTO `score` VALUES ('201821123089', '3-236', 93.0);
INSERT INTO `score` VALUES ('201821123090', '3-236', 7.0);
INSERT INTO `score` VALUES ('201821123091', '3-245', 59.0);
INSERT INTO `score` VALUES ('201821123092', '3-245', 43.0);
INSERT INTO `score` VALUES ('201821123093', '3-245', 62.0);
INSERT INTO `score` VALUES ('201821123094', '3-245', 2.0);
INSERT INTO `score` VALUES ('201821123095', '3-245', 63.0);
INSERT INTO `score` VALUES ('201821123096', '3-245', 25.0);
INSERT INTO `score` VALUES ('201821123097', '3-245', 4.0);
INSERT INTO `score` VALUES ('201821123098', '3-245', 38.0);
INSERT INTO `score` VALUES ('201821123099', '3-245', 98.0);
INSERT INTO `score` VALUES ('201821123100', '3-245', 75.0);
INSERT INTO `score` VALUES ('201821123101', '3-245', 59.0);
INSERT INTO `score` VALUES ('201821123103', '3-245', 15.0);
INSERT INTO `score` VALUES ('201821123104', '3-245', 52.0);
INSERT INTO `score` VALUES ('201821123105', '3-245', 42.0);
INSERT INTO `score` VALUES ('201821123106', '3-245', 39.0);
INSERT INTO `score` VALUES ('201821123107', '3-245', 12.0);
INSERT INTO `score` VALUES ('201821123108', '3-245', 82.0);
INSERT INTO `score` VALUES ('201821123109', '3-245', 98.0);
INSERT INTO `score` VALUES ('201821123110', '3-245', 89.0);
INSERT INTO `score` VALUES ('201821123111', '3-245', 83.0);
INSERT INTO `score` VALUES ('201821123112', '3-245', 34.0);
INSERT INTO `score` VALUES ('201821123113', '3-245', 5.0);
INSERT INTO `score` VALUES ('201821123115', '3-245', 26.0);
INSERT INTO `score` VALUES ('201821123116', '3-245', 79.0);
INSERT INTO `score` VALUES ('201821123117', '3-245', 80.0);
INSERT INTO `score` VALUES ('201821123118', '3-245', 73.0);
INSERT INTO `score` VALUES ('201821123119', '3-245', 19.0);
INSERT INTO `score` VALUES ('201821123120', '3-245', 84.0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sno` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `sname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `ssex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `sbirthday` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '生日',
  `sid` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号码',
  `snation` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '民族',
  `clno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级编号',
  PRIMARY KEY (`sno`) USING BTREE,
  INDEX `Student_ibfk_1`(`clno`) USING BTREE,
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`clno`) REFERENCES `class` (`clno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('201821123001', '李施旻', '女', '1941-11-17', '410104194111176000', '汉', '211231');
INSERT INTO `student` VALUES ('201821123002', '林榕冬', '女', '1959-02-10', '360921195902102016', '汉', '211231');
INSERT INTO `student` VALUES ('201821123003', '吴金玉', '女', '1929-04-25', '14102919290425557X', '汉', '211231');
INSERT INTO `student` VALUES ('201821123004', '潘艺丹', '女', '1957-08-08', '430581195708086976', '汉', '211231');
INSERT INTO `student` VALUES ('201821123005', '范彦真', '男', '1903-12-27', '211282190312272000', '汉', '211231');
INSERT INTO `student` VALUES ('201821123007', '何炎玲', '女', '1986-05-01', '360881198605016000', '汉', '211231');
INSERT INTO `student` VALUES ('201821123009', '景建芳', '男', '1939-08-28', '230902193908286016', '汉', '211231');
INSERT INTO `student` VALUES ('201821123010', '陈志伟', '男', '1900-06-22', '451024190006227008', '汉', '211231');
INSERT INTO `student` VALUES ('201821123011', '商梅淳', '女', '1911-12-23', '622921191112236032', '汉', '211231');
INSERT INTO `student` VALUES ('201821123012', '林贵龙', '男', '1910-07-13', '411200191007131008', '汉', '211231');
INSERT INTO `student` VALUES ('201821123013', '康钦铭', '女', '1989-05-22', '360926198905220992', '汉', '211231');
INSERT INTO `student` VALUES ('201821123014', '陈柏炎', '男', '1925-12-27', '341702192512273024', '汉', '211231');
INSERT INTO `student` VALUES ('201821123015', '陈荣灿', '男', '1976-04-06', '140224197604060000', '汉', '211231');
INSERT INTO `student` VALUES ('201821123016', '王江鸿', '女', '2014-11-17', '632625201411176960', '汉', '211231');
INSERT INTO `student` VALUES ('201821123017', '潘煦', '男', '1936-06-21', '340400193606219008', '汉', '211231');
INSERT INTO `student` VALUES ('201821123019', '简达亮', '女', '1927-12-27', '360900192712278976', '汉', '211231');
INSERT INTO `student` VALUES ('201821123020', '文正胜', '女', '1993-05-30', '140700199305308000', '汉', '211231');
INSERT INTO `student` VALUES ('201821123021', '陈星星', '女', '1995-02-16', '410602199502166016', '汉', '211231');
INSERT INTO `student` VALUES ('201821123022', '罗赐', '女', '1944-08-26', '610729194408259968', '汉', '211231');
INSERT INTO `student` VALUES ('201821123023', '黄泓彬', '男', '1941-05-10', '150124194105104000', '汉', '211231');
INSERT INTO `student` VALUES ('201821123024', '韩佳欣', '男', '1928-02-15', '513332192802153024', '汉', '211231');
INSERT INTO `student` VALUES ('201821123025', '臧麒越', '男', '1945-12-09', '410922194512092032', '汉', '211231');
INSERT INTO `student` VALUES ('201821123026', '杨耿晨', '女', '1934-05-12', '370306193405126976', '汉', '211231');
INSERT INTO `student` VALUES ('201821123027', '温海杰', '男', '1960-07-13', '341225196007132032', '土家', '211231');
INSERT INTO `student` VALUES ('201821123028', '王定伟', '男', '1963-02-11', '331021196302118016', '侗', '211231');
INSERT INTO `student` VALUES ('201821123029', '张一弛', '男', '1903-07-11', '52000019030711438X', '汉', '211231');
INSERT INTO `student` VALUES ('201821123030', '伍泓润', '女', '1994-09-05', '440901199409052032', '哈尼', '211231');
INSERT INTO `student` VALUES ('201821123031', '方婷', '女', '1940-11-07', '610202194011074944', '汉', '211232');
INSERT INTO `student` VALUES ('201821123032', '陈煜如', '男', '2015-03-09', '211021201503094016', '汉', '211232');
INSERT INTO `student` VALUES ('201821123033', '吴美婷', '男', '1915-08-22', '350303191508228992', '汉', '211232');
INSERT INTO `student` VALUES ('201821123034', '林莹', '男', '1971-04-05', '511126197104054016', '汉', '211232');
INSERT INTO `student` VALUES ('201821123035', '陈艺芳', '女', '2020-12-24', '331100202012244000', '汉', '211232');
INSERT INTO `student` VALUES ('201821123036', '吕恬', '女', '2007-08-07', '13043320070807545X', '汉', '211232');
INSERT INTO `student` VALUES ('201821123037', '许梦雪', '男', '1930-09-12', '220181193009128000', '汉', '211232');
INSERT INTO `student` VALUES ('201821123038', '李永会', '女', '2002-07-16', '310116200207168000', '汉', '211232');
INSERT INTO `student` VALUES ('201821123039', '周晓敏', '女', '1901-12-12', '441201190112126016', '汉', '211232');
INSERT INTO `student` VALUES ('201821123040', '林可嘉', '女', '1990-02-25', '141102199002251008', '汉', '211232');
INSERT INTO `student` VALUES ('201821123041', '陈伟杰', '男', '1999-11-04', '152202198509019008', '汉', '211232');
INSERT INTO `student` VALUES ('201821123042', '叶鸿章', '女', '1937-10-10', '331122193710099968', '汉', '211232');
INSERT INTO `student` VALUES ('201821123043', '张赓', '男', '1932-11-17', '621223193211171968', '汉', '211232');
INSERT INTO `student` VALUES ('201821123044', '王予平', '女', '2002-12-05', '510182200212056000', '汉', '211232');
INSERT INTO `student` VALUES ('201821123045', '吴潮汇', '男', '1989-03-27', '511526198903276992', '汉', '211232');
INSERT INTO `student` VALUES ('201821123046', '林阿强', '女', '1981-04-30', '371102198104300992', '汉', '211232');
INSERT INTO `student` VALUES ('201821123047', '刘泽南', '女', '1947-03-16', '542622194703161984', '汉', '211232');
INSERT INTO `student` VALUES ('201821123048', '苏雨', '女', '1920-03-27', '35030219200327492X', '汉', '211232');
INSERT INTO `student` VALUES ('201821123049', '赖富烨', '女', '2010-01-02', '420115201001022976', '汉', '211232');
INSERT INTO `student` VALUES ('201821123050', '李星宝', '男', '1938-09-28', '53290119380928404X', '汉', '211232');
INSERT INTO `student` VALUES ('201821123051', '张煌', '女', '1921-07-13', '362427192107134976', '汉', '211232');
INSERT INTO `student` VALUES ('201821123052', '林祥涛', '男', '1955-08-22', '513435195508224000', '汉', '211232');
INSERT INTO `student` VALUES ('201821123053', '余俊良', '女', '1987-11-08', '350429198711081984', '汉', '211232');
INSERT INTO `student` VALUES ('201821123054', '常云鹏', '女', '1938-05-20', '653227193805206016', '汉', '211232');
INSERT INTO `student` VALUES ('201821123055', '赵昱昊', '男', '1989-02-04', '13018419890204962X', '汉', '211232');
INSERT INTO `student` VALUES ('201821123056', '李烨聪', '女', '1990-04-24', '532531199004243968', '汉', '211232');
INSERT INTO `student` VALUES ('201821123057', '许成龙', '女', '1912-05-29', '431128191205299008', '苗', '211232');
INSERT INTO `student` VALUES ('201821123058', '邬东霖', '女', '1945-02-13', '321182194502136000', '汉', '211232');
INSERT INTO `student` VALUES ('201821123059', '高凯', '女', '2010-05-27', '220702201005278016', '汉', '211232');
INSERT INTO `student` VALUES ('201821123060', '兰炜', '男', '1985-09-16', '430682198509160000', '畲', '211232');
INSERT INTO `student` VALUES ('201821123061', '林玮璐', '男', '1960-01-04', '52273019600104293X', '汉', '211233');
INSERT INTO `student` VALUES ('201821123062', '张瑀鑫', '男', '1975-01-22', '110115197501220992', '汉', '211233');
INSERT INTO `student` VALUES ('201821123063', '苏楚雯', '女', '2003-05-10', '530326200305108992', '汉', '211233');
INSERT INTO `student` VALUES ('201821123064', '陈玲清', '男', '1953-03-13', '341401195303137984', '汉', '211233');
INSERT INTO `student` VALUES ('201821123065', '林舒馨', '女', '1999-11-23', '370882199911232000', '汉', '211233');
INSERT INTO `student` VALUES ('201821123066', '汪雨', '女', '1923-11-11', '652927192311115008', '汉', '211233');
INSERT INTO `student` VALUES ('201821123067', '刘鲜', '男', '1959-03-08', '610827195903081984', '苗', '211233');
INSERT INTO `student` VALUES ('201821123068', '何汐', '男', '2004-01-16', '542125200401164992', '汉', '211233');
INSERT INTO `student` VALUES ('201821123069', '许馨予', '男', '1966-02-18', '340501196602188032', '汉', '211233');
INSERT INTO `student` VALUES ('201821123070', '林硕', '男', '1941-04-18', '350504194104188032', '汉', '211233');
INSERT INTO `student` VALUES ('201821123071', '吴沂聪', '女', '1922-02-09', '530122192202092032', '汉', '211233');
INSERT INTO `student` VALUES ('201821123072', '刘羽', '女', '1949-07-13', '532624194907137024', '汉', '211233');
INSERT INTO `student` VALUES ('201821123073', '林中鹏', '女', '1914-11-10', '441301191411105984', '汉', '211233');
INSERT INTO `student` VALUES ('201821123074', '王赐荣', '女', '1977-07-23', '510682197707230016', '汉', '211233');
INSERT INTO `student` VALUES ('201821123075', '蔡德鑫', '男', '2004-01-24', '231005200401243008', '汉', '211233');
INSERT INTO `student` VALUES ('201821123076', '沈宇涛', '女', '1922-11-06', '362300192211068032', '汉', '211233');
INSERT INTO `student` VALUES ('201821123077', '范华', '男', '1945-05-04', '360400194505044992', '汉', '211233');
INSERT INTO `student` VALUES ('201821123078', '翁凌涛', '女', '1918-07-25', '542323191807257984', '汉', '211233');
INSERT INTO `student` VALUES ('201821123079', '白海槟', '女', '1908-06-01', '640324190806017024', '汉', '211233');
INSERT INTO `student` VALUES ('201821123080', '唐洪俊', '女', '1961-05-24', '36078219610524213X', '汉', '211233');
INSERT INTO `student` VALUES ('201821123081', '蔡丰骏', '男', '1933-07-07', '510824193307073984', '汉', '211233');
INSERT INTO `student` VALUES ('201821123082', '潘楚坤', '男', '1942-02-01', '610825194202013056', '汉', '211233');
INSERT INTO `student` VALUES ('201821123083', '祝文涛', '男', '1920-03-30', '659002192003307008', '汉', '211233');
INSERT INTO `student` VALUES ('201821123084', '郭坤', '男', '1993-04-08', '370782199304086016', '汉', '211233');
INSERT INTO `student` VALUES ('201821123085', '李谦', '女', '1906-11-16', '53062119061116242X', '汉', '211233');
INSERT INTO `student` VALUES ('201821123086', '阮承南', '男', '2013-11-21', '511402201311217024', '汉', '211233');
INSERT INTO `student` VALUES ('201821123087', '张明海', '女', '1997-07-01', '230107199707017984', '汉', '211233');
INSERT INTO `student` VALUES ('201821123088', '古锦源', '男', '1989-11-12', '500103198911129024', '苗', '211233');
INSERT INTO `student` VALUES ('201821123089', '珠玛', '男', '1965-08-26', '520114196508267008', '藏', '211233');
INSERT INTO `student` VALUES ('201821123090', '郭东阳', '男', '1992-05-01', '150102199205009984', '回', '211233');
INSERT INTO `student` VALUES ('201821123091', '程建君', '男', '1998-05-21', '360521199805212032', '汉', '211234');
INSERT INTO `student` VALUES ('201821123092', '黄雅静', '男', '1977-09-05', '340711197709057984', '汉', '211234');
INSERT INTO `student` VALUES ('201821123093', '赖慧颖', '女', '2013-06-07', '652325201306072960', '汉', '211234');
INSERT INTO `student` VALUES ('201821123094', '杨鸿漾', '女', '1971-12-25', '130700197112251008', '汉', '211234');
INSERT INTO `student` VALUES ('201821123095', '闫栩宁', '男', '2002-07-28', '341423200207280000', '汉', '211234');
INSERT INTO `student` VALUES ('201821123096', '李金妲', '女', '1938-11-22', '522632193811219968', '汉', '211234');
INSERT INTO `student` VALUES ('201821123097', '韦荣桃', '男', '1927-11-29', '330303192711294976', '水', '211234');
INSERT INTO `student` VALUES ('201821123098', '钟海清', '男', '2019-05-28', '510781201905283968', '汉', '211234');
INSERT INTO `student` VALUES ('201821123099', '吴彬凯', '女', '1920-12-02', '421181192012025984', '汉', '211234');
INSERT INTO `student` VALUES ('201821123100', '林晓龙', '男', '2016-01-06', '420683201601065024', '汉', '211234');
INSERT INTO `student` VALUES ('201821123101', '严威', '女', '2010-10-04', '150927201010044000', '汉', '211234');
INSERT INTO `student` VALUES ('201821123103', '谢晓淞', '女', '1913-04-18', '411300191304184000', '汉', '211234');
INSERT INTO `student` VALUES ('201821123104', '康友煌', '女', '2009-10-21', '450924200910217024', '汉', '211234');
INSERT INTO `student` VALUES ('201821123105', '张伟龙', '女', '2016-04-23', '430304201604236032', '汉', '211234');
INSERT INTO `student` VALUES ('201821123106', '黄陈昊', '男', '1923-10-11', '340122192310113984', '汉', '211234');
INSERT INTO `student` VALUES ('201821123107', '陈梓灿', '女', '1999-01-25', '361130199901251008', '汉', '211234');
INSERT INTO `student` VALUES ('201821123108', '罗小川', '男', '1927-09-22', '14080219270922413X', '汉', '211234');
INSERT INTO `student` VALUES ('201821123109', '岳小钢', '男', '1905-09-25', '430923190509249984', '汉', '211234');
INSERT INTO `student` VALUES ('201821123110', '王粤翰', '女', '1918-04-27', '210281191804271008', '汉', '211234');
INSERT INTO `student` VALUES ('201821123111', '曾俊伟', '男', '1953-02-04', '341523195302046016', '汉', '211234');
INSERT INTO `student` VALUES ('201821123112', '王鑫杰', '男', '1983-06-28', '51142119830628479X', '汉', '211234');
INSERT INTO `student` VALUES ('201821123113', '刘海博', '女', '1994-09-25', '340403199409257984', '汉', '211234');
INSERT INTO `student` VALUES ('201821123115', '刘聚文', '女', '1977-09-05', '320706197709056000', '汉', '211234');
INSERT INTO `student` VALUES ('201821123116', '李天明', '女', '1921-07-09', '130535192107092000', '汉', '211234');
INSERT INTO `student` VALUES ('201821123117', '霍淏华', '男', '1996-01-13', '522601199601137024', '布依', '211234');
INSERT INTO `student` VALUES ('201821123118', '周秋斌', '女', '1944-08-25', '342522194408259008', '汉', '211234');
INSERT INTO `student` VALUES ('201821123119', '鲁俊文', '女', '1956-11-24', '330411195611244032', '汉', '211234');
INSERT INTO `student` VALUES ('201821123120', '周仁杰', '男', '1957-01-18', '371521195701184000', '土家', '211234');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tno` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教师编号',
  `tname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `tsex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`tno`) USING BTREE,
  INDEX `tno`(`tno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('T001', '李诚', '男');
INSERT INTO `teacher` VALUES ('T002', '张旭', '男');
INSERT INTO `teacher` VALUES ('T003', '王萍', '女');
INSERT INTO `teacher` VALUES ('T004', '李丽丽', '女');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `Account` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `Username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', 'admin', '1', '管理员');
INSERT INTO `user` VALUES ('T001', '111', '2', '李诚');
INSERT INTO `user` VALUES ('T002', '111', '2', '张旭');
INSERT INTO `user` VALUES ('T003', '111', '2', '王萍');
INSERT INTO `user` VALUES ('T004', '111', '2', '李丽丽');
INSERT INTO `user` VALUES ('201821123001', '201821123001', '3', '李施旻');
INSERT INTO `user` VALUES ('201821123002', '201821123002', '3', '林榕冬');
INSERT INTO `user` VALUES ('201821123003', '201821123003', '3', '吴金玉');
INSERT INTO `user` VALUES ('201821123004', '201821123004', '3', '潘艺丹');
INSERT INTO `user` VALUES ('201821123005', '201821123005', '3', '范彦真');
INSERT INTO `user` VALUES ('201821123007', '201821123007', '3', '何炎玲');
INSERT INTO `user` VALUES ('201821123009', '201821123009', '3', '景建芳');
INSERT INTO `user` VALUES ('201821123010', '201821123010', '3', '陈志伟');
INSERT INTO `user` VALUES ('201821123011', '201821123011', '3', '商梅淳');
INSERT INTO `user` VALUES ('201821123012', '201821123012', '3', '林贵龙');
INSERT INTO `user` VALUES ('201821123013', '201821123013', '3', '康钦铭');
INSERT INTO `user` VALUES ('201821123014', '201821123014', '3', '陈柏炎');
INSERT INTO `user` VALUES ('201821123015', '201821123015', '3', '陈荣灿');
INSERT INTO `user` VALUES ('201821123016', '201821123016', '3', '王江鸿');
INSERT INTO `user` VALUES ('201821123017', '201821123017', '3', '潘煦');
INSERT INTO `user` VALUES ('201821123019', '201821123019', '3', '简达亮');
INSERT INTO `user` VALUES ('201821123020', '201821123020', '3', '文正胜');
INSERT INTO `user` VALUES ('201821123021', '201821123021', '3', '陈星星');
INSERT INTO `user` VALUES ('201821123022', '201821123022', '3', '罗赐');
INSERT INTO `user` VALUES ('201821123023', '201821123023', '3', '黄泓彬');
INSERT INTO `user` VALUES ('201821123024', '201821123024', '3', '韩佳欣');
INSERT INTO `user` VALUES ('201821123025', '201821123025', '3', '臧麒越');
INSERT INTO `user` VALUES ('201821123026', '201821123026', '3', '杨耿晨');
INSERT INTO `user` VALUES ('201821123027', '201821123027', '3', '温海杰');
INSERT INTO `user` VALUES ('201821123028', '201821123028', '3', '王定伟');
INSERT INTO `user` VALUES ('201821123029', '201821123029', '3', '张一弛');
INSERT INTO `user` VALUES ('201821123030', '201821123030', '3', '伍泓润');
INSERT INTO `user` VALUES ('201821123031', '201821123031', '3', '方婷');
INSERT INTO `user` VALUES ('201821123032', '201821123032', '3', '陈煜如');
INSERT INTO `user` VALUES ('201821123033', '201821123033', '3', '吴美婷');
INSERT INTO `user` VALUES ('201821123034', '201821123034', '3', '林莹');
INSERT INTO `user` VALUES ('201821123035', '201821123035', '3', '陈艺芳');
INSERT INTO `user` VALUES ('201821123036', '201821123036', '3', '吕恬');
INSERT INTO `user` VALUES ('201821123037', '201821123037', '3', '许梦雪');
INSERT INTO `user` VALUES ('201821123038', '201821123038', '3', '李永会');
INSERT INTO `user` VALUES ('201821123039', '201821123039', '3', '周晓敏');
INSERT INTO `user` VALUES ('201821123040', '201821123040', '3', '林可嘉');
INSERT INTO `user` VALUES ('201821123041', '201821123041', '3', '陈伟杰');
INSERT INTO `user` VALUES ('201821123042', '201821123042', '3', '叶鸿章');
INSERT INTO `user` VALUES ('201821123043', '201821123043', '3', '张赓');
INSERT INTO `user` VALUES ('201821123044', '201821123044', '3', '王予平');
INSERT INTO `user` VALUES ('201821123045', '201821123045', '3', '吴潮汇');
INSERT INTO `user` VALUES ('201821123046', '201821123046', '3', '林阿强');
INSERT INTO `user` VALUES ('201821123047', '201821123047', '3', '刘泽南');
INSERT INTO `user` VALUES ('201821123048', '201821123048', '3', '苏雨');
INSERT INTO `user` VALUES ('201821123049', '201821123049', '3', '赖富烨');
INSERT INTO `user` VALUES ('201821123050', '201821123050', '3', '李星宝');
INSERT INTO `user` VALUES ('201821123051', '201821123051', '3', '张煌');
INSERT INTO `user` VALUES ('201821123052', '201821123052', '3', '林祥涛');
INSERT INTO `user` VALUES ('201821123053', '201821123053', '3', '余俊良');
INSERT INTO `user` VALUES ('201821123054', '201821123054', '3', '常云鹏');
INSERT INTO `user` VALUES ('201821123055', '201821123055', '3', '赵昱昊');
INSERT INTO `user` VALUES ('201821123056', '201821123056', '3', '李烨聪');
INSERT INTO `user` VALUES ('201821123057', '201821123057', '3', '许成龙');
INSERT INTO `user` VALUES ('201821123058', '201821123058', '3', '邬东霖');
INSERT INTO `user` VALUES ('201821123059', '201821123059', '3', '高凯');
INSERT INTO `user` VALUES ('201821123060', '201821123060', '3', '兰炜');
INSERT INTO `user` VALUES ('201821123061', '201821123061', '3', '林玮璐');
INSERT INTO `user` VALUES ('201821123062', '201821123062', '3', '张瑀鑫');
INSERT INTO `user` VALUES ('201821123063', '201821123063', '3', '苏楚雯');
INSERT INTO `user` VALUES ('201821123064', '201821123064', '3', '陈玲清');
INSERT INTO `user` VALUES ('201821123065', '201821123065', '3', '林舒馨');
INSERT INTO `user` VALUES ('201821123066', '201821123066', '3', '汪雨');
INSERT INTO `user` VALUES ('201821123067', '201821123067', '3', '刘鲜');
INSERT INTO `user` VALUES ('201821123068', '201821123068', '3', '何汐');
INSERT INTO `user` VALUES ('201821123069', '201821123069', '3', '许馨予');
INSERT INTO `user` VALUES ('201821123070', '201821123070', '3', '林硕');
INSERT INTO `user` VALUES ('201821123071', '201821123071', '3', '吴沂聪');
INSERT INTO `user` VALUES ('201821123072', '201821123072', '3', '刘羽');
INSERT INTO `user` VALUES ('201821123073', '201821123073', '3', '林中鹏');
INSERT INTO `user` VALUES ('201821123074', '201821123074', '3', '王赐荣');
INSERT INTO `user` VALUES ('201821123075', '201821123075', '3', '蔡德鑫');
INSERT INTO `user` VALUES ('201821123076', '201821123076', '3', '沈宇涛');
INSERT INTO `user` VALUES ('201821123077', '201821123077', '3', '范华');
INSERT INTO `user` VALUES ('201821123078', '201821123078', '3', '翁凌涛');
INSERT INTO `user` VALUES ('201821123079', '201821123079', '3', '白海槟');
INSERT INTO `user` VALUES ('201821123080', '201821123080', '3', '唐洪俊');
INSERT INTO `user` VALUES ('201821123081', '201821123081', '3', '蔡丰骏');
INSERT INTO `user` VALUES ('201821123082', '201821123082', '3', '潘楚坤');
INSERT INTO `user` VALUES ('201821123083', '201821123083', '3', '祝文涛');
INSERT INTO `user` VALUES ('201821123084', '201821123084', '3', '郭坤');
INSERT INTO `user` VALUES ('201821123085', '201821123085', '3', '李谦');
INSERT INTO `user` VALUES ('201821123086', '201821123086', '3', '阮承南');
INSERT INTO `user` VALUES ('201821123087', '201821123087', '3', '张明海');
INSERT INTO `user` VALUES ('201821123088', '201821123088', '3', '古锦源');
INSERT INTO `user` VALUES ('201821123089', '201821123089', '3', '珠玛');
INSERT INTO `user` VALUES ('201821123090', '201821123090', '3', '郭东阳');
INSERT INTO `user` VALUES ('201821123091', '201821123091', '3', '程建君');
INSERT INTO `user` VALUES ('201821123092', '201821123092', '3', '黄雅静');
INSERT INTO `user` VALUES ('201821123093', '201821123093', '3', '赖慧颖');
INSERT INTO `user` VALUES ('201821123094', '201821123094', '3', '杨鸿漾');
INSERT INTO `user` VALUES ('201821123095', '201821123095', '3', '闫栩宁');
INSERT INTO `user` VALUES ('201821123096', '201821123096', '3', '李金妲');
INSERT INTO `user` VALUES ('201821123097', '201821123097', '3', '韦荣桃');
INSERT INTO `user` VALUES ('201821123098', '201821123098', '3', '钟海清');
INSERT INTO `user` VALUES ('201821123099', '201821123099', '3', '吴彬凯');
INSERT INTO `user` VALUES ('201821123100', '201821123100', '3', '林晓龙');
INSERT INTO `user` VALUES ('201821123101', '201821123101', '3', '严威');
INSERT INTO `user` VALUES ('201821123103', '201821123103', '3', '谢晓淞');
INSERT INTO `user` VALUES ('201821123104', '201821123104', '3', '康友煌');
INSERT INTO `user` VALUES ('201821123105', '201821123105', '3', '张伟龙');
INSERT INTO `user` VALUES ('201821123106', '201821123106', '3', '黄陈昊');
INSERT INTO `user` VALUES ('201821123107', '201821123107', '3', '陈梓灿');
INSERT INTO `user` VALUES ('201821123108', '201821123108', '3', '罗小川');
INSERT INTO `user` VALUES ('201821123109', '201821123109', '3', '岳小钢');
INSERT INTO `user` VALUES ('201821123110', '201821123110', '3', '王粤翰');
INSERT INTO `user` VALUES ('201821123111', '201821123111', '3', '曾俊伟');
INSERT INTO `user` VALUES ('201821123112', '201821123112', '3', '王鑫杰');
INSERT INTO `user` VALUES ('201821123113', '201821123113', '3', '刘海博');
INSERT INTO `user` VALUES ('201821123115', '201821123115', '3', '刘聚文');
INSERT INTO `user` VALUES ('201821123116', '201821123116', '3', '李天明');
INSERT INTO `user` VALUES ('201821123117', '201821123117', '3', '霍淏华');
INSERT INTO `user` VALUES ('201821123118', '201821123118', '3', '周秋斌');
INSERT INTO `user` VALUES ('201821123119', '201821123119', '3', '鲁俊文');
INSERT INTO `user` VALUES ('201821123120', '201821123120', '3', '周仁杰');

-- ----------------------------
-- View structure for scoreinfo
-- ----------------------------
DROP VIEW IF EXISTS `scoreinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `scoreinfo` AS select `student`.`sno` AS `sno`,`student`.`sname` AS `sname`,`course`.`cno` AS `cno`,`course`.`cname` AS `cname`,`course`.`term` AS `term`,`teacher`.`tname` AS `tname`,`score`.`grade` AS `grade` from (((`course` join `score` on((`course`.`cno` = `score`.`cno`))) join `student` on((`student`.`sno` = `score`.`sno`))) join `teacher` on((`course`.`tno` = `teacher`.`tno`)));

-- ----------------------------
-- View structure for studentinfo
-- ----------------------------
DROP VIEW IF EXISTS `studentinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `studentinfo` AS select `student`.`sno` AS `sno`,`student`.`sname` AS `sname`,`student`.`ssex` AS `ssex`,`student`.`sbirthday` AS `sbirthday`,`student`.`sid` AS `sid`,`student`.`snation` AS `snation`,`department`.`dname` AS `dname`,`class`.`clname` AS `clname` from ((`student` join `class` on((`student`.`clno` = `class`.`clno`))) join `department` on((`class`.`dno` = `department`.`dno`)));

-- ----------------------------
-- View structure for teacherinfo
-- ----------------------------
DROP VIEW IF EXISTS `teacherinfo`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `teacherinfo` AS select `teacher`.`tno` AS `tno`,`teacher`.`tname` AS `tname`,`teacher`.`tsex` AS `tsex`,`course`.`cname` AS `cname` from (`teacher` left join `course` on((`teacher`.`tno` = `course`.`tno`)));

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `add_user`;
delimiter ;;
CREATE TRIGGER `add_user` AFTER INSERT ON `student` FOR EACH ROW BEGIN
DECLARE account VARCHAR(15);
DECLARE username VARCHAR(20);
DECLARE pwd VARCHAR(20);
set account=NEW.Sno;
set username=NEW.Sname;
set pwd=NEW.Sno;
INSERT INTO `user` VALUES(account,pwd,'3',username);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `update_user`;
delimiter ;;
CREATE TRIGGER `update_user` AFTER UPDATE ON `student` FOR EACH ROW BEGIN
DECLARE old_acc VARCHAR(15);
DECLARE new_acc VARCHAR(15);
set old_acc=OLD.Sno;
set new_acc=NEW.Sno;
update user set account = new_acc where account = old_acc;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `del_user`;
delimiter ;;
CREATE TRIGGER `del_user` AFTER DELETE ON `student` FOR EACH ROW BEGIN
DECLARE acc VARCHAR(15);
set acc=OLD.Sno;
DELETE from `user` WHERE Account=acc;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
