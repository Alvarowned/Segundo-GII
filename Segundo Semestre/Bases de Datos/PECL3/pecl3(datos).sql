INSERT INTO public."Formato" ("Referencia","Tipo") 
VALUES (1,'CD'),
(2,'LP'),
(3,'CD'),
(4,'LP'),
(5,'CD'),
(6,'LP'),
(7,'CD'),
(8,'LP'),
(9,'CD'),
(10,'LP'),
(11,'CD'),
(12,'LP'),
(13,'LP'),
(14,'LP'),
(15,'MP3'),
(16,'AAC'),
(17,'WMA'),
(18,'FLAC'),
(19,'MP3'),
(20,'AAC'),
(21,'WMA'),
(22,'FLAC'),
(23,'MP3'),
(24,'AAC'),
(25,'WMA'),
(26,'FLAC'),
(27,'MP3'),
(28,'AAC');

INSERT INTO public."Formato Digital" ("Tamaño_Mbytes","Referencia","Tipo") 
VALUES (15,15,'MP3'),
(20,16,'AAC'),
(12,17,'WMA'),
(19,18,'FLAC'),
(16,19,'MP3'),
(18,20,'AAC'),
(13,21,'WMA'),
(11,22,'FLAC'),
(14,23,'MP3'),
(21,24,'AAC'),
(10,25,'WMA'),
(22,26,'FLAC'),
(23,27,'MP3'),
(24,28,'AAC');

INSERT INTO public."Formato Físico" ("Referencia","Tipo") 
VALUES (1,'CD'),
(2,'LP'),
(3,'CD'),
(4,'LP'),
(5,'CD'),
(6,'LP'),
(7,'CD'),
(8,'LP'),
(9,'CD'),
(10,'LP'),
(11,'CD'),
(12,'LP'),
(13,'LP'),
(14,'LP');

INSERT INTO Public."Grupo" ("Código_grupo","Nombre_grupo","Género")
VALUES 
(1,'Fondo Flamenco','Flamenco'),
(2,'Louis Armstrong','Jazz'),
(3,'Efecto Pasillo','Pop'),
(4,'ACDC','Rock'),
(5,'Iron Maiden','Heavy'),
(6,'BB King','Blues'),
(7,'Wolfang Amadeus Mozart','Clásica');

INSERT INTO Public."Concierto" ("Código_concierto", "Fecha")
VALUES (1, '16-06-2015'),
(2, '19-06-2017'),
(3, '03-03-2018'),
(4, '11-10-2005'),
(5, '29-12-2008'),
(6, '06-12-1998'),
(7, '27-04-2001');

INSERT INTO Public."Sitio Concierto"("Código_concierto_Concierto","País","Ciudad","Recinto")
VALUES(1,'Estados Unidos','Nueva York','Madison Triangle Garden'),
(2,'Inglaterra', 'Londres','O3 Arena'),
(3,'España', 'Madrid','Wozink Center'),
(4,'Inglaterra', 'Manchester' ,'Manchaster Arena'),
(5,'EEUU', 'Nueva York','Barcleys Center'),
(6,'España', 'Madrid','Palacio de Vistatriste'),
(7,'Australia', 'Sydney','Somephones Arena');

INSERT INTO Public."Grupo_da_Concierto" ("Código_grupo_Grupo","Código_concierto_Concierto")
Values(1,1),
(1,3),
(1,5),
(2,1),
(2,7),
(2,6),
(3,1),
(3,4),
(3,5),
(3,6),
(3,7),
(4,1),
(4,3),
(4,4),
(4,6),
(5,1),
(5,2),
(5,3),
(5,5),
(5,7),
(6,2),
(6,3),
(6,4),
(6,6),
(6,7),
(7,2),
(7,5),
(7,6);

