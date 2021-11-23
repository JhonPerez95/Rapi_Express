package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jhonperez
 */
public class Conexion {

    String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String pass = "root12345";    
    //String pass = "root12345";

    String nameDb = "bdcaferapiexpress";  
    //String nameDb = "bdcaferapiexpress";
    String url = "jdbc:mysql://localhost:3306/" + nameDb + "?useUnicode=true&use"
            + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC";

    Connection conn = null;
//constructor de la clase

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");

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
        System.out.println("Se desconecto de la base de datos");
        conn = null;
    }

    /*
        Metodo que devuelve datos encontrados por la consulta
     */
    public ResultSet consultarRegistros(String strSentenciaSQL) {
        try {
            PreparedStatement pstm = conn.prepareStatement(strSentenciaSQL);
            ResultSet respuesta = pstm.executeQuery();
            return respuesta;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     *
     * @param strSentenciaSQL , setencia SQL que sera ejecutado
     * @return devuelve falso o verdadero
     */
    public boolean ejecutarSetenciaSQL(String strSentenciaSQL) {
        try {
            PreparedStatement pstm = conn.prepareStatement(strSentenciaSQL);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
