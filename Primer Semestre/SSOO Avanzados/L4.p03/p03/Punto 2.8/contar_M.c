#include "contar_M.h"

int ContarCaracteres(int fd, char caracter)
{
	char *file;
	off_t size = lseek(fd, 0, SEEK_END);
	int cont = 0;

	if(size == -1) {
		perror("Error al realizar lseek (ContarCaracteres, SEEK_END).");
		return -1;
	}

	if(lseek(fd, 0, SEEK_SET) == -1) {
		perror("Error al realizar lseek (ContarCaracteres, SEEK_SET).");
		return -1;
	}

	file = mmap(NULL, size, PROT_READ, MAP_PRIVATE, fd, 0);
	if(file == MAP_FAILED) {
		perror("Error al realizar mmap (ContarCaracteres).");
		return -1;	
	}

	for(off_t i = 0; i < size; i++)
		if(file[i] == caracter) cont++;

	if(munmap(file, size) == -1) {
		perror("Error al realizar munmap (ContarCaracteres).");
		return -1;
	}

	return cont;
}