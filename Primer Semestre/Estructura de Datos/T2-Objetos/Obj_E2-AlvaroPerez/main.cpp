#include <iostream>
#include <fraccion.h>
using namespace std;

int main(int argc, char **argv)
{
    Fraccion f1(3, 2);
    f1.mostrarF();
    
    Fraccion f2(0, 0);
    f2.leerF();
    f2.mostrarF();
    
    Fraccion fsum(0, 0), fmult(0, 0);
    
    fsum = f1.sumaF(f2);
    cout << "Resultado de la suma: \n";
    fsum.mostrarF();
    
    fmult = f1.multF(f2);
    cout << "\nResultado de la multiplicacion: \n";
    fmult.mostrarF();
}
