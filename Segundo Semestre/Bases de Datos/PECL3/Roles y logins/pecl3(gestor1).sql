﻿CREATE ROLE "Gestor1" LOGIN
  PASSWORD 'Gestor1'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION CONNECTION LIMIT 1;
GRANT "Gestor" TO "Gestor1";