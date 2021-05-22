/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.p3.alvaroperez;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author alvar
 */
public class Supermercado {

    private final int aforo;
    private long tColaFin, tEntrFin;
    private int numEstantes = 0, numDentro = 0, numPers = 0;
    private boolean abierto, caja1Libre, caja2Libre;
    private final ArrayList<Thread> arrayEntr;
    private final Log log;
    private final Semaphore s_estantes, s_carniceria, s_pescaderia, s_cajas, s_cajaLibre;
    private final ListaThreads entrada, cola_carniceria, cola_pescaderia, cola_cajas;
    private final JTextField jtCarniceroAtiende, jtPescaderoAtiende, jtNumEstantes, jtCajera1, jtCajera2;
    private final Carnicero carn;
    private final Pescadero pesc;
    private final Cajera caj1, caj2;
    private final Lock lEntr = new ReentrantLock(), lCarn = new ReentrantLock(), lPesc = new ReentrantLock(), lCaj1 = new ReentrantLock(), lCaj2 = new ReentrantLock(), lCierre = new ReentrantLock();
    private final Condition llenoEntr = lEntr.newCondition(), vacioCarn = lCarn.newCondition(), termCarn = lCarn.newCondition(), vacioPesc = lPesc.newCondition(), termPesc = lPesc.newCondition(), termCaj1 = lCaj1.newCondition(), vacioCaj1 = lCaj1.newCondition(), termCaj2 = lCaj2.newCondition(), vacioCaj2 = lCaj2.newCondition(), superCerrado = lCierre.newCondition();

    public Supermercado(int aforo, Log log, JTextField jt1, JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt5, JTextField jt6, JTextField jt7, JTextField jt8, JTextField jt9) {
        this.aforo = aforo;
        this.arrayEntr = new ArrayList<>();
        this.abierto = true;
        this.caja1Libre = true;
        this.caja2Libre = true;
        this.log = log;
        entrada = new ListaThreads(jt1);
        cola_carniceria = new ListaThreads(jt2);
        cola_pescaderia = new ListaThreads(jt3);
        jtCarniceroAtiende = jt4;
        jtPescaderoAtiende = jt5;
        jtNumEstantes = jt6;
        cola_cajas = new ListaThreads(jt7);
        jtCajera1 = jt8;
        jtCajera2 = jt9;
        s_estantes = new Semaphore(1, true);
        s_carniceria = new Semaphore(1, true);
        s_pescaderia = new Semaphore(1, true);
        s_cajas = new Semaphore(2, true);
        s_cajaLibre = new Semaphore(1, true);
        carn = new Carnicero(this);
        carn.start();
        pesc = new Pescadero(this);
        pesc.start();
        caj1 = new Cajera(this, 1);
        caj1.start();
        caj2 = new Cajera(this, 2);
        caj2.start();
    }

    public void entrar(Thread t) throws InterruptedException {
        this.parar();
        entrada.meter(t);
        String mensaje = "Comprador-" + t.getName() + " espera para entrar.";
        log.añadirTarea(mensaje);            //Añade el mensaje a las tareas

        lEntr.lock();
        while (arrayEntr.size() == aforo) {
            llenoEntr.await();
        }
        try {
            arrayEntr.add(t);
            numDentro++;
        } finally {
            lEntr.unlock();
        }
    }

    public void quitarDeCola(Thread t) {
        entrada.sacar(t);
        String mensaje = "Comprador-" + t.getName() + " entra al supermercado.";
        log.añadirTarea(mensaje);            //Añade el mensaje a las tareas
    }

    public void estantesEntrar(Thread t) throws InterruptedException {
        this.parar();
        String mensaje = "Comprador-" + t.getName() + " va a los estantes.";
        log.añadirTarea(mensaje);            //Añade el mensaje a las tareas

        s_estantes.acquire();
        numEstantes++;
        jtNumEstantes.setText(String.valueOf(numEstantes));
        s_estantes.release();
    }

    public void estantesSalir(Thread t) throws InterruptedException {
        this.parar();
        s_estantes.acquire();
        numEstantes--;
        jtNumEstantes.setText(String.valueOf(numEstantes));
        s_estantes.release();

        String mensaje = "Comprador-" + t.getName() + " sale de los estantes.";
        log.añadirTarea(mensaje);            //Añade el mensaje a las tareas
    }

    public boolean getAbierto() {
        return this.abierto;
    }

    public void carniceria(Thread t) throws InterruptedException {
        String mensaje = "Comprador-" + t.getName() + " entra a la cola de la carniceria.";
        log.añadirTarea(mensaje);            //Añade el mensaje a las tareas
        cola_carniceria.meter(t);

        s_carniceria.acquire();
        this.parar();
        cola_carniceria.sacar(t);

        lCarn.lock();
        try {
            vacioCarn.signalAll();
            mensaje = "Comprador-" + t.getName() + " es atendido en la carniceria.";
            log.añadirTarea(mensaje);            //Añade el mensaje a las tareas

            jtCarniceroAtiende.setText(t.getName());
            termCarn.await();
            this.parar();
            jtCarniceroAtiende.setText("");
        } finally {
            lCarn.unlock();
        }

        s_carniceria.release();
    }

    public void atenderCarn() throws InterruptedException {
        lCarn.lock();
        try {
            vacioCarn.await();
        } finally {
            lCarn.unlock();
        }
    }

