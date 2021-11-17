package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.FrmCliente;

public class ConsultaCliente extends Conexion {

    private FrmCliente frmCliente;
    DefaultTableModel model;

    public ConsultaCliente(FrmCliente frmCliente) {
        this.frmCliente = frmCliente;

        String[] titles = {"Cedula", "Nombre", "Apellido", "Correo", "Telefono", "Direccion"};
        model = new DefaultTableModel(null, titles);
        frmCliente.tblCliente.setModel(model);
    }

    public boolean guardar(Cliente client) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "INSERT INTO cliente (cedula_cliente, nombre, apellido, correo, telefono, direccion)"
                + "VALUES(?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, client.getCedula_cliente());
            ps.setString(2, client.getNombre());
            ps.setString(3, client.getApellido());
            ps.setString(4, client.getCorreo());
            ps.setString(5, client.getTelefono());
            ps.setString(6, client.getDireccion());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }

    }

    public boolean modificar(Cliente client) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "UPDATE  cliente SET cedula_cliente=?, nombre=?, apellido=?, correo=?, telefono=?, direccion=?"
                + "WHERE cedula_cliente=?";

        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, client.getCedula_cliente());
            ps.setString(2, client.getNombre());
            ps.setString(3, client.getApellido());
            ps.setString(4, client.getCorreo());
            ps.setString(5, client.getTelefono());
            ps.setString(6, client.getDireccion());
            ps.setInt(7, client.getCedula_cliente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean eliminar(Cliente client) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "DELETE FROM cliente WHERE cedula_cliente=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, client.getCedula_cliente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean traerUnCliente(Cliente client) {

        PreparedStatement ps = null;
        Connection con = getConnection();
        ResultSet respuesta = null;
        String sql = "SELECT * FROM cliente WHERE cedula_cliente=?";

        // Resetear datos tabla
        model.setRowCount(0);

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, client.getCedula_cliente());
            respuesta = ps.executeQuery();

            if (respuesta.next()) {
//                client.setCedula_cliente(Integer.parseInt(respuesta.getString("cedula_cliente")));
//                client.setNombre(respuesta.getString("nombre"));
//                client.setApellido(respuesta.getString("apellido"));
//                client.setCorreo(respuesta.getString("correo"));
//                client.setTelefono(respuesta.getString("telefono"));
//                client.setDireccion(respuesta.getString("direccion"));
//                

                Object[] objCliente = {Integer.parseInt(respuesta.getString("cedula_cliente")), respuesta.getString("nombre"), respuesta.getString("apellido"),
                    respuesta.getString("correo"), respuesta.getString("telefono"), respuesta.getString("direccion")};
                model.addRow(objCliente);
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean traerClientes() {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM cliente";

        // Resetear datos tabla
        model.setRowCount(0);
        try {
            ResultSet respuesta = consultarRegistros(sql);
            while (respuesta.next()) {
//                client.setCedula_cliente(Integer.parseInt(respuesta.getString("cedula_cliente")));
//                client.setNombre(respuesta.getString("nombre"));
//                client.setApellido(respuesta.getString("apellido"));
//                client.setCorreo(respuesta.getString("correo"));
//                client.setTelefono(respuesta.getString("telefono"));
//                client.setDireccion(respuesta.getString("direccion"));

                Object[] objCliente = {Integer.parseInt(respuesta.getString("cedula_cliente")), respuesta.getString("nombre"), respuesta.getString("apellido"),
                    respuesta.getString("correo"), respuesta.getString("telefono"), respuesta.getString("direccion")};
                model.addRow(objCliente);

            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
