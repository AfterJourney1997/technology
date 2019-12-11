# Host: localhost  (Version 5.7.25)
# Date: 2019-12-10 22:08:53
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "awards"
#

CREATE TABLE `awards` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `awards_name` varchar(100) DEFAULT NULL COMMENT '奖项名字',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='奖项表';

#
# Data for table "awards"
#

INSERT INTO `awards` VALUES (1,'国家技术发明奖');

#
# Structure for table "building"
#

CREATE TABLE `building` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '房区名称',
  `area` float(6,2) DEFAULT NULL COMMENT '房区面积',
  `createtime` date DEFAULT NULL COMMENT '建造时间',
  `money` float(11,2) DEFAULT NULL COMMENT '建造成本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

#
# Data for table "building"
#

INSERT INTO `building` VALUES (3,'string',123.00,'2019-12-08',12442.00),(4,'string',123.00,'2019-12-08',12442.00);

#
# Structure for table "cooperation"
#

CREATE TABLE `cooperation` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '合作名称',
  `partner` varchar(50) DEFAULT NULL COMMENT '合作院系',
  `money` double(16,6) DEFAULT NULL COMMENT '合作经费',
  `major` varchar(50) DEFAULT NULL COMMENT '专业',
  `people` int(11) DEFAULT NULL COMMENT '人数',
  `category` varchar(50) DEFAULT NULL COMMENT '类别',
  `day` int(10) DEFAULT NULL COMMENT '时天',
  `date` date DEFAULT NULL COMMENT '日期',
  `c_Id` int(11) DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='校企合作';

#
# Data for table "cooperation"
#

INSERT INTO `cooperation` VALUES (1,'合作1','软件学院',5000.000000,'软件工程',0,'参观',55,'2019-12-09',2),(2,'合作2','软件学院',5000.000000,'软件工程',0,'参观',55,'2019-12-09',2),(3,'合作3','软件学院',5000.000000,'软件工程',0,'参观',55,'2019-12-09',2),(4,'合作4','软件学院',5000.000000,'软件工程',0,'参观',55,'2019-12-09',2),(5,'合作5','软件学院',5000.000000,'软件工程',0,'参观',55,'2019-12-09',2),(6,'合作6','软件学院',5000.000000,'软件工程',0,'参观',55,'2019-12-09',2);

#
# Structure for table "declare_award"
#

