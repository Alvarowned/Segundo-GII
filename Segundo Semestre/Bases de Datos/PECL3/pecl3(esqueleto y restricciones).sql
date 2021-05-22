-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database;
-- -- ddl-end --
-- 

-- object: public."Canción" | type: TABLE --
-- DROP TABLE IF EXISTS public."Canción" CASCADE;
CREATE TABLE public."Canción"(
	"Nombre_canción" varchar NOT NULL,
	"Fecha_grabación" date,
	"Duración" smallint,
	"Num_referencia_Disco" smallint NOT NULL,
	CONSTRAINT "Canción_pk" PRIMARY KEY ("Nombre_canción")

);
-- ddl-end --
ALTER TABLE public."Canción" OWNER TO postgres;
-- ddl-end --

-- object: public."Disco" | type: TABLE --
-- DROP TABLE IF EXISTS public."Disco" CASCADE;
CREATE TABLE public."Disco"(
	"Num_referencia" smallint NOT NULL,
	"Género" varchar,
	"Título" varchar,
	"Fecha_edición" date,
	"Referencia_Formato" smallint NOT NULL,
	CONSTRAINT "Disco_pk" PRIMARY KEY ("Num_referencia")

);
-- ddl-end --
ALTER TABLE public."Disco" OWNER TO postgres;
-- ddl-end --

-- object: "Disco_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Canción" DROP CONSTRAINT IF EXISTS "Disco_fk" CASCADE;
ALTER TABLE public."Canción" ADD CONSTRAINT "Disco_fk" FOREIGN KEY ("Num_referencia_Disco")
REFERENCES public."Disco" ("Num_referencia") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Formato" | type: TABLE --
-- DROP TABLE IF EXISTS public."Formato" CASCADE;
CREATE TABLE public."Formato"(
	"Referencia" smallint NOT NULL,
	"Tipo" varchar,
	CONSTRAINT "Formato_pk" PRIMARY KEY ("Referencia")

);
-- ddl-end --
ALTER TABLE public."Formato" OWNER TO postgres;
-- ddl-end --

-- object: "Formato_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Disco" DROP CONSTRAINT IF EXISTS "Formato_fk" CASCADE;
ALTER TABLE public."Disco" ADD CONSTRAINT "Formato_fk" FOREIGN KEY ("Referencia_Formato")
REFERENCES public."Formato" ("Referencia") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Formato Físico" | type: TABLE --
-- DROP TABLE IF EXISTS public."Formato Físico" CASCADE;
CREATE TABLE public."Formato Físico"(
-- 	"Referencia" smallint NOT NULL,
-- 	"Tipo" varchar,
	CONSTRAINT "Formato Físico_pk" PRIMARY KEY ("Referencia")

) INHERITS(public."Formato")
;
-- ddl-end --
ALTER TABLE public."Formato Físico" OWNER TO postgres;
-- ddl-end --

-- object: public."Formato Digital" | type: TABLE --
-- DROP TABLE IF EXISTS public."Formato Digital" CASCADE;
CREATE TABLE public."Formato Digital"(
	"Tamaño_Mbytes" smallint,
-- 	"Referencia" smallint NOT NULL,
-- 	"Tipo" varchar,
	CONSTRAINT "Formato Digital_pk" PRIMARY KEY ("Referencia")

) INHERITS(public."Formato")
;
-- ddl-end --
ALTER TABLE public."Formato Digital" OWNER TO postgres;
-- ddl-end --

-- object: public."Músico" | type: TABLE --
-- DROP TABLE IF EXISTS public."Músico" CASCADE;
CREATE TABLE public."Músico"(
	"DNI_músico" varchar NOT NULL,
	"Apellidos_músico" varchar,
	"Nombre_músico" varchar,
	"Código_grupo_Grupo" smallint,
	CONSTRAINT "Músico_pk" PRIMARY KEY ("DNI_músico")

);
-- ddl-end --
ALTER TABLE public."Músico" OWNER TO postgres;
-- ddl-end --

-- object: public."Teléfono" | type: TABLE --
-- DROP TABLE IF EXISTS public."Teléfono" CASCADE;
CREATE TABLE public."Teléfono"(
	"Número_tel" integer NOT NULL,
	"DNI_músico_Músico" varchar NOT NULL,
	CONSTRAINT "Teléfono_pk" PRIMARY KEY ("Número_tel")

);
-- ddl-end --
ALTER TABLE public."Teléfono" OWNER TO postgres;
-- ddl-end --

