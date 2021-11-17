package Controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ConsultaCliente;
import vista.FrmCliente;
import vista.FrmInicial;

public class CtrCliente implements ActionListener {

    private Cliente client;
    private ConsultaCliente consCliente;
    private FrmCliente frmCliente;

    public CtrCliente(Cliente client, ConsultaCliente consCliente, FrmCliente frmCliente) {
        this.client = client;
        this.consCliente = consCliente;
        this.frmCliente = frmCliente;
        this.frmCliente.btnConsultar.addActionListener(this);
        this.frmCliente.btnAgregar.addActionListener(this);
        this.frmCliente.btnCancelar.addActionListener(this);
        this.frmCliente.btnEliminar.addActionListener(this);
        this.frmCliente.btnModificar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // BOTON AGREGAR
        if (e.getSource() == frmCliente.btnAgregar) {

            if (validarFormulario()) {
                client.setCedula_cliente(Integer.parseInt(frmCliente.txtCedula.getText()));
                client.setNombre(frmCliente.txtNombre.getText());
                client.setApellido(frmCliente.txtApellido.getText());
                client.setCorreo(frmCliente.txtCorreo.getText());
                client.setTelefono(frmCliente.txtTelefono.getText());
                client.setDireccion(frmCliente.txtDireccion.getText());

                if (consCliente.guardar(client)) {
                    limpiarTxt();
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al  guardar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios por llenar !");
            }

        }

        // BOTON CONSULTAR
        if (e.getSource() == frmCliente.btnConsultar) {
            client.setCedula_cliente(Integer.parseInt(frmCliente.txtCedula.getText()));
            if (consCliente.traerUnCliente(client)) {
                limpiarTxt();
                JOptionPane.showMessageDialog(null, "Se trajeron Registros con exito");
            } else {
                JOptionPane.showMessageDialog(null, "Error al  traer registros");
            }
        }

        // BOTON CANCELAR
        if (e.getSource() == frmCliente.btnCancelar) {
            ocultar();
        }

        // BOTON ELIMINAR
        if (e.getSource() == frmCliente.btnEliminar) {

            String txtCedula = frmCliente.txtCedula.getText().trim();
            System.out.println(txtCedula.length());

            if (txtCedula.length() > 0) {
                try {
                    client.setCedula_cliente(Integer.parseInt(txtCedula));
                    if (consCliente.eliminar(client)) {
                        limpiarTxt();
                        JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    } else {
                        limpiarTxt();
                        JOptionPane.showMessageDialog(null, "Error al  eliminar, comunicarse con el administrador !");
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "El campo cedula debe ser un numero !");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar la cedula del cliente que quiere eliminar !");
            }
        }
    }

    public void limpiarTxt() {
        frmCliente.txtCedula.setText(null);
        frmCliente.txtNombre.setText(null);
        frmCliente.txtApellido.setText(null);
        frmCliente.txtCorreo.setText(null);
        frmCliente.txtDireccion.setText(null);
        frmCliente.txtTelefono.setText(null);
    }

    public boolean validarFormulario() {
        if (frmCliente.txtCedula.getText().trim().length() == 0) {
            return false;
        }
        if (frmCliente.txtNombre.getText().trim().length() == 0) {
            return false;
        }
        if (frmCliente.txtApellido.getText().trim().length() == 0) {
            return false;
        }
        if (frmCliente.txtCorreo.getText().trim().length() == 0) {
            return false;
        }
        if (frmCliente.txtDireccion.getText().trim().length() == 0) {
            return false;
        }
        if (frmCliente.txtTelefono.getText().trim().length() == 0) {
            return false;
        }
        return true;
    }

    public void inicar() {
        frmCliente.setTitle("Clientes");
        frmCliente.setLocationRelativeTo(null);
        frmCliente.setVisible(true);
    }

    public void ocultar() {
        frmCliente.setVisible(false);
    }
}