INSERT INTO Public."Usuario"("DNI_usuario","Nombre_usuario","Apellidos_usuario","Correo_electrónico","Puntos")
VALUES ('12595629R','Charlie','Walls','cwalls@gmail.com',100),
('30398427V','Jose Antonio','Lozano','Josean@gmail.com',150),
('00665757E','Armando','Blazquez','armandovarullo@gmail.com',25),
('14560607C','German','Velazquez','germanpintor@gmail.com',200),
('58819222B','Bruno','Cortes','Bruneitor@gmail.com',0),
('43422293H','Sofia','Altron','soysofi@gmail.com',125),
('27814752R','Rebeca','Rodriguez','Rbcrodri@gmail.com',100),
('01435787N','Mar','Echeverria','echevemar@gmail.com',75),
('39278938K','Malena','Ferrera','missmalena@gmail.com',50),
('35251416Y','Oscar','Simon','O4Csimon@gmail.com',25),
('59398930G','Francisco','Margot','francisquillo11@gmail.com',225),
('96744568J','Rosa','Estrigena','Rossestrie@gmail.com',100),
('83527993C','Alberto','Trinidad','albertrini@gmail.com',25),
('74210404F','David','Canales','dcanales@gmail.com',0),
('50572557L','Marina','Arribas','marribas@gmail.com',50),
('20207055Z','Livia','Nievas','Livitas@gmail.com',125);

INSERT INTO Public."Músico" ("DNI_músico","Apellidos_músico","Nombre_músico","Código_grupo_Grupo")
VALUES ('23437671G','Sanchez','Laura',2),--JAZZ
('77160507S','Perez','Jorge',4),--ROCK
('01552057V','Castro','Adrian',1),--FLAMENCO
('31209682Q','Castellano','Sergio',6),--TECNO
('38279041A','Moreno','Carlos',2),--JAZZ
('19212547A','Garcia','Sofia',5),--HEAVY
('94826621J','Hernandez','Guillermo',2),--JAZZ
('03618525G','Gomez','Juan',5),--HEAVY
('70712099W','Castillejo','Sara',2),--JAZZ
('29255679R','Cebrian','Clara',7),--CLASICA
('54008405N','Cabello','Adrian',4),--ROCK
('43099759J','Sanz','Sergio',2),--JAZZ
('76654052C','Jimenez','Alba',5),--HEAVY
('79887423M','Tejada','Javier',7),--CLASICA
('38034721N','Cabello','Antonio',3),--POP
('06747148Y','Alamo','Blanca',7),--CLASICA
('29027790L','Calvo','Jesus',3),--POP
('44016307B','Castrillo','Irene',3),--SOLO
('95029382Y','Rosales','Alvaro',5),--HEAVY
('17289546D','De la Rosa','Alejandro',5),--SOLO
('12665696X','Serrano','Claudia',7),--SOLO
('67108877Y','Gonzalez','Alejandra',3),--SOLO
('14969909Z','Marin','Martin',1),--FLAMENCO
('85685209C','Rivas','Luis',1),--FLAMENCO
('30849182H','Olmo','Manuel',4),--ROCK
('75360020Z','Leon','Leon',6),--TECNO
('59463092L','Ceballos','Marta',2),--SOLO
('01843123H','Ramos','Roberto',4),--ROCK
('70209112W','Carvajal','Alberto',4),--Rock
('63123162F','Mercury','Freddie',4);--Rock

