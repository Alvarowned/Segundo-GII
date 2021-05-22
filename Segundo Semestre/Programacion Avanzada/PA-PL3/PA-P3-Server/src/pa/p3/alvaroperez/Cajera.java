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
public class Cajera extends Thread {
    final private Supermercado s;
    final private int id;

    public Cajera(Supermercado s, int id) {
        this.s = s;
        this.id = id;
    }

    @Override
    public void run() {
        if(id == 1) {
            while (s.getAbierto()) {
                s.atenderCaja1();
                try {
                    sleep(3001 + (int)(2000 * Math.random()));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Carnicero.class.getName()).log(Level.SEVERE, null, ex);
                }
                s.libCaja1();
            }
        } else {
            while (s.getAbierto()) {
                s.atenderCaja2();
                try {
                    sleep(3001 + (int)(2000 * Math.random()));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Carnicero.class.getName()).log(Level.SEVERE, null, ex);
                }
                s.libCaja2();
            }
        }
    }
}
