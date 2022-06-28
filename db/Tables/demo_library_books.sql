-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: demo_library
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `author_id` int NOT NULL,
  `genre_id` int NOT NULL,
  `publisher_id` int NOT NULL,
  `quantity` int NOT NULL,
  `available` int NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  KEY `books_ibfk_2` (`genre_id`),
  KEY `FK_publisher_id_idx` (`publisher_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`),
  CONSTRAINT `books_ibfk_2` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`),
  CONSTRAINT `FK_publisher_id` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`),
  CONSTRAINT `books_chk_1` CHECK ((`quantity` >= `available`))
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'aaa',1,1,1,7,7,1),(2,'aa',1,1,1,2,2,1),(3,'aa aa aa',1,1,1,3,3,1),(4,'Leonard Cortez',1,7,3,5,5,1),(5,'Cade White',7,3,1,6,6,1),(6,'Damon Noel',3,7,3,4,4,1),(7,'Thomas Nolan',2,5,3,1,1,1),(8,'Alexandra Osborn',3,6,1,3,3,1),(9,'Solomon Gray',6,6,5,20,20,1),(10,'Logan Dale',5,5,2,6,6,1),(11,'Matthew Goodman',3,4,6,6,6,1),(12,'Linus Hale',4,2,4,6,6,1),(13,'Salvador Holmes',7,7,4,4,4,1),(14,'Harding Richardson',4,3,3,8,8,1),(15,'Isaac Mckee',5,4,5,5,5,1),(16,'Nayda Curry',8,1,2,62,62,1),(17,'Medge Santiago',5,7,3,35,35,1),(18,'Tasha Hurst',2,5,5,13,13,1),(19,'Victor Cantu',3,3,2,16,16,1),(20,'Yvonne Brennan',5,5,5,18,18,1),(21,'Ross Stark',7,6,1,48,48,1),(22,'Nathan Long',8,6,1,51,51,1),(23,'Camden Cain',3,6,3,89,89,1),(24,'Darrel Dean',2,1,4,18,18,1),(25,'Cheyenne Massey',3,2,2,15,15,1),(26,'Lyle Floyd',2,6,3,34,34,1),(27,'Cailin Mcguire',8,7,2,7,7,1),(28,'Tatyana Leblanc',6,4,3,93,93,1);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-28  9:30:08