    public void libCarn() {
        lCarn.lock();
        this.parar();
        try {
            termCarn.signalAll();
        } finally {
            lCarn.unlock();
        }
    }

    public void pescaderia(Thread t) throws InterruptedException {
        String mensaje = "Comprador-" + t.getName() + " entra a la cola de la pescaderia.";
        log.añadirTarea(mensaje);            //Añade el mensaje a las tareas
        cola_pescaderia.meter(t);

        s_pescaderia.acquire();
        this.parar();
        cola_pescaderia.sacar(t);

        lPesc.lock();
        try {
            vacioPesc.signalAll();
            mensaje = "Comprador-" + t.getName() + " es atendido en la pescaderia.";
            log.añadirTarea(mensaje);            //Añade el mensaje a las tareas

            jtPescaderoAtiende.setText(t.getName());
            termPesc.await();
            this.parar();
            jtPescaderoAtiende.setText("");
        } finally {
            lPesc.unlock();
        }

        s_pescaderia.release();
    }

    public void atenderPesc() {
        lPesc.lock();
        try {
            try {
                vacioPesc.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            lPesc.unlock();
        }
    }

    public void libPesc() {
        lPesc.lock();
        this.parar();
        try {
            termPesc.signalAll();
        } finally {
            lPesc.unlock();
        }
    }

    public void entrarCaja(Thread t) {
        int libre = 0;

        cola_cajas.meter(t);
        String mensaje = "Comprador-" + t.getName() + " esta esperando en la cola para pagar.";
        log.añadirTarea(mensaje);

        this.parar();
        try {
            s_cajas.acquire();

            s_cajaLibre.acquire();
            if (caja1Libre) {
                caja1Libre = false;
                libre = 1;
            } else if (caja2Libre) {
                caja2Libre = false;
                libre = 2;
            }
            cola_cajas.sacar(t);
            s_cajaLibre.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (libre == 1) {
            jtCajera1.setText(t.getName());
            mensaje = "Comprador-" + t.getName() + " esta siendo atendido por Cajera 1.";
            log.añadirTarea(mensaje);

            lCaj1.lock();
            try {
                vacioCaj1.signalAll();
                termCaj1.await();
                this.parar();
                jtCajera1.setText("");
                caja1Libre = true;
            } catch (InterruptedException ex) {
                Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                lCaj1.unlock();
            }
        } else if (libre == 2) {
            jtCajera2.setText(t.getName());
            mensaje = "Comprador-" + t.getName() + " esta siendo atendido por Cajera 2.";
            log.añadirTarea(mensaje);

            lCaj2.lock();
            try {
                vacioCaj2.signalAll();
                termCaj2.await();
                this.parar();
                jtCajera2.setText("");
                caja2Libre = true;
            } catch (InterruptedException ex) {
                Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                lCaj2.unlock();
            }
        }

        s_cajas.release();
    }

    public void atenderCaja1() {
        lCaj1.lock();
        try {
            try {
                vacioCaj1.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            this.parar();
            lCaj1.unlock();
        }
    }

    public void libCaja1() {
        lCaj1.lock();
        this.parar();
        try {
            termCaj1.signalAll();
        } finally {
            lCaj1.unlock();
        }
    }

    public void atenderCaja2() {
        lCaj2.lock();
        try {
            try {
                vacioCaj2.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            this.parar();
            lCaj2.unlock();
        }
    }

    public void libCaja2() {
        lCaj2.lock();
        this.parar();
        try {
            termCaj2.signalAll();
        } finally {
            lCaj2.unlock();
        }
    }

    public void salir(Thread t, long tColaFin, long tEntrFin) {
        this.parar();
        lEntr.lock();
        try {
            arrayEntr.remove(t);
            this.tColaFin = tColaFin;
            this.tEntrFin = tEntrFin;
            numPers++;
            numDentro--;
            String mensaje = "Comprador-" + t.getName() + " sale del supermercado.";
            log.añadirTarea(mensaje);
            if (numDentro == 0 && entrada.getLista().isEmpty() && cola_carniceria.getLista().isEmpty() && cola_pescaderia.getLista().isEmpty() && !abierto) {
                log.setTerminar(true);
                log.setInfo(carn.getTM(), carn.getTT(), pesc.getTM(), pesc.getTT(), numPers, tColaFin, tEntrFin);
            }
            llenoEntr.signal();
        } finally {
            lEntr.unlock();
        }
    }

    public void setAbierto(boolean b) {
        this.abierto = b;
    }

    public void parar() {
        lCierre.lock();
        try {
            if (!abierto) {
                superCerrado.await();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lCierre.unlock();
        }
    }

    public void reanudar() {
        lCierre.lock();
        try {
            superCerrado.signalAll();
        } finally {
            lCierre.unlock();
        }
    }

    public void apagar() {
        for (int i = 0; i < entrada.getLista().size(); i++) {
            entrada.getLista().get(i).interrupt();
        }
        entrada.quitarTodos();
        for (int i = 0; i < cola_carniceria.getLista().size(); i++) {
            cola_carniceria.getLista().get(i).interrupt();
            numDentro--;
        }
        cola_carniceria.quitarTodos();
        for (int i = 0; i < cola_pescaderia.getLista().size(); i++) {
            cola_pescaderia.getLista().get(i).interrupt();
            numDentro--;
        }
        cola_pescaderia.quitarTodos();
    }
}
