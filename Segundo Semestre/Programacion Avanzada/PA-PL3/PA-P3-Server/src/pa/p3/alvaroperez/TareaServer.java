/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa.p3.alvaroperez;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alvar
 */
public class TareaServer implements Runnable {
    Supermercado s;
    ServerSocket skServer;
    
    public TareaServer(Supermercado s, ServerSocket skServer) {
        this.s = s;
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
                    if(null != mensaje) switch (mensaje) {
                        case "Detener super":
                            s.setAbierto(false);
                            break;
                        case "Reanudar super":
                            s.setAbierto(true);
                            s.reanudar();
                            break;
                        case "Detener carn":
                            s.setParadoCarn(true);
                            break;
                        case "Reanudar carn":
                            s.unlockCarn();
                            break;
                        case "Detener pesc":
                            s.setParadoPesc(true);
                            break;
                        case "Reanudar pesc":
                            s.unlockPesc();
                            break;
                        default:
                            break;
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

