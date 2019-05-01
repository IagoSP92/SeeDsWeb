USE SEEDS;

INSERT INTO IDIOMA (ID_IDIOMA)
VALUES ('ES'), ('EN');

INSERT INTO PAIS (ID_PAIS/*, NOMBRE_PAIS*/)
VALUES
('AD'/*, 'ANDORRA'*/),
('DE'/*, 'ALEMANIA'*/),
('ES'/*, 'ESPAÑA'*/),
('FR'/*, 'FRANCIA'*/),
('GB'/*, 'REINO UNIDO'*/),
('IT'/*, 'ITALIA'*/),
('PT'/*, 'PORTUGAL'*/),
('US'/*, 'ESTADOS UNIDOS'*/);

INSERT INTO PAIS_IDIOMA (ID_IDIOMA, ID_PAIS, NOMBRE_PAIS)
VALUES
('ES', 'AD', 'ANDORRA'),
('ES', 'DE', 'ALEMANIA'),
('ES', 'ES', 'ESPAÑA'),
('ES', 'FR', 'FRANCIA'),
('ES', 'GB', 'REINO UNIDO'),
('ES', 'IT', 'ITALIA'),
('ES', 'PT', 'PORTUGAL'),
('ES', 'US', 'ESTADOS UNIDOS'),
('EN', 'AD', 'ANDORRA'),
('EN', 'DE', 'GERMANY'),
('EN', 'ES', 'SPAIN'),
('EN', 'FR', 'FRANCE'),
('EN', 'GB', 'UNITED KINGDOM'),
('EN', 'IT', 'ITALY'),
('EN', 'PT', 'PORTUGAL'),
('EN', 'US', 'UNITED STATES');

INSERT INTO CATEGORIA (ID_CATEGORIA/*, NOMBRE_CATEGORIA*/)
VALUES
('1'/*, 'VIDEOCLIPS'*/),
('2'/*, 'SERIES'*/),
('3'/*, 'CORTOS'*/),
('4'/*, 'DOCUMENTAL'*/),
('5'/*, 'GUIAS'*/);

INSERT INTO CATEGORIA_IDIOMA (ID_IDIOMA, ID_CATEGORIA, NOMBRE_CATEGORIA)
VALUES
('ES', '1', 'VIDEOCLIPS'),
('ES', '2', 'SERIES'),
('ES', '3', 'CORTOS'),
('ES', '4', 'DOCUMENTAL'),
('ES', '5', 'GUIAS'),
('EN', '1', 'VIDEOCLIPS'),
('EN', '2', 'SERIES'),
('EN', '3', 'SHOT FILM'),
('EN', '4', 'DOCUMENTARY'),
('EN', '5', 'GUIDES');

INSERT INTO ETIQUETA (ID_ETIQUETA/*, NOMBRE_ETIQUETA*/)
VALUES
('1'/*, 'ACCION'*/),
('2'/*, 'DRAMA'*/),
('3'/*, 'ANIMACION'*/),
('4'/*, 'COMEDIA'*/),
('5'/*, 'ROMANTICISMO'*/),
('6'/*, 'JAZZ'*/),
('7'/*, 'SOUL'*/),
('8'/*, 'POP'*/),
('9'/*, 'ROCK'*/),
('10'/*, 'BLUES'*/),
('11'/*, 'TECHNO'*/),
('12'/*, 'HOUSE'*/),
('13'/*, 'BELICO'*/),
('14'/*, 'AVENTURA'*/),
('15'/*, 'HISTORICO'*/),
('16'/*, 'TECNOLOGIA'*/),
('17'/*, 'NATURALEZA'*/),
('18'/*, 'OCEANOGRAFIA'*/),
('19'/*, 'PROGRAMACION'*/),
('20'/*, 'JAVA'*/),
('21'/*, 'CLASICA'*/);

