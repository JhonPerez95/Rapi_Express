package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.FrmEmpleado;

public class ConsultaEmpleado extends Conexion {

    private FrmEmpleado frmEmpleado;
    DefaultTableModel model;

    public ConsultaEmpleado(FrmEmpleado frmEmpleado) {
        this.frmEmpleado = frmEmpleado;
        String[] titles = {"ID", "Nombre", "Apellido", "Salario", "Cedula", "Rol"};
        model = new DefaultTableModel(null, titles);
        frmEmpleado.tblEmpleado.setModel(model);
    }

    public boolean guardar(Empleado empleado) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "INSERT INTO empleado (nombre, apellido, salario, cedula, rol)"
                + "VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setInt(3, empleado.getSalario());
            ps.setInt(4, empleado.getCedula());
            ps.setString(5, empleado.getRol());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }

    }

    public boolean modificar(Empleado empleado) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "UPDATE  empleado SET  nombre=?, apellido=?, salario=?, cedula=?, rol=?"
                + "WHERE id_empleado=?";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setInt(3, empleado.getSalario());
            ps.setInt(4, empleado.getCedula());
            ps.setString(5, empleado.getRol());
            ps.setInt(6, empleado.getId_empleado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminar(Empleado empleado) {
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "DELETE FROM empleado WHERE id_empleado=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, empleado.getId_empleado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean traerUnEmpleado(Empleado empleado) {

        PreparedStatement ps = null;
        Connection con = getConnection();
        ResultSet respuesta = null;
        String sql = "SELECT * FROM empleado WHERE id_empleado=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, empleado.getId_empleado());
            respuesta = ps.executeQuery();

            if (respuesta.next()) {
                Object[] objEmpleado = {Integer.parseInt(respuesta.getString("id_empleado")), respuesta.getString("nombre"), respuesta.getString("apellido"),
                    respuesta.getString("salario"), respuesta.getString("cedula"), respuesta.getString("rol")};
                model.addRow(objEmpleado);
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean traerEmpleados() {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM empleado";

        try {
            ResultSet respuesta = consultarRegistros(sql);
            while (respuesta.next()) {
                Object[] objEmpleado = {Integer.parseInt(respuesta.getString("id_empleado")), respuesta.getString("nombre"), respuesta.getString("apellido"),
                    respuesta.getString("salario"), respuesta.getString("cedula"), respuesta.getString("rol")};
                model.addRow(objEmpleado);
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
