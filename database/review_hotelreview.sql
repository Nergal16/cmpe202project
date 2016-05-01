-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: review
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hotelreview`
--

DROP TABLE IF EXISTS `hotelreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotelreview` (
  `id` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `dept` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotelreview`
--

LOCK TABLES `hotelreview` WRITE;
/*!40000 ALTER TABLE `hotelreview` DISABLE KEYS */;
INSERT INTO `hotelreview` VALUES (1,'Smith',32,5,'Hotel Paris\'s Delicafe (Soups/Sandwiches). Walking in, you are immersed in the wild west - mostly in decorations, of course. The sandwiches and soups, though, steal the show. Good is an understatement, even down to the drinks and desserts. Confusing orders are handled easily and substitutions are welcomed warmly. In other words, the service is very nice.','Food'),(2,'Joe',34,4,'unbeatable price. Georgia made me feel very welcome. I really appreciated all the personal touches (cookies in the room upon arrival, advice for off-the-beaten track attractions). All tourist attractions are within easy and quick reach. Quiet neighbourhood. Highly recommended for those who value personal contacts when traveling.','Hotel'),(3,'Duane',25,3,'Everything was all good, we possibly just didn\'t see the note that the bathroom would be down the hall lol (pack your shower shoes and all of the stuff to prep in a public bathroom) :-) All in all, all was well for the cost and location.','Hotel'),(4,'Rachey',29,2,'The rooms are cleaned daily with fresh towels & linen. Con\'s: The rooms are tiny. No kettle, toaster or utensils in shared kitchen. Wifi is very poor and the TV\'s didn\'t work the entire week we stayed','Rooms');
/*!40000 ALTER TABLE `hotelreview` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-29 22:22:37
