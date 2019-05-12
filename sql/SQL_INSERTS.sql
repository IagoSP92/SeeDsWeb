-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: seeds
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1),(2),(3),(4),(5);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categoria_contenido`
--

LOCK TABLES `categoria_contenido` WRITE;
/*!40000 ALTER TABLE `categoria_contenido` DISABLE KEYS */;
INSERT INTO `categoria_contenido` VALUES (4,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(5,10),(5,11),(5,12),(5,13),(5,14),(5,15),(1,26),(1,27),(1,28),(1,29),(1,30),(4,31),(4,32),(4,33),(4,34),(3,35),(3,36),(3,37),(3,38),(3,39);
/*!40000 ALTER TABLE `categoria_contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categoria_idioma`
--

LOCK TABLES `categoria_idioma` WRITE;
/*!40000 ALTER TABLE `categoria_idioma` DISABLE KEYS */;
INSERT INTO `categoria_idioma` VALUES ('EN',1,'VIDEOCLIPS'),('ES',1,'VIDEOCLIPS'),('EN',2,'SERIES'),('ES',2,'SERIES'),('EN',3,'SHOT FILM'),('ES',3,'CORTOS'),('EN',4,'DOCUMENTARY'),('ES',4,'DOCUMENTAL'),('EN',5,'GUIDES'),('ES',5,'GUIAS');
/*!40000 ALTER TABLE `categoria_idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contenido`
--

LOCK TABLES `contenido` WRITE;
/*!40000 ALTER TABLE `contenido` DISABLE KEYS */;
INSERT INTO `contenido` VALUES (1,'Ana LP','2019-05-12','2019-05-12',NULL,1,11),(2,'Documental Elefantes','2019-05-12','2019-05-12',1,2,5),(3,'Aventuras en el Bosque 1','2019-05-12','2019-05-12',1,2,8),(4,'Aventuras en el Bosque 2','2019-05-12','2019-05-12',1,2,4),(5,'Aventuras en el Bosque 3','2019-05-12','2019-05-12',1,2,6),(6,'Aventuras en el Bosque 4','2019-05-12','2019-05-12',1,2,4),(7,'Aventuras en el Bosque 5','2019-05-12','2019-05-12',1,2,4),(8,'Aventuras en el Bosque. La Serie','2019-05-12','2019-05-12',1,3,40),(9,'Bea','2019-05-12','2019-05-12',NULL,1,7),(10,'First Modules','2019-05-12','2019-05-12',9,2,7),(11,'Lego Rotor','2019-05-12','2019-05-12',9,2,7),(12,'Lego Gyroscope','2019-05-12','2019-05-12',9,2,7),(13,'Shunt Wound','2019-05-12','2019-05-12',9,2,5),(14,'Lego Multimachine','2019-05-12','2019-05-12',9,2,5),(15,'Tutorial: Lego Engineering','2019-05-12','2019-05-12',9,3,22),(16,'Cris','2019-05-12','2019-05-12',NULL,1,0),(17,'Deivi','2019-05-12','2019-05-12',NULL,1,0),(18,'KiKo','2019-05-12','2019-05-12',NULL,1,0),(19,'Lucy Lov','2019-05-12','2019-05-12',NULL,1,0),(20,'Nere23','2019-05-12','2019-05-12',NULL,1,1),(21,'OscarS','2019-05-12','2019-05-12',NULL,1,2),(22,'Mer Mari','2019-05-12','2019-05-12',NULL,1,0),(23,'Juanín','2019-05-12','2019-05-12',NULL,1,0),(24,'Raul27','2019-05-12','2019-05-12',NULL,1,0),(25,'IagoS','2019-05-12','2019-05-12',NULL,1,0),(26,'Cuando la vi','2019-05-12','2019-05-12',25,2,9),(27,'Al amanecer','2019-05-12','2019-05-12',25,2,8),(28,'Imagínatelo','2019-05-12','2019-05-12',25,2,11),(29,'La satisfacción','2019-05-12','2019-05-12',25,2,7),(30,'Solo Respirar','2019-05-12','2019-05-12',25,2,6),(31,'Todo sobre los paquidermos','2019-05-12','2019-05-12',1,2,13),(32,'Fauna Africana','2019-05-12','2019-05-12',1,2,3),(33,'Furtivos: una amenaza real','2019-05-12','2019-05-12',1,2,3),(34,'Safari','2019-05-12','2019-05-12',1,2,2),(35,'La ciudad','2019-05-12','2019-05-12',25,2,6),(36,'Patrullando las catlles','2019-05-12','2019-05-12',16,2,2),(37,'Gambler','2019-05-12','2019-05-12',16,2,1),(38,'El gran hombre','2019-05-12','2019-05-12',16,2,0),(39,'Rutinas','2019-05-12','2019-05-12',16,2,0);
/*!40000 ALTER TABLE `contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `etiqueta`
--