INSERT INTO ETIQUETA_IDIOMA (ID_IDIOMA, ID_ETIQUETA, NOMBRE_ETIQUETA)
VALUES
('ES', '1', 'ACCION'),
('ES', '2', 'DRAMA'),
('ES', '3', 'ANIMACION'),
('ES', '4', 'COMEDIA'),
('ES', '5', 'ROMANTICISMO'),
('ES', '6', 'JAZZ'),
('ES', '7', 'SOUL'),
('ES', '8', 'POP'),
('ES', '9', 'ROCK'),
('ES', '10', 'BLUES'),
('ES', '11', 'TECHNO'),
('ES', '12', 'HOUSE'),
('ES', '13', 'BELICO'),
('ES', '14', 'AVENTURA'),
('ES', '15', 'HISTORICO'),
('ES', '16', 'TECNOLOGIA'),
('ES', '17', 'NATURALEZA'),
('ES', '18', 'OCEANOGRAFIA'),
('ES', '19', 'PROGRAMACION'),
('ES', '20', 'JAVA'),
('ES', '21', 'CLASICA'),
('EN', '1', 'ACCTION'),
('EN', '2', 'DRAMA'),
('EN', '3', 'ANIMATION'),
('EN', '4', 'COMEDY'),
('EN', '5', 'ROMANCE'),
('EN', '6', 'JAZZ'),
('EN', '7', 'SOUL'),
('EN', '8', 'POP'),
('EN', '9', 'ROCK'),
('EN', '10', 'BLUES'),
('EN', '11', 'TECHNO'),
('EN', '12', 'HOUSE'),
('EN', '13', 'WAR'),
('EN', '14', 'ADVENTURE'),
('EN', '15', 'HISTORY'),
('EN', '16', 'TECNOLOGY'),
('EN', '17', 'NATURE'),
('EN', '18', 'OCEAN'),
('EN', '19', 'PROGRAMMING'),
('EN', '20', 'JAVA'),
('EN', '21', 'CLASIC');



