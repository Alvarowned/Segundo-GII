#include <Evaluacion.hpp>

int main(int argc, char** argv)
{
    Evaluacion evaluacion;
    char opcion;
    char expresion[40] = "";

    ShowWindow(GetConsoleWindow(), SW_MAXIMIZE);
    do {

        HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
        SetConsoleTextAttribute(hConsole, 3);
        cout << "\n\t------------------------------------------------\n";
        cout << "\t                       MENU                       \n";
        cout << "\t------------------------------------------------\n";
        SetConsoleTextAttribute(hConsole, 2);
        cout << "\n\tA. Evaluar una expresion infija. \n";
        cout << "\tB. Evaluar una expresion infija 2.\n";
        cout << "\tC. Expresion infija a expresion postfija.\n";
        cout << "\tD. Evaluar una expresion posfija.\n";
        cout << "\tE. Evaluar si una expresion infija es correcta.\n";
        cout << "\tF. Completar parentesis de una expresion infija.\n\n";
        SetConsoleTextAttribute(hConsole, 7);
        cout << "\tS.Salir del programa.\n\n";
        cout << "\tIndique la opcion deseada: ";
        cin >> opcion;
        opcion = toupper(opcion);
        system("cls");

        switch(opcion) {

        case 'A': {
            cout << "\n\tIntroduzca una expresion (sin espacios): "; // ((2*5)-(1*2))/(11-9))
            cin >> expresion;
            evaluacion.evaluar_expresionInfija(expresion);
            break;
        }
        case 'B': {
            cout << "\n\tIntroduzca una expresion (sin espacios): "; // (2*5-1*2)/(11-9)    3*(4*2-3)-(4+6/3)
            cin >> expresion;
            evaluacion.evaluar_expresionInfija2(expresion);
            break;
        }
        case 'C': {
            cout << "\n\tIntroduzca una expresion (sin espacios): "; // 4*2+(12/3)-5 ((2*5)-(1*2))/(11-9))  ----->>
                                                                     // 2 5 * 1 2 * - 11 9 - /
            cin >> expresion;
            evaluacion.expresionInfija_a_expresionPostfija(expresion);
            break;
        }

        case 'D': {
            cout << "\n\tIntroduzca una expresion (sin espacios): "; // 4*2+(12/3)-5 d  ----->> 2 5 * 1 2 * - 11 9 - /
            cin >> expresion;
            int resultado = evaluacion.evaluar_expresionPostfija(expresion);
            cout << resultado << endl;
            break;
        }
        case 'E':

            break;
        case 'F':

            break;

        case 'S':
            cout << "\n\tSaliendo del programa...\n";
            break;
        default:
            cout << "\n\tOpcion incorrecta!\n\n";
            break;
        }
    } while(opcion != 'S');
    return 0;
}