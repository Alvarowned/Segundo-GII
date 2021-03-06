#include "Cola.hpp"

Cola::Cola()
{
    primero = NULL;
    ultimo = NULL;
}

Cola::~Cola()
{
    while(primero) 
        eliminar();
}

void Cola::insertar(int v)
{
    pnodo nuevo;
    nuevo = new Nodo(v);
    if(ultimo) 
        ultimo->siguiente = nuevo;
    ultimo = nuevo;
    if(!primero) 
        primero = nuevo;
}

int Cola::eliminar()
{
    pnodo nodo;
    int v;
    nodo = primero;
    if(!nodo) 
        return 0;
    primero = nodo->siguiente;
    v = nodo->valor;
    delete nodo;
    if(!primero) 
        ultimo = NULL;
    return v;
}

void Cola::mostrar() 
{
    pnodo aux = primero;
    while(aux) {
        cout << " -> " << aux->valor;
        aux = aux->siguiente;
    }
    cout << endl;
}