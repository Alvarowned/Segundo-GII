/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.p3.alvaroperez;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
public class Generador extends Thread {

    private boolean parar;
    final private Supermercado s;

    public Generador(Supermercado s) {
        this.s = s;
        this.parar = false;
    }

    @Override
    public void run() {
        int id = 0;
        while (!parar) {
            try {
                sleep(200 + (int) (800 * Math.random()));
            } catch (InterruptedException ex) {
                Logger.getLogger(Comprador.class.getName()).log(Level.SEVERE, null, ex);
            }
            Comprador comp = new Comprador(id, s);
            if(!parar) comp.start();
            id++;
        }
    }
    
    public void setParar(boolean parar) {
        this.parar = parar;
    }
}
