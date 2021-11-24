package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import modelo.ConsultaFactura;
import modelo.Factura;

import vista.FrmFactura;

public class CtrFactura implements ActionListener {

    private Factura factura;
    private ConsultaFactura consFactura;
    private FrmFactura frmFactura;

    public CtrFactura(Factura factura, ConsultaFactura consFactura, FrmFactura frmFactura) {
        this.factura = factura;
        this.consFactura = consFactura;
        this.frmFactura = frmFactura;

        this.frmFactura.btnConsultar.addActionListener(this);
        this.frmFactura.btnAgregar.addActionListener(this);
        this.frmFactura.btnCancelar.addActionListener(this);
        this.frmFactura.btnEliminar.addActionListener(this);
        this.frmFactura.btnModificar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // BOTON AGREGAR
        if (e.getSource() == frmFactura.btnAgregar) {

            String txtCantidad = frmFactura.txtCantidad.getText().trim();
            String txtTotalaPagar = frmFactura.txtTotalaPagar.getText().trim();
            String formaPago = frmFactura.cbxFormaPago.getSelectedItem().toString();

            if (validarFormulario()) {
                if (isNumerico(txtCantidad, "Cantidad") && isNumerico(txtTotalaPagar, "Total Pagar")) {

                    factura.setCantidad(Integer.parseInt(txtCantidad));
                    factura.setDetalle(frmFactura.txtDetalle.getText());
                    factura.setForma_pago(formaPago);
                    factura.setTotal_pagar(Integer.parseInt(frmFactura.txtTotalaPagar.getText()));
                    factura.setCedula_cliente(Integer.parseInt(frmFactura.txtCedulaCliente.getText()));
                    factura.setId_empleado(Integer.parseInt(frmFactura.txtIdEmpleado.getText()));

                    if (consFactura.guardar(factura)) {
                        limpiarTxt();
                        JOptionPane.showMessageDialog(null, "Registro Guardado");
                        llenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al  guardar");
                    }
                }
            }
        }

        // BOTON ACTUALIZAR
        if (e.getSource() == frmFactura.btnModificar) {
            String txtCantidad = frmFactura.txtCantidad.getText().trim();
            String txtTotalaPagar = frmFactura.txtTotalaPagar.getText().trim();
            String formaPago = frmFactura.cbxFormaPago.getSelectedItem().toString();

            if (validarFormulario()) {
                if (isNumerico(txtCantidad, "Cantidad") && isNumerico(txtTotalaPagar, "Total Pagar")) {

                    factura.setId_factura(Integer.parseInt(frmFactura.txtId.getText()));
                    factura.setCantidad(Integer.parseInt(txtCantidad));
                    factura.setDetalle(frmFactura.txtDetalle.getText());
                    factura.setForma_pago(formaPago);
                    factura.setTotal_pagar(Integer.parseInt(frmFactura.txtTotalaPagar.getText()));
                    factura.setCedula_cliente(Integer.parseInt(frmFactura.txtCedulaCliente.getText()));
                    factura.setId_empleado(Integer.parseInt(frmFactura.txtIdEmpleado.getText()));

                    if (consFactura.modificar(factura)) {
                        limpiarTxt();
                        JOptionPane.showMessageDialog(null, "Registro modificado exitosamente");
                        llenarTabla();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al  modificar");
                    }
                }
            }
        }

        // BOTON CONSULTAR
        if (e.getSource() == frmFactura.btnConsultar) {
            llenarTabla();
        }

        // BOTON CANCELAR
        if (e.getSource() == frmFactura.btnCancelar) {
            ocultar();
        }

        // BOTON ELIMINAR
        if (e.getSource() == frmFactura.btnEliminar) {

            String txtId = frmFactura.txtId.getText().trim();
            factura.setId_factura(Integer.parseInt(txtId));
            if (consFactura.eliminar(factura)) {
                limpiarTxt();
                JOptionPane.showMessageDialog(null, "Registro fue eliminado exitosamente !");
                llenarTabla();
            } else {
                limpiarTxt();
                JOptionPane.showMessageDialog(null, "Error al  eliminar, comunicarse con el administrador !");
            }
        }
    }

    // 
    public void inicar() {
        frmFactura.setTitle("Facturas");
        frmFactura.setLocationRelativeTo(null);
        frmFactura.setVisible(true);
    }

    public void ocultar() {
        frmFactura.setVisible(false);
    }

    public boolean isNumerico(String txtCampo, String nombreCampo) {
        if (txtCampo.matches("[0-9]*")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser un numerico !");
            return false;
        }
    }

    public void limpiarTxt() {
        frmFactura.txtCantidad.setText(null);
        frmFactura.txtCedulaCliente.setText(null);
        frmFactura.txtDetalle.setText(null);
        frmFactura.txtFecha.setText(null);
        frmFactura.txtId.setText(null);
        frmFactura.txtIdEmpleado.setText(null);
        frmFactura.txtId.setText(null);
        frmFactura.txtTotalaPagar.setText(null);
    }

    public void llenarTabla() {
        String txtId = frmFactura.txtId.getText().trim();

        if (txtId.length() > 0) {
            factura.setId_factura(Integer.parseInt(txtId));
            if (consFactura.traerUnaFactura(factura)) {
                limpiarTxt();
                System.out.println("Se trajo la facturas !");
            } else {
                JOptionPane.showMessageDialog(null, "Error al  traer la factura , por favor comunicarse con el administrador !");
            }

        } else {
            if (consFactura.traerFacturas()) {
                limpiarTxt();
                System.out.println("Se trajo todas las facturas !");
            } else {
                JOptionPane.showMessageDialog(null, "Error al  traer al las facturas , por favor comunicarse con el administrador !");
            }
        }
    }

    public boolean validarFormulario() {

        if (frmFactura.txtCantidad.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Cantidad es obligatorio! ");
            return false;
        }
        if (frmFactura.txtDetalle.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Detalle es obligatorio! ");
            return false;
        }
        if (frmFactura.txtTotalaPagar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo total a pagar es obligatorio! ");
            return false;
        }
          if (frmFactura.txtCedulaCliente.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo cedula del cliente  es obligatorio! ");
            return false;
        }
            if (frmFactura.txtIdEmpleado.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Id del empleado  es obligatorio! ");
            return false;
        }

        return true;
    }
}
