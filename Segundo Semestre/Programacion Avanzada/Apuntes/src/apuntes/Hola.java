/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apuntes;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
public class Hola extends Thread {
    private final Semaphore s;
    private final int n;
    
    public Hola(Semaphore s, int n) {
        this.s = s;
        this.n = n;
    }
    
    @Override
    public void run() {
        try {
            s.acquire();
            System.out.println("Hola " + n);
            System.out.println("Adios " + n);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hola.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            s.release();
        }
    }
}
