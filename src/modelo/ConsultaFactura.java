package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.FrmFactura;

public class ConsultaFactura extends Conexion {

    private FrmFactura frmFactura;
    DefaultTableModel model;

    public ConsultaFactura(FrmFactura frmFactura) {
        this.frmFactura = frmFactura;

        String[] titles = {"ID", "Fecha", "Cantidad", "Detalle", "Forma Pago", "Total Pagar", "Cliente", "Empleado"};
        model = new DefaultTableModel(null, titles);
        frmFactura.tblFactura.setModel(model);
    }

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

    public boolean traerUnaFactura(Factura factura) {

        PreparedStatement ps = null;
        Connection con = getConnection();
        ResultSet respuesta = null;
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        String sql = "SELECT * FROM factura WHERE id_factura=?";
        
        // Resetear datos tabla
        model.setRowCount(0);
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getId_factura());
            respuesta = ps.executeQuery();

            if (respuesta.next()) {
                Object[] objFactura = {Integer.parseInt(respuesta.getString("id_factura")), respuesta.getString("fecha"), respuesta.getString("cantidad"),
                    respuesta.getString("detalle"), respuesta.getString("forma_pago"), respuesta.getString("total_pagar"), respuesta.getString("cedula_cliente"),
                    respuesta.getString("id_empleado")};
                model.addRow(objFactura);
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
        
        // Resetear datos tabla
        model.setRowCount(0);

        try {
            ResultSet respuesta = consultarRegistros(sql);
            while (respuesta.next()) {
                Object[] objFactura = {Integer.parseInt(respuesta.getString("id_factura")), respuesta.getString("fecha"), respuesta.getString("cantidad"),
                    respuesta.getString("detalle"), respuesta.getString("forma_pago"), respuesta.getString("total_pagar"), respuesta.getString("cedula_cliente"),
                    respuesta.getString("id_empleado")};
                model.addRow(objFactura);
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
