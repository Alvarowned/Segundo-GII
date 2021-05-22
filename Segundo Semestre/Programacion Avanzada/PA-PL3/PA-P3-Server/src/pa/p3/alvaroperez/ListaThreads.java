package pa.p3.alvaroperez;

import java.util.*;
import javax.swing.JTextField;

public class ListaThreads
{
    final private ArrayList<Thread> lista;
    final private JTextField tf;
    
    public ListaThreads(JTextField tf)
    {
        lista=new ArrayList<>();
        this.tf=tf;
    }
    
    public synchronized void meter(Thread t)
    {
        lista.add(t);
        imprimir();
    }
    
    public synchronized void sacar(Thread t)
    {
        lista.remove(t);
        imprimir();
    }
    
    public void imprimir()
    {
        String contenido="";
        for(int i=0; i<lista.size(); i++)
        {
           contenido=contenido+lista.get(i).getName()+" ";
        }
        tf.setText(contenido);
    }
    
    public ArrayList<Thread> getLista() {
        return lista;
    }
    
    public void quitarTodos() {
        while(!lista.isEmpty()) {
            lista.remove(0);
        }
        imprimir();
    }
}
