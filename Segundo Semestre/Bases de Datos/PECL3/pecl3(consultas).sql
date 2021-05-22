-- Consulta 1 --
SELECT "Código_grupo"
FROM public."Grupo"
WHERE "Género" LIKE 'Jazz';

-- Consulta 2 --
SELECT S."Nombre_músico"
FROM public."Músico_toca_Instrumento" AS T, public."Músico" AS S
WHERE T."DNI_músico_Músico" = S."DNI_músico" AND "Nombre_instrumento_Instrumento" LIKE 'Batería';

-- Consulta 3 --
SELECT "Nombre_músico"
FROM public."Músico" AS T
JOIN public."Músico_toca_Instrumento" AS S ON T."DNI_músico" = S."DNI_músico_Músico"
JOIN public."Grupo" AS W ON T."Código_grupo_Grupo" = W."Código_grupo"
WHERE "Género" LIKE 'Jazz' AND "Nombre_instrumento_Instrumento" LIKE 'Saxofón';

-- Consulta 4 --
SELECT "Código_grupo"
FROM public."Grupo" AS T
JOIN public."Grupo_da_Concierto" AS S ON T."Código_grupo" = S."Código_grupo_Grupo"
JOIN public."Concierto" AS W ON S."Código_concierto_Concierto" = W."Código_concierto"
JOIN public."Sitio Concierto" AS SC ON SC."Código_concierto_Concierto" = W."Código_concierto"
WHERE "País" LIKE 'España';

-- Consulta 5 --
SELECT "Título"
FROM public."Disco" AS Disco INNER JOIN (SELECT "Num_referencia_Disco", COUNT("Nombre_canción")
                                FROM public."Canción"
                                GROUP BY "Num_referencia_Disco"
                                HAVING COUNT("Nombre_canción") > 10) AS T
ON Disco."Num_referencia" = T."Num_referencia_Disco";


-- Consulta 6 --
SELECT "Código_grupo", "Nombre_grupo"
FROM (SELECT "Código_grupo", "Nombre_grupo", COUNT("DNI_músico")
      FROM (SELECT "Nombre_grupo", "Código_grupo", "DNI_músico"
            FROM public."Grupo" AS Grupo INNER JOIN public."Músico" AS Músico ON Grupo."Código_grupo" = Músico."Código_grupo_Grupo") AS T
      GROUP BY "Código_grupo", "Nombre_grupo"
      HAVING COUNT("DNI_músico") > 3) 
AS S;


-- Consulta 7 --
SELECT "Opinión", DNI
FROM public."Compra_Disco" AS S INNER JOIN (SELECT "DNI_usuario_Usuario" AS DNI, COUNT("Num_referencia_Disco")
                                            FROM public."Compra_Disco"
                                            GROUP BY DNI
                                            HAVING COUNT("Num_referencia_Disco") > 2) AS T
ON S."DNI_usuario_Usuario" = T.DNI;



-- Consulta 8 --
SELECT "Título"
FROM public."Formato" AS Formato INNER JOIN (SELECT "Título", "Referencia_Formato", "Género"
                                             FROM public."Disco" AS Disco INNER JOIN (SELECT "Num_referencia_Disco", MAX("Duración")
                                                                                      FROM public."Canción"
                                                                                      GROUP BY "Num_referencia_Disco"
                                                                                      HAVING MAX("Duración") >= 5) AS T
                                             ON Disco."Num_referencia" = T."Num_referencia_Disco") AS S
ON Formato."Referencia" = S."Referencia_Formato"
WHERE "Tipo" LIKE 'LP' AND "Género" LIKE 'Rock';


-- Consulta 9 --
SELECT "Nombre_músico"
FROM public."Músico" AS Músico INNER JOIN (SELECT "Código_grupo_Grupo" AS Código_grupo
                                           FROM public."Grupo_da_Concierto" AS Grupo_da_Conc INNER JOIN (SELECT Cod_con, "Ciudad"
                                                                                                         FROM public."Sitio Concierto" AS SC INNER JOIN (SELECT "Código_concierto_Concierto" AS Cod_con, MAX("Precio")
                                                                                                                                                         FROM public."Entrada"
                                                                                                                                                         GROUP BY Cod_con
                                                                                                                                                         HAVING MAX("Precio") > 100) AS T
                                                                                                         ON SC."Código_concierto_Concierto" = T.Cod_con
                                                                                                         WHERE "Ciudad" LIKE 'Madrid') AS S
                                           ON Grupo_da_Conc."Código_concierto_Concierto" = S.Cod_con) AS W
