
package rapi_express;
import modelo.Conexion;
/**
 *
 * @author jhonperez
 */
public class Rapi_Express {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            Conexion con = new Conexion();
            con.getConnection();
    }
    
}
