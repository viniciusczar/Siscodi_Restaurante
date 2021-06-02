CREATE DATABASE  IF NOT EXISTS `siscodibd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `siscodibd`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: siscodibd2
-- ------------------------------------------------------
-- Server version	5.7.25

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `entrada`
--

DROP TABLE IF EXISTS `entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrada` (
  `Id` int(11) NOT NULL,
  `Cod_embalagem` int(11) NOT NULL,
  `Validade` datetime DEFAULT NULL,
  `QuantidadeEntrada` int(11) DEFAULT '1',
  `Preco` double DEFAULT NULL,
  `DataEntrada` datetime DEFAULT NULL,
  `Obs1` varchar(300) DEFAULT NULL,
  `Obs2` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `Id` int(11) NOT NULL,
  `Nome` varchar(200) NOT NULL,
  `Marca` varchar(200) DEFAULT NULL,
  `Cod_embalagem` int(11) NOT NULL,
  `Validade` datetime DEFAULT NULL,
  `Quantidade` int(11) DEFAULT '1',
  `Preco` double DEFAULT NULL,
  `Data_ins` datetime DEFAULT NULL,
  `CodigoBarra` int(11) DEFAULT NULL,
  `Obs1` varchar(300) DEFAULT NULL,
  `Obs2` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `mesafuncionario`
--

DROP TABLE IF EXISTS `mesafuncionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `mesas`
--

DROP TABLE IF EXISTS `mesas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesas` (
  `totalPedido` float DEFAULT NULL,
  `mesa` int(11) NOT NULL,
  PRIMARY KEY (`mesa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produtodelivery`
--

DROP TABLE IF EXISTS `produtodelivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `produtomesa`
--

DROP TABLE IF EXISTS `produtomesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos` (
  `IdProdutos` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(30) DEFAULT NULL,
  `categoria` varchar(30) DEFAULT NULL,
  `preco` float DEFAULT NULL,
  `icms` float DEFAULT NULL,
  `aliquota` varchar(3) DEFAULT NULL,
  `marca` varchar(200) DEFAULT NULL,
  `Quantidade` int(11) NOT NULL,
  PRIMARY KEY (`IdProdutos`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `produtovenda`
--

DROP TABLE IF EXISTS `produtovenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtovenda` (
  `IdVenda` int(11) DEFAULT NULL,
  `IdProdutos` int(11) DEFAULT NULL,
  `qtdProdutos` int(11) DEFAULT NULL,
  `precoProduto` float DEFAULT NULL,
  KEY `IdProdutos` (`IdProdutos`),
  KEY `IdVenda` (`IdVenda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger Tgr_desconta_venda AFTER INSERT 
ON produtovenda
for each row
begin
	UPDATE produtos as p SET p.Quantidade = p.Quantidade - NEW.qtdProdutos 
	WHERE p.IdProdutos = new.IdProdutos;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `saida`
--

DROP TABLE IF EXISTS `saida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saida` (
  `id` int(11) NOT NULL,
  `Cod_embalagem` int(11) NOT NULL,
  `Validade` datetime DEFAULT NULL,
  `QuantidadeSaida` int(11) DEFAULT '1',
  `DataSaida` datetime DEFAULT NULL,
  `Preco` double DEFAULT NULL,
  `Obs1` varchar(300) DEFAULT NULL,
  `Obs2` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `IdFuncionario` int(11) NOT NULL,
  `Senha` varchar(15) DEFAULT NULL,
  `Login` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`IdFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendas` (
  `IdVenda` int(11) NOT NULL AUTO_INCREMENT,
  `IdDelivery` int(11) DEFAULT NULL,
  `mesa` int(11) DEFAULT NULL,
  `totalVenda` float DEFAULT NULL,
  `dataVenda` date DEFAULT NULL,
  PRIMARY KEY (`IdVenda`),
  KEY `IdDelivery` (`IdDelivery`),
  KEY `mesa` (`mesa`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
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

--
-- Dumping events for database 'siscodibd2'
--

--
-- Dumping routines for database 'siscodibd2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-25 18:18:44
