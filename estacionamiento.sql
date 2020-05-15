CREATE DATABASE  IF NOT EXISTS `estacionamiento` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `estacionamiento`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: estacionamiento
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `boleto`
--

DROP TABLE IF EXISTS `boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `boleto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(50) DEFAULT NULL,
  `modelo` varchar(50) DEFAULT NULL,
  `placas` varchar(10) DEFAULT NULL,
  `color` varchar(15) DEFAULT NULL,
  `propietario` varchar(50) DEFAULT NULL,
  `entrada` datetime DEFAULT NULL,
  `salida` datetime DEFAULT NULL,
  `total` float(6,2) DEFAULT NULL,
  `id_usuario_entrada` int(11) DEFAULT NULL,
  `id_usuario_salida` int(11) DEFAULT NULL,
  `id_cajon` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario_entrada` (`id_usuario_entrada`),
  KEY `id_usuario_salida` (`id_usuario_salida`),
  KEY `id_cajon` (`id_cajon`),
  CONSTRAINT `boleto_ibfk_1` FOREIGN KEY (`id_usuario_entrada`) REFERENCES `usuario` (`id`),
  CONSTRAINT `boleto_ibfk_2` FOREIGN KEY (`id_usuario_salida`) REFERENCES `usuario` (`id`),
  CONSTRAINT `boleto_ibfk_3` FOREIGN KEY (`id_cajon`) REFERENCES `cajon` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto`
--

LOCK TABLES `boleto` WRITE;
/*!40000 ALTER TABLE `boleto` DISABLE KEYS */;
INSERT INTO `boleto` VALUES (2,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39','2020-05-14 21:23:49',725.00,1,2,4),(3,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39','2020-05-14 21:33:55',730.00,1,6,1),(4,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39','2020-05-14 21:34:05',730.00,1,6,2),(5,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39','2020-05-14 21:34:16',730.00,1,6,3),(6,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39',NULL,NULL,1,1,1),(7,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39',NULL,NULL,1,1,1),(8,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39',NULL,NULL,1,1,2),(9,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39',NULL,NULL,1,1,5),(10,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39','2020-05-14 16:47:12',655.00,1,1,7),(11,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39','2020-05-13 16:58:56',300.00,1,1,3),(12,'vw','vento','AATR30','plata','Rosa','2020-05-12 22:32:39','2020-05-13 16:40:59',295.00,1,1,2),(13,'ff','ff','ff','ff','ff','2020-05-13 17:11:09','2020-05-13 17:15:07',35.00,1,1,3),(14,'Nissan','Versa','ASD2312','Rojo','Rafa','2020-05-13 20:09:10',NULL,NULL,1,1,8),(15,'Nissan','Versa','QWE1231','Plata','Plata','2020-05-13 20:12:26',NULL,NULL,1,1,3),(16,'VW','Vento','KAL1231','Rojo','Aby','2020-05-13 20:18:12',NULL,NULL,1,1,4),(17,'test','rtest','test1312','test','test','2020-05-13 20:43:46',NULL,NULL,1,1,6),(18,'gg','gg','gg','gg','gg','2020-05-13 22:00:31','2020-05-13 22:01:44',35.00,1,1,9),(19,'Ford','Figo','asd1231','Verde','Esperancita','2020-05-14 16:16:56',NULL,NULL,2,2,9),(20,'VW','Jetta','AJS123','rojo','Cesar','2020-05-14 10:00:00','2020-05-14 11:00:00',35.00,3,3,7),(21,'VW','Jetta','BB123','blanco','Pepe','2020-05-14 10:00:00','2020-05-14 11:00:00',35.00,4,4,8),(22,'VW','Jetta','XLS123','azul','Jose','2020-05-14 10:00:00','2020-05-14 11:00:00',35.00,5,5,9);
/*!40000 ALTER TABLE `boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cajon`
--

DROP TABLE IF EXISTS `cajon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cajon` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(10) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `id_nivel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_nivel` (`id_nivel`),
  CONSTRAINT `cajon_ibfk_1` FOREIGN KEY (`id_nivel`) REFERENCES `nivel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cajon`
--

LOCK TABLES `cajon` WRITE;
/*!40000 ALTER TABLE `cajon` DISABLE KEYS */;
INSERT INTO `cajon` VALUES (1,'A1',0,1),(2,'A2',0,1),(3,'A3',0,1),(4,'A4',0,1),(5,'A5',1,1),(6,'A6',1,1),(7,'A7',0,1),(8,'A8',1,1),(9,'A9',1,1),(10,'A10',0,1),(11,'A11',0,1),(12,'A12',0,1),(13,'A13',0,1),(14,'A14',0,1),(15,'A15',0,1),(16,'A16',0,1),(17,'A17',0,1),(18,'A18',0,1),(19,'A19',0,1),(20,'A20',0,1),(21,'B1',0,2),(22,'B2',0,2),(23,'B3',0,2),(24,'B4',0,2),(25,'B5',0,2),(26,'B6',0,2),(27,'B7',0,2),(28,'B8',0,2),(29,'B9',0,2),(30,'B10',0,2),(31,'B11',0,2),(32,'B12',0,2),(33,'B13',0,2),(34,'B14',0,2),(35,'B15',0,2),(36,'B16',0,2),(37,'B17',0,2),(38,'B18',0,2),(39,'B19',0,2),(40,'B20',0,2),(41,'C1',0,3),(42,'C2',0,3),(43,'C3',0,3),(44,'C4',0,3),(45,'C5',0,3),(46,'C6',0,3),(47,'C7',0,3),(48,'C8',0,3),(49,'C9',0,3),(50,'C10',0,3),(51,'C11',0,3),(52,'C12',0,3),(53,'C13',0,3),(54,'C14',0,3),(55,'C15',0,3),(56,'C16',0,3),(57,'C17',0,3),(58,'C18',0,3),(59,'C19',0,3),(60,'C20',0,3),(61,'D1',0,4),(62,'D2',0,4),(63,'D3',0,4),(64,'D4',0,4),(65,'D5',0,4),(66,'D6',0,4),(67,'D7',0,4),(68,'D8',0,4),(69,'D9',0,4),(70,'D10',0,4),(71,'D11',0,4),(72,'D12',0,4),(73,'D13',0,4),(74,'D14',0,4),(75,'D15',0,4),(76,'D16',0,4),(77,'D17',0,4),(78,'D18',0,4),(79,'D19',0,4),(80,'D20',0,4);
/*!40000 ALTER TABLE `cajon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel`
--

DROP TABLE IF EXISTS `nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nivel` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel`
--

LOCK TABLES `nivel` WRITE;
/*!40000 ALTER TABLE `nivel` DISABLE KEYS */;
INSERT INTO `nivel` VALUES (1,'A'),(2,'B'),(3,'C'),(4,'D');
/*!40000 ALTER TABLE `nivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `cuenta` varchar(50) DEFAULT NULL,
  `contrasena` varchar(50) DEFAULT NULL,
  `puesto` varchar(50) DEFAULT NULL,
  `sueldo` float(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Aby','Vidaña','aby','aby','Administrador',1000.10),(2,'nuevo','nuevo','nuevo','nuevo','acomodador',1000.10),(3,'Jorge','Lopez','jorge','jorge','acomodador',1000.10),(4,'Raul','Escajeda','raul','raul','acomodador',1000.10),(5,'Laura','Perez','laura','laura','acomodador',1000.10),(6,'Andrea','Hernandez','andrea','andrea','acomodador',1000.10);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'estacionamiento'
--

--
-- Dumping routines for database 'estacionamiento'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-15 13:26:52