INSERT INTO Public."Disco"("Num_referencia","Género","Título","Fecha_edición","Referencia_Formato")
VALUES  (1,'Rock','Perdedor','01-01-2010',1),
(2,'Flamenco','La cuarta pantalla','15-02-1956',2),
(3,'Pop','Que lujos','10-10-2007',3),
(4,'Funk','Hello','14-09-2017',4),
(5,'Samba','Por la cara','06-03-2004',5),                        --1FLAMENCO 2JAZZ 3POP 4ROCK 5HEAVY 6TECNO 7CLASICA
(6,'Indie','Algun dia...','23-02-1970',6),
(7,'Folk','Por ti, por mi,por ellos','23-02-1977',7),
(8,'Metal','Controlador','18-12-1990',8),
(9,'Flamenco','Ir yendo','03-03-2010',9),
(10,'Fusion','La despedida','19-12-2000',10),
(11,'World Music','Hasta luego','23-11-1900',11),
(12,'Jazz','White celebration','30-12-2002',12),
(13,'Heavy Metal','Tierra','22-06-1995',13),
(14,'Rap','False','15-11-2011',14),
(15,'Rock','Blue rain','01-02-1970',15),
(16,'Progresivo','Never young','14-10-2007',16),
(17,'Rock Urbano','Viento calmado','23-12-2005',17),
(18,'Punk','Queen of beasts','12-09-1987',18),
(19,'Electronica','Zero','14-12-1996',19),
(20,'Pop','Antartis','02-05-2000',20),
(21,'Indie','El disco feo','07-07-1990',21),
(22,'Surf Rock','A cualquier parte','04-07-2010',22),
(23,'Ska','Vallecanos','15-12-2011',23),
(24,'Flamenco','Riosia','03-06-2000',24),
(25,'Rap','La oscuridad','15-05-2003',25),
(26,'Rock','Retroexposicion','23-02-1995',26),
(27,'Pop','La monda','10-09-2009',27),
(28,'Pop','Novio','23-02-1977',28);

INSERT INTO Public."Canción" ("Nombre_canción","Fecha_grabación","Duración","Num_referencia_Disco")
Values ('Hijo del sol','27/8/2016',189,1),
('A jesus le pido','27/8/2016',177,1),
('Me doy por vencido','27/8/2016',200,1),
('Y nos dieron las 11','05/01/1998',197,2),
('Corazon roto','05/01/1998',221,2),
('Con miedo a todo','08/08/2012',180,2),
('Entre dos mares','08/08/2012',171,3),
('Ocupado','08/08/2012',199,3),
('Cantabrico','08/08/2012',167,3),
('Hombre lobo en Paris','04/12/2000',550,3),--
('La gorda','04/12/2000',170,3),
('500 dias y 19 noches','04/12/2000',184,3),
('No me cuesta olvidarte','07/01/2006',159,3),
('Sorda, ciegamuda','07/01/2006',231,3),
('Ojala que llueva cola-cao','16/11/2011',168,3),
('A quien no le importa','16/11/2011',196,3),
('Maldito Leprecound','16/11/2011',159,4),
('Chica de hoy','25/07/1993',198,5),
('Se vino','25/07/1993',169,6),
('Barrio de los sueños partios','25/07/1993',147,6),
('Tu y todos','25/07/1993',190,6),
('Morire','01/03/2001',185,7),
('Manifiesto','01/03/2001',152,7),
('Capintan','01/03/2001',191,7),
('El señor del feudo','01/03/2001',132,7),
('Leyenda','01/03/2001',190,7),
('Algo suave','01/03/2001',127,7),
('Entre tu y yo','01/03/2001',145,7),
('Nunca os perdere','01/03/2001',200,7),
('Las verdades aflojan','01/03/2001',188,8),
('Como te enseñe','01/03/2001',166,8),
('Reaccion','01/03/2001',177,8),
('Como chuleton','10/12/2002',201,8),
('Viviendo the crazy life','10/12/2002',199,8),
('Hombre contra hombre','14/08/1994',240,9),
('No me dejes','14/08/1994',189,9),
('Contigo soy algo','14/08/1994',174,9),
('Madrid','14/08/1994',196,9),
('Santa Aurelia','23/09/2007',222,10),
('Ocho vidas','23/09/2007',211,10),
('No es por ti','23/09/2007',186,10),
('El sitio de mi patio','25/10/2002',157,10),
('Cuentame una historia','25/10/2002',178,10),
('Solo le pido a Jesus','07/06/1996',165,10),
('Escuela de frio','07/06/1996',179,10),
('Maria','07/06/1996',184,11),
('Bailar separados','18/06/2017',185,11),
('0','18/06/2017',157,11),
('Sabor de desamor','18/06/2017',194,11),
('Mago','21/04/2018',158,11),
('Alicia en el pais de las maravillas','21/04/2018',350,11),
('Si tu vuelves','27/06/2015',167,11),
('Alli no hay playa','27/06/2015',196,11),
('Devuelveme la muerte','28/05/2018',183,11),
('Desconteminame','28/05/2018',142,12),
('Aquellas grandes cosas','28/05/2018',175,13),
('Esos locos altos','09/06/1999',185,14),
('Agua','09/06/1999',201,14),
('Cantabria','09/06/1999',221,15),
('No hemos cambiado','09/06/1999',167,15),
('Es lo mismo','12/02/2011',154,15),
('Cinco cosas','12/02/2011',176,15),
('Una amapola es una amapola','12/02/2011',159,15),
('Lio','18/10/2009',180,16),
('Barco a marte','18/10/2009',196,25),
('La distancia de tu piel','04/05/1998',158,26),
('Manos al suelo','04/05/1998',199,16),
('Me importa todo','09/02/2010',166,16),
('Voy en moto','09/02/2010',174,16),
('Mi hogar','09/02/2010',197,16),
('Cuando la tierra te tenga','10/06/2001',153,16),
('Viviria por ti','10/06/2001',209,16),
('El lado luminoso','10/06/2001',215,24),
('Fueron las cintas','17/10/2007',135,23),
('A fuego rapido','17/10/2007',220,22),
('Nuestra tierra','17/10/2007',175,16),
('Años 2000','06/01/2006',132,16),
('Entre tus recuerdos','06/01/2006',178,17),
('¿No lo ves?','06/01/2006',200,17),
('Mucho peor','06/01/2006',205,17),
('¡Que feo!','04/07/1989',188,17),
('Un nuevo dia oscurecera','04/07/1989',154,21),
('Odio','25/08/2002',206,21),
('No sigas asi','25/08/2002',165,18),
('Besare el suelo','25/08/2002',333,18),
('Rozando la nada','15/11/1980',134,18),
('Que todos','15/11/1980',164,18),
('Mayo','15/11/1980',196,18),
('Ruido','15/11/1980',215,18),
('Estamos locos','20/05/2013',300,18),
('Agua y azucar','20/05/2013',183,20),
('Encima de un puente','20/05/2013',200,19),
('Tu frio','11/10/1999',175,27),
('El','11/10/1999',193,28),
('Alegria de morir','11/10/1999',214,28);

