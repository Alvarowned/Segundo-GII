package ejemplo1;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import javax.swing.JTextField;

public class Exposicion {

    int aforo;
    ListaThreads colaEspera, dentro;
    Semaphore semaforo;
    final Lock lock = new ReentrantLock();
    final Condition pausa = lock.newCondition();
    boolean pausado;

    public Exposicion(int aforo, JTextField tfEsperan, JTextField tfDentro) {
        this.aforo = aforo;
        semaforo = new Semaphore(aforo, true);
        colaEspera = new ListaThreads(tfEsperan);
        dentro = new ListaThreads(tfDentro);
        pausado = false;
    }

    public void entrar(Visitante v) {
        try {
            this.detener();
        } catch (InterruptedException ex) {
        }

        colaEspera.meter(v);
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
        }

        try {
            this.detener();
        } catch (InterruptedException ex) {
        }

        colaEspera.sacar(v);
        dentro.meter(v);
    }

    public void salir(Visitante v) {
        try {
            this.detener();
        } catch (InterruptedException ex) {}
        
        dentro.sacar(v);
        semaforo.release();
    }

    public void mirar(Visitante v) {
        try {
            this.detener();
        } catch (InterruptedException ex) {
        }

        try {
            Thread.sleep(2000 + (int) (3000 * Math.random()));
        } catch (InterruptedException e) {
        }
    }

    public void detener() throws InterruptedException {
        lock.lock();
        try {
            if (pausado) {
                pausa.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void setPausado(boolean pausado) {
        lock.lock();
        try {
            this.pausado = pausado;
            if (!pausado) {
                pausa.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
