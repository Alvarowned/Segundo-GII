all: contar

contar: principal.o libcontar.a
	gcc -g -Wall principal.o -o contar -L . -l contar

principal.o: principal.c
	gcc -g -Wall principal.c -c

leercar_R.o: leercar_R.c leercar_R.h
	gcc -g -Wall leercar_R.c leercar_R.h -c

contar_M.o: contar_M.c contar_M.h
	gcc -g -Wall contar_M.c contar_M.h -c

libcontar.a: leercar_R.o contar_M.o
	ar -r libcontar.a leercar_R.o contar_M.o

clean:
	rm -f contar principal.o leercar_R.o contar_M.o libcontar.a contar_M.h.gch leercar_R.h.gch