INSERT INTO public."Músico_compone_Canción"("DNI_músico_Músico","Nombre_canción_Canción")
VALUES ('77160507S','Hijo del sol'),
('01552057V','A jesus le pido'),
('31209682Q','Me doy por vencido'),
('38279041A','Y nos dieron las 11'),
('19212547A','Corazon roto'),
('94826621J','Entre dos mares'),
('03618525G','Ocupado'),
('70712099W','Hombre lobo en Paris'),
('29255679R','La gorda'),
('54008405N','500 dias y 19 noches'),
('43099759J','No me cuesta olvidarte'),
('76654052C','Sorda, ciegamuda'),
('79887423M','A quien no le importa'),
('38034721N','Ojala que llueva cola-cao'),
('06747148Y','Maldito Leprecound'),
('29027790L','Chica de hoy'),
('44016307B','Tu y todos'),
('95029382Y','Capintan'),
('17289546D','Leyenda'),
('12665696X','Algo suave'),
('67108877Y','Entre tu y yo'),
('14969909Z','Las verdades aflojan'),
('85685209C','Como te enseñe'),
('30849182H','Hombre contra hombre'),
('75360020Z','No me dejes'),
('59463092L','Contigo soy algo'),
('01843123H','Santa Aurelia'),
('70209112W','El sitio de mi patio'),
('63123162F','Cuentame una historia');

