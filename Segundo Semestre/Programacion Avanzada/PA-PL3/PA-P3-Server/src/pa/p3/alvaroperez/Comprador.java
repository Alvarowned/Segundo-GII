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
public class Comprador extends Thread {
    final private Supermercado s;

    public Comprador(int id, Supermercado s) {
        super.setName(String.valueOf(id));
        this.s = s;
    }

    @Override
    public void run() {
        try {
            long tIniCola = System.currentTimeMillis();
            s.entrar(this);

            s.quitarDeCola(this);
            long tEntrada = System.currentTimeMillis();

            int random = (int) (3 * Math.random());
            switch (random) {
                case 0:
                    s.estantesEntrar(this);
                    sleep(1000 + (int) (10000 * Math.random()));
                    s.estantesSalir(this);
                    break;
                case 1:
                    s.carniceria(this);
                    break;
                case 2:
                    s.pescaderia(this);
                    break;
            }

            s.entrarCaja(this);
            long tFinCola = System.currentTimeMillis();
            long tColaFin = tFinCola - tIniCola;
            long tEntrFin = tFinCola - tEntrada;
            s.salir(this, tColaFin, tEntrFin);
        } catch (InterruptedException ie) {
        }
    }
}
