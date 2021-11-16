package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ConsultaCliente;
import vista.FrmCliente;
import vista.FrmInicial;

public class CtrInicial implements ActionListener {

    private FrmInicial frmInicial;
    
    public CtrInicial(FrmInicial frmInicial) {
        this.frmInicial = frmInicial;
        this.frmInicial.btnCliente.addActionListener(this);
        this.frmInicial.btnFactura.addActionListener(this);
        this.frmInicial.btnEmpleado.addActionListener(this);
    }

    public void mostrarFrame() {
        frmInicial.setTitle("RAPI EXPRESS");
        frmInicial.setLocationRelativeTo(null);
        frmInicial.setVisible(true);
    }

    public void ocultarFrame() {
        frmInicial.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == frmInicial.btnCliente) {
            Cliente client = new Cliente();
            ConsultaCliente consCliente = new ConsultaCliente();
            FrmCliente frmCliente = new FrmCliente();
            CtrCliente ctrCliente = new CtrCliente(client, consCliente, frmCliente);

            ctrCliente.inicar();
        }
        
        if (e.getSource() == frmInicial.btnFactura) {
            JOptionPane.showMessageDialog(null, "aqui va cargar el Frame Factura");

        }
        
        if (e.getSource() == frmInicial.btnEmpleado) {
            JOptionPane.showMessageDialog(null, "aqui va cargar el Frame Empleado");

        }
    }
}
