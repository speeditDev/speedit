-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: book-plate
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `book-plate`
--

CREATE DATABASE IF NOT EXISTS `book-plate`;

USE `book-plate`;

DROP TABLE IF EXISTS `Book`;
DROP TABLE IF EXISTS `BookCategory`;

--
-- Table structure for table `BookCategory`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BookCategory` (
                                `code` bigint NOT NULL,
                                `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `depth0` varchar(255),
                                `depth1` varchar(255),
                                `depth2` varchar(255),
                                `depth3` varchar(255),
                                `depth4` varchar(255),
                                `depth5` varchar(255),
                                `createdAt` datetime(6) NOT NULL,
                                `updatedAt` datetime(6) NOT NULL,
                                PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Book`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Book` (
                        `isbn` bigint NOT NULL,
                        `createdAt` datetime(6) NOT NULL,
                        `updatedAt` datetime(6) NOT NULL,
                        `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `discount` bigint NOT NULL,
                        `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `price` bigint NOT NULL,
                        `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `categoryCode` bigint DEFAULT NULL,
                        PRIMARY KEY (`isbn`),
                        KEY `userAccount_user_userId_fk` (`categoryCode`),
                        CONSTRAINT `FKaf93xcr9wchht6viajwunyh75` FOREIGN KEY (`categoryCode`) REFERENCES `BookCategory` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
                        `userId` bigint NOT NULL AUTO_INCREMENT,
                        `socialAccountId` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `socialAccountType` enum('KAKAO','GOOGLE','NAVER','APPLE') COLLATE utf8mb4_unicode_ci NOT NULL,
                        `nickName` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `sex` enum('M','F') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `birth` date DEFAULT NULL COMMENT '생년월일',
                        `thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '프로필 사진 URL',
                        `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '이메일',
                        `companyName` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '회사명',
                        `companyEmail` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '회사 Email',
                        `isCompanyEmailValid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '회사 이메일 인증 여부',
                        `allowedPrivacyTerm` tinyint(1) NOT NULL DEFAULT '0' COMMENT '이용약관 동의여부',
                        `allowedUsedTerm` tinyint(1) NOT NULL DEFAULT '0' COMMENT '개인정보활용동의 약관 확인 여부',
                        `state` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'created' COMMENT '상태값( "created": 사용중, "deleted": 삭제됨)',
                        `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `updatedAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `deletedAt` datetime DEFAULT NULL,
                        PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-21  1:55:24
