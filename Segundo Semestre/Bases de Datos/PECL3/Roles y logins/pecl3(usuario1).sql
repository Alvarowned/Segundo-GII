CREATE ROLE "Usuario1" LOGIN
  PASSWORD 'Usuario1'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION CONNECTION LIMIT 1;
GRANT "Usuario" TO "Usuario1";