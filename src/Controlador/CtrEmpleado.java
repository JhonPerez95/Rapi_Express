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
            JOptionPane.showMessageDialog(null, "Se oprimio el boton Consultar");
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

        try {
            Integer.parseInt(txtCampo);
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser un numerico !");
        }
        return false;
    }


}
