package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ConsultaCliente extends Conexion {

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

        String sql = "DELETE   FROM cliente WHERE cedula_cliente=?";
        System.out.println("modelo.ConsultaCliente.eliminar()");
        System.out.println(client);

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, client.getCedula_cliente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());

            return false;
        }
         
        

    }

    public boolean traerUnCliente(Cliente client) {
       
        PreparedStatement ps = null;
        Connection con = getConnection();
        ResultSet respuesta = null;
        String sql = "SELECT * FROM cliente WHERE cedula_cliente=?";
        
        System.out.println("modelo.ConsultaCliente.traerUnCliente()");
        System.out.println(client.getCedula_cliente());
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

                System.out.println(Integer.parseInt(respuesta.getString("cedula_cliente")));
                System.out.println(respuesta.getString("nombre"));
                System.out.println(respuesta.getString("apellido"));
                System.out.println(respuesta.getString("correo"));
                System.out.println(respuesta.getString("telefono"));
                System.out.println(respuesta.getString("direccion"));

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
        
        try {
            ResultSet respuesta = consultarRegistros(sql);
            while (respuesta.next()) {
//                client.setCedula_cliente(Integer.parseInt(respuesta.getString("cedula_cliente")));
//                client.setNombre(respuesta.getString("nombre"));
//                client.setApellido(respuesta.getString("apellido"));
//                client.setCorreo(respuesta.getString("correo"));
//                client.setTelefono(respuesta.getString("telefono"));
//                client.setDireccion(respuesta.getString("direccion"));

                System.out.println(Integer.parseInt(respuesta.getString("cedula_cliente")));
                System.out.println(respuesta.getString("nombre"));
                System.out.println(respuesta.getString("apellido"));
                System.out.println(respuesta.getString("correo"));
                System.out.println(respuesta.getString("telefono"));
                System.out.println(respuesta.getString("direccion"));

            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