LOCK TABLES `etiqueta` WRITE;
/*!40000 ALTER TABLE `etiqueta` DISABLE KEYS */;
INSERT INTO `etiqueta` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21);
/*!40000 ALTER TABLE `etiqueta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `etiqueta_contenido`
--

LOCK TABLES `etiqueta_contenido` WRITE;
/*!40000 ALTER TABLE `etiqueta_contenido` DISABLE KEYS */;
/*!40000 ALTER TABLE `etiqueta_contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `etiqueta_idioma`
--

LOCK TABLES `etiqueta_idioma` WRITE;
/*!40000 ALTER TABLE `etiqueta_idioma` DISABLE KEYS */;
INSERT INTO `etiqueta_idioma` VALUES ('EN',1,'ACCTION'),('ES',1,'ACCION'),('EN',2,'DRAMA'),('ES',2,'DRAMA'),('EN',3,'ANIMATION'),('ES',3,'ANIMACION'),('EN',4,'COMEDY'),('ES',4,'COMEDIA'),('EN',5,'ROMANCE'),('ES',5,'ROMANTICISMO'),('EN',6,'JAZZ'),('ES',6,'JAZZ'),('EN',7,'SOUL'),('ES',7,'SOUL'),('EN',8,'POP'),('ES',8,'POP'),('EN',9,'ROCK'),('ES',9,'ROCK'),('EN',10,'BLUES'),('ES',10,'BLUES'),('EN',11,'TECHNO'),('ES',11,'TECHNO'),('EN',12,'HOUSE'),('ES',12,'HOUSE'),('EN',13,'WAR'),('ES',13,'BELICO'),('EN',14,'ADVENTURE'),('ES',14,'AVENTURA'),('EN',15,'HISTORY'),('ES',15,'HISTORICO'),('EN',16,'TECNOLOGY'),('ES',16,'TECNOLOGIA'),('EN',17,'NATURE'),('ES',17,'NATURALEZA'),('EN',18,'OCEAN'),('ES',18,'OCEANOGRAFIA'),('EN',19,'PROGRAMMING'),('ES',19,'PROGRAMACION'),('EN',20,'JAVA'),('ES',20,'JAVA'),('EN',21,'CLASIC'),('ES',21,'CLASICA');
/*!40000 ALTER TABLE `etiqueta_idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `idioma`
--

LOCK TABLES `idioma` WRITE;
/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
INSERT INTO `idioma` VALUES ('EN'),('ES');
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lista`
--

LOCK TABLES `lista` WRITE;
/*!40000 ALTER TABLE `lista` DISABLE KEYS */;
INSERT INTO `lista` VALUES (8,1,'Divertida serie de anamación para toda la familia protagonizada por un conejo bonachón.',1),(15,9,'Lear how to make some Lego machines.',1);
/*!40000 ALTER TABLE `lista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES ('AD'),('DE'),('ES'),('FR'),('GB'),('IT'),('PT'),('US');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pais_idioma`
--

LOCK TABLES `pais_idioma` WRITE;
/*!40000 ALTER TABLE `pais_idioma` DISABLE KEYS */;
INSERT INTO `pais_idioma` VALUES ('EN','AD','ANDORRA'),('ES','AD','ANDORRA'),('EN','DE','GERMANY'),('ES','DE','ALEMANIA'),('EN','ES','SPAIN'),('ES','ES','ESPAÑA'),('EN','FR','FRANCE'),('ES','FR','FRANCIA'),('EN','GB','UNITED KINGDOM'),('ES','GB','REINO UNIDO'),('EN','IT','ITALY'),('ES','IT','ITALIA'),('EN','PT','PORTUGAL'),('ES','PT','PORTUGAL'),('EN','US','UNITED STATES'),('ES','US','ESTADOS UNIDOS');
/*!40000 ALTER TABLE `pais_idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'ana@gmail.com','XJFokaxQHGM0MP7QaIOcgbs+FEzzKjI0CXs6ZYb9T8BwJKzjJdyKDaD0Wjy+xnaB','Me gustan los documentales y las series de animación, compartiré algunas con ustedes. Espero que les gusten.',NULL,'Ana','Ledo Piñeiro','ES','1996-01-31'),(9,'bea@gmail.com','xVJ/NM2pit1n0hjryVozj+FAI00CFpd95eQ2PKkZB3GFq1Kgx3VlUFbNjTEFnXVF','Hola a todoa! Bienvenidos a mi perfil1',NULL,'Beatriz','Seijas','ES','1995-12-19'),(16,'cris@gmail.com','O4XeuiMW0w+z2qP/wHEPDVU1ycq0GfiWENJdqXEhPF7SMWrjy1K6vbnzw0kjKhm4',NULL,NULL,'Cristina','García González','ES','1990-06-03'),(17,'david@gmail.com','HaaSYKtjInJDgi3rO4hhu4zqBwqBYJDEZYgs5/SXDSdBJgKn4d0TRveRV2dIoFBB',NULL,NULL,'David','Montes Vázquez','ES','1991-06-15'),(18,'kiko@gmail.com','Wabq3SSMPNSnFRj1Etpk8HapbCj3NlQBDutcz57Hemt1+wiwPWzwDOTg4u+Y6r9Y',NULL,NULL,'Enrique','Lamela','ES','1997-05-04'),(19,'lucia@gmail.com','QBHASj2wiqXicBu/l+C6+6yaIpHJumu9JNx3Cv091HRXVD3f01P0I2FKtkKEhbK+',NULL,NULL,'Lucia','Regueira','ES','2002-05-08'),(20,'nerea@gmail.com','yfQxvHNqtd7VG8UIdCbSHuG7Dprw3U8aPqZyFmnaOou0gMSPR2untDHMeRkRY/XV',NULL,NULL,'Nerea','García Diéguez','ES','2001-01-05'),(21,'oscar@gmail.com','BViOfA6vrsF3j/4/i+enwj6nZVJutEV4NBGh0B3XnRz4iZdSp8TeI77NnNyismEk',NULL,NULL,'Oscar','Santos','ES','1991-12-08'),(22,'maria@gmail.com','uHQyzX2nxau7UuzhWjPkqdPRrEzY/HJ407YO/YK0iOdO0v3dj6gWpHkcIRrlLwCU',NULL,NULL,'Maria Mercedes','Del Pinar Jimenez','ES','1994-04-03'),(23,'juan@gmail.com','2dZG+4PQDdoiJB6xkeNdhwEvrbnh6CdPQc+gJxqxrHtL1F75aHWwSVswbIeTaFE9',NULL,NULL,'Juan','Deus Vila','ES','1992-10-09'),(24,'raul@gmail.com','W/vtMMlJKjwmUV1GMLH42MpqWUZ7jjgkFA071wcPMcqmOF4i2HofoFQIJj0Lg/Bv',NULL,NULL,'Raúl','Garia Pereira','ES','1996-04-05'),(25,'iago@gmail.com','aWvaF1YFsFctfmoSjRqSLyLFcNrksOXYcVCHFQ9lZ/m9Eu/kswkB6KLL5OxlWHig','Bienvenidos! Me congratula enormemente que hayan decido conocer mis obras. Espero que les encanten!',NULL,'Iago','Seijas Piñeiro','ES','1992-03-02');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario_contenido`
--

LOCK TABLES `usuario_contenido` WRITE;
/*!40000 ALTER TABLE `usuario_contenido` DISABLE KEYS */;
INSERT INTO `usuario_contenido` VALUES (1,10,0,0,7,0,'Este tutorial me ha ayudado muchísimo a sacarle el máximo partido a mi Set. Recomendado.'),(1,11,0,0,7,0,'Este tutorial me ha ayudado muchísimo a sacarle el máximo partido a mi Set. Recomendado.'),(1,12,0,0,7,0,'Este tutorial me ha ayudado muchísimo a sacarle el máximo partido a mi Set. Recomendado.'),(1,13,0,0,7,0,'Este tutorial me ha ayudado muchísimo a sacarle el máximo partido a mi Set. Recomendado.'),(1,14,0,0,7,0,'Este tutorial me ha ayudado muchísimo a sacarle el máximo partido a mi Set. Recomendado.'),(1,26,0,0,9,0,'Me encanta esta canción. Sonaba de fondo cuando mi marido se me declaró y creaba una atmósfera  preciosa. Inolvidable!'),(1,27,0,0,9,0,'Me encanta esta canción. Sonaba de fondo cuando mi marido se me declaró y creaba una atmósfera preciosa. Inolvidable!'),(1,28,0,0,9,0,'Me encanta esta canción. Sonaba de fondo cuando mi marido se me declaró y creaba una atmósfera preciosa. Inolvidable!'),(1,29,0,0,9,0,'Me encanta esta canción. Sonaba de fondo cuando mi marido se me declaró y creaba una atmósfera preciosa. Inolvidable!'),(1,30,0,0,9,0,'Me encanta esta canción. Sonaba de fondo cuando mi marido se me declaró y creaba una atmósfera preciosa. Inolvidable!'),(9,2,0,0,10,0,'Es un documental realmente interesante. Recomendado!'),(9,8,1,0,7,1,'Es genial para ponersela a los niños antes de acostarlos. Recomentadada.'),(16,8,0,0,7,0,'Es genial, a mis niños del encanta, siempre se quedan al mar de tranquilos viéndola.'),(16,15,0,0,6,0,'Es útil para sacar algo de partido al set de Lego, pero hay algunas partes que resultan un poco confusas.'),(16,28,0,0,10,0,'Adoro esta canción.'),(18,3,0,0,9,0,'Me ha encantado, es superdivertido!'),(18,4,0,0,8,0,'Me ha encantado, es superdivertido!!'),(18,5,0,0,8,0,'Me ha encantado, es superdivertido!!'),(18,6,0,0,8,0,'Me ha encantado, es superdivertido!!'),(18,7,0,0,8,0,'Me ha encantado, es superdivertido!!'),(18,8,0,0,0,0,'Me encanta esta serie, es una de mis favoritas!!'),(18,15,0,0,6,0,'Me ha costado entender algunas partes, pero contiene un montón de información útil.'),(18,31,0,0,8,0,'En un documental interesantísimo, y no se hace nada pesado.'),(21,2,0,0,7,0,'Merece la pena verlo.'),(21,3,0,0,7,0,'Merece la pena verlo.'),(21,4,0,0,7,0,'Merece la pena verlo.'),(21,5,0,0,7,0,'Merece la pena verlo.'),(21,6,0,0,7,0,'Merece la pena verlo.'),(21,7,0,0,7,0,'Merece la pena verlo.'),(21,8,0,0,7,0,'Merece la pena verlo.'),(21,10,0,0,7,0,'Merece la pena verlo.'),(21,11,0,0,7,0,'Merece la pena verlo.'),(21,12,0,0,7,0,'Merece la pena verlo.'),(21,13,0,0,7,0,'Merece la pena verlo.'),(21,14,0,0,7,0,'Merece la pena verlo.'),(21,15,0,0,7,0,'Merece la pena verlo.'),(21,26,0,0,7,0,'Merece la pena verlo.'),(21,27,0,0,7,0,'Merece la pena verlo.'),(21,28,0,0,7,0,'Merece la pena verlo.'),(21,29,0,0,7,0,'Merece la pena verlo.'),(21,30,0,0,7,0,'Merece la pena verlo.'),(21,31,0,0,7,0,'Merece la pena verlo.'),(21,32,0,0,7,0,'Merece la pena verlo.'),(21,33,0,0,7,0,'Merece la pena verlo.'),(21,34,0,0,7,0,'Merece la pena verlo.'),(21,35,0,0,7,0,'Merece la pena verlo.'),(21,36,0,0,7,0,'Merece la pena verlo.'),(25,1,1,0,0,0,NULL),(25,2,0,0,0,1,NULL),(25,8,1,0,8,1,NULL),(25,9,1,0,0,0,NULL),(25,10,0,0,0,1,NULL),(25,15,1,0,6,1,NULL),(25,20,1,0,0,0,NULL),(25,21,1,0,0,0,NULL),(25,31,0,0,7,0,NULL),(25,32,0,0,0,1,NULL),(25,33,0,0,0,1,NULL),(25,35,0,0,5,0,NULL);
/*!40000 ALTER TABLE `usuario_contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (2,1,'En este documental podemos descubirr más aceca de los hábitos d elo elefantes.','C:\\videos\\1\\2.mp4'),(3,1,'Divertida serie de anamación para toda la familia protagonizada por un conejo bonachón. Primer episodio.','C:\\videos\\1\\3.mp4'),(4,1,'Divertida serie de animación para toda la familia protagonizada por un conejo bonachón. Segundo episodio.','C:\\videos\\1\\4.mp4'),(5,1,'Divertida serie de animación para toda la familia protagonizada por un conejo bonachón. Tercer episodio. episodio.','C:\\videos\\1\\5.mp4'),(6,1,'Divertida serie de anamación para toda la familia protagonizada por un conejo bonachón. Cuarto episodio.','C:\\videos\\1\\6.mp4'),(7,1,'Divertida serie de anamación para toda la familia protagonizada por un conejo bonachón. Quinto episodio.','C:\\videos\\1\\7.mp4'),(10,9,'Initiation to Lego Engineering','C:\\videos\\9\\10.mp4'),(11,9,'How to make your own Lego rotor.','C:\\videos\\9\\11.mp4'),(12,9,'How to make your own Lego gyroscope.','C:\\videos\\9\\12.mp4'),(13,9,'How to make your own Shunt Wound motor with Lego.','C:\\videos\\9\\13.mp4'),(14,9,'It\'s time to learn how to combine complex Lego structures to do something more useful.','C:\\videos\\9\\14.mp4'),(26,25,'Track 1','C:\\videos\\25\\26.mp4'),(27,25,'Track 2','C:\\videos\\25\\27.mp4'),(28,25,'Track 3','C:\\videos\\25\\28.mp4'),(29,25,'Track 5','C:\\videos\\25\\29.mp4'),(30,25,'Track 6','C:\\videos\\25\\30.mp4'),(31,1,'Documental sobre paquidermos.','C:\\videos\\1\\31.mp4'),(32,1,'Documental sobre los animales que habitan en África','C:\\videos\\1\\32.mp4'),(33,1,'Documental que pretende concienciar a la población sobre el daño que los furtivos y la caza ilegal les están haciendo a los ecosistemas del planeta.','C:\\videos\\1\\33.mp4'),(34,1,'Un tour por África','C:\\videos\\1\\34.mp4'),(35,25,'Corto realista sobre el día a día en la ciudad de Moratalá.','C:\\videos\\25\\35.mp4'),(36,16,'Thriller sobre los últimos días en la calles de un policía de la vieja escuela a punto de retirarse.','C:\\videos\\16\\36.mp4'),(37,16,'Humor Negro. Historia acerca la las desventuras de un antiguo timador caído en desgracia.','C:\\videos\\16\\37.mp4'),(38,16,'La gran semana del exitoso dueño y director del Gran Casino','C:\\videos\\16\\38.mp4'),(39,16,'Vida cotidiana, pero aún así interesante, de una mujer corriente a cargo de la gerencia de una sección tipicamente masculina','C:\\videos\\16\\39.mp4');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `video_lista`
--

LOCK TABLES `video_lista` WRITE;
/*!40000 ALTER TABLE `video_lista` DISABLE KEYS */;
INSERT INTO `video_lista` VALUES (3,8,1),(4,8,2),(5,8,3),(6,8,4),(7,8,5),(10,15,1),(11,15,3),(12,15,4),(13,15,2),(14,15,5);
/*!40000 ALTER TABLE `video_lista` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-12 21:53:15
