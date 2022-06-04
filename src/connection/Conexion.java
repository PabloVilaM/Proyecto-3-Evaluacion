package connection;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author celta
 */
public class Conexion {

    private static Conexion instance;
    private Statement sentence;
    private static Connection c;
    private String nombreTabla;
    private ResultSet rs;

    private Conexion() {
        // cadena de conexión
        String url = "jdbc:sqlite:src/connection/exceptionRun.db";

        // hago la conexion en el constructor
        // creo un Statement para reusar
        try {
            c = DriverManager.getConnection(url);
            sentence = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstance(){
        // solo hago el new si es null
        if (instance == null){
            instance = new Conexion();
        }
        // devuelvo siempre la unica instancia
        return instance;
    }

    /*public Conexion(Statement sentence, Connection c, String nombreTabla) {
        this.sentence = sentence;
        this.c = c;
        this.nombreTabla = nombreTabla;
    }*/

    //public Conexion() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    //Código:
    public void crearBase(String nombreTabla) throws SQLException {
        this.nombreTabla = nombreTabla;
        try {
            String sql ="CREATE TABLE IF NOT EXISTS " + nombreTabla + " "
                    + "(NOMBRE TEXT NOT NULL,"
                    + "SCORE INT NOT NULL)";

            sentence.execute(sql);
            sentence.close();
            c.close();
            System.out.println("TABLA CREADA");
        } catch (Exception e) {
            System.err.println("ERROR CREACION TABLA");
        }

    }

    public void conectarBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/connection/exceptionRun.db");

        } catch (Exception e) {
            System.err.println("Error al conectar");
        }

    }

    public void insertar(String alias, String vida) throws SQLException, ClassNotFoundException {
       
        try {
             Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/connection/exceptionRun.db");
           sentence = c.createStatement();
            PreparedStatement ps = c.prepareStatement("insert into Puntuaciones values (?, ?)");
             ps.setString(1, alias);
            ps.setString(2, vida);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void consultar(String nombreTabla) throws SQLException, ClassNotFoundException {
        
        try {
             Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:src/connection/exceptionRun.db");
             PreparedStatement st = c.prepareStatement("select * from Puntuaciones");
           
            
            rs = st.executeQuery();
            while (rs.next()) {
               
                System.out.println(rs.getString("NOMBRE"));
                System.out.println(rs.getString("SCORE"));
            }
 
           
       rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
