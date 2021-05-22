/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apuntes;

import java.util.concurrent.Semaphore;

/**
 *
 * @author alvar
 */
public class Apuntes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1, true);
        Hola h1 = new Hola(s, 1);
        Hola h2 = new Hola(s, 2);
        Hola h3 = new Hola(s, 3);
        
        h1.start();
        h2.start();
        h3.start();
    }
    
}