ON Músico."Código_grupo_Grupo" = W.Código_grupo;


-- Consulta 10 --
SELECT "Opinión"
FROM public."Compra_Entrada" AS Compra INNER JOIN (SELECT *
                                                   FROM public."Entrada" AS Entrada INNER JOIN (SELECT Cod_con
                                                                                                FROM public."Sitio Concierto" AS SC INNER JOIN (SELECT "Código_concierto_Concierto" AS Cod_con, MAX("Precio")
                                                                                                                                                FROM public."Entrada"
                                                                                                                                                GROUP BY Cod_con
                                                                                                                                                HAVING MAX("Precio") > 100) AS T
                                                                                                ON SC."Código_concierto_Concierto" = T.Cod_con
                                                                                                WHERE "Ciudad" LIKE 'Madrid') AS S
                                                   ON Entrada."Código_concierto_Concierto" = S.Cod_con) AS W
ON Compra."Código_entrada_Entrada" = W."Código_entrada";


-- Consulta 11 --
SET DATESTYLE TO 'European';
SELECT "Nombre_músico", "Apellidos_músico", "Título", "Nombre_canción"
FROM public."Músico" AS Músico INNER JOIN (SELECT "Nombre_canción", "Título", "DNI_músico_Músico"
                                           FROM public."Músico_compone_Canción" AS Músico_comp INNER JOIN (SELECT "Nombre_canción", "Título"
                                                                                                           FROM public."Canción" AS Canción INNER JOIN (SELECT "Num_referencia", "Título"
                                                                                                                                                        FROM public."Disco"
                                                                                                                                                        WHERE "Fecha_edición" BETWEEN '01/01/2018' AND '31/12/2018') AS T
                                                                                                           ON Canción."Num_referencia_Disco" = T."Num_referencia") AS S
                                           ON Músico_comp."Nombre_canción_Canción" = S."Nombre_canción") AS W
ON Músico."DNI_músico" = W."DNI_músico_Músico";


-- Consulta 12 --
SELECT "Nombre_músico", "Apellidos_músico", "Título", "Tipo", "Tamaño_Mbytes"
FROM public."Formato Digital" AS FD INNER JOIN (SELECT DISTINCT "Título", "Nombre_músico", "Apellidos_músico", "Referencia_Formato"
                                                FROM public."Disco" AS Disco INNER JOIN (SELECT "Nombre_músico", "Apellidos_músico", "Nombre_canción", "Num_referencia_Disco"
                                                                                         FROM public."Canción" AS Canción INNER JOIN (SELECT "Nombre_músico", "Apellidos_músico", "Nombre_canción_Canción" AS Nombre_canción
                                                                                                                                      FROM public."Músico_compone_Canción" AS Músico_compone INNER JOIN (SELECT "Nombre_músico", "Apellidos_músico", "DNI_músico"
                                                                                                                                                                                                         FROM public."Músico_toca_Instrumento" AS Músico_toca INNER JOIN public."Músico" AS Músico
                                                                                                                                                                                                         ON Músico."DNI_músico" = Músico_toca."DNI_músico_Músico"
                                                                                                                                                                                                         WHERE "Nombre_instrumento_Instrumento" LIKE 'Guitarra') AS T
                                                                                                                                      ON Músico_compone."DNI_músico_Músico" = T."DNI_músico") AS S
                                                                                         ON Canción."Nombre_canción" = S.Nombre_canción) AS W
                                                ON Disco."Num_referencia" = W."Num_referencia_Disco") AS H
ON FD."Referencia" = H."Referencia_Formato";


-- Consulta 13 --
SET DATESTYLE TO 'European';
SELECT "Nombre_usuario", "Apellidos_usuario"
FROM public."Usuario" AS Usuario INNER JOIN (SELECT DISTINCT "DNI_usuario_Usuario" AS DNI
                                             FROM public."Compra_Entrada" AS Compra INNER JOIN (SELECT "Código_entrada"
                                                                                                FROM public."Entrada" AS Entrada INNER JOIN (SELECT "Código_concierto"
                                                                                                                                             FROM (SELECT "Código_concierto_Concierto"
                                                                                                                                                   FROM public."Grupo_da_Concierto" AS Grupo_da INNER JOIN (SELECT "Código_grupo", "Género"
                                                                                                                                                                                                            FROM public."Grupo"
                                                                                                                                                                                                            WHERE "Género" LIKE 'Blues') AS T
                                                                                                                                                   ON Grupo_da."Código_grupo_Grupo" = T."Código_grupo") AS S
                                                                                                                                             INNER JOIN 
                                                                                                                                                  (SELECT "Código_concierto"
                                                                                                                                                   FROM public."Concierto"
                                                                                                                                                   WHERE "Fecha" BETWEEN '01/01/2018' AND '31/12/2018') AS W
                                                                                                                                             ON S."Código_concierto_Concierto" = W."Código_concierto") AS H
                                                                                                ON Entrada."Código_concierto_Concierto" = H."Código_concierto") AS X
                                            ON Compra."Código_entrada_Entrada" = X."Código_entrada"
                                            WHERE "Opinión" IS NOT NULL) AS G
