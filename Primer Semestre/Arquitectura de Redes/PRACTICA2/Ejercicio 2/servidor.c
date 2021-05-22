/* servidor.c */

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
#define STDOUT 1
#define SERV_ADDR (IPPORT_RESERVED+1)

int main()
{
int rval;
int sock,length,msgsock;
struct sockaddr_in server;
char buf[1024];

struct sockaddr_in sockaddr_cliente;
unsigned int addr_cliente_len;
struct hostent *hp;

sock=socket(AF_INET, SOCK_STREAM,0);

if (sock<0)
  {
    perror("No hay socket de escucha");
    exit(1);
  }

server.sin_family=AF_INET;
server.sin_addr.s_addr=htonl(INADDR_ANY);
server.sin_port = htons(SERV_ADDR);

if (bind(sock,(struct sockaddr *)&server, sizeof(server))<0)
  {
    perror("Direccion no asignada");
    exit(1);
  }

listen(sock,1);
addr_cliente_len = sizeof(sockaddr_cliente);

while (1)
 {
  /*Estará bloqueado esperando petición de conexión*/
  msgsock = accept(sock, (struct sockaddr *)&sockaddr_cliente, &addr_cliente_len);

  if (msgsock==-1)
     perror("Conexion no aceptada");
  else
    do
     {
      printf("\nS.- CONEXION CON EL CLIENTE CORRECTA\n");
      printf("S.- PUERTO %u\n", ntohs(sockaddr_cliente.sin_port));
      printf("S.- IP: %s\n", inet_ntoa(sockaddr_cliente.sin_addr));

      hp = gethostbyaddr((char*)&sockaddr_cliente.sin_addr, sizeof(sockaddr_cliente.sin_addr), AF_INET);
      printf("S.- NOMBRE: %s", hp->h_name);	
	
      /*Me dispongo a leer datos de la conexion*/
      memset(buf,0,sizeof(buf));
      rval=read(msgsock,buf,1024);

      if (rval<0)
        perror("Mensaje no leído");
      else
        write(STDOUT,buf,rval);
      }
     while (rval>0);
  
   printf("\nConexion cerrada\n");
   close(msgsock);
  }
exit(0);
}
