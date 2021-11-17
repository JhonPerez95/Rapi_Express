package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ConsultaFactura extends Conexion {

    public boolean guardar(Factura factura) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "INSERT INTO factura (fecha, cantidad, detalle, forma_pago, total_pagar, cedula_cliente, id_empleado)"
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setDate(1, (Date) factura.getFecha());
            ps.setInt(2, factura.getCantidad());
            ps.setString(3, factura.getDetalle());
            ps.setString(4, factura.getForma_pago());
            ps.setInt(5, factura.getTotal_pagar());
            ps.setInt(6, factura.getCedula_cliente());
            ps.setInt(7, factura.getId_empleado());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }

    }

    public boolean modificar(Factura factura) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "UPDATE  factura SET fecha=?, cantidad=?, detalle=?, forma_pago=?, total_pagar=?, cedula_cliente=?, id_empleado=?"
                + "WHERE id_factura=?";

        try {
            ps = con.prepareStatement(sql);

            ps.setDate(1, (Date) factura.getFecha());
            ps.setInt(2, factura.getCantidad());
            ps.setString(3, factura.getDetalle());
            ps.setString(4, factura.getForma_pago());
            ps.setInt(5, factura.getTotal_pagar());
            ps.setInt(6, factura.getCedula_cliente());
            ps.setInt(7, factura.getId_empleado());
            ps.setInt(8, factura.getId_factura());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminar(Factura factura) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "DELETE FROM factura WHERE id_factura=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getId_factura());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean traerUnaFactura(Factura factura) throws ParseException {

        PreparedStatement ps = null;
        Connection con = getConnection();
        ResultSet respuesta = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        String sql = "SELECT * FROM factura WHERE id_factura=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getId_factura());
            respuesta = ps.executeQuery();

            if (respuesta.next()) {
//                factura.setId_factura(Integer.parseInt(respuesta.getString("id_factura")));
//                factura.setFecha(formato.parse(respuesta.getString("fecha")));
//                factura.setCantidad(Integer.parseInt(respuesta.getString("cantidad")));
//                factura.setDetalle(respuesta.getString("detalle"));
//                factura.setForma_pago(respuesta.getString("forma_pago"));
//                factura.setTotal_pagar(Integer.parseInt(respuesta.getString("total_pagar")));
//                factura.setCedula_cliente(Integer.parseInt(respuesta.getString("cedula_cliente")));
//                factura.setId_empleado(Integer.parseInt(respuesta.getString("id_empleado")));

                System.out.println(Integer.parseInt(respuesta.getString("=============================")));

                System.out.println(Integer.parseInt(respuesta.getString("id_factura")));
                System.out.println(respuesta.getString("fecha"));
                System.out.println(respuesta.getString("cantidad"));
                System.out.println(respuesta.getString("detalle"));
                System.out.println(respuesta.getString("forma_pago"));
                System.out.println(respuesta.getString("total_pagar"));
                System.out.println(respuesta.getString("cedula_cliente"));
                System.out.println(respuesta.getString("id_empleado"));

                System.out.println(Integer.parseInt(respuesta.getString("=============================")));
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean traerFacturas() {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM factura";

        try {
            ResultSet respuesta = consultarRegistros(sql);
            while (respuesta.next()) {
//                factura.setId_factura(Integer.parseInt(respuesta.getString("id_factura")));
//                factura.setFecha(formato.parse(respuesta.getString("fecha")));
//                factura.setCantidad(Integer.parseInt(respuesta.getString("cantidad")));
//                factura.setDetalle(respuesta.getString("detalle"));
//                factura.setForma_pago(respuesta.getString("forma_pago"));
//                factura.setTotal_pagar(Integer.parseInt(respuesta.getString("total_pagar")));
//                factura.setCedula_cliente(Integer.parseInt(respuesta.getString("cedula_cliente")));
//                factura.setId_empleado(Integer.parseInt(respuesta.getString("id_empleado")));                System.out.println(Integer.parseInt(respuesta.getString("cedula_cliente")));

                System.out.println(Integer.parseInt(respuesta.getString("=============================")));

                System.out.println(Integer.parseInt(respuesta.getString("id_factura")));
                System.out.println(respuesta.getString("fecha"));
                System.out.println(respuesta.getString("cantidad"));
                System.out.println(respuesta.getString("detalle"));
                System.out.println(respuesta.getString("forma_pago"));
                System.out.println(respuesta.getString("total_pagar"));
                System.out.println(respuesta.getString("cedula_cliente"));
                System.out.println(respuesta.getString("id_empleado"));

                System.out.println(Integer.parseInt(respuesta.getString("=============================")));
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