INSERT INTO public."Localización"("DNI_músico_Músico","Provincia","Código_postal","Dirección","Ciudad")
VALUES('23437671G','Madrid', 28110, 'Calle los Pardos 67','Algete'),
('77160507S','Madrid',28130,'Calle Rosales 15','Valdeolmos'),
('01552057V','Madrid',28189,'Avenida Alcala 2','Patones'),
('31209682Q','Madrid',28806,'Plaza Cervantes 34','Alcala de Henares'),
('38279041A','Madrid',28850,'Calle La Pedriza 10','Torrejon de Ardoz'),
('19212547A','Madrid',28211,'Calle Nazca 78','Peralejo'),
('94826621J','Madrid',28260,'Avenida de la Ilustracion s/n','las latas'),
('03618525G','Madrid',28280,'Calle Tratado de Roma 1','El Escorial'),
('70712099W','Madrid',28294,'Plaza de la Constitucion 27 - 2B','Canopus'),
('29255679R','Madrid',28300,'Calle La Esperanza 13','El Monje'),
('54008405N','Madrid',28850,'Avenida Guadalajara 137','Torrejon de Ardoz'),
('43099759J','Guadalajara',19005,'Avenida del Ejercito 10','Guadalajara'),
('76654052C','Madrid',28412,'Calle Benavente 10-A','Cerceda'),
('79887423M','Madrid',28430,'Calle Ariston 65','Alpedrete'),
('38034721N','Soria',42110,'Calle La Alegria 1','Olvega'),
('06747148Y','Murcia',30832,'Calle Estepona 23','Murcia'),
('29027790L','Madrid',28737,'Calle Lavanda','Braojos'),
('44016307B','Madrid',28820,'Calle El Ademán 235','Coslada'),
('95029382Y','Madrid',28804,'Paseo Caballeria Española 5 - 5B','Alcala de Henares'),
('17289546D','Madrid',28903,'Calle Portugal','Getafe'),
('12665696X','Madrid',28864,'Paseo Castellano 13','Aljalvir'),
('67108877Y','Madrid',28935,'Travesia del Cortejo 2 BIS','Mostoles'),
('14969909Z','Malaga',29011,'Calle Sevilla 34','Malaga'),
('85685209C','Navarra',31300,'Avenida Trieste 34','Tafalla'),
('30849182H','Asturias',33425,'Calle El Esperpento','Llanera'),
('75360020Z','Leon',24001,'Bulevar aguasfrias - 10 - 3B','Leon'),
('59463092L','La Coruña',15001,'Calle El Raudal 10','La Coruña'),
('01843123H','Sevilla',41001,'Calle Acueducto 57','Sevilla'),
('70209112W','Badajoz',06001,'Urbanizacion Arcoiris Chalet 43','Badajoz'),
('63123162F','Albacete',02001,'Calle La Roda 3','Albacete');

INSERT INTO public."Instrumento" ("Nombre_instrumento") 
VALUES('Saxofón'),
('Guitarra eléctrica'),
('Cantar'),
('Cajón'),
('Sintetizador'),
('Batería'),
('Piano'),
('Trompeta'),
('Flauta'),
('Bajo'),
('Guitarra acústica'),
('Violín'),
('Zambomba'),
('Cello'),
('Teclado'),
('Mesa de mezclas');

INSERT INTO public."Músico_toca_Instrumento" ("Nombre_instrumento_Instrumento","DNI_músico_Músico") 
VALUES ('Saxofón','23437671G'),--JAZZ
('Guitarra eléctrica','77160507S'),--ROCK
('Cantar','77160507S'),--ROCK
('Cajón','01552057V'),--FLAMENCO
('Sintetizador','31209682Q'),--TECNO
('Batería','38279041A'),--JAZZ
('Cantar','19212547A'),--HEAVY 
('Piano','94826621J'),--JAZZ
('Batería','03618525G'),--HEAVY
('Trompeta','70712099W'),--JAZZ
('Flauta','29255679R'),--CLASICA
('Bajo','54008405N'),--ROCK
('Guitarra acústica','43099759J'),--JAZZ
('Guitarra eléctrica','76654052C'),--HEAVY
('Violín','79887423M'),--CLASICA
('Zambomba','38034721N'),--POP
('Piano','06747148Y'),--CLASICA
('Cantar','29027790L'),--POP
('Guitarra acústica','44016307B'),--SOLO
('Cantar','44016307B'),--SOLO
('Bajo','95029382Y'),--HEAVY
('Cantar','17289546D'),--SOLO
('Cello','12665696X'),--SOLO
('Guitarra acústica','14969909Z'),--FLAMENCO
('Cantar','85685209C'),--FLAMENCO
('Teclado','30849182H'),--ROCK
('Mesa de mezclas','75360020Z'),--TECNO
('Mesa de mezclas','59463092L'),--SOLO
('Batería','01843123H'),--ROCK
('Guitarra eléctrica','70209112W'),--ROCK
('Cantar','63123162F'),--ROCK
('Bajo','63123162F');--ROCK

