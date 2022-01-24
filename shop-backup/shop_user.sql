-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `disabled` tinyint(1) DEFAULT '0',
  `account_expired` tinyint(1) DEFAULT '0',
  `account_locked` tinyint(1) DEFAULT '0',
  `credentials_expired` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user','$2a$10$yeMPlReKiIhHNZFwKxEhIu4AOywxFr7Sh6XwV3/liEmTxd6cCsXLe',0,0,0,0),(2,'andrei','$2a$10$hFUwqM9GyOHIK.kq/Qz02uFRXa0UMzRtfdbs4m6U3nUnRykKH6lu.',0,0,0,0),(3,'norbert','$2a$10$06b3/GXFznRQEsojqCmddeqgRZ4qO95FY65LDAs/AO8.Hh76vnNgK',0,0,0,0),(5,'producer','$2a$10$NwbAv9NpPoyZZdUODLI4hOn5PUK0SsRFbXbbWRYlSQvI3yn7oD9GS',0,0,0,0),(6,'trader','$2a$10$7pS2c.NkWwQG3dNpB3s3B.XYD9.4bYxhr7mvOta0Jlc5mTZMI9aGG',0,0,0,0),(7,'buyer','$2a$10$pbh.2kpTOWQl4wZr1H71sepx.W0OJWIGI9G9mKU/BrHXJpkUBqYKW',0,0,0,0),(8,'producer junior','$2a$10$GPoHfDZkN5C7.fIewpoVgOp9Uo9egJkY1zK4joi4GNBYQ7Hs.g/Z.',0,0,0,0),(9,'trader pro','$2a$10$aWh23WN.Ac2VVh1TadCXd.kOnYYcQtdadd0z1nK50R7TMjYfWG7B.',0,0,0,0),(10,'producer 69420','$2a$10$7DN.fiTivd9GcXjnSCbSZeH0U66dr4HV0iGS1y9s/oMxRQXBByNue',0,0,0,0),(11,'trader fancy','$2a$10$TaCg6d5GL34xgayhZd6I4OrhdAmAHduokpUxwoUXSD6NZlOLLmzn2',0,0,0,0),(12,'producer senior','$2a$10$QMMYli1HFPIrgaTposlAEOg2.0z4bhoqcgQ8xxHjhQpCfXHYB/jAe',0,0,0,0);
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

-- Dump completed on 2022-01-24 20:54:41
