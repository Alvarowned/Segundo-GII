#include <stdio.h>
#include <Pila.hpp>

int main(int argc, char **argv)
{
    Pila pila;

    pila.insertar(1);
    pila.insertar(2);
    pila.insertar(3);
    pila.insertar(4);
    cout << "Mi pila: ";
    pila.mostrar();
    pila.extraer();
    cout << "Mi pila: ";
    pila.mostrar();
    pila.insertar(5);
    cout << "Mi pila: ";
    pila.mostrar();
    pila.extraer();
    cout << "Mi pila: ";
    pila.mostrar();
    pila.extraer();
    cout << "Mi pila: ";
    pila.mostrar();
    pila.extraer();
    cout << "Mi pila: ";
    pila.mostrar();
    pila.extraer();
    cout << "Mi pila: ";
    pila.mostrar();
}
