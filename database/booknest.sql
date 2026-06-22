-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: booknest
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,'2026-05-17 10:12:16'),(2,2,'2026-05-17 10:12:16'),(3,3,'2026-05-17 10:12:16'),(4,7,'2026-05-18 09:02:48'),(5,8,'2026-05-18 12:44:50');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `price_at_addition` decimal(10,2) NOT NULL,
  `added_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_item_id`),
  UNIQUE KEY `uq_cart_product` (`cart_id`,`product_id`),
  KEY `fk_cart_items_product` (`product_id`),
  CONSTRAINT `fk_cart_items_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `fk_cart_items_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (1,1,1,2,499.00,'2026-05-17 10:12:24'),(2,1,3,1,899.00,'2026-05-17 10:12:24'),(3,2,2,1,399.00,'2026-05-17 10:12:24'),(4,2,5,3,299.00,'2026-05-17 10:12:24'),(5,3,4,1,1099.00,'2026-05-17 10:12:24');
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Fiction','Imaginative stories and novels'),(2,'Non-Fiction','Real stories and factual books'),(3,'Programming','Coding and software development books'),(4,'AI & ML','Artificial Intelligence and Machine Learning books'),(5,'Comics','Comic books and graphic novels'),(6,'Self Help','Personal development and productivity books'),(7,'Biography','Life stories of famous personalities'),(8,'Academic','Educational and syllabus books');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `fk_order_items_order` (`order_id`),
  KEY `fk_order_items_product` (`product_id`),
  CONSTRAINT `fk_order_items_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `fk_order_items_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,1,1,1,499.00),(2,1,3,1,899.00),(3,2,2,1,399.00),(4,2,5,3,299.00),(5,3,4,1,1099.00),(6,4,1,1,499.00),(7,4,2,2,399.00),(8,5,2,1,399.00),(9,6,3,1,899.00),(10,7,2,1,399.00),(11,7,3,1,899.00),(12,8,5,1,299.00),(13,9,7,1,799.00),(14,10,2,1,399.00),(15,11,2,1,399.00),(16,12,4,1,1099.00),(17,13,6,1,350.00),(18,14,2,1,399.00),(19,15,7,1,799.00),(20,16,1,1,499.00),(21,17,6,1,350.00),(22,18,6,1,350.00),(23,18,5,1,299.00),(24,19,6,1,350.00),(25,20,1,1,499.00),(26,21,5,1,299.00),(27,22,1,3,499.00),(28,23,5,1,299.00);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `delivery_name` varchar(100) NOT NULL,
  `delivery_phone` varchar(15) NOT NULL,
  `delivery_address_line1` varchar(255) NOT NULL,
  `delivery_address_line2` varchar(255) DEFAULT NULL,
  `delivery_city` varchar(100) NOT NULL,
  `delivery_state` varchar(100) NOT NULL,
  `delivery_postal_code` varchar(20) NOT NULL,
  `delivery_country` varchar(100) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `payment_method` varchar(50) NOT NULL,
  `order_status` varchar(50) DEFAULT 'Placed',
  `order_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  KEY `fk_orders_user` (`user_id`),
  CONSTRAINT `fk_orders_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'Rahul Sharma','9876543210','BTM Layout','Near Udupi Garden','Bangalore','Karnataka','560076','India',1398.00,'COD','Placed','2026-05-17 10:12:35'),(2,2,'Priya Verma','9876501234','HSR Layout','Sector 2','Bangalore','Karnataka','560102','India',1296.00,'UPI','Delivered','2026-05-17 10:12:35'),(3,3,'Aman Khan','9988776655','Electronic City','Phase 1','Bangalore','Karnataka','560100','India',1099.00,'Card','Shipped','2026-05-17 10:12:35'),(4,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',1297.00,'Cash On Delivery','Cancelled','2026-05-18 10:53:28'),(5,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',399.00,'Cash On Delivery','Cancelled','2026-05-18 10:57:01'),(6,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',899.00,'Cash On Delivery','Cancelled','2026-05-18 11:06:22'),(7,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',1298.00,'UPI','Cancelled','2026-05-18 11:07:38'),(8,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',299.00,'UPI','Cancelled','2026-05-18 11:37:05'),(9,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',799.00,'Cash On Delivery','Cancelled','2026-05-18 11:52:51'),(10,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',399.00,'Cash On Delivery','Cancelled','2026-05-18 11:56:16'),(11,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',399.00,'Cash On Delivery','Cancelled','2026-05-18 12:15:46'),(12,8,'Shanaya','6584128995','BTM Layout, 2nd Stage, ','','Banglore','Karnataka','580025','India',1099.00,'Cash On Delivery','Placed','2026-05-18 12:45:00'),(13,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',350.00,'Cash On Delivery','Cancelled','2026-05-18 13:00:48'),(14,8,'Shanaya','6584128995','BTM Layout, 2nd Stage  , Madiwala','','Banglore','Karnataka','580025','India',399.00,'Cash On Delivery','Placed','2026-05-18 13:23:39'),(15,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',799.00,'Credit Card','Placed','2026-05-18 13:26:23'),(16,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',499.00,'Cash On Delivery','Placed','2026-05-18 14:13:59'),(17,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',350.00,'Cash On Delivery','Placed','2026-05-18 16:02:39'),(18,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',649.00,'Cash On Delivery','Placed','2026-05-18 16:22:02'),(19,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',350.00,'Cash On Delivery','Placed','2026-05-18 16:27:58'),(20,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',499.00,'Cash On Delivery','Cancelled','2026-05-18 21:56:48'),(21,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',299.00,'Cash On Delivery','Placed','2026-06-21 15:51:30'),(22,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',1497.00,'Cash On Delivery','Cancelled','2026-06-21 21:39:23'),(23,7,'UMERA BEPARI','7795659197','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India',299.00,'Cash On Delivery','Placed','2026-06-21 22:26:20');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_details`
--

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_details` (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `publisher` varchar(100) NOT NULL,
  `language` varchar(50) NOT NULL,
  `pages` int NOT NULL,
  `isbn` varchar(30) DEFAULT NULL,
  `publication_year` year NOT NULL,
  PRIMARY KEY (`detail_id`),
  UNIQUE KEY `product_id` (`product_id`),
  UNIQUE KEY `isbn` (`isbn`),
  CONSTRAINT `fk_product_details_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_details`
--

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
INSERT INTO `product_details` VALUES (1,1,'Penguin','English',320,'9781847941831',2018),(2,2,'HarperOne','English',208,'9780061122415',1993),(3,3,'McGraw Hill','English',1248,'9781260440232',2021),(4,4,'OReilly Media','English',856,'9781492032649',2022),(5,5,'Viz Media','English',192,'9781569319000',2003),(6,6,'Universities Press','English',180,'9788173711466',1999),(7,7,'Khanna Publishers','English',850,'9789382609810',2018),(8,13,'Jaico Publishing House','English',256,'9789390166268',2020);
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `author` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `rating` decimal(2,1) DEFAULT '0.0',
  `is_active` tinyint(1) DEFAULT '1',
  `category_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`),
  KEY `fk_products_category` (`category_id`),
  CONSTRAINT `fk_products_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Atomic Habits','James Clear','A practical guide to building good habits.',499.00,25,'assets/images/books/atomic-habits.jpg',4.8,1,6,'2026-05-17 09:37:51'),(2,'The Alchemist','Paulo Coelho','A motivational fiction novel.',399.00,18,'assets/images/books/the-alchemist.jpg',4.7,1,1,'2026-05-17 09:37:51'),(3,'Java Complete Reference','Herbert Schildt','Comprehensive Java programming guide.',899.00,12,'assets/images/books/java-complete-reference.jpg',4.9,1,3,'2026-05-17 09:37:51'),(4,'Hands-On Machine Learning','Aurelien Geron','Machine learning concepts with Python.',1099.00,10,'assets/images/books/hands-on-machine-learning.jpg',4.8,1,4,'2026-05-17 09:37:51'),(5,'Naruto Volume 1','Masashi Kishimoto','Popular Japanese manga series.',299.00,30,'assets/images/books/naruto-volume-1.jpg',4.6,1,5,'2026-05-17 09:37:51'),(6,'Wings of Fire','A.P.J Abdul Kalam','Autobiography of Dr. APJ Abdul Kalam.',350.00,20,'assets/images/books/wings-of-fire.jpg',4.9,1,7,'2026-05-17 09:37:51'),(7,'Engineering Mathematics','B.S Grewal','Mathematics textbook for engineering students.',799.00,15,'assets/images/books/engineering-mathematics.jpg',4.5,1,8,'2026-05-17 09:37:51'),(13,'The Psychology of Money','Morgan Housel','Timeless lessons about wealth and financial behavior.',599.00,20,'assets/images/books/psychology-of-money.jpg',4.9,1,2,'2026-05-18 21:15:42');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address_line1` varchar(255) NOT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `postal_code` varchar(20) NOT NULL,
  `country` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Rahul Sharma','rahul@gmail.com','9876543210','rahul123','BTM Layout','Near Udupi Garden','Bangalore','Karnataka','560076','India','2026-05-17 09:37:36'),(2,'Priya Verma','priya@gmail.com','9876501234','priya123','HSR Layout','Sector 2','Bangalore','Karnataka','560102','India','2026-05-17 09:37:36'),(3,'Aman Khan','aman@gmail.com','9988776655','aman123','Electronic City','Phase 1','Bangalore','Karnataka','560100','India','2026-05-17 09:37:36'),(7,'UMERA BEPARI','umerabepari1312@gmail.com','7795659197','umera@123','Ganesh Peth, Hubli , Karnataka','','Hubli','Karnataka','580020','India','2026-05-17 14:47:18'),(8,'Shanaya','shanaya12@gmail.com','6584128995','shnaya@123','BTM Layout, 2nd Stage, ','','Banglore','Karnataka','580025','India','2026-05-18 12:43:17'),(9,'John','John@gmail.com','789232298','byewdvye123','BTM layout','','Banglore','Karnataka','580020','India','2026-06-21 22:04:35'),(11,'John','john12@gmail.com','78946943','evdy354','Btm Layout','','Banglore','Karnataka','580020','India','2026-06-21 22:06:20'),(12,'XYZ','xyz@gmail.com','798265495','xyz@123','BTM layout','','Banglore','Karnataka','580020','India','2026-06-21 22:19:58'),(13,'Umera Bepari','umerabepari906@gmail.com','7795659197','12345','btm 2nd stage','','Hubli','Karnataka','580020','India','2026-06-22 08:00:59');
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

-- Dump completed on 2026-06-22 20:21:50
