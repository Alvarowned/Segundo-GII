/*
    prueba.c
*/

#include <stdio.h>
#include <stdlib.h>

#include "parser.h"
#include "funciones.h"

void mostrar_orden (orden * O);

int main()
{
    orden O;
    int r;

    char path[256];
    if(getcwd(path, sizeof(path)) == NULL) 
        perror("Error al obtener el path actual (main).\n");

    printf ("Introduzca órdenes (pulse Ctrl-D para terminar)\n");

    do              // Leer órdenes y mostrarlas
    {
        inicializar_orden (&O);

        printf ("$ ");
        r = leer_orden (&O, stdin);

        if(O.argc != 0) {
            if (r < 0)
                fprintf (stderr, "\nError %d: %s\n",
                                 -r, mensajes_err[-r]);
            else {
                mostrar_orden (&O);
                
                if(strcmp(O.argv[0], "mipwd") == 0)
                    mipwd(&O);
                else if(strcmp(O.argv[0], "mils") == 0)
                    mils(&O);
                else if(strcmp(O.argv[0], "mimkdir") == 0)
                    mimkdir(&O);
                else if(strcmp(O.argv[0], "mirmdir") == 0)
                    mirmdir(&O);
                else if(strcmp(O.argv[0], "micd") == 0)
                    micd(&O, path);
                else if(strcmp(O.argv[0], "micat") == 0)
                    micat(&O);
                else if(strcmp(O.argv[0], "micp") == 0)
                    micp(&O);
                else if(strcmp(O.argv[0], "mirm") == 0)
                    mirm(&O);
                else if(strcmp(O.argv[0], "exit") == 0)
                    return 0;
                else
                    perror("Esa no es una instruccion definida.");
            }
        }

        liberar_orden (&O);
    }
    while (r==0);   // Repetir hasta error o EOF

    return 0;
}

void mostrar_orden (orden * O)
{
    int i;

    printf ("\tOrden cruda: \"%s\"\n", O->orden_cruda);
    printf ("\tNúmero de argumentos: %d\n", O->argc);

    for (i=0; i<=O->argc; i++)
        if (O->argv[i] != NULL)
            printf ("\t\targv[%d]: \"%s\"\n", i, O->argv[i]);
        else
            printf ("\t\targv[%d]: NULL\n", i);

    if (O->entrada)
        printf ("\tEntrada: \"%s\"\n", O->entrada);

    if (O->salida)
        printf ("\tSalida: \"%s\"\n", O->salida);

    if (O->salida_err)
        printf ("\tSalida de err.: \"%s\"\n", O->salida_err);

    printf ("\tEjecutar en segundo plano: %s\n",
            O->segundo_plano ? "Sí" : "No");
}