INSERT INTO Public."Teléfono" ("Número_tel", "DNI_músico_Músico")
VALUES (686944238,'23437671G'),
(667577923, '77160507S'),
(625225367, '01552057V'),
(635971602, '31209682Q'),
(645949428, '38279041A'),
(677368329, '19212547A'),
(641581545, '94826621J'),
(654286314, '03618525G'),
(699954129, '70712099W'),
(680776008, '29255679R'),
(679130672, '54008405N'),
(628615836, '43099759J'),
(676158764, '76654052C'),
(651089322, '79887423M'),
(639864017, '38034721N'),
(636360153, '06747148Y'),
(693025360, '29027790L'),
(691198448, '44016307B'),
(657536406, '95029382Y'),
(617907349, '17289546D'),
(646966931, '12665696X'),
(676882070, '67108877Y'),
(696933057, '14969909Z'),
(655004867, '85685209C'),
(679798659, '30849182H'),
(695138295, '75360020Z'),
(630851512, '59463092L'),
(623716122, '01843123H'),
(610114436, '70209112W'),
(647483758, '63123162F');

INSERT INTO Public."Entrada"("Código_entrada","Precio","Código_concierto_Concierto")
VALUES (1,100,1),
(2,170,2),
(3,180,3),
(4,145,4),
(5,200,5),
(6,110,6),
(7,105,7),
(8,100,1),
(9,170,2),
(10,180,3),
(11,145,4),
(12,200,5),
(13,110,6),
(14,175,7),
(15,180,3),
(16,110,6);




INSERT INTO Public."Compra_Disco"("ID_compra_disco","DNI_usuario_Usuario","Opinión","Num_referencia_Disco")
VALUES(0,'12595629R',7,1),
(1,'12595629R',5,7),
(2,'12595629R',9,15),
(3,'30398427V',10,3),
(4,'30398427V',3,15),
(5,'30398427V',6,19),
(6,'30398427V',8,24),
(7,'00665757E',7,8),
(8,'14560607C',9,21),
(9,'58819222B',5,27),
(10,'43422293H',6,20),
(11,'43422293H',8,23),
(12,'27814752R',7,5),
(13,'01435787N',9,11),
(14,'39278938K',4,13),
(15,'35251416Y',6,19),
(16,'59398930G',7,14),
(17,'59398930G',6,11),
(18,'59398930G',5,8),
(19,'96744568J',6,9),
(20,'83527993C',4,6),
(21,'74210404F',2,16),
(22,'50572557L',1,26),
(23,'20207055Z',9,18),
(24,'20207055Z',6,21),
(25,'20207055Z',5,13),
(26,'20207055Z',6,19),
(27,'20207055Z',7,23);

INSERT INTO Public."Compra_Entrada"("Código_entrada_Entrada","DNI_usuario_Usuario","Opinión")
VALUES (1,'12595629R',3),
(2,'30398427V',7),
(3,'00665757E',10),
(4,'14560607C',6),
(5,'58819222B',8),
(6,'43422293H',6),
(7,'27814752R',6),
(8,'01435787N',9),
(9,'39278938K',4), 
(10,'35251416Y',2),
(11,'59398930G',8),
(12,'96744568J',7),
(13,'83527993C',9),
(14,'74210404F',10),
(15,'50572557L',7),
(16,'20207055Z',6);