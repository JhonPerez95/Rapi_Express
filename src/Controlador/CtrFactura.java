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
            JOptionPane.showMessageDialog(null, "Se oprimio el boton Consultar");
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

        try {
            Integer.parseInt(txtCampo);
            return true;
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser un numerico !");
        }
        return false;
    }

}
