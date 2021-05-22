#include <fraccion.h>
#include <iostream>
using namespace std;

Fraccion::Fraccion(int numx, int denomx) {
    if(denomx != 0){
        denom = denomx;
        num = numx;
    } else {
    denom = 1;
    num = 0;
    }
}

Fraccion::~Fraccion() {
}

void Fraccion::mostrarF() {
    cout << "La fraccion es " << num << "/" << denom << ".\n";
}

void Fraccion::leerF() {
    cout << "Numerador: ";
    cin >> num;
    cout << "Denominador: ";
    cin >> denom;
    if(denom == 0) {
        num = 0;
        denom = 1;
        cout << "Fraccion erronea. Nueva fraccion: " << num << "/" << denom << ".\n";
    }
}

void Fraccion::reducirF() {
    int i, mcd;
    for(i = 1; i <= min(num,denom); i++) {
        if(num % i == 0 && denom % i == 0) mcd = i;
    }
    num = num / mcd;
    denom = denom / mcd;
}

Fraccion Fraccion::sumaF(Fraccion f2) {
    int mcm = 0, fl = max(denom, f2.denom);
    while(mcm == 0) {
        if(fl % denom == 0 && fl % f2.denom == 0) mcm = fl;
        fl++;
    }
    Fraccion resul = Fraccion((num*(mcm/denom))+(f2.num*(mcm/f2.denom)), mcm);
    resul.reducirF();
    return resul;
}

Fraccion Fraccion::multF(Fraccion f2) {
    Fraccion resul = Fraccion(num*f2.num, denom*f2.denom);
    resul.reducirF();
    return resul;
}