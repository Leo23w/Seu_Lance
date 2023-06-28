-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `leilao`
--

DROP TABLE IF EXISTS `leilao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leilao` (
  `idLeilao` int(11) NOT NULL AUTO_INCREMENT,
  `tempo` int(11) NOT NULL,
  `valorBase` int(11) NOT NULL,
  `lanceInicial` int(11) NOT NULL,
  `lanceAtual` int(11) NOT NULL,
  `lanceFinal` int(11) NOT NULL,
  `Produto_idProduto` int(11) NOT NULL,
  `Produto_Vendedor_idVendedor` int(11) NOT NULL,
  `Usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idLeilao`,`Produto_idProduto`,`Produto_Vendedor_idVendedor`,`Usuario_idUsuario`),
  KEY `fk_Leilao_Produto1_idx` (`Produto_idProduto`,`Produto_Vendedor_idVendedor`),
  KEY `fk_Leilao_Usuario1_idx` (`Usuario_idUsuario`),
  CONSTRAINT `fk_Leilao_Produto1` FOREIGN KEY (`Produto_idProduto`, `Produto_Vendedor_idVendedor`) REFERENCES `produto` (`idProduto`, `Vendedor_idVendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Leilao_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leilao`
--

LOCK TABLES `leilao` WRITE;
/*!40000 ALTER TABLE `leilao` DISABLE KEYS */;
/*!40000 ALTER TABLE `leilao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `idlogin` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  PRIMARY KEY (`idlogin`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'carlos','123');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveldeacesso`
--

DROP TABLE IF EXISTS `niveldeacesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveldeacesso` (
  `idAdm` int(11) NOT NULL AUTO_INCREMENT,
  `login` char(90) NOT NULL,
  PRIMARY KEY (`idAdm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveldeacesso`
--

LOCK TABLES `niveldeacesso` WRITE;
/*!40000 ALTER TABLE `niveldeacesso` DISABLE KEYS */;
/*!40000 ALTER TABLE `niveldeacesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveldeacesso_has_usuario`
--

DROP TABLE IF EXISTS `niveldeacesso_has_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveldeacesso_has_usuario` (
  `NivelDeAcesso_idAdm` int(11) NOT NULL,
  `Usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`NivelDeAcesso_idAdm`,`Usuario_idUsuario`),
  KEY `fk_NivelDeAcesso_has_Usuario_Usuario1_idx` (`Usuario_idUsuario`),
  KEY `fk_NivelDeAcesso_has_Usuario_NivelDeAcesso1_idx` (`NivelDeAcesso_idAdm`),
  CONSTRAINT `fk_NivelDeAcesso_has_Usuario_NivelDeAcesso1` FOREIGN KEY (`NivelDeAcesso_idAdm`) REFERENCES `niveldeacesso` (`idAdm`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_NivelDeAcesso_has_Usuario_Usuario1` FOREIGN KEY (`Usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveldeacesso_has_usuario`
--

LOCK TABLES `niveldeacesso_has_usuario` WRITE;
/*!40000 ALTER TABLE `niveldeacesso_has_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `niveldeacesso_has_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT,
  `nomeProduto` varchar(45) NOT NULL,
  `valor` int(11) NOT NULL,
  `quantidadeProduto` int(11) NOT NULL,
  `Vendedor_idVendedor` int(11) NOT NULL,
  PRIMARY KEY (`idProduto`,`Vendedor_idVendedor`),
  KEY `fk_Produto_Vendedor_idx` (`Vendedor_idVendedor`),
  CONSTRAINT `fk_Produto_Vendedor` FOREIGN KEY (`Vendedor_idVendedor`) REFERENCES `vendedor` (`idVendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `telefone` int(15) NOT NULL,
  `localizacao` varchar(45) NOT NULL,
  `contaBancaria` int(11) NOT NULL,
  `login_idlogin` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`,`login_idlogin`),
  KEY `fk_usuario_login1_idx` (`login_idlogin`),
  CONSTRAINT `fk_usuario_login1` FOREIGN KEY (`login_idlogin`) REFERENCES `login` (`idlogin`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `idVendedor` int(11) NOT NULL AUTO_INCREMENT,
  `telefone` int(15) NOT NULL,
  `login` char(90) NOT NULL,
  `localisacao` varchar(45) NOT NULL,
  `contaBancaria` int(11) NOT NULL,
  PRIMARY KEY (`idVendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 20:20:56
