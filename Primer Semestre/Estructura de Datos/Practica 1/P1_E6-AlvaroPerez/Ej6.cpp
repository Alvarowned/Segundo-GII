#include <iostream>
using namespace std;

void tabla_mult(int n)
{
    int i;
    for(i = 1; i < 11; i++)
    {
        cout << n << " x " << i << " = " << n*i << "\n";
    }
}

int main(int argc, char **argv)
{
	int n;
    cout << "Introduza un numero: ";
    cin >> n;
    tabla_mult(n);
    return 0;
}
