#include <iostream>
using namespace std;

#define MAX 10

void leer_v(int v[])
{
    int i, n;
    for(i = 0; i < MAX; i++) 
        {
        cout << "Introduzca el entero numero " << i+1 << ": ";
        cin >> n;
        v[i] = n;
    }
}

void escribe_v(int v[])
{
    int i;
    for(i = 0; i < MAX; i++)
    {
        cout << "La posicion " << i << " contiene el numero: " << v[i] << "\n";
    }
}

void escribe_p(int *p)
{
	int i;
	for(i = 0; i < MAX; i++)
	{
		cout << "La posicion " << i << " contiene el numero: " << *p++ << "\n";
	}
}

int max_min(int v[], int& min)
{
    int i, max = 0;
    min = v[0];
    for(i = 0; i < MAX; i++)
    {
        if(max < v[i]) max = v[i];
        if(min > v[i]) min = v[i];
    }
    return max;
}

void ordenar_aux(int v[], int aux[]) {
    int i, j, n_aux;
    for(i = 0; i < MAX; i++) 
    {
        aux[i] = v[i];
        for(j = i; j > 0; j--)
        {
            if(aux[j-1] > aux[j]) {
                n_aux = aux[j];
                aux[j] = aux[j-1];
                aux[j-1] = n_aux;
            }
        }
    }
}


int main(int argc, char **argv)
{
	int v[MAX], max, min, aux[MAX];
    
    leer_v(v);
	cout << "\n";
	
    escribe_v(v);
	cout << "\n";
	
    escribe_p(v);
	cout << "\n";
	
    max = max_min(v, min);      //Pasa por return max y por referencia min
    cout << "El maximo es " << max << " y el minimo " << min << "\n";
    
    ordenar_aux(v, aux);
    escribe_v(aux);
    
    return 0;
}
