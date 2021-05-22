package pa.pl1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Álvaro Pérez Álamo
 */
public class Consumidor extends Thread {
    private final String nombre;
    private final Buffer buf;
    
    public Consumidor(String nombre, Buffer buf) {
        this.nombre = nombre;
        this.buf = buf;
    }
    
    @Override
    public void run() {
        Random random = new Random();
        while(true) {
            try {
                sleep(200 + random.nextInt(601));
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            buf.extraerDato(nombre);
        }
    }
}
