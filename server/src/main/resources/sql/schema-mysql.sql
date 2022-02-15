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

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `book-plate` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `book-plate`;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Book` (
                        `isbn` bigint NOT NULL,
                        `createdAt` datetime(6) NOT NULL,
                        `updatedAt` datetime(6) NOT NULL,
                        `author` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `discount` bigint DEFAULT NULL,
                        `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `price` bigint NOT NULL,
                        `publisher` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        PRIMARY KEY (`isbn`)
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
                        `createdAt` datetime(6) NOT NULL,
                        `updatedAt` datetime(6) NOT NULL,
                        `allowedPrivacyTerm` tinyint NOT NULL,
                        `allowedUsedTerm` tinyint NOT NULL,
                        `birth` date NOT NULL,
                        `companyEmail` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `companyName` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        `deletedAt` datetime(6) DEFAULT NULL,
                        `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `isCompanyEmailValid` tinyint NOT NULL,
                        `nickName` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `sex` enum('M','F') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'M',
                        `state` tinyint NOT NULL,
                        `thumbnail` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                        PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserAccount`
--

DROP TABLE IF EXISTS `UserAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserAccount` (
                               `userAccountId` bigint NOT NULL AUTO_INCREMENT,
                               `createdAt` datetime(6) NOT NULL,
                               `updatedAt` datetime(6) NOT NULL,
                               `deletedAt` datetime(6) DEFAULT NULL,
                               `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `socialAccountId` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `socialAccountType` enum('KAKAO','GOOGLE','NAVER','APPLE') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `state` bit(1) NOT NULL DEFAULT b'1',
                               `userId` bigint NOT NULL,
                               PRIMARY KEY (`userAccountId`),
                               UNIQUE KEY `UK_qseudf7rceerpt5obt2pc3a8p` (`userId`),
                               CONSTRAINT `FK4qg49pla0p6g97dtuiouja1o6` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-15 23:00:00
