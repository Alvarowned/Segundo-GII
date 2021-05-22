#include <stdio.h>
#include <iostream>
using namespace std;

int producto(int a, int b)
{
    return a == 1 ? b : producto(a-1, b) + b;
}

int elevar(int a, int b)
{
    return b == 1 ? a : elevar(a, b-1) * a;
}

int cuadrado(int a)
{
    return a*a;
}

int main(int argc, char **argv)
{
	int e1, e2;
    cout << "Introduzca 2 numeros enteros: ";
    cin >> e1 >> e2;
    cout << "El resultado de " << e1 << "x" << e2 << " es " << producto(e1,e2) << "\n";
    cout << "El resultado de " << e1 << "^" << e2 << " es " << elevar(e1,e2) << "\n";
    cout << "El resultado de " << e1 << "^2 es " << cuadrado(e1) << "\n";
    cout << "El resultado de " << e2 << "^2 es " << cuadrado(e2) << "\n";
    
	return 0;
}