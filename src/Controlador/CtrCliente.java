package Controlador;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import javax.swing.JOptionPane;import modelo.Cliente;import modelo.ConsultaCliente;import vista.FrmCliente;public class CtrCliente implements ActionListener {    private Cliente client;    private ConsultaCliente consCliente;    private FrmCliente frmCliente;    public CtrCliente(Cliente client, ConsultaCliente consCliente, FrmCliente frmCliente) {        this.client = client;        this.consCliente = consCliente;        this.frmCliente = frmCliente;        this.frmCliente.btnConsultar.addActionListener(this);        this.frmCliente.btnAgregar.addActionListener(this);        this.frmCliente.btnCancelar.addActionListener(this);        this.frmCliente.btnEliminar.addActionListener(this);        this.frmCliente.btnModificar.addActionListener(this);    }    @Override    public void actionPerformed(ActionEvent e) {        // BOTON AGREGAR        if (e.getSource() == frmCliente.btnAgregar) {            String txtCedula = frmCliente.txtCedula.getText().trim();            String txtTelefono = frmCliente.txtTelefono.getText().trim();            if (validarFormulario()) {                if (isNumerico(txtCedula, "Cedula") && isNumerico(txtTelefono, "Telefono")) {                    client.setCedula_cliente(Integer.parseInt(txtCedula));                    client.setNombre(frmCliente.txtNombre.getText());                    client.setApellido(frmCliente.txtApellido.getText());                    client.setCorreo(frmCliente.txtCorreo.getText());                    client.setTelefono(txtTelefono);                    client.setDireccion(frmCliente.txtDireccion.getText());                    if (consCliente.guardar(client)) {                        limpiarTxt();                        llenarTabla();                        JOptionPane.showMessageDialog(null, "Registro Guardado");                    } else {                        JOptionPane.showMessageDialog(null, "Error al  guardar");                    }                }            }        }        // BOTON ACTUALIZAR        if (e.getSource() == frmCliente.btnModificar) {            String txtCedula = frmCliente.txtCedula.getText().trim();            String txtTelefono = frmCliente.txtTelefono.getText().trim();            if (validarFormulario()) {                if (isNumerico(txtCedula, "Cedula") && isNumerico(txtTelefono, "Telefono")) {                    client.setCedula_cliente(Integer.parseInt(txtCedula));                    client.setNombre(frmCliente.txtNombre.getText());                    client.setApellido(frmCliente.txtApellido.getText());                    client.setCorreo(frmCliente.txtCorreo.getText());                    client.setTelefono(txtTelefono);                    client.setDireccion(frmCliente.txtDireccion.getText());                    if (consCliente.modificar(client)) {                        limpiarTxt();                        llenarTabla();                        JOptionPane.showMessageDialog(null, "El cliente fue actualizado");                    } else {                        JOptionPane.showMessageDialog(null, "Error al  guardar");                    }                }            }        }        // BOTON CONSULTAR        if (e.getSource() == frmCliente.btnConsultar) {            llenarTabla();        }        // BOTON CANCELAR        if (e.getSource() == frmCliente.btnCancelar) {            ocultar();        }        // BOTON ELIMINAR        if (e.getSource() == frmCliente.btnEliminar) {            String txtCedula = frmCliente.txtCedula.getText().trim();            if (txtCedula.length() > 0) {                if (isNumerico(txtCedula, "Cedula")) {                    client.setCedula_cliente(Integer.parseInt(txtCedula));                    if (consCliente.eliminar(client)) {                        limpiarTxt();                        JOptionPane.showMessageDialog(null, "Registro fue eliminado exitosamente !");                        llenarTabla();                    } else {                        limpiarTxt();                        JOptionPane.showMessageDialog(null, "Error al  eliminar, comunicarse con el administrador !");                    }                }            } else {                JOptionPane.showMessageDialog(null, "Debe ingresar la cedula del cliente que quiere eliminar !");            }        }    }    public void limpiarTxt() {        frmCliente.txtCedula.setText(null);        frmCliente.txtNombre.setText(null);        frmCliente.txtApellido.setText(null);        frmCliente.txtCorreo.setText(null);        frmCliente.txtDireccion.setText(null);        frmCliente.txtTelefono.setText(null);    }    public boolean validarFormulario() {        if (frmCliente.txtCedula.getText().trim().length() == 0) {            JOptionPane.showMessageDialog(null, "El campo Cedula es obligatorio! ");            return false;        }        if (frmCliente.txtNombre.getText().trim().length() == 0) {            JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio! ");            return false;        }        if (frmCliente.txtApellido.getText().trim().length() == 0) {            JOptionPane.showMessageDialog(null, "El campo Apellido es obligatorio! ");            return false;        }        if (frmCliente.txtCorreo.getText().trim().length() == 0) {            JOptionPane.showMessageDialog(null, "El campo Correo es obligatorio! ");            return false;        }        if (frmCliente.txtDireccion.getText().trim().length() == 0) {            JOptionPane.showMessageDialog(null, "El campo Direccion es obligatorio! ");            return false;        }        if (frmCliente.txtTelefono.getText().trim().length() == 0) {            JOptionPane.showMessageDialog(null, "El campo Telefono es obligatorio! ");            return false;        }        return true;    }    public void inicar() {        frmCliente.setTitle("Clientes");        frmCliente.setLocationRelativeTo(null);        frmCliente.setVisible(true);    }    public void ocultar() {        frmCliente.setVisible(false);    }    public boolean isNumerico(String txtCampo, String nombreCampo) {        try {            Integer.parseInt(txtCampo);            return true;        } catch (NumberFormatException nfe) {            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser un numerico !");        }        return false;    }    public void llenarTabla() {        String txtCedula = frmCliente.txtCedula.getText().trim();        if (txtCedula.length() > 0) {            if (isNumerico(txtCedula, "Cedula")) {                client.setCedula_cliente(Integer.parseInt(txtCedula));                if (consCliente.traerUnCliente(client)) {                    limpiarTxt();                    System.out.println("Se trajo todos los clientes !");                } else {                    JOptionPane.showMessageDialog(null, "Error al  traer al cliente , por favor comunicarse con el administrador !");                }            }        } else {            if (consCliente.traerClientes()) {                limpiarTxt();                 System.out.println("Se trajo todos los clientes !");            } else {                JOptionPane.showMessageDialog(null, "Error al  traer al los clientes , por favor comunicarse con el administrador !");            }        }    }}