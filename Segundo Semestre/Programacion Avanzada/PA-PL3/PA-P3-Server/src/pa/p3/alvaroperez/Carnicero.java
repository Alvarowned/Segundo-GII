/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.p3.alvaroperez;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
public class Carnicero extends Thread {

    final private Supermercado s;
    private int tiempoM, tiempoT;

    public Carnicero(Supermercado s) {
        this.tiempoT = 0;
        this.tiempoM = 0;
        this.s = s;
    }

    @Override
    public void run() {
        while (s.getAbierto()) {
            try {
                s.atenderCarn();
                int t = 1501 + (int) (1000 * Math.random());
                sleep(t);
                tiempoT += t;
                tiempoM = (tiempoM + t) / 2;
                s.libCarn();
            } catch (InterruptedException ex) {
                Logger.getLogger(Carnicero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int getTM() {
        return tiempoM;
    }
    
    public int getTT() {
        return tiempoT;
    }
}
