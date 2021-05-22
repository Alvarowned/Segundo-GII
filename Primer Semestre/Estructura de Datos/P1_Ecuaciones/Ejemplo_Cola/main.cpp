#include <stdio.h>
#include <Cola.hpp>

int main(int argc, char **argv)
{
    Cola cola;

    cola.insertar(1);
    cola.insertar(2);
    cola.insertar(3);
    cola.insertar(4);
    cout << "Mi cola: ";
    cola.mostrar();
    cola.eliminar();
    cout << "Mi cola: ";
    cola.mostrar();
    cola.insertar(5);
    cout << "Mi cola: ";
    cola.mostrar();
    cola.eliminar();
    cout << "Mi cola: ";
    cola.mostrar();
    cola.eliminar();
    cout << "Mi cola: ";
    cola.mostrar();
    cola.eliminar();
    cout << "Mi cola: ";
    cola.mostrar();
    cola.eliminar();
    cout << "Mi cola: ";
    cola.mostrar();
}