-- object: "Músico_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Teléfono" DROP CONSTRAINT IF EXISTS "Músico_fk" CASCADE;
ALTER TABLE public."Teléfono" ADD CONSTRAINT "Músico_fk" FOREIGN KEY ("DNI_músico_Músico")
REFERENCES public."Músico" ("DNI_músico") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Instrumento" | type: TABLE --
-- DROP TABLE IF EXISTS public."Instrumento" CASCADE;
CREATE TABLE public."Instrumento"(
	"Nombre_instrumento" varchar NOT NULL,
	CONSTRAINT "Instrumento_pk" PRIMARY KEY ("Nombre_instrumento")

);
-- ddl-end --
ALTER TABLE public."Instrumento" OWNER TO postgres;
-- ddl-end --

-- object: public."Músico_toca_Instrumento" | type: TABLE --
-- DROP TABLE IF EXISTS public."Músico_toca_Instrumento" CASCADE;
CREATE TABLE public."Músico_toca_Instrumento"(
	"DNI_músico_Músico" varchar NOT NULL,
	"Nombre_instrumento_Instrumento" varchar NOT NULL,
	CONSTRAINT "Músico_toca_Instrumento_pk" PRIMARY KEY ("DNI_músico_Músico","Nombre_instrumento_Instrumento")

);
-- ddl-end --

-- object: "Músico_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Músico_toca_Instrumento" DROP CONSTRAINT IF EXISTS "Músico_fk" CASCADE;
ALTER TABLE public."Músico_toca_Instrumento" ADD CONSTRAINT "Músico_fk" FOREIGN KEY ("DNI_músico_Músico")
REFERENCES public."Músico" ("DNI_músico") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Instrumento_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Músico_toca_Instrumento" DROP CONSTRAINT IF EXISTS "Instrumento_fk" CASCADE;
ALTER TABLE public."Músico_toca_Instrumento" ADD CONSTRAINT "Instrumento_fk" FOREIGN KEY ("Nombre_instrumento_Instrumento")
REFERENCES public."Instrumento" ("Nombre_instrumento") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Músico_compone_Canción" | type: TABLE --
-- DROP TABLE IF EXISTS public."Músico_compone_Canción" CASCADE;
CREATE TABLE public."Músico_compone_Canción"(
	"DNI_músico_Músico" varchar NOT NULL,
	"Nombre_canción_Canción" varchar NOT NULL,
	CONSTRAINT "Músico_compone_Canción_pk" PRIMARY KEY ("DNI_músico_Músico","Nombre_canción_Canción")

);
-- ddl-end --

-- object: "Músico_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Músico_compone_Canción" DROP CONSTRAINT IF EXISTS "Músico_fk" CASCADE;
ALTER TABLE public."Músico_compone_Canción" ADD CONSTRAINT "Músico_fk" FOREIGN KEY ("DNI_músico_Músico")
REFERENCES public."Músico" ("DNI_músico") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Canción_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Músico_compone_Canción" DROP CONSTRAINT IF EXISTS "Canción_fk" CASCADE;
ALTER TABLE public."Músico_compone_Canción" ADD CONSTRAINT "Canción_fk" FOREIGN KEY ("Nombre_canción_Canción")
REFERENCES public."Canción" ("Nombre_canción") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Grupo" | type: TABLE --
-- DROP TABLE IF EXISTS public."Grupo" CASCADE;
CREATE TABLE public."Grupo"(
	"Código_grupo" smallint NOT NULL,
	"Nombre_grupo" varchar,
	"Género" varchar,
	CONSTRAINT "Grupo_pk" PRIMARY KEY ("Código_grupo")

);
-- ddl-end --
ALTER TABLE public."Grupo" OWNER TO postgres;
-- ddl-end --

