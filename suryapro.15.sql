/*
SQLyog Community
MySQL - 10.1.26-MariaDB : Database - db_suryapro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_suryapro` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_suryapro`;

/*Table structure for table `filterkat_mitra` */

DROP TABLE IF EXISTS `filterkat_mitra`;

CREATE TABLE `filterkat_mitra` (
  `ID_FIMIT` varchar(12) NOT NULL,
  `ID_MITRA` varchar(12) DEFAULT NULL,
  `ID_PELAYANAN` varchar(12) DEFAULT NULL,
  `ID_SAPRONAK` varchar(12) DEFAULT NULL,
  `ID_PEMBAYARAN` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`ID_FIMIT`),
  KEY `FK_REFERENCE_5` (`ID_PELAYANAN`),
  KEY `FK_REFERENCE_6` (`ID_SAPRONAK`),
  KEY `FK_REFERENCE_7` (`ID_PEMBAYARAN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `filterkat_mitra` */

insert  into `filterkat_mitra`(`ID_FIMIT`,`ID_MITRA`,`ID_PELAYANAN`,`ID_SAPRONAK`,`ID_PEMBAYARAN`) values 
('FM001','KD001','MP001','MS001','MB001'),
('FM002','KD003','MP002','MS002','MB002'),
('FM003','KD007','MP003','MS003','MB003'),
('FM004','KD011','MP004','MS004','MB004');

/*Table structure for table `filterkat_peternak` */

DROP TABLE IF EXISTS `filterkat_peternak`;

CREATE TABLE `filterkat_peternak` (
  `ID_FILPET` char(11) NOT NULL,
  `ID_PETER` varchar(12) DEFAULT NULL,
  `ID_PTRNK` char(11) DEFAULT NULL,
  `ID_FASILITAS` char(11) DEFAULT NULL,
  `ID_KAPS` char(11) DEFAULT NULL,
  PRIMARY KEY (`ID_FILPET`),
  KEY `FK_REFERENCE_1` (`ID_PTRNK`),
  KEY `FK_REFERENCE_2` (`ID_FASILITAS`),
  KEY `FK_REFERENCE_3` (`ID_KAPS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `filterkat_peternak` */

insert  into `filterkat_peternak`(`ID_FILPET`,`ID_PETER`,`ID_PTRNK`,`ID_FASILITAS`,`ID_KAPS`) values 
('FP001','KD002','PP001','PF001','PK001'),
('FP002','KD004','PP002','PF002','PK002'),
('FP003','KD005','PP003','PF003','PK003'),
('FP004','KD006','PP004','PF004','PK004'),
('FP005','KD008','PP005','PF005','PK005'),
('FP006','KD009','PP006','PF006','PK006'),
('FP007','KD010','PP007','PF007','PK007');

/*Table structure for table `fkm_pelayanan` */

DROP TABLE IF EXISTS `fkm_pelayanan`;

CREATE TABLE `fkm_pelayanan` (
  `ID_PELAYANAN` varchar(12) NOT NULL,
  `PPL_PN` varchar(12) DEFAULT NULL,
  `KESEHATAN_PN` varchar(12) DEFAULT NULL,
  `CHECK_PN` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`ID_PELAYANAN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fkm_pelayanan` */

insert  into `fkm_pelayanan`(`ID_PELAYANAN`,`PPL_PN`,`KESEHATAN_PN`,`CHECK_PN`) values 
('MP001','3','3','1'),
('MP002','3','3','1'),
('MP003','3','3','1'),
('MP004',NULL,NULL,NULL);

/*Table structure for table `fkm_pembayaran` */

DROP TABLE IF EXISTS `fkm_pembayaran`;

CREATE TABLE `fkm_pembayaran` (
  `ID_PEMBAYARAN` varchar(11) NOT NULL,
  `JML_MODAL` varchar(11) DEFAULT NULL,
  `KOMISI_MODAL` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PEMBAYARAN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fkm_pembayaran` */

insert  into `fkm_pembayaran`(`ID_PEMBAYARAN`,`JML_MODAL`,`KOMISI_MODAL`) values 
('MB001','1','1'),
('MB002','3','1'),
('MB003','3','3'),
('MB004',NULL,NULL);

/*Table structure for table `fkm_sapronak` */

DROP TABLE IF EXISTS `fkm_sapronak`;

CREATE TABLE `fkm_sapronak` (
  `ID_SAPRONAK` varchar(12) NOT NULL,
  `DOC_SAP` varchar(12) DEFAULT NULL,
  `PAKAN_SAP` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`ID_SAPRONAK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fkm_sapronak` */

insert  into `fkm_sapronak`(`ID_SAPRONAK`,`DOC_SAP`,`PAKAN_SAP`) values 
('MS001','1','1'),
('MS002','3','2'),
('MS003','3','2'),
('MS004',NULL,NULL);

/*Table structure for table `fkp_fasilitas` */

DROP TABLE IF EXISTS `fkp_fasilitas`;

CREATE TABLE `fkp_fasilitas` (
  `ID_FASILITAS` char(11) NOT NULL,
  `FKP_SUHU` varchar(22) DEFAULT NULL,
  `FKP_TMPTMKN` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`ID_FASILITAS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fkp_fasilitas` */

insert  into `fkp_fasilitas`(`ID_FASILITAS`,`FKP_SUHU`,`FKP_TMPTMKN`) values 
('PF001','2','1'),
('PF002','3','4'),
('PF003','2','3'),
('PF004','1','1'),
('PF005',NULL,NULL),
('PF006',NULL,NULL),
('PF007','2','3');

/*Table structure for table `fkp_kandang` */

DROP TABLE IF EXISTS `fkp_kandang`;

CREATE TABLE `fkp_kandang` (
  `ID_KAPS` char(11) NOT NULL,
  `KAPASITAS_KAPS` char(25) DEFAULT NULL,
  `JENIS_KAPS` varchar(22) DEFAULT NULL,
  `JML_KAPS` char(22) DEFAULT NULL,
  PRIMARY KEY (`ID_KAPS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fkp_kandang` */

insert  into `fkp_kandang`(`ID_KAPS`,`KAPASITAS_KAPS`,`JENIS_KAPS`,`JML_KAPS`) values 
('PK001','3','2','3'),
('PK002','2','2','1'),
('PK003','5','1','2'),
('PK004','5','1','3'),
('PK005',NULL,NULL,NULL),
('PK006',NULL,NULL,NULL),
('PK007','4','1','1');

/*Table structure for table `fkp_peternak` */

DROP TABLE IF EXISTS `fkp_peternak`;

CREATE TABLE `fkp_peternak` (
  `ID_PTRNK` char(11) NOT NULL,
  `PENGALAMAN_PTRNK` char(12) DEFAULT NULL,
  `JMLTNG_PTRNK` char(22) DEFAULT NULL,
  PRIMARY KEY (`ID_PTRNK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fkp_peternak` */

insert  into `fkp_peternak`(`ID_PTRNK`,`PENGALAMAN_PTRNK`,`JMLTNG_PTRNK`) values 
('PP001','2','2'),
('PP002','2','3'),
('PP003','4','1'),
('PP004','4','3'),
('PP005',NULL,NULL),
('PP006',NULL,NULL),
('PP007','1','1');

/*Table structure for table `ideal_mitra` */

DROP TABLE IF EXISTS `ideal_mitra`;

CREATE TABLE `ideal_mitra` (
  `kideal1` char(3) DEFAULT NULL,
  `kideal2` char(3) DEFAULT NULL,
  `kideal3` char(3) DEFAULT NULL,
  `kideal4` char(3) DEFAULT NULL,
  `kideal5` char(3) DEFAULT NULL,
  `kideal6` char(3) DEFAULT NULL,
  `kideal7` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ideal_mitra` */

insert  into `ideal_mitra`(`kideal1`,`kideal2`,`kideal3`,`kideal4`,`kideal5`,`kideal6`,`kideal7`) values 
('5','1','1','1','1','1','3');

/*Table structure for table `ideal_peternak` */

DROP TABLE IF EXISTS `ideal_peternak`;

CREATE TABLE `ideal_peternak` (
  `kideal1` char(3) DEFAULT NULL,
  `kideal2` char(3) DEFAULT NULL,
  `kideal3` char(3) DEFAULT NULL,
  `kideal4` char(3) DEFAULT NULL,
  `kideal5` char(3) DEFAULT NULL,
  `kideal6` char(3) DEFAULT NULL,
  `kideal7` char(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ideal_peternak` */

insert  into `ideal_peternak`(`kideal1`,`kideal2`,`kideal3`,`kideal4`,`kideal5`,`kideal6`,`kideal7`) values 
('2','2','1','1','1','2','2');

/*Table structure for table `mitra_master` */

DROP TABLE IF EXISTS `mitra_master`;

CREATE TABLE `mitra_master` (
  `ID_MITRA` varchar(11) NOT NULL,
  `NAMA_MITRA` varchar(33) DEFAULT NULL,
  `ALAMAT_MITRA` text,
  `CONTACT_MITRA` char(14) DEFAULT NULL,
  `THN_BERDIRI` char(15) DEFAULT NULL,
  `JMT_MITRA` char(10) DEFAULT NULL,
  `JMB_MITRA` char(10) DEFAULT NULL,
  `VENDOR_MITRA` text,
  `FT_MITRA` varchar(44) DEFAULT NULL,
  PRIMARY KEY (`ID_MITRA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mitra_master` */

insert  into `mitra_master`(`ID_MITRA`,`NAMA_MITRA`,`ALAMAT_MITRA`,`CONTACT_MITRA`,`THN_BERDIRI`,`JMT_MITRA`,`JMB_MITRA`,`VENDOR_MITRA`,`FT_MITRA`) values 
('KD001','Derris Group','Jl simpanglima','0857222','2020-5-22','17:37','07:50','Dpgroup','DC20203725013745,jpg'),
('KD003','PT ciomas adisatwa ','jalan Kartini gang prabu anom kediri ','085334493999','1998-5-22','04:30','08:00','PT Japfa Comfeed','DC20203322183320,jpg'),
('KD007','PT. SMS Kediri','Jl. RA. Kartini 40 Pocanan','6285646433633','2000-5-1','05:00','08:00','CPIN','DC20202024142008,jpg'),
('KD011',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `peternak_master` */

DROP TABLE IF EXISTS `peternak_master`;

CREATE TABLE `peternak_master` (
  `ID_PETER` varchar(11) NOT NULL,
  `NAMA_PETER` varchar(30) DEFAULT NULL,
  `NAMA_PEMILIK` varchar(30) DEFAULT NULL,
  `ALAMAT_PETER` text,
  `THN_BERDIRI` char(30) DEFAULT NULL,
  `NOHP_PETER` char(15) DEFAULT NULL,
  `STATUS_KEMITRA` varchar(20) DEFAULT NULL,
  `STATUS_LAHAN` varchar(20) DEFAULT NULL,
  `FT_PETER` varchar(44) DEFAULT NULL,
  PRIMARY KEY (`ID_PETER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `peternak_master` */

insert  into `peternak_master`(`ID_PETER`,`NAMA_PETER`,`NAMA_PEMILIK`,`ALAMAT_PETER`,`THN_BERDIRI`,`NOHP_PETER`,`STATUS_KEMITRA`,`STATUS_LAHAN`,`FT_PETER`) values 
('KD002','Ijmal farms','ijmal djafar','kediri, kampung dalem','2020','6285744521','Bermitra','Pribadi','DC20203325013350,jpg'),
('KD004','cahyo farm','cahyono','jl semampir gg77','2005','0896555','Belum Bermitra','Sewa','DC20203922183935,jpg'),
('KD005','jawis farm','aan hanafi','tempurejo wates kab.kediri','2011','6285812271799','Bermitra','Pribadi','DC20201423151430,jpg'),
('KD006','peternakan bu marsiyem','marsiyem','ds plaosan jl mojopahit','2015','6285232708406','Bermitra','Pribadi','DC20200524110541,jpg'),
('KD008',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('KD009',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('KD010','raedi farms','raedi','dsn. tempuran ds. sidomulyo wates kediri','2015','6285755109980','Bermitra','Pribadi','DC20205824155838,jpg');

/*Table structure for table `ranking_mitra` */

DROP TABLE IF EXISTS `ranking_mitra`;

CREATE TABLE `ranking_mitra` (
  `id` char(3) DEFAULT NULL,
  `idp` char(10) DEFAULT NULL,
  `nilai` char(10) DEFAULT NULL,
  `gk1` char(5) DEFAULT NULL,
  `gk2` char(5) DEFAULT NULL,
  `gk3` char(5) DEFAULT NULL,
  `gk4` char(5) DEFAULT NULL,
  `gk5` char(5) DEFAULT NULL,
  `gk6` char(5) DEFAULT NULL,
  `gk7` char(5) DEFAULT NULL,
  `ck1` char(5) DEFAULT NULL,
  `ck2` char(5) DEFAULT NULL,
  `ck3` char(5) DEFAULT NULL,
  `ck4` char(5) DEFAULT NULL,
  `ck5` char(5) DEFAULT NULL,
  `cfkan` char(5) DEFAULT NULL,
  `cffas` char(5) DEFAULT NULL,
  `totkan` char(5) DEFAULT NULL,
  `totfas` char(5) DEFAULT NULL,
  `sk1` char(5) DEFAULT NULL,
  `sk2` char(5) DEFAULT NULL,
  `sfsdm` char(5) DEFAULT NULL,
  `totsdm` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ranking_mitra` */

insert  into `ranking_mitra`(`id`,`idp`,`nilai`,`gk1`,`gk2`,`gk3`,`gk4`,`gk5`,`gk6`,`gk7`,`ck1`,`ck2`,`ck3`,`ck4`,`ck5`,`cfkan`,`cffas`,`totkan`,`totfas`,`sk1`,`sk2`,`sfsdm`,`totsdm`) values 
('1','KD002','2.57','-2','1','2','1','0','1','-1','3','4.5','3.5','4.5','5','3.666','4.75','2.566','3.325','4.5','4','4.25','1.275'),
('2','KD004','2.23','-3','1','0','2','3','1','0','2','4.5','5','3.5','2.5','3.833','3','2.683','2.1','4.5','5','4.75','1.425'),
('3','KD005','2.67','0','0','1','1','2','3','-2','5','5','4.5','4.5','3.5','4.833','4','3.383','2.8','2.5','3','2.75','0.825'),
('4','KD006','2.87','0','0','2','0','0','3','0','5','5','3.5','5','5','4.5','5','3.15','3.5','2.5','5','3.75','1.125'),
('5','KD010','2.69','-1','0','0','1','2','0','-2','4','5','5','4.5','3.5','4.666','4','3.266','2.8','5','3','4','1.2');

/*Table structure for table `ranking_peternak` */

DROP TABLE IF EXISTS `ranking_peternak`;

CREATE TABLE `ranking_peternak` (
  `id` char(3) DEFAULT NULL,
  `idm` char(10) DEFAULT NULL,
  `nilai` char(10) DEFAULT NULL,
  `gk1` char(5) DEFAULT NULL,
  `gk2` char(5) DEFAULT NULL,
  `gk3` char(5) DEFAULT NULL,
  `gk4` char(5) DEFAULT NULL,
  `gk5` char(5) DEFAULT NULL,
  `gk6` char(5) DEFAULT NULL,
  `gk7` char(5) DEFAULT NULL,
  `ck1` char(5) DEFAULT NULL,
  `ck2` char(5) DEFAULT NULL,
  `ck3` char(5) DEFAULT NULL,
  `ck4` char(5) DEFAULT NULL,
  `cfsap` char(5) DEFAULT NULL,
  `cfmod` char(5) DEFAULT NULL,
  `totsap` char(5) DEFAULT NULL,
  `totmod` char(5) DEFAULT NULL,
  `sk1` char(5) DEFAULT NULL,
  `sk2` char(5) DEFAULT NULL,
  `sk3` char(5) DEFAULT NULL,
  `sfpel` char(5) DEFAULT NULL,
  `totpel` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `ranking_peternak` */

insert  into `ranking_peternak`(`id`,`idm`,`nilai`,`gk1`,`gk2`,`gk3`,`gk4`,`gk5`,`gk6`,`gk7`,`ck1`,`ck2`,`ck3`,`ck4`,`cfsap`,`cfmod`,`totsap`,`totmod`,`sk1`,`sk2`,`sk3`,`sfpel`,`totpel`) values 
('1','KD001','2.45','-1','-1','0','0','2','1','-1','4','4','5','5','4','5','2.4','3','3.5','4.5','4','4','1.6'),
('2','KD003','2.50','1','0','0','2','2','1','-1','4.5','5','5','3.5','4.75','4.25','2.85','2.55','3.5','4.5','4','4','1.6'),
('3','KD007','2.34','1','0','2','2','2','1','-1','4.5','5','3.5','3.5','4.75','3.5','2.85','2.1','3.5','4.5','4','4','1.6');

/*Table structure for table `user_master` */

DROP TABLE IF EXISTS `user_master`;

CREATE TABLE `user_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usr` char(22) NOT NULL,
  `usr_name` varchar(22) DEFAULT NULL,
  `usr_email` text,
  `usr_password` text,
  `type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`,`id_usr`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `user_master` */

insert  into `user_master`(`id`,`id_usr`,`usr_name`,`usr_email`,`usr_password`,`type`) values 
(1,'KD001','derris','rusilver11@gmail.com','d744ccdbe8f94a4de48399b160f13a9f','Mitra'),
(2,'KD002','Ijmal','ijmal@gmail.com','75469000b59eb0cb56769c2527082728','Peternak'),
(3,'KD003','Hafidz','Hafidz.lfc@gmail.com','85c90c2df55a3a2e4fc5a988931a3706','Mitra'),
(4,'KD004','cahyono','cahyono@gmail.com','4b882c3c7d73f98ac7b7827b31173b83','Peternak'),
(5,'KD005','hanafi','hanafiaan@gmail.com','95981dfd2dc724c3893d9756e69619ef','Peternak'),
(6,'KD006','marsiyem','marsiyem@gmail.com','3a7358d62be23c3cea5390898ae14a8b','Peternak'),
(7,'KD007','dinditaradipa','moccha94@gmail.com','2735a5301fb9806c0066eec8e1a37e64','Mitra'),
(8,'KD008','raediTEMPURAN','wicisme.reds@gmail.com','67d48aef359e39ef6a1ac5a0b057d715','Peternak'),
(9,'KD009','dumy1','dumy1@gmail.com','678cc9a69d377b948b83e0b0255c2b6e','Peternak'),
(10,'KD010','raedi','raedi@gmail.com','81802950c8aa56d7c7594fae2d82e0b4','Peternak'),
(11,'KD011','dumy2','dumy2@gmail.com','99c6ac32e19f848aaa9786e0886c73d1','Mitra'),
(12,'KD012','dumy2','dumy2@gmail.com','99c6ac32e19f848aaa9786e0886c73d1','Mitra');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
