/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo1;

import java.io.*;
import java.net.*;

/**
 *
 * @author alvar
 */
public class TareaServer implements Runnable {
    Exposicion exposicion;
    ServerSocket skServer;
    
    public TareaServer(Exposicion exposicion, ServerSocket skServer) {
        this.exposicion = exposicion;
        this.skServer = skServer;
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                Socket conexion = skServer.accept();
                DataInputStream entrada = new DataInputStream(conexion.getInputStream());
                String mensaje;
                
                DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
                salida.writeBoolean(true);
                
                do {
                    mensaje = entrada.readUTF();
                    if("Detener".equals(mensaje)) {
                        exposicion.setPausado(true);
                    } else if("Reanudar".equals(mensaje)) {
                        exposicion.setPausado(false);
                    }
                } while (!"Cierre".equals(mensaje));
                
                entrada.close();
                salida.close();
                conexion.close();
            } catch(IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
    }
}
