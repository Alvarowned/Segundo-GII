#include "leercar_R.h"

char LeerCaracter(int fd, int posicion) 
{
	char c;

	off_t pos = sizeof(char)*posicion;
	if(lseek(fd, pos, SEEK_SET) == -1) {
		perror("Error al realizar lseek (LeerCaracter).");
		return -1;
	}

	if(read(fd, &c, sizeof(char)) < 0) {
		perror("Error al realizar read (LeerCaracter).");
		return -1;
	}

	return c;
}