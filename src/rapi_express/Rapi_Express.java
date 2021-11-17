package rapi_express;


import Controlador.CtrInicial;
import vista.FrmInicial;


public class Rapi_Express {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FrmInicial frmInicial = new FrmInicial();
        CtrInicial ctrInicial = new CtrInicial(frmInicial);

        ctrInicial.mostrarFrame();
    }

}
