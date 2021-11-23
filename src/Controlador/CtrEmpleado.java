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
            String txtCedula = frmEmpleado.txtCedula.getText().trim();
            String txtSalario = frmEmpleado.txtSalario.getText().trim();
            String rol = frmEmpleado.cbxRol.getSelectedItem().toString();
            if (validarFormulario()) {
                if (isNumerico(txtCedula, "Cedula") && isNumerico(txtSalario, "Salario")) {
                    empleado.setCedula(Integer.parseInt(txtCedula));
                    empleado.setNombre(frmEmpleado.txtNombre.getText());
                    empleado.setApellido(frmEmpleado.txtApellido.getText());
                    empleado.setSalario(Integer.parseInt(txtSalario));
                    empleado.setRol(rol);

                    if (consEmpleado.guardar(empleado)) {
                        limpiarTxt();
                        llenarTabla();
                        JOptionPane.showMessageDialog(null, "Registro Guardado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al  guardar");
                    }
                }
            }
        }

        // BOTON ACTUALIZAR
        if (e.getSource() == frmEmpleado.btnModificar) {
            String txtCedula = frmEmpleado.txtCedula.getText().trim();
            String txtSalario = frmEmpleado.txtSalario.getText().trim();
            String rol = frmEmpleado.cbxRol.getSelectedItem().toString();

            if (validarFormulario()) {
                if (isNumerico(txtCedula, "Cedula") && isNumerico(txtSalario, "Salario")) {
                    empleado.setId_empleado(Integer.parseInt(frmEmpleado.txtId.getText()));
                    empleado.setCedula(Integer.parseInt(txtCedula));
                    empleado.setNombre(frmEmpleado.txtNombre.getText());
                    empleado.setApellido(frmEmpleado.txtApellido.getText());
                    empleado.setSalario(Integer.parseInt(txtSalario));
                    empleado.setRol(rol);

                    if (consEmpleado.modificar(empleado)) {
                        limpiarTxt();
                        llenarTabla();
                        JOptionPane.showMessageDialog(null, "El Empleado fue actualizado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al  guardar");
                    }
                }
            }
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
            String txtCedula = frmEmpleado.txtCedula.getText().trim();

            if (txtCedula.length() > 0) {
                if (isNumerico(txtCedula, "Cedula")) {
                    empleado.setId_empleado(Integer.parseInt(frmEmpleado.txtId.getText()));
                    if (consEmpleado.eliminar(empleado)) {
                        limpiarTxt();
                        JOptionPane.showMessageDialog(null, "Registro fue eliminado exitosamente !");
                        llenarTabla();
                    } else {
                        limpiarTxt();
                        JOptionPane.showMessageDialog(null, "Error al  eliminar, comunicarse con el administrador !");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar la cedula del Empleado que quiere eliminar !");
            }
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
                    System.out.println("Se trajo todos los Empleados !");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al  traer al Empleado , por favor comunicarse con el administrador !");
                }
            }
        } else {
            if (consEmpleado.traerEmpleados()) {
                limpiarTxt();
                System.out.println("Se trajo todos los Empleados !");
            } else {
                JOptionPane.showMessageDialog(null, "Error al  traer al los Empleados , por favor comunicarse con el administrador !");
            }
        }
    }

    public void limpiarTxt() {
        frmEmpleado.txtCedula.setText(null);
        frmEmpleado.txtNombre.setText(null);
        frmEmpleado.txtApellido.setText(null);
        frmEmpleado.txtSalario.setText(null);
        frmEmpleado.txtId.setText(null);
    }
}
