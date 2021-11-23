package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.ConsultaEmpleado;
import modelo.Empleado;
import vista.FrmEmpleado;

public class CtrEmpleado implements ActionListener {

    private Empleado empleado;
    private ConsultaEmpleado consEmpleado;
    private FrmEmpleado frmEmpleado;

    public CtrEmpleado(Empleado empleado, ConsultaEmpleado consEmpleado, FrmEmpleado frmEmpleado) {
        this.empleado = empleado;
        this.consEmpleado = consEmpleado;
        this.frmEmpleado = frmEmpleado;

        this.frmEmpleado.btnConsultar.addActionListener(this);
        this.frmEmpleado.btnAgregar.addActionListener(this);
        this.frmEmpleado.btnCancelar.addActionListener(this);
        this.frmEmpleado.btnEliminar.addActionListener(this);
        this.frmEmpleado.btnModificar.addActionListener(this);
    }

    //
    @Override
    public void actionPerformed(ActionEvent e) {

        // BOTON AGREGAR
        if (e.getSource() == frmEmpleado.btnAgregar) {
            JOptionPane.showMessageDialog(null, "Se oprimio el boton Agregar");

        }

        // BOTON ACTUALIZAR
        if (e.getSource() == frmEmpleado.btnModificar) {
            JOptionPane.showMessageDialog(null, "Se oprimio el boton actualizar");
        }

        // BOTON CONSULTAR
        if (e.getSource() == frmEmpleado.btnConsultar) {
            llenarTabla();
        }

        // BOTON CANCELAR
        if (e.getSource() == frmEmpleado.btnCancelar) {
            ocultar();
        }

        // BOTON ELIMINAR
        if (e.getSource() == frmEmpleado.btnEliminar) {
            JOptionPane.showMessageDialog(null, "Se oprimio el boton Eliminar");
        }
    }

    // 
    public void inicar() {
        frmEmpleado.setTitle("Empleado");
        frmEmpleado.setLocationRelativeTo(null);
        frmEmpleado.setVisible(true);
    }

    public void ocultar() {
        frmEmpleado.setVisible(false);
    }

    public boolean isNumerico(String txtCampo, String nombreCampo) {
        if (txtCampo.matches("[0-9]*")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser un numerico !");
            return false;
        }
    }

    public boolean validarFormulario() {
        if (frmEmpleado.txtCedula.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Cedula es obligatorio! ");
            return false;
        }
        if (frmEmpleado.txtNombre.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio! ");
            return false;
        }
        if (frmEmpleado.txtApellido.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Apellido es obligatorio! ");
            return false;
        }
        if (frmEmpleado.txtSalario.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Correo es obligatorio! ");
            return false;
        }

        return true;
    }

    public void llenarTabla() {
        String txtCedula = frmEmpleado.txtCedula.getText().trim();

        if (txtCedula.length() > 0) {
            if (isNumerico(txtCedula, "Cedula")) {
                empleado.setCedula(Integer.parseInt(txtCedula));
                if (consEmpleado.traerUnEmpleado(empleado)) {
                    limpiarTxt();
                    System.out.println("Se trajo todos los clientes !");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al  traer al cliente , por favor comunicarse con el administrador !");
                }
            }
        } else {
            if (consEmpleado.traerEmpleados()) {
                limpiarTxt();
                System.out.println("Se trajo todos los clientes !");
            } else {
                JOptionPane.showMessageDialog(null, "Error al  traer al los clientes , por favor comunicarse con el administrador !");
            }
        }
    }

    public void limpiarTxt() {
        frmEmpleado.txtCedula.setText(null);
        frmEmpleado.txtNombre.setText(null);
        frmEmpleado.txtApellido.setText(null);
        frmEmpleado.txtSalario.setText(null);
    }
}
