#include "funciones.h"

void mipwd(orden *O)
{
	if(O->argc == 1) {
		char buf[256];
		if(getcwd(buf, sizeof(buf)) == NULL) 
			perror("Error al obtener el path actual (getcwd).");
		else 
			printf("Tu path actual: %s \n", buf);
	} else perror("Uso incorrecto de mipwd. Uso: mipwd");
}

void mils(orden *O)
{
	if(O->argc == 1) {      //mils
		char buf[256];
		DIR *dir;
		struct dirent *entrada;
		if(getcwd(buf, sizeof(buf)) == NULL) 
			perror("Error al obtener el path actual (getcwd).");
		if((dir = opendir(buf)) == NULL)
			perror("Error al abrir el directorio actual.");
		else {
			printf("Contenido:\n");
			while((entrada = readdir(dir)) != NULL)
				printf("    %s\n", entrada->d_name);
			closedir(dir);
		}
	} else if(O->argc == 2) {
		if(strcmp(O->argv[1], "-l") == 0) {       	//mils -l
			char buf[256];
			DIR *dir;
			struct dirent *entrada;
			if(getcwd(buf, sizeof(buf)) == NULL) 
				perror("Error al obtener el path actual (getcwd).");
			if((dir = opendir(buf)) == NULL)
				perror("Error al abrir el directorio actual.");
			else {
				struct stat archivo;
				printf("Contenido:\n");
				while((entrada = readdir(dir)) != NULL) {
					printf("    %s\n", entrada->d_name);
					stat(entrada->d_name, &archivo);
					printf("\tUID: %d\n\tGID: %d\n\tTamaño: %ld\n", archivo.st_uid, archivo.st_gid, archivo.st_size);
					printf("\tPermisos: %3o\n", archivo.st_mode&0777);
					printf("\tUltima fecha de modificacion: %s", asctime(gmtime(&archivo.st_mtime)));
					printf("\tTipo archivo: ");
					if(S_ISREG(archivo.st_mode))
						printf("Regular\n");
					else if(S_ISDIR(archivo.st_mode))
						printf("Directorio\n");
					else if(S_ISBLK(archivo.st_mode))
						printf("Dispositivo de bloque\n");
					else if(S_ISCHR(archivo.st_mode))
						printf("Dispositivo de caracter\n");
					printf("\n");
				}
				closedir(dir);
			}
		} else {               						//mils directorio
			DIR *dir;
			struct dirent *entrada;
			if((dir = opendir(O->argv[1])) == NULL)
				perror("Error al abrir el directorio.");
			else {
				printf("Contenido:\n");
				while((entrada = readdir(dir)) != NULL)
					printf("\t%s\n", entrada->d_name);
				closedir(dir);
			}
		}
	} else if(O->argc == 3) {
		if(strcmp(O->argv[1], "-l") == 0) {        	//mils -l directorio
			char buf[256];
			DIR *dir;
			struct dirent *entrada;
			if(getcwd(buf, sizeof(buf)) == NULL) 
				perror("Error al obtener el path actual (getcwd).");
			if((dir = opendir(O->argv[2])) == NULL)
				perror("Error al abrir el directorio.");
			else {
				struct stat archivo;
				printf("Contenido:\n");
				while((entrada = readdir(dir)) != NULL) {
					printf("    %s\n", entrada->d_name);
					char name[256] = "";
					strcat(name, buf);
					strcat(name, "/");
					strcat(name, entrada->d_name);
					stat(name, &archivo);
					printf("\tUID: %d\n\tGID: %d\n\tTamaño: %ld\n", archivo.st_uid, archivo.st_gid, archivo.st_size);
					printf("\tPermisos: %3o\n", archivo.st_mode&0777);
					printf("\tUltima fecha de modificacion: %s", asctime(gmtime(&archivo.st_mtime)));
					printf("\tTipo archivo: ");
					if(S_ISREG(archivo.st_mode))
						printf("Regular\n");
					else if(S_ISDIR(archivo.st_mode))
						printf("Directorio\n");
					else if(S_ISBLK(archivo.st_mode))
						printf("Dispositivo de bloque\n");
					else if(S_ISCHR(archivo.st_mode))
						printf("Dispositivo de caracter\n");
					printf("\n");
				}
				closedir(dir);
			}
		} else perror("Uso incorrecto de mils. Uso 1: mils ; Uso 2: mils -l ; Uso 3: mils directorio ; Uso 4: mils -l directorio");
	}
	else perror("Uso incorrecto de mils. Uso 1: mils ; Uso 2: mils -l ; Uso 3: mils directorio ; Uso 4: mils -l directorio");
}

