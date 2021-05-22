CREATE ROLE "Administrador"
  SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;

GRANT ALL Privileges
ON public."Teléfono"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Localización"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Instrumento"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Músico"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Canción"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Disco"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Formato"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Formato Físico"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Formato Digital"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Músico_compone_Canción"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Músico_toca_Instrumento"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Grupo"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Grupo_da_Concierto"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Concierto"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Sitio Concierto"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Entrada"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Compra_Entrada"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Usuario"
TO "Administrador"   WITH GRANT OPTION;

GRANT ALL Privileges
ON public."Compra_Disco"
TO "Administrador"   WITH GRANT OPTION;