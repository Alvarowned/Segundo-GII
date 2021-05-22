package pa.pl1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Álvaro Pérez Álamo
 */
public class Buffer {
    private int[] datos = new int[20];
    private boolean[] ocupados = new boolean[20];
    private int resultado = 0, indiceE = 0, indiceS = 0;
    private Lock cerrojo = new ReentrantLock();
    private Condition vacio = cerrojo.newCondition();
    private Condition lleno = cerrojo.newCondition();
    
    public Buffer() {
        for(int i = 0; i < 20; i++) ocupados[i] = false;
    }
    
    public void insertarDato(int dato, String nombre) {
        try {
            cerrojo.lock();
            while(ocupados[indiceE]) {
                try {
                    lleno.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            datos[indiceE] = dato;
            ocupados[indiceE] = true;
            indiceE ++;
            if(indiceE == 20) indiceE = 0;
            
            System.out.println(nombre + " genera " + dato + ".");
            
            vacio.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }
    
    public void extraerDato(String nombre) {
        try {
            cerrojo.lock();
            while(!ocupados[indiceS]) {
                try {
                    vacio.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            int dato = datos[indiceS];
            ocupados[indiceS] = false;
            resultado += dato;
            indiceS ++;
            if(indiceS == 20) indiceS = 0;
            
            System.out.println(nombre + " ha leido " + dato + ", resultado: " + resultado);
            
            lleno.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }
}