-- object: "Grupo_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Músico" DROP CONSTRAINT IF EXISTS "Grupo_fk" CASCADE;
ALTER TABLE public."Músico" ADD CONSTRAINT "Grupo_fk" FOREIGN KEY ("Código_grupo_Grupo")
REFERENCES public."Grupo" ("Código_grupo") MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Concierto" | type: TABLE --
-- DROP TABLE IF EXISTS public."Concierto" CASCADE;
CREATE TABLE public."Concierto"(
	"Código_concierto" smallint NOT NULL,
	"Fecha" date,
	CONSTRAINT "Concierto_pk" PRIMARY KEY ("Código_concierto")

);
-- ddl-end --
ALTER TABLE public."Concierto" OWNER TO postgres;
-- ddl-end --

-- object: public."Grupo_da_Concierto" | type: TABLE --
-- DROP TABLE IF EXISTS public."Grupo_da_Concierto" CASCADE;
CREATE TABLE public."Grupo_da_Concierto"(
	"Código_grupo_Grupo" smallint NOT NULL,
	"Código_concierto_Concierto" smallint NOT NULL,
	CONSTRAINT "Grupo_da_Concierto_pk" PRIMARY KEY ("Código_grupo_Grupo","Código_concierto_Concierto")

);
-- ddl-end --

-- object: "Grupo_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Grupo_da_Concierto" DROP CONSTRAINT IF EXISTS "Grupo_fk" CASCADE;
ALTER TABLE public."Grupo_da_Concierto" ADD CONSTRAINT "Grupo_fk" FOREIGN KEY ("Código_grupo_Grupo")
REFERENCES public."Grupo" ("Código_grupo") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Concierto_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Grupo_da_Concierto" DROP CONSTRAINT IF EXISTS "Concierto_fk" CASCADE;
ALTER TABLE public."Grupo_da_Concierto" ADD CONSTRAINT "Concierto_fk" FOREIGN KEY ("Código_concierto_Concierto")
REFERENCES public."Concierto" ("Código_concierto") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Entrada" | type: TABLE --
-- DROP TABLE IF EXISTS public."Entrada" CASCADE;
CREATE TABLE public."Entrada"(
	"Código_entrada" smallint NOT NULL,
	"Precio" smallint,
	"Código_concierto_Concierto" smallint NOT NULL,
	CONSTRAINT "Entrada_pk" PRIMARY KEY ("Código_entrada")

);
-- ddl-end --
ALTER TABLE public."Entrada" OWNER TO postgres;
-- ddl-end --

-- object: "Concierto_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Entrada" DROP CONSTRAINT IF EXISTS "Concierto_fk" CASCADE;
ALTER TABLE public."Entrada" ADD CONSTRAINT "Concierto_fk" FOREIGN KEY ("Código_concierto_Concierto")
REFERENCES public."Concierto" ("Código_concierto") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Usuario" | type: TABLE --
-- DROP TABLE IF EXISTS public."Usuario" CASCADE;
CREATE TABLE public."Usuario"(
	"DNI_usuario" varchar NOT NULL,
	"Nombre_usuario" varchar,
	"Apellidos_usuario" varchar,
	"Correo_electrónico" varchar,
	"Puntos" smallint,
	CONSTRAINT "Usuario_pk" PRIMARY KEY ("DNI_usuario")

);
-- ddl-end --
ALTER TABLE public."Usuario" OWNER TO postgres;
-- ddl-end --

-- object: public."Compra_Disco" | type: TABLE --
-- DROP TABLE IF EXISTS public."Compra_Disco" CASCADE;
CREATE TABLE public."Compra_Disco"(
	"ID_compra_disco" smallint NOT NULL,
	"DNI_usuario_Usuario" varchar NOT NULL,
	"Opinión" smallint,
	"Num_referencia_Disco" smallint NOT NULL,
	CONSTRAINT "Usuario_compra_Disco_pk" PRIMARY KEY ("ID_compra_disco")

);
-- ddl-end --
ALTER TABLE public."Compra_Disco" OWNER TO postgres;
-- ddl-end --

