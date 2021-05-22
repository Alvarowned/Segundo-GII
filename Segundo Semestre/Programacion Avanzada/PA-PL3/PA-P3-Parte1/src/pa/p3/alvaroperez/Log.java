/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.p3.alvaroperez;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.Condition;

/**
 *
 * @author alvar
 */
public class Log extends Thread {

    private final ArrayList<String> buffer;
    private boolean terminar, in;
    private final Lock lock = new ReentrantLock();
    private final Condition vacio = lock.newCondition(), dentro = lock.newCondition();
    private int tMCarnic, tMPescad, tTCarn, tTPesc, numPers;
    private long tMCS, tMES;

    public Log() {
        buffer = new ArrayList<>();
        terminar = false;
        in = false;
    }

    public void añadirTarea(String mensaje) {
        lock.lock();
        try {
            if (in) {
                dentro.await();
            }
            buffer.add(mensaje);
            vacio.signalAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }

    public String getTarea() {
        String men = buffer.remove(0);
        return men;
    }

    public void setTerminar(boolean term) {
        this.terminar = term;
    }

    @Override
    public void run() {
        FileWriter doc;
        PrintWriter pw;
        String mens;

        try {
            doc = new FileWriter("./log.txt");          // El log.txt se borra cada ejecución
            pw = new PrintWriter(doc);
            lock.lock();
            try {
                while (buffer.isEmpty()) {
                    try {
                        vacio.await();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                in = true;
                mens = getTarea();
                in = false;
                dentro.signal();
            } finally {
                lock.unlock();
            }

            pw.println(mens);
            pw.close();
            doc.close();

            do {
                doc = new FileWriter("./log.txt", true);
                pw = new PrintWriter(doc);

                lock.lock();
                try {
                    while (!terminar && buffer.isEmpty()) {
                        vacio.await();
                    }
                    in = true;
                    mens = this.getTarea();
                    in = false;
                    dentro.signal();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    lock.unlock();
                }
                pw.println(mens);
                pw.close();
                doc.close();
            } while (!terminar || !buffer.isEmpty());
            this.printInfo();
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printInfo() throws IOException {
        FileWriter doc;
        PrintWriter pw;
                
        doc = new FileWriter("./log.txt", true);
        pw = new PrintWriter(doc);
        pw.println();
        pw.println("INFORMACION SOBRE EL SUPERMERCADO: ");
        pw.println("Tiempo medio en carniceria: " + tMCarnic + " ms.");
        pw.println("Tiempo total en carniceria: " + tTCarn + " ms.");
        pw.println("Tiempo medio en pescaderia: " + tMPescad + " ms.");
        pw.println("Tiempo total en pescaderia: " + tTPesc + " ms.");
        pw.println("Numero de personas que han entrado y salido del supermercado: " + numPers);
        pw.println("Tiempo medio desde cola a salida: " + tMCS + " ms.");
        pw.println("Tiempo medio desde entrada a salida: " + tMES + " ms.");
        
        pw.close();
        doc.close();
    }
    
    public void setInfo(int tMCarn, int tTCarn, int tMPesc, int tTPesc, int numPers, long tMCS, long tMES) {
        this.tMCarnic = tMCarn;
        this.tTCarn = tTCarn;
        this.tMPescad = tMPesc;
        this.tTPesc = tTPesc;
        this.numPers = numPers;
        this.tMCS = tMCS;
        this.tMES = tMES;
    }
}