CREATE TABLE `declare_award` (
  `d_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申报Id',
  `d_name` varchar(100) DEFAULT NULL COMMENT '名称',
  `a_id` int(11) DEFAULT NULL COMMENT '奖项Id',
  `filename` varchar(100) DEFAULT NULL COMMENT '附件名称',
  `d_status` int(1) DEFAULT '0' COMMENT '申报状态，默认值为0',
  `c_Id` int(11) DEFAULT NULL COMMENT '所属企业Id',
  PRIMARY KEY (`d_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='奖项申报表';

#
# Data for table "declare_award"
#

INSERT INTO `declare_award` VALUES (1,'中原大比赛',1,'java文件.jsp',0,2),(2,'奥特曼比赛',1,'拉拉.jsp',0,2);

#
# Structure for table "degree"
#

CREATE TABLE `degree` (
  `x_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学位Id',
  `x_name` varchar(20) DEFAULT NULL COMMENT '学位名称',
  PRIMARY KEY (`x_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='学位表';

#
# Data for table "degree"
#

INSERT INTO `degree` VALUES (1,'本科'),(2,'大学'),(4,'科科');

#
# Structure for table "device"
#

CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `category_id` int(11) DEFAULT NULL COMMENT '类别id，外键',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `total` int(11) DEFAULT NULL COMMENT '总数',
  `remain` int(11) DEFAULT NULL COMMENT '剩余数量',
  `owner` int(1) DEFAULT NULL COMMENT '所属单位，1为学校建设，2为企业自建',
  `kind` int(1) DEFAULT NULL COMMENT '所属种类，1为设备，2为家具',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

#
# Data for table "device"
#


#
# Structure for table "device_property"
#

CREATE TABLE `device_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `device_id` int(11) DEFAULT NULL COMMENT '设备id，外键',
  `property_id` int(11) DEFAULT NULL COMMENT '属性id，外键',
  `value` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '属性值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

#
# Data for table "device_property"
#


#
# Structure for table "employee"
#

CREATE TABLE `employee` (
  `e_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工Id',
  `e_name` varchar(20) DEFAULT NULL COMMENT '员工姓名',
  `e_sex` varchar(2) DEFAULT NULL COMMENT '员工性别',
  `z_Id` int(11) DEFAULT NULL COMMENT '员工政治面貌所属Id',
  `e_position` varchar(10) DEFAULT NULL COMMENT '员工职位',
  `x_Id` int(11) DEFAULT NULL COMMENT '员工学位所属Id',
  `file_name` varchar(100) DEFAULT NULL COMMENT '员工从业证书',
  `c_Id` int(11) DEFAULT NULL COMMENT '所属企业id',
  PRIMARY KEY (`e_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='员工表';

#
# Data for table "employee"
#

INSERT INTO `employee` VALUES (1,'小李','12',1,'经理',1,'年后',2);

#
# Structure for table "enterprise_information"
#

CREATE TABLE `enterprise_information` (
  `c_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '企业Id',
  `c_name` varchar(50) DEFAULT NULL COMMENT '企业名称',
  `c_category` varchar(20) DEFAULT NULL COMMENT '企业类别',
  `c_legalPerson` varchar(20) DEFAULT NULL COMMENT '企业法人',
  `c_code` char(18) DEFAULT NULL COMMENT '企业信用代码，国家统一长度18位',
  `c_date` datetime DEFAULT NULL COMMENT '企业成立时间',
  `taxpayer_Type` varchar(20) DEFAULT NULL COMMENT '纳税人类型',
  `c_fund` double(16,6) DEFAULT NULL COMMENT '注册资金',
  `c_product` varchar(50) DEFAULT NULL COMMENT '主要产品',
  `file_name` varchar(100) DEFAULT NULL COMMENT '附件名字',
  `c_employee` int(5) DEFAULT NULL COMMENT '入职员工数',
  `c_information` varchar(255) DEFAULT NULL COMMENT '员工信息',
  `c_status` int(1) DEFAULT '0' COMMENT '企业入驻申请的状态，默认为0',
  PRIMARY KEY (`c_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='企业信息表';

#
# Data for table "enterprise_information"
#

INSERT INTO `enterprise_information` VALUES (2,'string','string',NULL,'string','2019-12-08 00:00:00','string',0.000000,'string','string',NULL,NULL,0);

#
# Structure for table "maintain"
#

CREATE TABLE `maintain` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务Id',
  `serviceName` varchar(50) DEFAULT NULL COMMENT '服务名称',
  `serviceDescribe` varchar(200) DEFAULT NULL COMMENT '服务描述',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='服务属性表';

#
# Data for table "maintain"
#

INSERT INTO `maintain` VALUES (1,'修理桌子','描述描述');

#
# Structure for table "notice"
#

CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `title` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '公告标题',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` int(1) DEFAULT NULL COMMENT '状态，0为历史公告，1为当前公告',
  `content` text COLLATE utf8_bin COMMENT '公告内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

#
# Data for table "notice"
#

INSERT INTO `notice` VALUES (1,'关于为什么有公告的公告','2019-12-08 23:17:22',0,'关于为什么有公告的公告关于为什么有公告的公告关于为什么有公告的公告关于为什么有公告的公告关于为什么有公告的公告关于为什么有公告的公告关于为什么有公告的公告关于为什么有公告的公告关于为什么有公告的公告'),(3,'公告2','2019-12-08 23:56:24',1,'公告2');

#
# Structure for table "opinion"
#

CREATE TABLE `opinion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `opinion` varchar(250) DEFAULT NULL COMMENT '意见内容',
  `status` int(1) DEFAULT '0' COMMENT '意见状态',
  `c_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='意见表';

#
# Data for table "opinion"
#

INSERT INTO `opinion` VALUES (1,'房间太小',0,2),(2,'电费太贵',0,2),(4,'房间太热',0,2),(5,'累死了',0,2);

#
# Structure for table "platform_application"
#

CREATE TABLE `platform_application` (
  `p_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '平台id',
  `p_name` varchar(50) DEFAULT NULL COMMENT '平台名称',
  `p_category` varchar(20) DEFAULT NULL COMMENT '平台类别',
  `p_grade` varchar(20) DEFAULT NULL COMMENT '平台级别',
  `c_Id` int(11) DEFAULT NULL COMMENT '企业Id，表示所属单位',
  `status` int(1) DEFAULT '0' COMMENT '审批状态，默认为0未审批',
  PRIMARY KEY (`p_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='平台申请表';

#
# Data for table "platform_application"
#

INSERT INTO `platform_application` VALUES (2,'平台修改测试','孵化','国家级',2,0),(3,'平台名称','平台类别','等级',2,0);

#
# Structure for table "politics_status"
#

CREATE TABLE `politics_status` (
  `z_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '政治面貌Id',
  `z_name` varchar(20) DEFAULT NULL COMMENT '政治面貌名称',
  PRIMARY KEY (`z_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='政治面貌表';

#
# Data for table "politics_status"
#

INSERT INTO `politics_status` VALUES (1,'主席'),(2,'总理'),(4,'测试修改'),(5,'本科');

#
# Structure for table "property_device"
#

CREATE TABLE `property_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `kind` int(1) DEFAULT NULL COMMENT '区分设备、家具，1是设备，2是家具',
  `category_id` int(11) DEFAULT NULL COMMENT '类别id，如为父类此项为0',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '类别名称',
  `property` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '属性名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

#
# Data for table "property_device"
#

INSERT INTO `property_device` VALUES (1,2,0,'桌子',''),(2,2,1,'桌子','材质'),(3,2,1,'桌子','颜色'),(4,2,1,'桌子','大小'),(5,2,0,'凳deng！',NULL);

#
# Structure for table "role"
#

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `account` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户账户',
  `password` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `role` int(1) DEFAULT NULL COMMENT '用户身份，1为管理员，2为企业',
  `infoid` int(11) DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

#
# Data for table "role"
#

INSERT INTO `role` VALUES (1,'admin','1234',1,2);

#
# Structure for table "room_company"
#

CREATE TABLE `room_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `room` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '房间名称',
  `area` float(11,0) DEFAULT NULL COMMENT '房间面积',
  `status` int(1) DEFAULT NULL COMMENT '房间状态，0为空闲，1为入驻',
  `company_id` int(11) DEFAULT NULL COMMENT '入驻企业id',
  `company_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '入驻企业名称',
  `owner` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '拥有者名称',
  `owning_time` float(5,0) DEFAULT NULL COMMENT '拥有年限',
  `building_id` int(11) DEFAULT NULL COMMENT '所属房区id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

#
# Data for table "room_company"
#

INSERT INTO `room_company` VALUES (1,'2#A101',123,1,NULL,NULL,NULL,NULL,1),(2,'2#A102',124,1,NULL,NULL,NULL,NULL,2);

#
# Structure for table "room_garden"
#

CREATE TABLE `room_garden` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `room` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '房间名称',
  `area` float(11,2) DEFAULT NULL COMMENT '房间面积',
  `status` int(1) DEFAULT NULL COMMENT '房间状态，0为空闲，1为入驻',
  `company_id` int(11) DEFAULT NULL COMMENT '入驻企业id',
  `company_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '入驻企业名称',
  `building_id` int(11) DEFAULT NULL COMMENT '所属房区id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

#
# Data for table "room_garden"
#

INSERT INTO `room_garden` VALUES (1,'1#B101',12344.00,1,NULL,NULL,1),(2,'1#B102',200.00,1,NULL,NULL,1),(3,'1#B103',100.10,1,NULL,NULL,2);

#
# Structure for table "service_application"
#

CREATE TABLE `service_application` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务申请表id',
  `maintain_id` int(11) DEFAULT NULL COMMENT '服务Id',
  `date` date DEFAULT NULL COMMENT '预约时间',
  `status` int(1) DEFAULT '0' COMMENT '状态',
  `c_Id` int(11) DEFAULT NULL COMMENT '企业Id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='服务申请表';

#
# Data for table "service_application"
#

INSERT INTO `service_application` VALUES (2,1,'2019-12-09',0,2);