-- object: "Usuario_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Compra_Disco" DROP CONSTRAINT IF EXISTS "Usuario_fk" CASCADE;
ALTER TABLE public."Compra_Disco" ADD CONSTRAINT "Usuario_fk" FOREIGN KEY ("DNI_usuario_Usuario")
REFERENCES public."Usuario" ("DNI_usuario") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Disco_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Compra_Disco" DROP CONSTRAINT IF EXISTS "Disco_fk" CASCADE;
ALTER TABLE public."Compra_Disco" ADD CONSTRAINT "Disco_fk" FOREIGN KEY ("Num_referencia_Disco")
REFERENCES public."Disco" ("Num_referencia") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Compra_Entrada" | type: TABLE --
-- DROP TABLE IF EXISTS public."Compra_Entrada" CASCADE;
CREATE TABLE public."Compra_Entrada"(
	"Código_entrada_Entrada" smallint NOT NULL,
	"DNI_usuario_Usuario" varchar NOT NULL,
	"Opinión" smallint,
	CONSTRAINT "Compra_Entrada_pk" PRIMARY KEY ("Código_entrada_Entrada")

);
-- ddl-end --
ALTER TABLE public."Compra_Entrada" OWNER TO postgres;
-- ddl-end --

-- object: "Usuario_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Compra_Entrada" DROP CONSTRAINT IF EXISTS "Usuario_fk" CASCADE;
ALTER TABLE public."Compra_Entrada" ADD CONSTRAINT "Usuario_fk" FOREIGN KEY ("DNI_usuario_Usuario")
REFERENCES public."Usuario" ("DNI_usuario") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Entrada_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Compra_Entrada" DROP CONSTRAINT IF EXISTS "Entrada_fk" CASCADE;
ALTER TABLE public."Compra_Entrada" ADD CONSTRAINT "Entrada_fk" FOREIGN KEY ("Código_entrada_Entrada")
REFERENCES public."Entrada" ("Código_entrada") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Compra_Entrada_uq" | type: CONSTRAINT --
-- ALTER TABLE public."Compra_Entrada" DROP CONSTRAINT IF EXISTS "Compra_Entrada_uq" CASCADE;
ALTER TABLE public."Compra_Entrada" ADD CONSTRAINT "Compra_Entrada_uq" UNIQUE ("Código_entrada_Entrada");
-- ddl-end --

-- object: public."Localización" | type: TABLE --
-- DROP TABLE IF EXISTS public."Localización" CASCADE;
CREATE TABLE public."Localización"(
	"DNI_músico_Músico" varchar NOT NULL,
	"Provincia" varchar,
	"Código_postal" integer,
	"Dirección" varchar,
	"Ciudad" varchar,
	CONSTRAINT "Localización_pk" PRIMARY KEY ("DNI_músico_Músico")

);
-- ddl-end --
ALTER TABLE public."Localización" OWNER TO postgres;
-- ddl-end --

-- object: "Músico_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Localización" DROP CONSTRAINT IF EXISTS "Músico_fk" CASCADE;
ALTER TABLE public."Localización" ADD CONSTRAINT "Músico_fk" FOREIGN KEY ("DNI_músico_Músico")
REFERENCES public."Músico" ("DNI_músico") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Localización_uq" | type: CONSTRAINT --
-- ALTER TABLE public."Localización" DROP CONSTRAINT IF EXISTS "Localización_uq" CASCADE;
ALTER TABLE public."Localización" ADD CONSTRAINT "Localización_uq" UNIQUE ("DNI_músico_Músico");
-- ddl-end --

-- object: public."Sitio Concierto" | type: TABLE --
-- DROP TABLE IF EXISTS public."Sitio Concierto" CASCADE;
CREATE TABLE public."Sitio Concierto"(
	"Código_concierto_Concierto" smallint NOT NULL,
	"País" varchar,
	"Ciudad" varchar,
	"Recinto" varchar,
	CONSTRAINT "Sitio Concierto_pk" PRIMARY KEY ("Código_concierto_Concierto")

);
-- ddl-end --
ALTER TABLE public."Sitio Concierto" OWNER TO postgres;
-- ddl-end --

-- object: "Concierto_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Sitio Concierto" DROP CONSTRAINT IF EXISTS "Concierto_fk" CASCADE;
ALTER TABLE public."Sitio Concierto" ADD CONSTRAINT "Concierto_fk" FOREIGN KEY ("Código_concierto_Concierto")
REFERENCES public."Concierto" ("Código_concierto") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Sitio Concierto_uq" | type: CONSTRAINT --
-- ALTER TABLE public."Sitio Concierto" DROP CONSTRAINT IF EXISTS "Sitio Concierto_uq" CASCADE;
ALTER TABLE public."Sitio Concierto" ADD CONSTRAINT "Sitio Concierto_uq" UNIQUE ("Código_concierto_Concierto");
-- ddl-end --