void mimkdir(orden *O)
{
	if(O->argc == 2) {
		if (mkdir(O->argv[1], S_IRWXU) == -1)
			perror("Error al crear el directorio.");
		else printf("Directorio creado correctamente.\n");
	} else perror("Uso incorrecto de mimkdir. Uso: mimkdir archivo");
}

void mirmdir(orden *O)
{
	if(O->argc == 2) {
		if (rmdir(O->argv[1]) == -1)
			perror("Error al eliminar el directorio.");
		else printf("Directorio eliminado correctamente.\n");
	} else perror("Uso incorrecto de mimkdir. Uso: mimkdir archivo");
}

void micd(orden *O, char *path)
{
	if(O->argc == 2) {
		if(chdir(O->argv[1]) == -1)
			perror("Error al cambiar de directorio.");
	} else if(O->argc == 1) {
		if(chdir(path) == -1)
			perror("Error al cambiar de directorio.");
		else printf("Vuelta al directorio inicial.\n");
	} else perror("Uso incorrecto de micd. Uso1: micd ; Uso 2: micd path");
}

void micat(orden *O)
{
	if(O->argc == 2) {
		int fd, size;
		char* buffer;
		if((fd = open(O->argv[1], O_RDONLY)) == -1)
			perror("Error al abrir el archivo (open).");
		size= lseek(fd, 0, SEEK_END);
		if(size < 0)
			perror("Error al calcular el tamaño del archivo");

		buffer = mmap(NULL, size, PROT_READ, MAP_PRIVATE, fd, 0);
		if(buffer == ((void*)-1)) //Comprueba los errores al realizar la funcion mmap
			perror("Error al permitir proyectar el archivo en memoria");
		printf("%s\n",buffer);
	} else perror("Uso incorrecto de micat. Uso: micat archivo");
}

void micp(orden *O)
{
	if(O->argc == 3) {
		int fd1, fd2;
		char buffer;

		fopen(O->argv[2], "wb");
		if((fd1 = open(O->argv[1], O_RDONLY)) == -1)
			perror("Error al abrir el archivo de procedencia (open).");
		if((fd2 = open(O->argv[2], O_RDWR | O_CREAT)) == -1)
			perror("Error al abrir el archivo destino (open).");
		off_t size = lseek(fd1, 0, SEEK_END);
		if(size == -1) 
			perror("Error al mover puntero de archivo (lseek SEEK_END).");
		if(lseek(fd1, 0, SEEK_SET) == -1)
			perror("Error al mover puntero de archivo (lseek SEEK_SET).");
		if(lseek(fd2, 0, SEEK_SET) == -1)
			perror("Error al mover puntero de archivo (lseek SEEK_SET).");

		for(off_t i = 1; i <= size; i++) {
			if(read(fd1, &buffer, sizeof(char)) == -1) 
				perror("Error al realizar la lectura (read).");
			if(write(fd2, &buffer, sizeof(char)) == -1)
				perror("Error al realizar la escritura (write).");

			if(lseek(fd1, i, SEEK_SET) == -1)
				perror("Error al mover puntero de archivo (lseek SEEK_SET).");
			if(lseek(fd2, i, SEEK_SET) == -1)
				perror("Error al mover puntero de archivo (lseek SEEK_SET).");
		}
	} else perror("Uso incorrecto de micp. Uso: micp archivo1 archivo2");
}

void mirm(orden *O)
{
	if(O->argc == 2) {
		if(remove(O->argv[1]) == -1) 
			perror("Error al borrar el archivo (remove).");
	} else perror("Uso incorrecto de mirm. Uso: mirm archivo1");
}