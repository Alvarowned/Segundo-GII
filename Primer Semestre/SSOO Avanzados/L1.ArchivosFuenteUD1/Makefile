all: ejercicio

ejercicio: experimento_mem.o mem_dinamica.o
	gcc -Wall -g experimento_mem.o mem_dinamica.o -o ejercicio

experimento_mem.o: experimento_mem.c
	gcc -Wall -g experimento_mem.c -c

mem_dinamica.o: mem_dinamica.c mem_dinamica.h
	gcc -Wall -g mem_dinamica.c mem_dinamica.h -c

clean: 
	rm ejercicio mem_dinamica.o experimento_mem.o
