package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ConsultaEmpleado extends Conexion {

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
//                empleado.setId_empleado(Integer.parseInt(respuesta.getString("cedula_cliente")));
//                empleado.setNombre(respuesta.getString("nombre"));
//                empleado.setApellido(respuesta.getString("apellido"));
//                empleado.setSalario(Integer.parseInt(respuesta.getString("salario")));
//                empleado.setCedula(Integer.parseInt(respuesta.getString("cedula")));
//                empleado.setRol(respuesta.getString("rol"));

                System.out.println(Integer.parseInt(respuesta.getString("id_cliente")));
                System.out.println(respuesta.getString("nombre"));
                System.out.println(respuesta.getString("apellido"));
                System.out.println(Integer.parseInt(respuesta.getString("salario")));
                System.out.println(Integer.parseInt(respuesta.getString("cedula")));
                System.out.println(respuesta.getString("rol"));

            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean traerClientes() {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM empleado";

        try {
            ResultSet respuesta = consultarRegistros(sql);
            while (respuesta.next()) {
//                empleado.setId_empleado(Integer.parseInt(respuesta.getString("cedula_cliente")));
//                empleado.setNombre(respuesta.getString("nombre"));
//                empleado.setApellido(respuesta.getString("apellido"));
//                empleado.setSalario(Integer.parseInt(respuesta.getString("salario")));
//                empleado.setCedula(Integer.parseInt(respuesta.getString("cedula")));
//                empleado.setRol(respuesta.getString("rol"));

                System.out.println(Integer.parseInt(respuesta.getString("id_cliente")));
                System.out.println(respuesta.getString("nombre"));
                System.out.println(respuesta.getString("apellido"));
                System.out.println(Integer.parseInt(respuesta.getString("salario")));
                System.out.println(Integer.parseInt(respuesta.getString("cedula")));
                System.out.println(respuesta.getString("rol"));

            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
