package Controlador;

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

    public void inicar() {
        frmCliente.setTitle("Clientes");
        frmCliente.setLocationRelativeTo(null);
        frmCliente.setVisible(true);
    }

    public void ocultar() {
        frmCliente.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmCliente.btnAgregar) {
            // Agregamos valores de los textos al modelo

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
//                limpiarTxt();
                JOptionPane.showMessageDialog(null, "Error al  guardar");
            }
        }
        if (e.getSource() == frmCliente.btnConsultar) {
            // Agregamos valores de los textos al modelo

            client.setCedula_cliente(Integer.parseInt(frmCliente.txtCedula.getText()));

            if (consCliente.traerUnCliente(client)) {
                limpiarTxt();
                JOptionPane.showMessageDialog(null, "Se trajeron Registros con exito");
            } else {
                limpiarTxt();

                JOptionPane.showMessageDialog(null, "Error al  traer registros");
            }
        }

        if (e.getSource() == frmCliente.btnCancelar) {

            FrmInicial frmInicial = new FrmInicial();
            CtrInicial ctrInicial = new CtrInicial(frmInicial);
            ocultar();
            ctrInicial.mostrarFrame();
        }
        if (e.getSource() == frmCliente.btnEliminar) {
            client.setCedula_cliente(Integer.parseInt(frmCliente.txtCedula.getText()));

            if (consCliente.eliminar(client)) {
                limpiarTxt();
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            } else {
                limpiarTxt();
                JOptionPane.showMessageDialog(null, "Error al  eliminar");
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
}
