all: microshell

microshell: parser.o funciones.o prueba.c
	gcc -g -Wall parser.o funciones.o prueba.c -o  microshell

parser.o: parser.c parser.h
	gcc -g -Wall parser.c parser.h -c

funciones.o: funciones.c funciones.h
	gcc -g -Wall funciones.c funciones.h -c

clean:
	rm -f funciones.o parser.o funciones.h.gch parser.h.gch microshell