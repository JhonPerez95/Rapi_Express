package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jhonperez
 */
public class Conexion {

    String driver = "com.mysql.cj.jdbc.Driver";
    String user = "root";
    String pass = "root12345";
    String nameDb = "prueba";
    String url = "jdbc:mysql://localhost:3306/" + nameDb + "?useUnicode=true&use"
            + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC";

    Connection conn = null;
    //constructor de la clase

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Se conecto a la db: " + nameDb);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ocurre un error : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void desconectar() {
        conn = null;
    } 
  
}
