CREATE DATABASE  IF NOT EXISTS `carsdbcomp296` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carsdbcomp296`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: carsdbcomp296
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `cars_id` int NOT NULL AUTO_INCREMENT,
  `make` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `mileage` int NOT NULL,
  `price` double NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `date_added` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`cars_id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'Toyota','Corolla','2010',96789,5000,'mywebsite.com','2026-03-03 00:00:00.000000'),(2,'Toyota','Camry','2010',111000,5000,'siteA.html','2026-03-10 16:05:14.992000'),(3,'Toyota','Corolla','2005',95654,4500,'siteA.html','2026-03-10 16:05:14.992000'),(4,'Toyota','Tacoma','2002',215000,5500,'siteA.html','2026-03-10 16:05:14.992000'),(5,'Chevrolet','Impala','2012',76767,2500,'siteA.html','2026-03-12 11:32:34.636000'),(6,'Honda','Accord','2015',82000,12000,'siteA.html','2026-03-19 12:43:36.006000'),(7,'Nissan','Altima','2018',54000,14500,'siteA.html','2026-03-19 12:43:36.006000'),(8,'Ford','Fusion','2011',99000,7000,'siteA.html','2026-03-19 12:43:36.006000'),(9,'Hyundai','Elantra','2016',67000,11000,'siteA.html','2026-03-19 12:43:36.006000'),(10,'Kia','Optima','2014',88000,9000,'siteA.html','2026-03-19 12:43:36.006000'),(11,'Subaru','Outback','2008',142000,6500,'siteA.html','2026-03-19 12:43:36.006000'),(12,'Mazda','CX-5','2019',31000,21000,'siteA.html','2026-03-19 12:43:36.006000'),(13,'Volkswagen','Passat','2013',93000,8000,'siteA.html','2026-03-19 12:43:36.006000'),(14,'Toyota','RAV4','2020',28000,24000,'siteA.html','2026-03-19 12:43:36.006000'),(15,'Honda','Civic','2007',160000,4000,'siteA.html','2026-03-19 12:43:36.006000'),(16,'Ford','Escape','2017',45000,16000,'siteA.html','2026-03-19 12:43:36.006000'),(17,'Dodge','Charger','2012',102000,9500,'siteA.html','2026-03-19 12:43:36.006000'),(18,'BMW','328i','2010',120000,8500,'siteA.html','2026-03-19 12:43:36.006000'),(19,'Jeep','Cherokee','2018',60000,19000,'siteA.html','2026-03-19 12:43:36.006000'),(20,'Tesla','Model 3','2021',22000,32000,'siteA.html','2026-03-19 12:43:36.006000'),(21,'Chevrolet','Malibu','2016',78000,10500,'siteA.html','2026-03-19 12:43:36.006000'),(22,'Acura','TL','2009',140000,6000,'siteA.html','2026-03-19 12:43:36.006000'),(23,'Lexus','ES 350','2014',89000,15000,'siteA.html','2026-03-19 12:43:36.006000'),(24,'Nissan','Rogue','2013',95000,8500,'siteA.html','2026-03-19 12:43:36.006000'),(25,'Ford','Explorer','2006',175000,3500,'siteA.html','2026-03-19 12:43:36.006000'),(26,'Hyundai','Sonata','2011',112000,6500,'siteA.html','2026-03-19 12:48:47.397000'),(27,'Honda','CR-V','2019',35000,23000,'siteA.html','2026-03-19 12:48:47.397000'),(28,'Toyota','Highlander','2004',185000,4200,'siteA.html','2026-03-19 12:48:47.397000'),(29,'Mazda','Mazda3','2017',48000,15000,'siteA.html','2026-03-19 12:48:47.397000'),(30,'Subaru','Forester','2015',92000,13500,'siteA.html','2026-03-19 12:48:47.397000'),(31,'Kia','Sportage','2022',18000,27000,'siteA.html','2026-03-19 12:48:47.397000'),(32,'Chevrolet','Silverado','2003',210000,4800,'siteA.html','2026-03-19 12:48:47.397000'),(33,'Nissan','Sentra','2016',73000,9800,'siteA.html','2026-03-19 12:48:47.397000'),(34,'Volkswagen','Jetta','2010',125000,6200,'siteA.html','2026-03-19 12:48:47.397000'),(35,'Ford','Bronco Sport','2021',27000,31000,'siteA.html','2026-03-19 12:48:47.397000'),(36,'Honda','Pilot','2008',168000,5200,'siteA.html','2026-03-19 12:48:47.397000'),(37,'Buick','LaCrosse','2013',87000,8800,'siteA.html','2026-03-19 12:48:47.397000'),(38,'GMC','Terrain','2018',52000,18000,'siteA.html','2026-03-19 12:48:47.397000'),(39,'Lexus','RX 350','2007',155000,7000,'siteA.html','2026-03-19 12:48:47.397000'),(40,'Infiniti','G37','2012',98000,11000,'siteA.html','2026-03-19 12:48:47.397000'),(41,'Toyota','Prius','2019',41000,22000,'siteA.html','2026-03-19 12:48:47.397000'),(42,'Chrysler','300','2014',91000,9500,'siteA.html','2026-03-19 12:48:47.397000'),(43,'Pontiac','G6','2005',178000,3000,'siteA.html','2026-03-19 12:48:47.397000'),(44,'Jeep','Wrangler','2017',64000,28000,'siteA.html','2026-03-19 12:48:47.397000'),(45,'Hyundai','Tucson','2020',33000,21000,'siteA.html','2026-03-19 12:48:47.397000'),(46,'Ford','F150','2009',185000,6500,'siteB.html','2026-03-21 13:05:02.185000'),(47,'Honda','Civic','2015',85000,9000,'siteB.html','2026-03-21 13:05:02.185000'),(48,'Ford','Focus','2018',40000,12000,'siteB.html','2026-03-21 13:05:02.185000'),(49,'Nissan','Altima','2020',30000,15000,'siteB.html','2026-03-21 13:05:02.185000'),(50,'Volkswagen','Jetta','2001',145000,3300,'siteA.html','2026-03-23 16:15:29.821000'),(51,'Honda','Civic','2005',120000,4500,'siteB.html','2026-03-23 16:15:29.821000'),(52,'Pontiac','Vibe','2009',112000,4100,'siteA.html','2026-03-23 18:08:58.455000'),(53,'Dodge','Caliber','2013',87000,5900,'siteA.html','2026-03-23 18:08:58.455000'),(54,'Mitsubishi','Lancer','2006',130000,3600,'siteA.html','2026-03-23 18:08:58.455000'),(55,'Saturn','Ion','2010',99000,4700,'siteA.html','2026-03-23 18:08:58.455000'),(56,'Buick','LeSabre','2007',125000,3900,'siteA.html','2026-03-23 18:08:58.455000'),(57,'Kia','Forte','2014',72000,6400,'siteA.html','2026-03-23 18:08:58.455000'),(58,'Hyundai','Tiburon','2003',138000,3500,'siteA.html','2026-03-23 18:08:58.455000'),(59,'Nissan','Juke','2012',89000,7200,'siteA.html','2026-03-23 18:08:58.455000'),(60,'Chevrolet','Aveo','2008',115000,3900,'siteA.html','2026-03-23 18:08:58.455000'),(61,'Mazda','CX-5','2016',58000,15500,'siteA.html','2026-03-23 18:08:58.455000'),(62,'Dodge','Stratus','2002',147000,3400,'siteA.html','2026-03-23 18:08:58.455000'),(63,'Volkswagen','Jetta','2011',97000,6700,'siteA.html','2026-03-23 18:08:58.455000'),(64,'Pontiac','Grand Am','2005',128000,3700,'siteA.html','2026-03-23 18:08:58.455000'),(65,'Subaru','Legacy','2013',88000,7100,'siteA.html','2026-03-23 18:08:58.455000'),(66,'Toyota','Corolla','2008',95000,5200,'siteB.html','2026-03-23 18:08:58.455000'),(67,'Ford','Focus','2012',87000,6100,'siteB.html','2026-03-23 18:08:58.455000'),(68,'Chevrolet','Malibu','2003',130000,3700,'siteB.html','2026-03-23 18:08:58.455000'),(69,'Hyundai','Elantra','2010',102000,4900,'siteB.html','2026-03-23 18:08:58.455000'),(70,'Nissan','Sentra','2007',115000,4200,'siteB.html','2026-03-23 18:08:58.455000'),(71,'Kia','Soul','2015',68000,8800,'siteB.html','2026-03-23 18:08:58.455000'),(72,'Ford','Taurus','2004',140000,3500,'siteB.html','2026-03-23 18:08:58.455000'),(73,'Mazda','3','2013',97000,7500,'siteB.html','2026-03-23 18:08:58.455000'),(74,'Dodge','Neon','2006',125000,3600,'siteB.html','2026-03-23 18:08:58.455000'),(75,'Subaru','Impreza','2011',89000,6800,'siteB.html','2026-03-23 18:08:58.455000'),(76,'Chevrolet','Cobalt','2009',105000,4000,'siteB.html','2026-03-23 18:08:58.455000'),(77,'Ford','Fiesta','2014',72000,6700,'siteB.html','2026-03-23 18:08:58.455000'),(78,'Toyota','Camry','2002',150000,3800,'siteB.html','2026-03-23 18:08:58.455000'),(79,'Chevrolet','Cruze','2016',64000,9500,'siteB.html','2026-03-23 18:08:58.455000'),(80,'Subaru','Forrester','1999',250000,3500,'siteA.html','2026-04-16 15:18:49.530000'),(81,'Toyota','Corolla','1998',280000,2500,'siteB.html','2026-04-16 15:26:26.638000'),(82,'Chevrolet','Impala','1995',190000,2500,'siteB.html','2026-04-22 15:04:25.233000'),(83,'Volkswagen','Bus','1980',250000,10000,'siteA.html','2026-04-24 10:40:28.696000'),(84,'GMC','Sierra','1987',20000,2000,'siteA.html','2026-04-27 18:00:28.705000'),(85,'Subaru','Impreza','1999',150000,3500,'siteA.html','2026-04-27 18:00:28.705000'),(86,'Toyota','Camry','2005',172000,4200,'siteA.html','2026-04-28 14:32:28.422000'),(87,'Toyota','Camry','2005',148000,5100,'siteA.html','2026-04-28 14:32:28.422000'),(88,'Toyota','Camry','2006',165000,4800,'siteA.html','2026-04-28 14:32:28.422000'),(89,'Toyota','Camry','2006',132000,6200,'siteA.html','2026-04-28 14:32:28.422000'),(90,'Toyota','Camry','2007',141000,6700,'siteA.html','2026-04-28 14:32:28.422000'),(91,'Toyota','Camry','2007',119000,7200,'siteA.html','2026-04-28 14:32:28.422000'),(92,'Toyota','Camry','2008',128000,7600,'siteA.html','2026-04-28 14:32:28.422000'),(93,'Toyota','Camry','2008',98000,8900,'siteA.html','2026-04-28 14:32:28.422000'),(94,'Toyota','Camry','2009',110000,8300,'siteA.html','2026-04-28 14:32:28.422000'),(95,'Toyota','Camry','2009',87000,9800,'siteA.html','2026-04-28 14:32:28.422000'),(96,'Toyota','Camry','2010',102000,9500,'siteA.html','2026-04-28 14:32:28.422000'),(97,'Toyota','Camry','2010',76000,11200,'siteA.html','2026-04-28 14:32:28.422000');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `favorite_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `cars_id` int NOT NULL,
  PRIMARY KEY (`favorite_id`),
  UNIQUE KEY `unique_user_car` (`user_id`,`cars_id`),
  KEY `fk_favorites_car` (`cars_id`),
  CONSTRAINT `fk_favorites_car` FOREIGN KEY (`cars_id`) REFERENCES `cars` (`cars_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_favorites_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (47,4,1),(42,4,2),(48,4,3),(46,4,11),(43,4,20),(36,4,21),(37,4,76),(40,4,78),(49,26,87);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_type` (`role_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_users_role` (`role_id`),
  CONSTRAINT `fk_users_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'kieranmack','kieranmcmenaman2@gmail.com','$2a$10$uMWThG0F1hi.5wFWDBZTQusq1.ixshRoIRfYWZeHpvfYi51swfh8K',1),(3,'johndoe1','johndoe1@gmail.com','$2a$10$3w5D6j8SfmIT8fWPpL5lPuu.uHwr7XKMKTadqgvMn.3POB20PpF9S',2),(4,'janedoe1','janedoe@gmail.com','$2a$10$KgLEMzVtvatvULzMhloIUOvUh7qQvwpdTuIJ7Xp489d6QLTz9/fwW',2),(8,'jery101','jery101@gmail.com','$2a$10$1Hl7bNsbS0qH46DA0FpNqOFhon06k3mwVbrSt4WlVgfTP7AcmEVFO',2),(10,'test_user_frontend','testuser@gmail.com','$2a$10$7IqdSmV2gJdJLi7iNNZBGe6smBsf4eCS5zYu3wEaqMyuOi4N4CsyS',2),(15,'test_user_frontend2','testuser2@gmail.com','$2a$10$XfH/hHRunHeo5wtAM9kwvuQHD8xxzKLKcb7KLEs6Sz0QYs2PvInT2',2),(16,'testuser3','test3@gmail.com','$2a$10$VyphizkXWTYzAOFQteqE/.hGprAa0HFMTsFAPi4BvfYPq5nolkSdi',2),(17,'davefranco1','dave@jphogan.com','$2a$10$KzJhF1ch3SAXWkcmiN0/6uAqSA88paS/eE1Ormo/TD2S.CzRtWRyW',2),(18,'kieranmcm','kieranmcm@yahoo.com','$2a$10$cllP71h7mRA6urAtFyOWKOlwuABhh93qTDx2NbfWhKCxWRcOwO0cO',2),(19,'bradleynowell','bnowell@gmail.com','$2a$10$NO7AcLx2KjGXzffCSdGUI.IXG0E9TUHIMQuwB6x8VAVuiLEaKf.42',2),(20,'greyman','grey@yahoo.com','$2a$10$foPeU7l0JS.gHn9Wi854z.2/U0Z8f6L8bR1GhglK3CrcmWbIQUYji',2),(21,'lilyjanemcm','lily@aol.com','$2a$10$qw2gN9MgiFQNJeC1vd1ziOI.69rtOTXLiGg0bv5mZKiFSlGZA5PMa',2),(22,'emmamcm','emmamcm10@gmail.com','$2a$10$YO5km/p8serFGsrD5IL3LOI6LI4OmNF5DMs3Te1JgJv1SVfEywWni',2),(23,'johnfrusciante','jf@aol.com','$2a$10$dVUh3d7x63YCFFPYZqy5XePtB83zp9jNhWob0N1vtUNAZSzXV5nNq',2),(24,'helloworld','h2@yahoo.com','$2a$10$zoW1raS98ihNcupXY8oKE.vr1fqu.stl.idqFXMxi474wbsMEPHgi',2),(25,'helloagain','whatdoyawant@gmail.com','$2a$10$FAMZBriVNafkVho9xYuAIemyo2Vflnf33GTV5D6C6V2ep4zWJnbsK',2),(26,'kieran679','kieranmcm@gmail.com','$2a$10$d2przBqjHNkEFpy03o/j6eyDj6VX1IoDqgs78zxgdRgMIv.V8jY8m',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-11 14:07:18