ON Usuario."DNI_usuario" = G.DNI;


-- Consulta 14 --
SELECT "Título", "Nombre_canción"
FROM public."Canción" AS Canción INNER JOIN (SELECT "Num_referencia", "Título"
                                             FROM (SELECT "Num_referencia_Disco", AVG("Opinión")
                                                   FROM public."Compra_Disco"
                                                   GROUP BY "Num_referencia_Disco"
                                                   HAVING AVG("Opinión") > 8) AS T
                                             INNER JOIN public."Disco" AS Disco
                                             ON Disco."Num_referencia" = T."Num_referencia_Disco") AS S
ON Canción."Num_referencia_Disco" = S."Num_referencia";


-- Consulta 15 --
SELECT "Nombre_músico", "Apellidos_músico", "Nombre_grupo"
FROM (SELECT DISTINCT "DNI_músico", "Nombre_músico", "Apellidos_músico", "Nombre_grupo"
      FROM (SELECT "Num_referencia_Disco", COUNT("Nombre_canción"), "Nombre_canción"
            FROM public."Canción"
            GROUP BY "Num_referencia_Disco", "Nombre_canción"
            HAVING COUNT("Nombre_canción") > 10) AS X
      INNER JOIN 
           (SELECT "Nombre_músico", "Apellidos_músico", "Nombre_grupo", "DNI_músico", "Nombre_canción_Canción"
            FROM public."Músico_compone_Canción" AS Músico_comp INNER JOIN (SELECT "Nombre_músico", "Apellidos_músico", "Nombre_grupo", "DNI_músico"
                                                                            FROM public."Músico" AS Músico INNER JOIN (SELECT "Código_grupo", "Nombre_grupo"
                                                                                                                       FROM public."Grupo" AS Grupo INNER JOIN (SELECT DISTINCT "Código_grupo_Grupo"
                                                                                                                                                                FROM public."Grupo_da_Concierto" AS Grupo_da LEFT JOIN (SELECT "Código_concierto_Concierto"
                                                                                                                                                                                                                        FROM public."Sitio Concierto"
                                                                                                                                                                                                                        WHERE "País" LIKE 'España') AS T
                                                                                                                                                                ON Grupo_da."Código_concierto_Concierto" = T."Código_concierto_Concierto") AS S
                                                                                                                       ON Grupo."Código_grupo" = S."Código_grupo_Grupo") AS C               
                                                                            ON Músico."Código_grupo_Grupo" = C."Código_grupo") AS W
            ON Músico_comp."DNI_músico_Músico" = W."DNI_músico") AS Y
      ON X."Nombre_canción" = Y."Nombre_canción_Canción") 
      AS F;


-- Consulta 16 --
SET DATESTYLE TO 'European';
SELECT "Nombre_músico", "Apellidos_músico", "Nombre_canción_Canción"
FROM public."Músico_compone_Canción" AS Músico_comp INNER JOIN (SELECT "Nombre_músico", "Apellidos_músico", "DNI_músico"
                                                                FROM public."Músico" AS Músico INNER JOIN (SELECT DISTINCT "Código_grupo_Grupo" AS Código_grupo
                                                                                                           FROM public."Grupo_da_Concierto" AS Grupo_da LEFT JOIN (SELECT "Código_concierto"
                                                                                                                                                                   FROM public."Concierto"
                                                                                                                                                                   WHERE "Fecha" BETWEEN '01/01/2017' AND '31/12/2017') AS T 
                                                                                                           ON Grupo_da."Código_concierto_Concierto" = T."Código_concierto") AS S
                                                                ON Músico."Código_grupo_Grupo" = S.Código_grupo) AS W
ON Músico_comp."DNI_músico_Músico" = W."DNI_músico";




























