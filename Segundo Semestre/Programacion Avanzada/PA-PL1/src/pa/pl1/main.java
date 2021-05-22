package pa.pl1;

/**
 * @author Álvaro Pérez Álamo
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Buffer buf = new Buffer();
         Productor a = new Productor("A", 70, buf);
         Productor b = new Productor("B", 70, buf);
         Productor c = new Productor("C", 70, buf);
         Consumidor jose = new Consumidor("Jose", buf);
         Consumidor ana = new Consumidor("Ana", buf);
         Consumidor maria = new Consumidor("Maria", buf);
         
         a.start();
         b.start();
         c.start();
         jose.start();
         ana.start();
         maria.start();
    }
}
