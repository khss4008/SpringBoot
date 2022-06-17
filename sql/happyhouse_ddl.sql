drop database if exists happyhouse;

create database happyhouse;

use happyhouse;

CREATE TABLE `sidocode` (
  `sidoCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sidoCode`),
  UNIQUE KEY `sidoName` (`sidoName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `guguncode` (
  `gugunCode` varchar(10) NOT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`gugunCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dongcode` (
  `dongCode` varchar(10) NOT NULL,
  `sidoName` varchar(30) DEFAULT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`dongCode`),
  KEY `sidoName` (`sidoName`),
  CONSTRAINT `dongcode_ibfk_1` FOREIGN KEY (`sidoName`) REFERENCES `sidocode` (`sidoName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `userinfo` (
  `uid` int unsigned NOT NULL AUTO_INCREMENT,
  `userId` varchar(45) NOT NULL,
  `userPwd` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `userAddr` varchar(45) DEFAULT NULL,
  `userTel` varchar(45) DEFAULT NULL,
  `role` varchar(30) NOT NULL DEFAULT (_utf8mb4'user'),
  `joinDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid_UNIQUE` (`uid`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `userlife` (
  `lid` int not null auto_increment,
  `uid` int DEFAULT NULL,
  `locationName` varchar(20) DEFAULT NULL,
  `lat` decimal(17,14) DEFAULT NULL,
  `lng` decimal(12,8) DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `baseaddress` (
  `no` int NOT NULL AUTO_INCREMENT,
  `sidoName` varchar(30) DEFAULT NULL,
  `gugunName` varchar(30) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  `dongCode` varchar(10) DEFAULT NULL,
  `lat` varchar(20) DEFAULT NULL,
  `lng` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `dongCode` (`dongCode`),
  CONSTRAINT `baseaddress_ibfk_1` FOREIGN KEY (`dongCode`) REFERENCES `dongcode` (`dongCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3597 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `houseinfo` (
  `aptCode` int NOT NULL,
  `aptName` varchar(50) DEFAULT NULL,
  `dongCode` varchar(10) DEFAULT NULL,
  `dongName` varchar(30) DEFAULT NULL,
  `buildYear` int DEFAULT NULL,
  `jibun` varchar(30) DEFAULT NULL,
  `lat` varchar(30) DEFAULT NULL,
  `lng` varchar(30) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  KEY `dongCode` (`dongCode`),
  CONSTRAINT `houseinfo_ibfk_1` FOREIGN KEY (`dongCode`) REFERENCES `dongcode` (`dongCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `housedeal` (
  `no` int NOT NULL AUTO_INCREMENT,
  `aptCode` int DEFAULT NULL,
  `dealAmount` varchar(20) DEFAULT NULL,
  `dealYear` int DEFAULT NULL,
  `dealMonth` int DEFAULT NULL,
  `dealDay` int DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  `floor` varchar(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `rentMoney` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `aptCode` (`aptCode`),
  CONSTRAINT `housedeal_ibfk_1` FOREIGN KEY (`aptCode`) REFERENCES `houseinfo` (`aptCode`)
) ENGINE=InnoDB AUTO_INCREMENT=68865 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `notice` (
  `nid` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `regtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uid` int DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


commit;