INSERT INTO CONTENIDO ( NOMBRE, FECHA_ALTA, FECHA_MOD, AUTOR_ID_CONTENIDO, TIPO, REPRODUCCIONES)
VALUES
('Ana', STR_TO_DATE('15/01/01','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/02/01','%d/%m/%Y %H:%i:%s') , NULL, 1,  '10'),
('Bea', STR_TO_DATE('15/06/01','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/06/01','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Cristina', STR_TO_DATE('15/12/01','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/12/01','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('David', STR_TO_DATE('01/01/01','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('01/01/01', '%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Enrique', STR_TO_DATE('15/01/01','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/01/01','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Facundo', STR_TO_DATE('30/01/01','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('30/01/01','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Gonzalo', STR_TO_DATE('15/01/02','%d/%m/%Y %H:%i:%s'),STR_TO_DATE('15/01/02','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Helena', STR_TO_DATE('15/01/03','%d/%m/%Y %H:%i:%s'),STR_TO_DATE('15/01/03','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Iago', STR_TO_DATE('15/01/04','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/01/04','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Javier', STR_TO_DATE('15/01/05','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/01/05','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Kevin', STR_TO_DATE('15/06/05','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/06/05','%d/%m/%Y %H:%i:%s'), NULL, 1,  '15'),
('Lucia', STR_TO_DATE('15/12/05','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/12/05','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Maria', STR_TO_DATE('10/01/06','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/01/06','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Nerea', STR_TO_DATE('20/01/06','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('20/01/06','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Oscar', STR_TO_DATE('15/01/07','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/01/07','%d/%m/%Y %H:%i:%s'), NULL, 1,  '11'),
('Patricia', STR_TO_DATE('15/08/01','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/08/01','%d/%m/%Y %H:%i:%s'), NULL, 1,  '9'),
('Quique', STR_TO_DATE('15/01/09','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/01/09','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Rocio', STR_TO_DATE('20/01/09','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('20/01/09','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Silvia', STR_TO_DATE('15/02/09','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('15/02/09','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10'),
('Tania', STR_TO_DATE('20/02/09','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('20/02/09','%d/%m/%Y %H:%i:%s'), NULL, 1,  '10');

INSERT INTO USUARIO (ID_CONTENIDO, EMAIL, CONTRASENA, DESCRIPCION, URL_AVATAR, NOMBRE_REAL, APELLIDOS, ID_PAIS, fecha_nac)
VALUES
( '1','ana1@gmail.com',       '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'ANA',      'LEDO PIÑEIRO', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
( '2','bea2@gmail.com',       '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'BEA',      'SEIJAS PIÑEIRO', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
( '3','cristina3@gmail.com',  '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'CRISTINA', 'RAMOS LAMAS', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
( '4','david4@gmail.com',     '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'DAVID',    'MONTES VAZQUEZ', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
( '5','enrique5@gmail.com',   '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'ENRIQUE',  'LAMELA GARCIA', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s') ),
( '6','facundo6@gmail.com',   '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'FACUNDO',  'PIPAS PAJARO', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
( '7','gonzalo7@gmail.com',   '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'GONZALO',  'FALGUERA PEREZ', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
( '8','helena8@gmail.com',    '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'HELENA',   'LEDO DOVAL', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
( '9','iago9@gmail.com',      '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'IAGO',     'MONTES VAZQUEZ', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s') ),
('10','javier10@gmail.com',   '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'JAVIAR',   'DIAZ MARTINEZ', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
('11','kevin11@gmail.com',    '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'KEVIN',    'GARCIA BARCO', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
('12','lucia12@gmail.com',    '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'LUCIA',    'ALMUIÑA LOPEZ', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
('13','maria13@gmail.com',    '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'MARIA',    'VAZQUEZ PEREZ', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s') ),
('14','nerea14@gmail.com',    '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'NEREA',    'LOPEZ DIEGUEZ', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
('15','oscar15@gmail.com',    '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'OSCAR',    'SANTOS LOPEZ', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s') ),
('16','patricia16@gmail.com', '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'PATRICIA', 'CASTIÑEIRA GUILLEN', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s') ),
('17','quique17@gmail.com',   '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'QUIQUE',   'APER ITIVO', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
('18','rocio18@gmail.com',    '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'ROCIO',    'GARCIA DIEGUEZ', 'ES' , STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s')),
('19','silvia19@gmail.com',   '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'SILVIA',   'RIOS GARCIA', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s') ),
('20','tania20@gmail.com',    '123456','Hola! Disfruten de mis videos!','C:\\img.jpg', 'TANIA',    'LEDO BORGES', 'ES', STR_TO_DATE('15/02/90','%d/%m/%Y %H:%i:%s') );

INSERT INTO CONTENIDO (NOMBRE, FECHA_ALTA, FECHA_MOD, AUTOR_ID_CONTENIDO, TIPO, REPRODUCCIONES)
VALUES
('Los Sampsun ep1', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), 1, 2,  10),
('Los Sampsun ep2', STR_TO_DATE('10/11/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/11/10','%d/%m/%Y %H:%i:%s'), '1', 2,  '10'),
('Los Sampsun ep3', STR_TO_DATE('10/12/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/12/10','%d/%m/%Y %H:%i:%s'), '1', 2,  '10'),
('Los Sampsun ep4', STR_TO_DATE('10/01/11','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/01/11','%d/%m/%Y %H:%i:%s'), '1', 2,  '10'),
('Los Sampsun ep5',	STR_TO_DATE('10/02/11','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/02/11','%d/%m/%Y %H:%i:%s'), '1', 2,  '20'),
('Los Sampsun', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '1', 3,  '10'),
('Pim Pam Theory ep1', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '2', 2,  '30'),
('Pim Pam Theory ep2', STR_TO_DATE('10/11/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/11/10','%d/%m/%Y %H:%i:%s'), '2', 2,  '10'),
('Pim Pam Theory ep3', STR_TO_DATE('10/12/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/12/10','%d/%m/%Y %H:%i:%s'), '2', 2,  '10'),
('Pim Pam Theory ep4', STR_TO_DATE('10/01/11','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/01/11','%d/%m/%Y %H:%i:%s'), '2', 2,  '10'),
('Pim Pam Theory ep5', STR_TO_DATE('10/02/11','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/02/11','%d/%m/%Y %H:%i:%s'), '2', 2,  '10'),
('Pim Pam Theory', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '2', 3,  '40'),
('Remix Lacasitos', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '3', 2,  '50'),
('Bailar pegados es rozar', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '4', 2,  '10'),
('Cuatro Estaciones con Piña', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '5', 2,  '10'),
('El Pachino', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '6', 2,  '10'),
('Ritual de Reproduccionn de Gamusino', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '7', 2,  '10'),
('El mundo se ahoga', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '8', 2,  '10'),
('Clases en Java', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '9', 2,  '10'),
('Herencia en Java', STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), STR_TO_DATE('10/10/10','%d/%m/%Y %H:%i:%s'), '10', 2,  '10');


INSERT INTO VIDEO (ID_CONTENIDO, USUARIO_ID_CONTENIDO, DESCRIPCION, URL_VIDEO)
VALUES
('21', '1', 'Primer capitulo de los Sansun',  'C:\\videos\\999\\999.mp4'),
('22', '1', 'Segundo capitulo de los Sansun', 'C:\\videos\\999\\999.mp4'),
('23', '1', 'Tercer capitulo de los Sansun',  'C:\\videos\\999\\999.mp4'),
('24', '1', 'Cuarto capitulo de los Sansun',  'C:\\videos\\999\\999.mp4'),
('25', '1', 'Quinto capitulo de los Sansun',  'C:\\videos\\999\\999.mp4'),

('27', '2', 'Primer capitulo de Pim Pam Theory ep1',  'C:\\videos\\999\\999.mp4'),
('28', '2', 'Segundo capitulo de Pim Pam Theory ep2', 'C:\\videos\\999\\999.mp4'),
('29', '2', 'Tercer capitulo de Pim Pam Theory ep3',  'C:\\videos\\999\\999.mp4'),
('30', '2', 'Cuarto capitulo de Pim Pam Theory ep4',  'C:\\videos\\999\\999.mp4'),
('31', '2', 'Quinto capitulo de Pim Pam Theory ep5',  'C:\\videos\\999\\999.mp4'),

('33', '3', 'Remix Lacasitos','C:\\videos\\999\\999.mp4'),
('34', '4', 'Bailar pegados es rozar',  'C:\\videos\\999\\999.mp4'),
('35', '5', 'Cuatro Estaciones con Piña',  'C:\\videos\\999\\999.mp4'),
('36', '6', 'El Pachino', 'C:\\videos\\999\\999.mp4'),
('37', '7', 'Ritual de Reproduccionn de Gamusino', 'C:\\videos\\999\\999.mp4'),
('38', '8', 'El mundo se ahoga', 'C:\\videos\\999\\999.mp4'),
('39', '9', 'Clases en Java', 'C:\\videos\\999\\999.mp4'),
('40', '9', 'Herencia en Java', 'C:\\videos\\999\\999.mp4');

INSERT INTO LISTA (ID_CONTENIDO, USUARIO_ID_CONTENIDO, DESCRIPCION, PUBLICA)
VALUES
('26', '1', 'Los Sampsun: Aventuras de disparatada familia',    '1'),
('32', '2', 'Pim Pam Theory: Aventuras de disparatados amigos', '1');

INSERT INTO CATEGORIA_CONTENIDO (ID_CATEGORIA, ID_CONTENIDO)
VALUES
('2', '21'),
('2', '22'),
('2', '23'),
('2', '24'),
('2', '25'),
('2', '26'),
('2', '27'),
('2', '28'),
('2', '29'),
('2', '30'),
('2', '31'),
('2', '32'),
('1', '33'),
('1', '34'),
('1', '35'),
('3', '36'),
('4', '37'),
('4', '38'),
('5', '39'),
('5', '40');

INSERT INTO ETIQUETA_CONTENIDO (ID_ETIQUETA, ID_CONTENIDO)
VALUES
('1', '36'),
('2', '36'),
('3', '21'),
('3', '22'),
('3', '23'),
('3', '24'),
('3', '25'),
('3', '26'),
('3', '27'),
('3', '28'),
('3', '29'),
('3', '30'),
('3', '31'),
('3', '32'),
('4', '21'),
('4', '22'),
('4', '23'),
('4', '24'),
('4', '25'),
('4', '26'),
('4', '27'),
('4', '28'),
('4', '29'),
('4', '30'),
('4', '31'),
('4', '32'),
('8', '34'),
('11', '33'),
('12', '33'),
('16', '39'),
('16', '40'),
('17', '37'),
('17', '38'),
('18', '38'),
('19', '39'),
('19', '40'),
('20', '39'),
('20', '40'),
('21', '35');

INSERT INTO VIDEO_LISTA (VIDEO_ID_CONTENIDO, LISTA_ID_CONTENIDO, POSICION)
VALUES
('21', '26', '1'),
('22', '26', '2'),
('23', '26', '3'),
('24', '26', '4'),
('25', '26', '5'),
('27', '32', '1'),
('28', '32', '2'),
('29', '32', '3'),
('30', '32', '4'),
('31', '32', '5');

INSERT INTO USUARIO_CONTENIDO (USUARIO_ID_CONTENIDO, CONTENIDO_ID_CONTENIDO, SIGUIENDO, DENUNCIADO, VALORACION, GUARDADO, COMENTARIO)
VALUES
('10', '21', NULL, NULL, '8', '0', 'Esta muy bien'),
('11', '21', NULL, NULL, '7', '0', 'Esta BASTANTE BIEN'),
('1', '21', NULL, NULL, '2', '0', 'MALISIMO'),
('12', '21', NULL, NULL, '10', '1', 'EXCELENTE,ME HA ENCANTADO'),
('5', '26', NULL, NULL, NULL, NULL, NULL),
('5', '1', NULL, NULL, NULL, NULL, NULL),
('5', '21', NULL, NULL, NULL, NULL, NULL),
('1', '5', NULL, NULL, NULL, NULL, NULL),
('2', '26', '1', NULL, '10', NULL, 'ME ENCANTA ESTA SERIE'),
('2', '32', '1', NULL, NULL, NULL, NULL),
('2', '1', '1', NULL, NULL, NULL, NULL),
('2', '2', '1', NULL, NULL, NULL, NULL),
('3', '26', '1', NULL, NULL, '1', NULL);

INSERT INTO USUARIO_CONTENIDO (USUARIO_ID_CONTENIDO, CONTENIDO_ID_CONTENIDO, SIGUIENDO, DENUNCIADO, VALORACION, GUARDADO, COMENTARIO)
VALUES

('7', '27', '1', NULL, NULL, NULL, NULL),
('7', '29', '1', NULL, NULL, NULL, NULL),
('7', '30', '1', NULL, NULL, NULL, NULL),
('7', '31', '1', NULL, NULL, NULL, NULL),
('7', '28', '1', NULL, NULL, '1', NULL);

