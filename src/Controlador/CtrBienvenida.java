package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.FrmBienvenida;

public class CtrBienvenida implements ActionListener{

    private FrmBienvenida frmBienvenida;

    public CtrBienvenida(FrmBienvenida frmBienvenida) {
        this.frmBienvenida = frmBienvenida;
        this.frmBienvenida.btnCancelar.addActionListener(this);

    }

    
    @Override
    public void actionPerformed(ActionEvent e) {


        // BOTON CANCELAR
        if (e.getSource() == frmBienvenida.btnCancelar) {
            ocultar();
        }

    }
    
    // 
    public void inicar() {
        frmBienvenida.setTitle("Bienvenida");
        frmBienvenida.setLocationRelativeTo(null);
        frmBienvenida.setVisible(true);
    }

    public void ocultar() {
        frmBienvenida.setVisible(false);
    }

}
