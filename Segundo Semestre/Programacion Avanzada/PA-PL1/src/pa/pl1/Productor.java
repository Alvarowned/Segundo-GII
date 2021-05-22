package pa.pl1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Álvaro Pérez Álamo
 */
public class Productor extends Thread {
    private final String nombre;
    private final int numMens;
    private final Buffer buf;
    private int dato;
    
    public Productor(String nombre, int numMens, Buffer buf) {
        this.nombre = nombre;
        this.numMens = numMens;
        this.buf = buf;
    }
    
    @Override
    public void run() {
        Random random = new Random();
        for(int i = 0; i < numMens; i++) {
            try {
                sleep(300 + random.nextInt(401));
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dato = random.nextInt(21);
            buf.insertarDato(dato, nombre);
        }
    }
}
