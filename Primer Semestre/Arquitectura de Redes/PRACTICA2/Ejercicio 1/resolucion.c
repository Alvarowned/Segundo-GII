/* resolucion.c */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>

int main(int argc, const char *argv[])
{
 struct hostent *hp;
 char **p;
 
 if (argc != 2)
    {
     printf("Uso: %s direccion IP\n",argv[0]);
     exit(1);
    }

 /*Obtiene una estructura hostent con la información del host*/  
 hp = gethostbyname(argv[1]); 
 
 if (hp==NULL)
    {
     printf("No se pude encontar la informacion sobre el equipo %s\n", argv[1]);
     exit(2);
    }
 
for (p=hp->h_addr_list; *p != 0; p++)
     {
      struct in_addr in;
      memcpy(&in.s_addr, *p, sizeof(in.s_addr));
      /*Pasa el binario de la tabla a in.s_addr porque esa estructura la necesita 
      inet_ntoa, para pasarla a formato notación de punto */
      printf("La dirección IP (%s) corresponde a %s \n", inet_ntoa(in),hp->h_name);
     }

/* Para obtener los aliases */
for (p=hp->h_aliases; *p != 0; p++)
     {
      printf("Alias: %s \n", *p);
     }
  exit(0);
}
