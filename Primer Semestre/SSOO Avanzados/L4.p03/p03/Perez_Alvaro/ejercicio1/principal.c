#include "leercar_R.h"
#include "contar_M.h"
#include <string.h>

int main(int argc, const char *argv[]) 
{
	if(argc != 4) {
		perror("Uso erroneo de la funcion.\nUso 1: ./contar R archivo caracter\nUso 2: ./contar M archivo caracter");
		return -1;
	}

	int fd;
	if((fd = open(argv[2], O_RDONLY)) == -1) {
		perror("Error al ejecutar open (principal).");
		return -1;
	}

	if(strcmp(argv[1], "R") == 0) {
		off_t size;
		if((size = lseek(fd, 0, SEEK_END)) == -1) {
			perror("Error al ejecutar lseek END (principal).");
			return -1;
		}

		if(lseek(fd, 0, SEEK_SET) == -1) {
			perror("Error al ejecutar lseek SET (principal).");
			return -1;
		}

		int cont = 0, x;
		char car = argv[3][0];
		for(off_t i = 0; i < size; i++) {
			if((x = LeerCaracter(fd, i)) == car) cont++;
			else if(x == -1) return -1;
		}

		printf("El archivo contiene '%c' %d veces.\n", car, cont);
	} else if(strcmp(argv[1], "M") == 0) {
		char car = argv[3][0];
		int cont = ContarCaracteres(fd, car);
		if(cont == -1) return -1;
		printf("El archivo contiene '%c' %d veces.\n", car, cont);
	} else {
		perror("Uso erroneo de la funcion.\nUso 1: ./contar R archivo caracter\nUso 2: ./contar M archivo caracter");
		return -1;
	} 

	if(close(fd) == -1) return -1;
	return 0;
}
