/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.2-m12 : Database - elec_equip
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`elec_equip` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `elec_equip`;

/*Table structure for table `peq` */

DROP TABLE IF EXISTS `peq`;

CREATE TABLE `peq` (
  `eqID` int(11) NOT NULL AUTO_INCREMENT,
  `eqName` varchar(50) DEFAULT NULL,
  `eqYear` date DEFAULT NULL,
  `eqType` varchar(50) DEFAULT NULL,
  `eqPact` varchar(250) DEFAULT NULL,
  `eqParam` varchar(250) DEFAULT NULL,
  `eqBugN` int(11) DEFAULT NULL,
  `eqRepairN` int(11) DEFAULT NULL,
  `eqState` varchar(250) DEFAULT NULL,
  `eqInfo` varchar(250) DEFAULT NULL,
  `eqVender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`eqID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `peq` */

insert  into `peq`(`eqID`,`eqName`,`eqYear`,`eqType`,`eqPact`,`eqParam`,`eqBugN`,`eqRepairN`,`eqState`,`eqInfo`,`eqVender`) values (1,'HD001','2015-01-14','重型机械','lrb:2015-02-24 10:35','参数',2,1,'良','简介无','太平洋舰队'),(2,'HD002','2015-01-14','重型机械','lrb:2015-02-24 10:35','参数',2,1,'良','简介无','太平洋舰队'),(3,'HD003','2015-01-14','重型机械','lrb:2015-02-24 10:35','参数',2,1,'良','简介无','太平洋舰队'),(4,'HD004','2015-01-14','重型机械','lrb:2015-02-24 10:35','参数',2,1,'良','简介无','太平洋舰队'),(5,'HD005','2015-01-14','重型机械','lrb:2015-02-24 10:35','参数',2,1,'良','简介无','太平洋舰队'),(7,'HD005','2015-01-05','重型机械','lrb:2015-02-24 10:35','参数',0,0,'不知道','简介无','太平洋舰队'),(8,'HD002','2015-01-07','不知道','lrb:2015-02-24 10:35','谁知道',0,0,NULL,'dd','火车头'),(9,'HD001','2015-01-05','不知道','lrb:2015-02-24 10:35','谁知道',0,0,NULL,'aa','太阳'),(10,'HD003','2015-01-07','不知道','lrb:2015-02-24 10:35','谁知道',0,0,NULL,'dd','太平洋舰队'),(11,'HD004','2015-01-26','消耗型','lrb:2015-02-24 10:35','额定电压：220，电流10A',0,0,NULL,'指示器','大日本帝国'),(12,'type','2015-02-01','得到','lrb:2015-02-24 10:35','不知道',0,0,NULL,'不知道','战斗民族');

/*Table structure for table `peqrecord` */

DROP TABLE IF EXISTS `peqrecord`;

CREATE TABLE `peqrecord` (
  `eqRecordID` int(11) NOT NULL AUTO_INCREMENT,
  `eqID` int(11) DEFAULT NULL,
  `eqBugtime` datetime DEFAULT NULL,
  `eqBugcase` varchar(250) DEFAULT NULL,
  `eqBugtype` varchar(250) DEFAULT NULL,
  `EqRepairtime` datetime DEFAULT NULL,
  `EqRepaircase` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`eqRecordID`),
  KEY `FK_record_eq` (`eqID`),
  CONSTRAINT `FK_record_eq` FOREIGN KEY (`eqID`) REFERENCES `peq` (`eqID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `peqrecord` */

insert  into `peqrecord`(`eqRecordID`,`eqID`,`eqBugtime`,`eqBugcase`,`eqBugtype`,`EqRepairtime`,`EqRepaircase`) values (1,1,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-12 00:00:00','已修好'),(2,1,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-24 12:01:56','已修好'),(3,1,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-12 00:00:00','已修好'),(4,1,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-24 00:00:00','已修好'),(5,1,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-12 00:00:00','已修好'),(6,1,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-12 00:00:00','已修好'),(7,1,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-24 12:49:22','已修好'),(8,1,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-12 00:00:00','已修好'),(9,2,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-12 00:00:00','已修好'),(10,2,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-27 09:22:29','已报废'),(11,2,'2015-01-11 16:03:32','不知道','搞不懂',NULL,NULL),(12,2,'2015-01-11 16:03:32','不知道','搞不懂','2015-02-24 12:53:46','已报废'),(13,2,'2015-01-11 16:03:32','不知道','搞不懂',NULL,NULL),(14,2,'2015-01-11 16:03:32','不知道','搞不懂',NULL,NULL),(15,2,'2015-01-11 16:03:32','不知道','搞不懂',NULL,NULL),(16,2,'2015-01-11 16:03:32','不知道','搞不懂',NULL,NULL),(17,3,'2015-01-13 21:03:08','谁知道','爱咋咋','2015-02-27 09:07:29','已报废'),(18,3,'2015-01-21 00:00:00','谁知道','爱咋咋',NULL,NULL);

/*Table structure for table `pmenu` */

DROP TABLE IF EXISTS `pmenu`;

CREATE TABLE `pmenu` (
  `menuID` int(11) NOT NULL AUTO_INCREMENT,
  `PID` int(11) DEFAULT NULL,
  `Iconcls` varchar(50) DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  `url` varchar(250) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `iconSkin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menuID`),
  KEY `FK_menu_menu` (`PID`),
  CONSTRAINT `FK_menu_menu` FOREIGN KEY (`PID`) REFERENCES `pmenu` (`menuID`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

/*Data for the table `pmenu` */

insert  into `pmenu`(`menuID`,`PID`,`Iconcls`,`text`,`url`,`type`,`iconSkin`) values (1,NULL,'','采购管理','',0,'pIcon01'),(2,NULL,'','台账管理','',0,'pIcon01'),(3,NULL,'','库存管理','',0,'pIcon01'),(4,NULL,'','故障管理','',0,'pIcon01'),(5,NULL,'','维修管理','',0,'pIcon01'),(6,NULL,'','系统管理','',0,'pIcon01'),(7,NULL,'','资料管理','',0,'pIcon01'),(8,NULL,'','权限管理','',0,'pIcon01'),(11,1,'icon-search','申请查询页面','/pages_purchase_search.action',0,'icon01'),(12,1,'icon-edit','下单页面','/pages_purchase_submit.action',0,'icon02'),(14,1,'icon-edit','合同签订页面','/pages_purchase_dealsign.action',0,'icon03'),(16,1,'icon-ok','设备验收页面','/pages_purchase_equipcheck.action',0,'icon04'),(17,1,'icon-sum','采购统计页面','/pages_purchase_purchase.action',0,'icon06'),(19,2,'icon-search','设备查询页面','/pages_ledger_search.action',0,'icon05'),(22,3,'icon-print','出库统计页面','/pages_repertory_outList.action',0,'icon02'),(23,3,'icon-sum','库存统计页面','/pages_repertory_inList.action',0,'icon03'),(25,4,'icon-search','故障查询','/pages_fault_search.action',0,'icon04'),(26,6,'icon-ok','数据备份页面','/pages_sys_databack.action',0,'icon06'),(31,6,'icon-search','查询用户页面','/pages_sys_usersearch.action',0,'icon05'),(32,6,'icon-reload','数据还原页面','/pages_sys_datarestore.action',0,'icon04'),(33,7,'icon-add','资料录入','/pages_data_in.action',0,'icon03'),(34,7,'icon-search','资料查询','/pages_data_search.action',0,'icon02'),(38,8,'icon-search','查看角色','/pages_permission_rolesearch.action',0,'icon03'),(39,5,'icon-search','设备维修查询页面','/pages_fix_search.action',0,'icon04'),(40,5,'icon-edit','设备维修页面','/pages_fix_fixplan.action',0,'icon05'),(42,11,'','采购申请','/selectAction!apply.action',1,NULL),(43,11,'','审批','/selectAction!approveOk.action',1,NULL),(45,6,'','登录','/userAction!login.action',1,NULL),(46,6,'','退出','/userAction!logout.action',1,NULL),(47,6,'','获得可操作的菜单','/menuAction!getAllTreeNodeWithRight.action',1,NULL),(48,31,'','用户查询','/userAction!usersearch.action',1,NULL),(49,25,'','新增故障记录','/eqRecordAction!add.action',1,NULL),(50,25,'','设备记录查询','/eqRecordAction!search.action',1,NULL),(51,19,'','查询所有设备','/eqAction!findAllEq.action',1,NULL),(52,19,'','查询设备','/eqAction!search.action',1,NULL),(53,19,'','设备删除','/eqAction!remove.action',1,NULL),(54,19,'','查询设备','/eqAction!search.action',1,NULL),(55,19,'','查询设备履历','/eqAction!searchRecord.action',1,NULL),(56,38,'','角色添加菜单','/roleAction!grantmenu.action',1,NULL),(57,38,'','角色添加','/roleAction!roleadd.action',1,NULL),(58,38,'','角色查询','/roleAction!search.action',1,NULL),(59,38,'','角色删除','/roleAction!remove.action',1,NULL),(60,38,'','查询菜单树','/menuAction!getAllTreeNodeChecked.action',1,NULL),(61,14,'','合同签订','/selectAction!dealsign.action',1,NULL),(62,14,'','查询待签订的合同','/selectAction!searchForSign.action',1,NULL),(63,16,'','查询待审查的记录','/selectAction!searchForCheck.action',1,NULL),(64,16,'','设备验收','/selectAction!equipCheck.action',1,NULL),(65,17,'','查询采购统计','/selectAction!searchForPurchase.action',1,NULL),(67,11,'','采购查询','/selectAction!search.action',1,NULL),(68,11,'','申请取消','/selectAction!remove.action',1,NULL),(70,12,'','下单操作','/selectAction!submitList.action',1,NULL),(71,12,'','查找待下单的申请','/selectAction!searchForSubmit.action',1,NULL),(72,23,'','出库','/storageAction!outputData.action',1,NULL),(73,22,'','入库查询','/storageAction!inList.action',1,NULL),(74,23,'','出库查询','/storageAction!outList.action',1,NULL),(75,22,'','入库','/storageAction!inputData.action',1,NULL),(76,31,'','添加用户','/userAction!useradd.action',1,NULL),(77,31,'','用户授权','/userAction!grantrole.action',1,NULL),(78,31,'','用户更新','/userAction!userupdate.action',1,NULL),(80,31,'','用户删除','/userAction!remove.action',1,NULL),(81,31,'','获取角色树','/roleAction!getAllTreeNodeChecked.action',1,NULL),(82,40,'','按照类型查询故障','/eqRecordAction!searchByType.action',1,NULL),(83,40,'','设备维修','/eqRecordAction!fix.action',1,NULL),(84,39,'','查询设备维修信息','/eqRecordAction!searchFix.action',1,NULL),(85,6,'','修改密码','/userAction!editpassword.action',1,NULL),(86,39,'','设备预警','/eqRecordAction!searchWarning.action',1,NULL);

/*Table structure for table `prole` */

DROP TABLE IF EXISTS `prole`;

CREATE TABLE `prole` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  `roleDesc` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `prole` */

insert  into `prole`(`roleID`,`roleName`,`roleDesc`) values (1,'管理员','拥有所有的权限'),(2,'财务','管账的'),(3,'员工','打工的'),(4,'行政','面子公程');

/*Table structure for table `pselect` */

DROP TABLE IF EXISTS `pselect`;

CREATE TABLE `pselect` (
  `selectID` int(11) NOT NULL AUTO_INCREMENT,
  `selectName` varchar(50) DEFAULT NULL,
  `selectYear` date DEFAULT NULL,
  `selectType` varchar(50) DEFAULT NULL,
  `selectParam` varchar(250) DEFAULT NULL,
  `selectInfo` varchar(250) DEFAULT NULL,
  `selectApprove` varchar(50) DEFAULT NULL,
  `selectAccept` varchar(50) DEFAULT NULL,
  `selectStat` varchar(50) DEFAULT NULL,
  `selectVender` varchar(50) DEFAULT NULL,
  `SelectCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`selectID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `pselect` */

insert  into `pselect`(`selectID`,`selectName`,`selectYear`,`selectType`,`selectParam`,`selectInfo`,`selectApprove`,`selectAccept`,`selectStat`,`selectVender`,`SelectCount`) values (1,'HD001','2015-01-05','消耗型','额定电压：220，电流10A','热水器','liuruibin','wh','liuruibin:2015-01-06 13:17','太阳',34),(2,'HD002','2015-01-07','消耗型','额定电压：220，电流10A','起重机','liuruibin','liuruibin','liuruibin:2014-12-28 13:15','火车头',3),(3,'HD003','2015-01-07','消耗型','额定电压：220，电流10A','轮渡','liuruibin','lrb','wh:2015-01-21 08:12','太平洋舰队',55),(4,'HD004','2015-01-26','消耗型','额定电压：220，电流10A','指示器','liuruibin','lrb','lrb:2015-02-17 11:35','大日本帝国',50),(5,'HD005','2015-01-26','消耗型','额定电压：220，电流10A','电磁炉','lrb',NULL,NULL,NULL,NULL),(7,'HD008','2015-01-06','消耗型','额定电压：220，电流10A','热水器','wh',NULL,NULL,'大日本帝国',20),(9,'HD006','2015-01-07','消耗型','额定电压：220，电流10A','水泵',NULL,NULL,NULL,NULL,NULL),(10,'hd550','2015-01-04','太阳能','大机械','热水器',NULL,NULL,NULL,NULL,NULL),(12,'HD897','2015-02-10','不知道','不知道','不知道','lrb',NULL,NULL,NULL,NULL),(13,'type','2015-02-01','得到','不知道','不知道','lrb','lrb','lrb:2015-02-24 10:35','战斗民族',2),(14,'HD809','2015-02-12','一次性','高压工作','没有收录','lrb',NULL,NULL,NULL,NULL);

/*Table structure for table `pstorage` */

DROP TABLE IF EXISTS `pstorage`;

CREATE TABLE `pstorage` (
  `storageID` int(11) NOT NULL AUTO_INCREMENT,
  `eqNumb` int(11) DEFAULT NULL,
  `storageNumb` int(11) DEFAULT NULL,
  `storageParam` varchar(250) DEFAULT NULL,
  `storageW` varchar(250) DEFAULT NULL,
  `storageS` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`storageID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `pstorage` */

insert  into `pstorage`(`storageID`,`eqNumb`,`storageNumb`,`storageParam`,`storageW`,`storageS`) values (1,2,0,'设备一','2','2'),(2,3,0,'设备二','3','4'),(3,3,0,'设备二','3','4'),(4,3,34,'设备二','3','4'),(5,3,2,'设备二','3','4'),(6,3,2,'设备二','3','4'),(7,3,2,'设备二','3','4'),(8,3,2,'设备二','3','4'),(9,3,2,'设备二','3','4'),(10,3,2,'设备二','3','4'),(11,3,2,'设备二','3','4'),(12,3,2,'设备二','3','4'),(13,3,2,'设备二','3','4'),(14,3,2,'设备二','3','4'),(15,3,2,'设备二','3','4'),(16,3,2,'设备二','3','4'),(17,3,2,'设备二','3','4');

/*Table structure for table `puser` */

DROP TABLE IF EXISTS `puser`;

CREATE TABLE `puser` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `userPwd` varchar(50) DEFAULT NULL,
  `userAble` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `puser` */

insert  into `puser`(`userID`,`username`,`userPwd`,`userAble`,`birthday`,`telephone`) values (1,'lrb','202cb962ac59075b964b07152d234b70','111','1992-08-04','18235149661'),(2,'Admin','202cb962ac59075b964b07152d234b70',NULL,'1992-08-04',NULL),(3,'wh','202cb962ac59075b964b07152d234b70',NULL,'1990-08-08',NULL),(4,'cr','202cb962ac59075b964b07152d234b70',NULL,'2015-02-02',NULL),(5,'hy','e10adc3949ba59abbe56e057f20f883e',NULL,'2015-01-25',NULL),(6,'lpf','202cb962ac59075b964b07152d234b70',NULL,'2014-12-28',NULL),(8,'中文是否乱码','202cb962ac59075b964b07152d234b70',NULL,'2015-02-08',NULL),(9,'我再修改一次','202cb962ac59075b964b07152d234b70',NULL,'1991-01-22',NULL);

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `roleID` int(11) NOT NULL,
  `menuID` int(11) NOT NULL,
  PRIMARY KEY (`roleID`,`menuID`),
  KEY `FK_tew6oxlyq7309msysrnxckfnl` (`menuID`),
  KEY `FK_klxydlepdccmon9fr0xt9pu8t` (`roleID`),
  CONSTRAINT `FK_klxydlepdccmon9fr0xt9pu8t` FOREIGN KEY (`roleID`) REFERENCES `prole` (`roleID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tew6oxlyq7309msysrnxckfnl` FOREIGN KEY (`menuID`) REFERENCES `pmenu` (`menuID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`roleID`,`menuID`) values (1,1),(2,1),(3,1),(4,1),(1,2),(2,2),(3,2),(1,3),(3,3),(1,4),(3,4),(1,5),(1,6),(2,6),(3,6),(4,6),(1,7),(4,7),(1,8),(1,11),(2,11),(3,11),(1,12),(1,14),(1,16),(1,17),(2,17),(4,17),(1,19),(2,19),(3,19),(1,22),(3,22),(1,23),(3,23),(1,25),(3,25),(1,26),(1,31),(3,31),(1,32),(1,33),(4,33),(1,34),(4,34),(1,38),(1,39),(1,40),(1,42),(3,42),(1,43),(3,43),(1,45),(2,45),(3,45),(4,45),(1,46),(2,46),(3,46),(4,46),(1,47),(2,47),(3,47),(4,47),(1,48),(3,48),(1,49),(3,49),(1,50),(3,50),(1,51),(2,51),(3,51),(1,52),(2,52),(3,52),(1,53),(2,53),(3,53),(1,54),(2,54),(3,54),(1,55),(2,55),(3,55),(1,56),(1,57),(1,58),(1,59),(1,60),(1,61),(1,62),(1,63),(1,64),(1,65),(2,65),(4,65),(1,67),(2,67),(3,67),(1,68),(3,68),(1,70),(1,71),(1,72),(3,72),(1,73),(3,73),(1,74),(3,74),(1,75),(3,75),(1,76),(1,77),(1,78),(1,80),(1,81),(1,82),(1,83),(1,84),(1,85),(2,85),(3,85),(4,85),(1,86);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `roleID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  PRIMARY KEY (`roleID`,`userID`),
  KEY `FK_21ngqfdstku9fm4p79h2uffso` (`userID`),
  KEY `FK_26dex4seu9pptspjfolsmjeob` (`roleID`),
  CONSTRAINT `FK_21ngqfdstku9fm4p79h2uffso` FOREIGN KEY (`userID`) REFERENCES `puser` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_26dex4seu9pptspjfolsmjeob` FOREIGN KEY (`roleID`) REFERENCES `prole` (`roleID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`roleID`,`userID`) values (1,1),(1,2),(3,3),(2,4),(3,5),(4,6),(2,8),(2,9);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
