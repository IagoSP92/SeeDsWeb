
CREATE SCHEMA `seeds` ;
USE SEEDS;

CREATE TABLE IDIOMA (
ID_IDIOMA		VARCHAR(3),
PRIMARY KEY (ID_IDIOMA)
);

CREATE TABLE ETIQUETA (
ID_ETIQUETA				INT AUTO_INCREMENT PRIMARY KEY/*,
NOMBRE_ETIQUETA			VARCHAR(64) NOT NULL*/
);

CREATE TABLE PAIS (
ID_PAIS			VARCHAR(2) PRIMARY KEY/*,
NOMBRE_PAIS		VARCHAR(64) NOT NULL*/
);

CREATE TABLE CATEGORIA (
ID_CATEGORIA			INT AUTO_INCREMENT PRIMARY KEY/*,
NOMBRE_CATEGORIA		VARCHAR(64) NOT NULL*/
);

CREATE TABLE CATEGORIA_IDIOMA (
ID_IDIOMA				VARCHAR(3),
ID_CATEGORIA			INT,
NOMBRE_CATEGORIA		VARCHAR(64) NOT NULL,
PRIMARY KEY (ID_CATEGORIA, ID_IDIOMA),
FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIA (ID_CATEGORIA),
FOREIGN KEY (ID_IDIOMA) REFERENCES IDIOMA (ID_IDIOMA)
);

CREATE TABLE ETIQUETA_IDIOMA (
ID_IDIOMA				VARCHAR(3),
ID_ETIQUETA				INT,
NOMBRE_ETIQUETA			VARCHAR(64), /* PODE SER NULL PORQUE AS ETIQUETAS AS CREAR OS USUARIOS */
PRIMARY KEY (ID_ETIQUETA, ID_IDIOMA),
FOREIGN KEY (ID_ETIQUETA) REFERENCES ETIQUETA (ID_ETIQUETA),
FOREIGN KEY (ID_IDIOMA) REFERENCES IDIOMA (ID_IDIOMA)
);

CREATE TABLE PAIS_IDIOMA (
ID_IDIOMA		VARCHAR(3),
ID_PAIS			VARCHAR(2),
NOMBRE_PAIS		VARCHAR(64) NOT NULL,
PRIMARY KEY (ID_PAIS, ID_IDIOMA),
FOREIGN KEY (ID_PAIS) REFERENCES PAIS (ID_PAIS),
FOREIGN KEY (ID_IDIOMA) REFERENCES IDIOMA (ID_IDIOMA)
);

CREATE TABLE CONTENIDO (
ID_CONTENIDO			INT AUTO_INCREMENT NOT NULL,
NOMBRE					VARCHAR(64) NOT NULL,
FECHA_ALTA				DATE  NOT NULL,
FECHA_MOD				DATE  NOT NULL,
AUTOR_ID_CONTENIDO		INT,
TIPO					INT   NOT NULL,
REPRODUCCIONES			INT NOT NULL,
FOREIGN KEY (AUTOR_ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO),
PRIMARY KEY (ID_CONTENIDO)
);


CREATE TABLE USUARIO (
ID_CONTENIDO			INT NOT NULL,
EMAIL					VARCHAR(255) NOT NULL,
CONTRASENA				VARCHAR(256) NOT NULL,
DESCRIPCION				VARCHAR(10000),
URL_AVATAR				VARCHAR(255),
NOMBRE_REAL				VARCHAR(64) NOT NULL,
APELLIDOS				VARCHAR(128) NOT NULL,
ID_PAIS					VARCHAR(2) NOT NULL,
FECHA_NAC				DATE  NOT NULL,
PRIMARY KEY (ID_CONTENIDO),
FOREIGN KEY (ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO),
FOREIGN KEY (ID_PAIS) REFERENCES PAIS (ID_PAIS)
);

CREATE TABLE VIDEO (
ID_CONTENIDO			INT NOT NULL,
USUARIO_ID_CONTENIDO	INT NOT NULL,
DESCRIPCION				VARCHAR(10000),
URL_VIDEO				VARCHAR(256),
PRIMARY KEY (ID_CONTENIDO),
FOREIGN KEY (ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO),
FOREIGN KEY (USUARIO_ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO)
);

CREATE TABLE LISTA (
ID_CONTENIDO			INT NOT NULL,
USUARIO_ID_CONTENIDO	INT NOT NULL,
DESCRIPCION				VARCHAR(10000),
PUBLICA					TINYINT,
PRIMARY KEY (ID_CONTENIDO),
FOREIGN KEY (ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO),
FOREIGN KEY (USUARIO_ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO)
);

CREATE TABLE VIDEO_LISTA (
VIDEO_ID_CONTENIDO		INT NOT NULL,
LISTA_ID_CONTENIDO		INT NOT NULL,
POSICION				INT NOT NULL,
PRIMARY KEY (LISTA_ID_CONTENIDO, VIDEO_ID_CONTENIDO),
FOREIGN KEY (LISTA_ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO),
FOREIGN KEY (VIDEO_ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO)
);

CREATE TABLE ETIQUETA_CONTENIDO (
ID_ETIQUETA				INT NOT NULL,
ID_CONTENIDO			INT NOT NULL,
PRIMARY KEY (ID_ETIQUETA, ID_CONTENIDO),
FOREIGN KEY (ID_ETIQUETA) REFERENCES ETIQUETA (ID_ETIQUETA),
FOREIGN KEY (ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO)
);

CREATE TABLE CATEGORIA_CONTENIDO (
ID_CATEGORIA			INT NOT NULL,
ID_CONTENIDO			INT NOT NULL,
PRIMARY KEY (ID_CATEGORIA, ID_CONTENIDO),
FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIA (ID_CATEGORIA),
FOREIGN KEY (ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO)
);

CREATE TABLE USUARIO_CONTENIDO (
USUARIO_ID_CONTENIDO	INT NOT NULL,
CONTENIDO_ID_CONTENIDO	INT NOT NULL,
SIGUIENDO				TINYINT,
DENUNCIADO				TINYINT,
VALORACION				INT,
GUARDADO				TINYINT,
COMENTARIO				VARCHAR(2000),
PRIMARY KEY (USUARIO_ID_CONTENIDO, CONTENIDO_ID_CONTENIDO),
FOREIGN KEY (USUARIO_ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO),
FOREIGN KEY (CONTENIDO_ID_CONTENIDO) REFERENCES CONTENIDO (ID_CONTENIDO)
);