package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import modelo.ConsultaCliente;
import modelo.ConsultaEmpleado;
import modelo.ConsultaFactura;

import modelo.Empleado;
import modelo.Factura;
import modelo.Cliente;

import vista.FrmCliente;
import vista.FrmEmpleado;
import vista.FrmFactura;
import vista.FrmInicial;
import vista.FrmBienvenida;


public class CtrInicial implements ActionListener {

    private FrmInicial frmInicial;

    public CtrInicial(FrmInicial frmInicial) {
        this.frmInicial = frmInicial;
        this.frmInicial.btnCliente.addActionListener(this);
        this.frmInicial.btnFactura.addActionListener(this);
        this.frmInicial.btnEmpleado.addActionListener(this);
        this.frmInicial.btnBienvenida.addActionListener(this);

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
            FrmCliente frmCliente = new FrmCliente();
            ConsultaCliente consCliente = new ConsultaCliente(frmCliente);
            CtrCliente ctrCliente = new CtrCliente(client, consCliente, frmCliente);
            
            ctrCliente.inicar();
            ctrCliente.llenarTabla();
        }

        if (e.getSource() == frmInicial.btnFactura) {
            Factura factura = new Factura();
            FrmFactura frmFactura = new FrmFactura();
            ConsultaFactura consFactura = new ConsultaFactura();
            CtrFactura ctrFactura = new CtrFactura(factura, consFactura, frmFactura);

            ctrFactura.inicar();
        }

        if (e.getSource() == frmInicial.btnEmpleado) {
            Empleado empleado = new Empleado();
            FrmEmpleado frmEmpleado = new FrmEmpleado();
            ConsultaEmpleado consEmpleado = new ConsultaEmpleado();
            CtrEmpleado ctrEmpleado = new CtrEmpleado(empleado,consEmpleado, frmEmpleado);

            ctrEmpleado.inicar();

        }

        if (e.getSource() == frmInicial.btnBienvenida) {
            FrmBienvenida frmEmpleado = new FrmBienvenida();
            CtrBienvenida ctrBienvenida = new CtrBienvenida(frmEmpleado);
            
            ctrBienvenida.inicar();
        }
    }
}
