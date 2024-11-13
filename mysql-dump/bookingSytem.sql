CREATE DATABASE  IF NOT EXISTS `booking_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `booking_system`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: booking_system
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `class_id` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `check_in` tinyint DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `package_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  `required_credit` int DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `available_slots` int DEFAULT NULL,
  `max_slots` int DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `package_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'Yoga Class 1 hr SG',2,'2024-11-14 03:55:00','2024-11-12 15:55:46',3,4,'2024-11-12 15:55:46','2024-11-12 15:55:46',5),(4,'Yoga Class 1 hr SG',2,'2024-11-12 15:55:46','2024-11-12 15:55:46',5,5,'2024-11-13 13:06:24','2024-11-12 15:55:46',1),(5,'Intermdiate Class 1 hr MM',1,'2024-11-13 22:30:00','2024-11-13 22:30:00',5,3,'2024-11-13 15:07:41','2024-11-13 15:07:41',1),(6,'Intermdiate Class 1 hr Malay',1,'2024-11-12 06:30:00','2024-11-12 06:30:00',5,3,'2024-11-14 00:55:26','2024-11-14 00:55:26',8);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Myanmar','2024-11-12 13:05:25','2024-11-12 13:05:25'),(2,'Singapore','2024-11-12 13:05:25','2024-11-12 13:05:25'),(3,'Malay','2024-11-14 00:43:01','2024-11-14 00:43:01');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packaged`
--

DROP TABLE IF EXISTS `packaged`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packaged` (
  `id` int NOT NULL AUTO_INCREMENT,
  `package_name` varchar(255) DEFAULT NULL,
  `credit` int DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `country_id_idx` (`country_id`),
  CONSTRAINT `country_id` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packaged`
--

LOCK TABLES `packaged` WRITE;
/*!40000 ALTER TABLE `packaged` DISABLE KEYS */;
INSERT INTO `packaged` VALUES (1,'Basic Package SG',5,20,'5 credits for SG','2024-11-01','2024-11-12 15:55:46','2024-11-12 15:20:51',2),(5,'Premium Package MM',10,35,'10 credits for MM','2024-11-01','2024-11-12 15:55:46','2024-11-12 15:55:46',1),(6,'Basic Package MM',5,20,'5 credits for MM','2024-11-01','2024-11-13 10:33:08','2024-11-12 15:55:46',1),(7,'Premium Package SG',10,35,'10 credits for SG','2024-11-01','2024-11-13 10:33:08','2024-11-12 15:55:46',2),(8,'Premium Package Malay',5,20,'5 credits for Singapore','2024-11-01','2024-11-14 00:43:46','2024-11-14 00:46:25',3);
/*!40000 ALTER TABLE `packaged` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `package_id` int DEFAULT NULL,
  `remaining_credit` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,14,5,5,'Active','2024-11-13 10:19:50','2024-11-13 10:19:50','2024-11-01'),(4,14,6,5,'Active','2024-11-13 11:16:39','2024-11-13 11:16:39','2024-11-01'),(6,15,6,5,'Active','2024-11-13 12:52:30','2024-11-13 12:52:30','2024-11-01'),(9,15,5,6,'Active','2024-11-13 13:55:07','2024-11-13 22:07:36','2024-11-01'),(12,16,5,8,'Active','2024-11-13 22:41:50','2024-11-14 02:13:49','2024-11-01'),(13,17,8,4,'Active','2024-11-14 01:01:39','2024-11-14 01:04:31','2024-11-01');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_verified` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user','$2a$10$R/t6x5OIJpFsY9/zyB15au3LoJRROBwNNyx2Eftx9ygivx3b0wGcC','Active',1),(2,'admin','$2a$10$smBLc9NHfa5Gm/1MGbmSBOB7sBLhr.bHKpilbKO/qVFPB/cC3lRWO','Active',1),(3,'master','$2a$10$X330qJlh/hE5qNef52YVXuaywGX2Av32BOaWzDEplemnbgIdPwmea','Active',1),(4,'thandar@gmail.com','$2a$10$6lMBKHbPBRx0CKMmTSTdN.uno.jSBg3W6wq7eE6U7uRerarezqbJq','Active',1),(15,'tda@gmail.com','$2a$10$fvdKIL2ZMkrcnVY7N5fdeeIJ4hDv08Zhu7ARwTOacd0BRc3.T7YJu','Active',1),(16,'dardar@gmail.com','$2a$10$pT7CELiUQx./4SbWo48GWuPILSSJfo.J2IvLGzuMoVUtvwOJVM1pW','Active',1),(17,'maynine@gmail.com','$2a$10$Tg5XOQCHl1G1v3thBEZ5AOQPsZ30eIZP72B/DBcSq6u6A..s6SAai','Active',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-14  2:50:54
