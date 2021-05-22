
class Fraccion {
private:
    int num, denom;
public:
    Fraccion(int num, int denom);   //Constructor
    ~Fraccion();
    
    void leerF();
    void mostrarF();
    void reducirF();
    Fraccion sumaF(Fraccion f2);
    Fraccion multF(Fraccion f2);
};