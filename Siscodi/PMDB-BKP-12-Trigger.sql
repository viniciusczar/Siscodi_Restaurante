CREATE DATABASE  IF NOT EXISTS `pubmanagerdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pubmanagerdb`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 10.190.81.160    Database: pubmanagerdb
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `IdClientes` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `cidade` varchar(18) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`IdClientes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery` (
  `IdDelivery` int(11) NOT NULL AUTO_INCREMENT,
  `IdClientes` int(11) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `obs` varchar(100) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `totalPedido` float DEFAULT NULL,
  `dataVenda` date DEFAULT NULL,
  `troco` float DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`IdDelivery`),
  KEY `IdClientes` (`IdClientes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionarios` (
  `IdFuncionario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `funcao` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`IdFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios`
--

/*!40000 ALTER TABLE `funcionarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionarios` ENABLE KEYS */;

--
-- Table structure for table `mesafuncionario`
--

DROP TABLE IF EXISTS `mesafuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesafuncionario` (
  `dataVenda` date DEFAULT NULL,
  `totalVendido` float DEFAULT NULL,
  `mesa` int(11) DEFAULT NULL,
  `IdFuncionario` int(11) DEFAULT NULL,
  KEY `mesa` (`mesa`),
  KEY `IdFuncionario` (`IdFuncionario`),
  CONSTRAINT `mesafuncionario_ibfk_1` FOREIGN KEY (`mesa`) REFERENCES `mesas` (`mesa`),
  CONSTRAINT `mesafuncionario_ibfk_2` FOREIGN KEY (`IdFuncionario`) REFERENCES `funcionarios` (`IdFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesafuncionario`
--

/*!40000 ALTER TABLE `mesafuncionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesafuncionario` ENABLE KEYS */;

--
-- Table structure for table `mesas`
--

DROP TABLE IF EXISTS `mesas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesas` (
  `totalPedido` float DEFAULT NULL,
  `mesa` int(11) NOT NULL,
  PRIMARY KEY (`mesa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesas`
--

/*!40000 ALTER TABLE `mesas` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesas` ENABLE KEYS */;

--
-- Table structure for table `produtodelivery`
--

DROP TABLE IF EXISTS `produtodelivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtodelivery` (
  `IdDelivery` int(11) DEFAULT NULL,
  `IdProdutos` int(11) DEFAULT NULL,
  `qtdProduto` int(11) DEFAULT NULL,
  `precoProduto` float DEFAULT NULL,
  KEY `IdDelivery` (`IdDelivery`),
  KEY `IdProdutos` (`IdProdutos`),
  CONSTRAINT `produtodelivery_ibfk_2` FOREIGN KEY (`IdProdutos`) REFERENCES `produtos` (`IdProdutos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtodelivery`
--

/*!40000 ALTER TABLE `produtodelivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `produtodelivery` ENABLE KEYS */;

--
-- Table structure for table `produtomesa`
--

DROP TABLE IF EXISTS `produtomesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtomesa` (
  `IdProdutos` int(11) DEFAULT NULL,
  `mesa` int(11) DEFAULT NULL,
  `qtdProduto` int(11) DEFAULT NULL,
  `precoProduto` float DEFAULT NULL,
  KEY `IdProdutos` (`IdProdutos`),
  KEY `mesa` (`mesa`),
  CONSTRAINT `produtomesa_ibfk_1` FOREIGN KEY (`IdProdutos`) REFERENCES `produtos` (`IdProdutos`),
  CONSTRAINT `produtomesa_ibfk_2` FOREIGN KEY (`mesa`) REFERENCES `mesas` (`mesa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtomesa`
--

/*!40000 ALTER TABLE `produtomesa` DISABLE KEYS */;
/*!40000 ALTER TABLE `produtomesa` ENABLE KEYS */;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `IdProdutos` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(30) DEFAULT NULL,
  `categoria` varchar(30) DEFAULT NULL,
  `preco` float DEFAULT NULL,
  `icms` float DEFAULT NULL,
  `aliquota` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`IdProdutos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;

--
-- Table structure for table `produtovenda`
--

DROP TABLE IF EXISTS `produtovenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtovenda` (
  `IdVenda` int(11) DEFAULT NULL,
  `IdProdutos` int(11) DEFAULT NULL,
  `qtdProdutos` int(11) DEFAULT NULL,
  `precoProduto` float DEFAULT NULL,
  KEY `IdProdutos` (`IdProdutos`),
  KEY `IdVenda` (`IdVenda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtovenda`
--

/*!40000 ALTER TABLE `produtovenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `produtovenda` ENABLE KEYS */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `IdFuncionario` int(11) NOT NULL,
  `Senha` varchar(15) DEFAULT NULL,
  `Login` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`IdFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (0,'123456','ADMIN');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas` (
  `IdVenda` int(11) NOT NULL AUTO_INCREMENT,
  `IdDelivery` int(11) DEFAULT NULL,
  `mesa` int(11) DEFAULT NULL,
  `totalVenda` float DEFAULT NULL,
  `dataVenda` date DEFAULT NULL,
  PRIMARY KEY (`IdVenda`),
  KEY `IdDelivery` (`IdDelivery`),
  KEY `mesa` (`mesa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`senai`@`%`*/ /*!50003 TRIGGER Fecha_Pedido AFTER INSERT

ON vendas

FOR EACH ROW

BEGIN

    

UPDATE delivery SET status = 'FECHADO'

WHERE IdDelivery = NEW.IdDelivery;



END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-15 18:53:44
