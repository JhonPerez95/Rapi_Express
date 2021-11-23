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
            JOptionPane.showMessageDialog(null, "Se oprimio el boton Agregar");

        }

        // BOTON ACTUALIZAR
        if (e.getSource() == frmFactura.btnModificar) {
            JOptionPane.showMessageDialog(null, "Se oprimio el boton actualizar");
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
            JOptionPane.showMessageDialog(null, "Se oprimio el boton Eliminar");
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
        frmFactura.txtFormadePago.setText(null);
        frmFactura.txtIdEmpleado.setText(null);
        frmFactura.txtId.setText(null);
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
        if (frmFactura.txtFecha.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Fecha es obligatorio! ");
            return false;
        }
        if (frmFactura.txtCantidad.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Cantidad es obligatorio! ");
            return false;
        }
        if (frmFactura.txtDetalle.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Detalle es obligatorio! ");
            return false;
        }
        if (frmFactura.txtFormadePago.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo Forma de pago es obligatorio! ");
            return false;
        }
        
        if (frmFactura.txtTotalaPagar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "El campo total a pagar es obligatorio! ");
            return false;
        }

        return true;
    }
}
