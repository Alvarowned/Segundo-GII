/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basesdedatospecl3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alvar
 */
public class Int_Consulta extends javax.swing.JFrame {
    
    private final String url, user, password;
    private final Interfaz inter;
    private Connection conn;
    
    /**
     * Creates new form Int_Consulta
     */
    public Int_Consulta(String url, String user, String password, Interfaz inter) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.inter = inter;
        this.url = url;
        this.user = user;
        this.password = password;
        this.conn = null;
        conn = this.connect(url, user, password);
    }
    
    // Realiza la conexión con la base de datos
    public Connection connect(String url, String usu, String pass){
        try
        {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(url, usu, pass);
        }catch(SQLException e){
            System.out.println(e.getMessage());        
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            System.out.println("Driver no detectado");
        }
        return conn;                        
    }
    
    // Realizar un número de consulta dada
    public void consultaN(int i) {
        String resul = "";
        String query;
        Statement stm;
        ResultSet rs;
        
        try {
            switch(i) {
                case 1:
                    query = "SELECT \"Código_grupo\"\n" +
                            "FROM public.\"Grupo\"\n" +
                            "WHERE \"Género\" LIKE 'Jazz';";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de grupos de género Jazz\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Código_grupo") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                
                case 2:
                    query = "SELECT S.\"Nombre_músico\"\n" +
                            "FROM public.\"Músico_toca_Instrumento\" AS T, public.\"Músico\" AS S\n" +
                            "WHERE T.\"DNI_músico_Músico\" = S.\"DNI_músico\" AND \"Nombre_instrumento_Instrumento\" LIKE 'Batería';";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de músicos que tocan la batería\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Nombre_músico") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 3:
                    query = "SELECT \"Nombre_músico\"\n" +
                            "FROM public.\"Músico\" AS T\n" +
                            "JOIN public.\"Músico_toca_Instrumento\" AS S ON T.\"DNI_músico\" = S.\"DNI_músico_Músico\"\n" +
                            "JOIN public.\"Grupo\" AS W ON T.\"Código_grupo_Grupo\" = W.\"Código_grupo\"\n" +
                            "WHERE \"Género\" LIKE 'Jazz' AND \"Nombre_instrumento_Instrumento\" LIKE 'Saxofón';";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de saxofonistas de Jazz\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Nombre_músico") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 4:
                    query = "SELECT \"Código_grupo\"\n" +
                            "FROM public.\"Grupo\" AS T\n" +
                            "JOIN public.\"Grupo_da_Concierto\" AS S ON T.\"Código_grupo\" = S.\"Código_grupo_Grupo\"\n" +
                            "JOIN public.\"Concierto\" AS W ON S.\"Código_concierto_Concierto\" = W.\"Código_concierto\"\n" +
                            "JOIN public.\"Sitio Concierto\" AS SC ON SC.\"Código_concierto_Concierto\" = W.\"Código_concierto\"\n" +
                            "WHERE \"País\" LIKE 'España';";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de grupos que han dado conciertos en España\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Código_grupo") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 5:
                    query = "SELECT \"Título\"\n" +
                            "FROM public.\"Disco\" AS Disco INNER JOIN (SELECT \"Num_referencia_Disco\", COUNT(\"Nombre_canción\")\n" +
                            "                                FROM public.\"Canción\"\n" +
                            "                                GROUP BY \"Num_referencia_Disco\"\n" +
                            "                                HAVING COUNT(\"Nombre_canción\") > 10) AS T\n" +
                            "ON Disco.\"Num_referencia\" = T.\"Num_referencia_Disco\";";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de discos con más de 10 canciones\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Título") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 6:
                    query = "SELECT \"Código_grupo\", \"Nombre_grupo\"\n" +
                            "FROM (SELECT \"Código_grupo\", \"Nombre_grupo\", COUNT(\"DNI_músico\")\n" +
                            "      FROM (SELECT \"Nombre_grupo\", \"Código_grupo\", \"DNI_músico\"\n" +
                            "            FROM public.\"Grupo\" AS Grupo INNER JOIN public.\"Músico\" AS Músico ON Grupo.\"Código_grupo\" = Músico.\"Código_grupo_Grupo\") AS T\n" +
                            "      GROUP BY \"Código_grupo\", \"Nombre_grupo\"\n" +
                            "      HAVING COUNT(\"DNI_músico\") > 3) \n" +
                            "AS S;";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de grupos con más de 3 miembros\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + "Código grupo: "+ rs.getString("Código_grupo").trim() + "\n";
                        resul = resul + "Nombre grupo: "+ rs.getString("Nombre_grupo").trim() + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 7:
                    query = "SELECT \"Opinión\", DNI\n" +
                            "FROM public.\"Compra_Disco\" AS S INNER JOIN (SELECT \"DNI_usuario_Usuario\" AS DNI, COUNT(\"Num_referencia_Disco\")\n" +
                            "                                            FROM public.\"Compra_Disco\"\n" +
                            "                                            GROUP BY DNI\n" +
                            "                                            HAVING COUNT(\"Num_referencia_Disco\") > 2) AS T\n" +
                            "ON S.\"DNI_usuario_Usuario\" = T.DNI;";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de opiniones emitidas por compradores de al menos 3 discos\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Código_grupo") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 8:
                    query = "SELECT \"Título\"\n" +
                            "FROM public.\"Formato\" AS Formato INNER JOIN (SELECT \"Título\", \"Referencia_Formato\", \"Género\"\n" +
                            "                                             FROM public.\"Disco\" AS Disco INNER JOIN (SELECT \"Num_referencia_Disco\", MAX(\"Duración\")\n" +
                            "                                                                                      FROM public.\"Canción\"\n" +
                            "                                                                                      GROUP BY \"Num_referencia_Disco\"\n" +
                            "                                                                                      HAVING MAX(\"Duración\") >= 5) AS T\n" +
                            "                                             ON Disco.\"Num_referencia\" = T.\"Num_referencia_Disco\") AS S\n" +
                            "ON Formato.\"Referencia\" = S.\"Referencia_Formato\"\n" +
                            "WHERE \"Tipo\" LIKE 'LP' AND \"Género\" LIKE 'Rock';";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de discos Rock de vinilo con al menos una canción de más de 5 minutos\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Título") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 9:
                    query = "SELECT \"Nombre_músico\"\n" +
                            "FROM public.\"Músico\" AS Músico INNER JOIN (SELECT \"Código_grupo_Grupo\" AS Código_grupo\n" +
                            "                                           FROM public.\"Grupo_da_Concierto\" AS Grupo_da_Conc INNER JOIN (SELECT Cod_con, \"Ciudad\"\n" +
                            "                                                                                                         FROM public.\"Sitio Concierto\" AS SC INNER JOIN (SELECT \"Código_concierto_Concierto\" AS Cod_con, MAX(\"Precio\")\n" +
                            "                                                                                                                                                         FROM public.\"Entrada\"\n" +
                            "                                                                                                                                                         GROUP BY Cod_con\n" +
                            "                                                                                                                                                         HAVING MAX(\"Precio\") > 100) AS T\n" +
                            "                                                                                                         ON SC.\"Código_concierto_Concierto\" = T.Cod_con\n" +
                            "                                                                                                         WHERE \"Ciudad\" LIKE 'Madrid') AS S\n" +
                            "                                           ON Grupo_da_Conc.\"Código_concierto_Concierto\" = S.Cod_con) AS W\n" +
                            "ON Músico.\"Código_grupo_Grupo\" = W.Código_grupo;";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de músicos de grupos que han dado conciertos en Madrid y la entrada cuesta más de 100 euros\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Nombre_músico") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 10:
                    query = "SELECT \"Opinión\"\n" +
                            "FROM public.\"Compra_Entrada\" AS Compra INNER JOIN (SELECT *\n" +
                            "                                                   FROM public.\"Entrada\" AS Entrada INNER JOIN (SELECT Cod_con\n" +
                            "                                                                                                FROM public.\"Sitio Concierto\" AS SC INNER JOIN (SELECT \"Código_concierto_Concierto\" AS Cod_con, MAX(\"Precio\")\n" +
                            "                                                                                                                                                FROM public.\"Entrada\"\n" +
                            "                                                                                                                                                GROUP BY Cod_con\n" +
                            "                                                                                                                                                HAVING MAX(\"Precio\") > 100) AS T\n" +
                            "                                                                                                ON SC.\"Código_concierto_Concierto\" = T.Cod_con\n" +
                            "                                                                                                WHERE \"Ciudad\" LIKE 'Madrid') AS S\n" +
                            "                                                   ON Entrada.\"Código_concierto_Concierto\" = S.Cod_con) AS W\n" +
                            "ON Compra.\"Código_entrada_Entrada\" = W.\"Código_entrada\";";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de opiniones de usuarios que han asistido a un concierto en Madrid en el que la entrada valía más de 100 euros\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + ""+rs.getString("Opinión") + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 11:
                    query = "SELECT \"Nombre_músico\", \"Apellidos_músico\", \"Título\", \"Nombre_canción\"\n" +
                            "FROM public.\"Músico\" AS Músico INNER JOIN (SELECT \"Nombre_canción\", \"Título\", \"DNI_músico_Músico\"\n" +
                            "                                           FROM public.\"Músico_compone_Canción\" AS Músico_comp INNER JOIN (SELECT \"Nombre_canción\", \"Título\"\n" +
                            "                                                                                                           FROM public.\"Canción\" AS Canción INNER JOIN (SELECT \"Num_referencia\", \"Título\"\n" +
                            "                                                                                                                                                        FROM public.\"Disco\"\n" +
                            "                                                                                                                                                        WHERE \"Fecha_edición\" BETWEEN '01/01/2018' AND '31/12/2018') AS T\n" +
                            "                                                                                                           ON Canción.\"Num_referencia_Disco\" = T.\"Num_referencia\") AS S\n" +
                            "                                           ON Músico_comp.\"Nombre_canción_Canción\" = S.\"Nombre_canción\") AS W\n" +
                            "ON Músico.\"DNI_músico\" = W.\"DNI_músico_Músico\";";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Canciones de los discos Heavy Metal publicados en 2018 y su compositor\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + "Nombre: "+rs.getString("Nombre_músico").trim() + "\n";
                        resul = resul + "Apellidos: "+rs.getString("Apellidos_músico").trim() + "\n";
                        resul = resul + "Título: "+rs.getString("Título").trim() + "\n";
                        resul = resul + "Nombre (canción): "+rs.getString("Nombre_canción").trim() + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 12:
                    query = "SELECT \"Nombre_músico\", \"Apellidos_músico\", \"Título\", \"Tipo\", \"Tamaño_Mbytes\"\n" +
                            "FROM public.\"Formato Digital\" AS FD INNER JOIN (SELECT DISTINCT \"Título\", \"Nombre_músico\", \"Apellidos_músico\", \"Referencia_Formato\"\n" +
                            "                                                FROM public.\"Disco\" AS Disco INNER JOIN (SELECT \"Nombre_músico\", \"Apellidos_músico\", \"Nombre_canción\", \"Num_referencia_Disco\"\n" +
                            "                                                                                         FROM public.\"Canción\" AS Canción INNER JOIN (SELECT \"Nombre_músico\", \"Apellidos_músico\", \"Nombre_canción_Canción\" AS Nombre_canción\n" +
                            "                                                                                                                                      FROM public.\"Músico_compone_Canción\" AS Músico_compone INNER JOIN (SELECT \"Nombre_músico\", \"Apellidos_músico\", \"DNI_músico\"\n" +
                            "                                                                                                                                                                                                         FROM public.\"Músico_toca_Instrumento\" AS Músico_toca INNER JOIN public.\"Músico\" AS Músico\n" +
                            "                                                                                                                                                                                                         ON Músico.\"DNI_músico\" = Músico_toca.\"DNI_músico_Músico\"\n" +
                            "                                                                                                                                                                                                         WHERE \"Nombre_instrumento_Instrumento\" LIKE 'Guitarra') AS T\n" +
                            "                                                                                                                                      ON Músico_compone.\"DNI_músico_Músico\" = T.\"DNI_músico\") AS S\n" +
                            "                                                                                         ON Canción.\"Nombre_canción\" = S.Nombre_canción) AS W\n" +
                            "                                                ON Disco.\"Num_referencia\" = W.\"Num_referencia_Disco\") AS H\n" +
                            "ON FD.\"Referencia\" = H.\"Referencia_Formato\";";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Guitarristas de Jazz y sus discos en formato digital, su formato y su tamaño (MBytes)\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + "Nombre: "+rs.getString("Nombre_músico").trim() + "\n";
                        resul = resul + "Apellidos: "+rs.getString("Apellidos_músico").trim() + "\n";
                        resul = resul + "Título: "+rs.getString("Título").trim() + "\n";
                        resul = resul + "Tipo: "+rs.getString("Tipo").trim() + "\n";
                        resul = resul + "Tamaño (en Mbytes): "+rs.getString("Tamaño_Mbytes").trim() + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 13:
                    query = "SELECT \"Nombre_usuario\", \"Apellidos_usuario\"\n" +
                            "FROM public.\"Usuario\" AS Usuario INNER JOIN (SELECT DISTINCT \"DNI_usuario_Usuario\" AS DNI\n" +
                            "                                             FROM public.\"Compra_Entrada\" AS Compra INNER JOIN (SELECT \"Código_entrada\"\n" +
                            "                                                                                                FROM public.\"Entrada\" AS Entrada INNER JOIN (SELECT \"Código_concierto\"\n" +
                            "                                                                                                                                             FROM (SELECT \"Código_concierto_Concierto\"\n" +
                            "                                                                                                                                                   FROM public.\"Grupo_da_Concierto\" AS Grupo_da INNER JOIN (SELECT \"Código_grupo\", \"Género\"\n" +
                            "                                                                                                                                                                                                            FROM public.\"Grupo\"\n" +
                            "                                                                                                                                                                                                            WHERE \"Género\" LIKE 'Blues') AS T\n" +
                            "                                                                                                                                                   ON Grupo_da.\"Código_grupo_Grupo\" = T.\"Código_grupo\") AS S\n" +
                            "                                                                                                                                             INNER JOIN \n" +
                            "                                                                                                                                                  (SELECT \"Código_concierto\"\n" +
                            "                                                                                                                                                   FROM public.\"Concierto\"\n" +
                            "                                                                                                                                                   WHERE \"Fecha\" BETWEEN '01/01/2018' AND '31/12/2018') AS W\n" +
                            "                                                                                                                                             ON S.\"Código_concierto_Concierto\" = W.\"Código_concierto\") AS H\n" +
                            "                                                                                                ON Entrada.\"Código_concierto_Concierto\" = H.\"Código_concierto\") AS X\n" +
                            "                                            ON Compra.\"Código_entrada_Entrada\" = X.\"Código_entrada\"\n" +
                            "                                            WHERE \"Opinión\" IS NOT NULL) AS G\n" +
                            "ON Usuario.\"DNI_usuario\" = G.DNI;";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de usuarios que han comprado entrada para un concierto Blues de 2018 y con alguna opinión\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + "Nombre: "+rs.getString("Nombre_usuario").trim() + "\n";
                        resul = resul + "Apellidos: "+rs.getString("Apellidos_usuario").trim() + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 14:
                    query = "SELECT \"Título\", \"Nombre_canción\"\n" +
                            "FROM public.\"Canción\" AS Canción INNER JOIN (SELECT \"Num_referencia\", \"Título\"\n" +
                            "                                             FROM (SELECT \"Num_referencia_Disco\", AVG(\"Opinión\")\n" +
                            "                                                   FROM public.\"Compra_Disco\"\n" +
                            "                                                   GROUP BY \"Num_referencia_Disco\"\n" +
                            "                                                   HAVING AVG(\"Opinión\") > 8) AS T\n" +
                            "                                             INNER JOIN public.\"Disco\" AS Disco\n" +
                            "                                             ON Disco.\"Num_referencia\" = T.\"Num_referencia_Disco\") AS S\n" +
                            "ON Canción.\"Num_referencia_Disco\" = S.\"Num_referencia\";";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de discos y sus canciones cuyas opiniones superan el 8\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + "Título: "+rs.getString("Título").trim() + "\n";
                        resul = resul + "Nombre: : "+rs.getString("Nombre_canción").trim() + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 15:
                    query = "SELECT \"Nombre_músico\", \"Apellidos_músico\", \"Nombre_grupo\"\n" +
                            "FROM (SELECT DISTINCT \"DNI_músico\", \"Nombre_músico\", \"Apellidos_músico\", \"Nombre_grupo\"\n" +
                            "      FROM (SELECT \"Num_referencia_Disco\", COUNT(\"Nombre_canción\"), \"Nombre_canción\"\n" +
                            "            FROM public.\"Canción\"\n" +
                            "            GROUP BY \"Num_referencia_Disco\", \"Nombre_canción\"\n" +
                            "            HAVING COUNT(\"Nombre_canción\") > 10) AS X\n" +
                            "      INNER JOIN \n" +
                            "           (SELECT \"Nombre_músico\", \"Apellidos_músico\", \"Nombre_grupo\", \"DNI_músico\", \"Nombre_canción_Canción\"\n" +
                            "            FROM public.\"Músico_compone_Canción\" AS Músico_comp INNER JOIN (SELECT \"Nombre_músico\", \"Apellidos_músico\", \"Nombre_grupo\", \"DNI_músico\"\n" +
                            "                                                                            FROM public.\"Músico\" AS Músico INNER JOIN (SELECT \"Código_grupo\", \"Nombre_grupo\"\n" +
                            "                                                                                                                       FROM public.\"Grupo\" AS Grupo INNER JOIN (SELECT DISTINCT \"Código_grupo_Grupo\"\n" +
                            "                                                                                                                                                                FROM public.\"Grupo_da_Concierto\" AS Grupo_da LEFT JOIN (SELECT \"Código_concierto_Concierto\"\n" +
                            "                                                                                                                                                                                                                        FROM public.\"Sitio Concierto\"\n" +
                            "                                                                                                                                                                                                                        WHERE \"País\" LIKE 'España') AS T\n" +
                            "                                                                                                                                                                ON Grupo_da.\"Código_concierto_Concierto\" = T.\"Código_concierto_Concierto\") AS S\n" +
                            "                                                                                                                       ON Grupo.\"Código_grupo\" = S.\"Código_grupo_Grupo\") AS C               \n" +
                            "                                                                            ON Músico.\"Código_grupo_Grupo\" = C.\"Código_grupo\") AS W\n" +
                            "            ON Músico_comp.\"DNI_músico_Músico\" = W.\"DNI_músico\") AS Y\n" +
                            "      ON X.\"Nombre_canción\" = Y.\"Nombre_canción_Canción\") \n" +
                            "      AS F;";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de grupos y sus músicos que no han dado conciertos en España y tienen discos con más de 10 canciones\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + "Nombre (músico): "+rs.getString("Nombre_músico").trim() + "\n";
                        resul = resul + "Apellidos (músico): "+rs.getString("Apellidos_músico").trim() + "\n";
                        resul = resul + "Nombre (grupo): "+rs.getString("Nombre_grupo").trim() + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
                    
                case 16:
                    query = "SELECT \"Nombre_músico\", \"Apellidos_músico\", \"Nombre_canción_Canción\"\n" +
                            "FROM public.\"Músico_compone_Canción\" AS Músico_comp INNER JOIN (SELECT \"Nombre_músico\", \"Apellidos_músico\", \"DNI_músico\"\n" +
                            "                                                                FROM public.\"Músico\" AS Músico INNER JOIN (SELECT DISTINCT \"Código_grupo_Grupo\" AS Código_grupo\n" +
                            "                                                                                                           FROM public.\"Grupo_da_Concierto\" AS Grupo_da LEFT JOIN (SELECT \"Código_concierto\"\n" +
                            "                                                                                                                                                                   FROM public.\"Concierto\"\n" +
                            "                                                                                                                                                                   WHERE \"Fecha\" BETWEEN '01/01/2017' AND '31/12/2017') AS T \n" +
                            "                                                                                                           ON Grupo_da.\"Código_concierto_Concierto\" = T.\"Código_concierto\") AS S\n" +
                            "                                                                ON Músico.\"Código_grupo_Grupo\" = S.Código_grupo) AS W\n" +
                            "ON Músico_comp.\"DNI_músico_Músico\" = W.\"DNI_músico\";";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);
            
                    resul = "Listado de canciones y sus compositores grabadas por grupos que no han dado ningún concierto en 2017\n";
                    resul = resul + "=============================\n";
                    while(rs.next())
                    {
                        resul = resul + "Nombre (músico): "+rs.getString("Nombre_músico").trim() + "\n";
                        resul = resul + "Apellidos (músico): "+rs.getString("Apellidos_músico").trim() + "\n";
                        resul = resul + "Nombre (canción): "+rs.getString("Nombre_canción_Canción").trim() + "\n";
                    }
                    resul = resul + "=============================\n";
                    break;
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        jTextArea1.setText(resul);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton9 = new javax.swing.JRadioButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton16 = new javax.swing.JRadioButton();
        jRadioButton17 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jRadioButton15 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        jRadioButton9.setText("8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ELEGIR CONSULTA:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("1");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("2");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("3");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("4");

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setText("5");

        buttonGroup1.add(jRadioButton6);
        jRadioButton6.setText("6");

        buttonGroup1.add(jRadioButton7);
        jRadioButton7.setText("7");

        buttonGroup1.add(jRadioButton8);
        jRadioButton8.setText("8");

        buttonGroup1.add(jRadioButton10);
        jRadioButton10.setText("9");

        buttonGroup1.add(jRadioButton11);
        jRadioButton11.setText("10");

        buttonGroup1.add(jRadioButton12);
        jRadioButton12.setText("11");

        buttonGroup1.add(jRadioButton13);
        jRadioButton13.setText("12");

        buttonGroup1.add(jRadioButton16);
        jRadioButton16.setText("15");

        buttonGroup1.add(jRadioButton17);
        jRadioButton17.setText("16");

        buttonGroup1.add(jRadioButton14);
        jRadioButton14.setText("13");

        buttonGroup1.add(jRadioButton15);
        jRadioButton15.setText("14");

        jButton1.setText("Realizar consulta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton10)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton5)
                                .addGap(25, 25, 25)
                                .addComponent(jRadioButton6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton14)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton15)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton16)
                            .addComponent(jRadioButton7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton17)
                            .addComponent(jRadioButton8))
                        .addGap(75, 75, 75)
                        .addComponent(jButton1)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton5)
                            .addComponent(jRadioButton6)
                            .addComponent(jRadioButton7)
                            .addComponent(jRadioButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton10)
                            .addComponent(jRadioButton11)
                            .addComponent(jRadioButton12)
                            .addComponent(jRadioButton13)
                            .addComponent(jRadioButton16)
                            .addComponent(jRadioButton17)
                            .addComponent(jRadioButton14)
                            .addComponent(jRadioButton15))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Para realizar una consulta seleccionada
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jRadioButton1.isSelected()) consultaN(1);
        else if(jRadioButton2.isSelected()) consultaN(2);
        else if(jRadioButton3.isSelected()) consultaN(3);
        else if(jRadioButton4.isSelected()) consultaN(4);
        else if(jRadioButton5.isSelected()) consultaN(5);
        else if(jRadioButton6.isSelected()) consultaN(6);
        else if(jRadioButton7.isSelected()) consultaN(7);
        else if(jRadioButton8.isSelected()) consultaN(8);
        else if(jRadioButton10.isSelected()) consultaN(9);
        else if(jRadioButton11.isSelected()) consultaN(10);
        else if(jRadioButton12.isSelected()) consultaN(11);
        else if(jRadioButton13.isSelected()) consultaN(12);
        else if(jRadioButton14.isSelected()) consultaN(13);
        else if(jRadioButton15.isSelected()) consultaN(14);
        else if(jRadioButton16.isSelected()) consultaN(15);
        else if(jRadioButton17.isSelected()) consultaN(16);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JRadioButton jRadioButton17;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
