package vista;


import controlador.Controlador;
import modelo.clientes.Cliente;
import modelo.clientes.Direccion;
import modelo.empresaTelefonica.EmpresaTelefonia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by al364498 on 24/04/18.
 */

public class InterfazGrafica extends JFrame{

    public Controlador controlador;
    public EmpresaTelefonia telefonia;

    private JTextField jtNif;
    private JTextField jtNombre;
    private JTextField jtApellido1;
    private JTextField jtApellido2;
    private JTextField jtCodigoPostal;
    private JTextField jtProvincia;
    private JTextField jtPoblacion;
    private JTextField jtEmail;
    private JLabel jbMostrarInformacionClientes;
    private ButtonGroup bgTipoCliente;
    private JRadioButtonMenuItem jrbParticular;
    private JRadioButtonMenuItem jrbEmpresa;

    private JLabel jbMostrarInformacionLlamadas;
    private JTextField jtNifClienteLlamada;
    private JTextField jtNumeroDestino;
    private JTextField jtDuracion;

    private JLabel jbMostrarInformacionFacturas;
    private JTextField jtNifClienteFactura;
    private JTextField jtCodigoFactura;



    public InterfazGrafica(){
        super("Programa gestion empresa telefonia");
    }
    public void ejecutar(){
        this.addWindowListener(new EscuchadorVentana());
        this.addMenuPrincipal();
        this.setSize(575, 575);
        setVisible(true);
    }

    public void setConntrolador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setTelefonia(EmpresaTelefonia telefonia) {
        this.telefonia = telefonia;
    }

    protected JPanel createInnerPanel() {
        JPanel jplPanel = new JPanel();
        jplPanel.setLayout(new GridLayout(1, 1));
        return jplPanel;
    }

    private void addMenuPrincipal(){
        Container contenedor = this.getContentPane();
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel jplInnerPanelClientes = createInnerPanel();
        tabbedPane.addTab("Clientes", null, jplInnerPanelClientes, "Tab 1");
        addPanelClientes(jplInnerPanelClientes);
        tabbedPane.setSelectedIndex(0);

        JPanel jplInnerPanelLlamadas = createInnerPanel();
        tabbedPane.addTab("Llamadas", null, jplInnerPanelLlamadas, "Tab 2");
        addPanelLamadas(jplInnerPanelLlamadas);
        tabbedPane.setSelectedIndex(1);

        JPanel jplInnerPanelFacturas = createInnerPanel();
        tabbedPane.addTab("Facturas", null, jplInnerPanelFacturas, "Tab 3");
        addPanelFacturas(jplInnerPanelFacturas);
        tabbedPane.setSelectedIndex(0);

        contenedor.add(tabbedPane);

    }

    private void addPanelClientes(JPanel panel) {
        jbMostrarInformacionClientes = new JLabel();
        panel.add(jbMostrarInformacionClientes);



        jtNif = new JTextField(20);
        JLabel jlNIF = new JLabel("NIF: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlNIF);
        panel.add(jtNif);

        JLabel jlTipoCliente = new JLabel("Tipo de cliente: ");
        bgTipoCliente = new ButtonGroup();
        panel.add(jlTipoCliente);
        jrbParticular = new JRadioButtonMenuItem("Particular");
        bgTipoCliente.add(jrbParticular);
        jrbParticular.setActionCommand("particular");
        panel.add(jrbParticular);
        jrbEmpresa= new JRadioButtonMenuItem("Empresa");
        bgTipoCliente.add(jrbEmpresa);
        jrbEmpresa.setActionCommand("empresa");
        panel.add(jrbEmpresa);


        jtNombre = new JTextField(20);
        JLabel jlNombre = new JLabel("Nombre: ");
        panel.add(jlNombre);
        panel.add(jtNombre);

        jtApellido1 = new JTextField(20);
        JLabel jlApellido1 = new JLabel("Primer apellido: ");
        panel.add(jlApellido1);
        panel.add(jtApellido1);

        jtApellido2 = new JTextField(20);
        JLabel jlApellido2 = new JLabel("Segundo apellido: ");
        panel.add(jlApellido2);
        panel.add(jtApellido2);

        jtCodigoPostal = new JTextField(20);
        JLabel jlCodigoPostal = new JLabel("Codigo Postal: ");
        panel.add(jlCodigoPostal);
        panel.add(jtCodigoPostal);

        jtProvincia = new JTextField(20);
        JLabel jlProvincia = new JLabel("Provincia: ");
        panel.add(jlProvincia);
        panel.add(jtProvincia);

        jtPoblacion = new JTextField(20);
        JLabel jlPoblacion = new JLabel("Poblacion: ");
        panel.add(jlPoblacion);
        panel.add(jtPoblacion);

        jtEmail = new JTextField(20);
        JLabel jlEmail = new JLabel("Email: ");
        panel.add(jlEmail);
        panel.add(jtEmail);

        EscuchadorBotonesClientes escuchadorBotonesClientes = new EscuchadorBotonesClientes(controlador);

        JPanel jpBotonesCliente = new JPanel();
        JButton jbAddClientes = new JButton("Añadir cliente");
        jbAddClientes.setActionCommand("añadir-cliente");
        jbAddClientes.addActionListener(escuchadorBotonesClientes);
        jpBotonesCliente.add(jbAddClientes);

        JButton jbDeleteCliente = new JButton("Borrar cliente");
        jbDeleteCliente.setActionCommand("borrar-cliente");
        jbDeleteCliente.addActionListener(escuchadorBotonesClientes);
        jpBotonesCliente.add(jbDeleteCliente);

        JButton jbBuscarCliente = new JButton("Buscar cliente");
        jbBuscarCliente.setActionCommand("buscar-cliente");
        jbBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });
        jpBotonesCliente.add(jbBuscarCliente);

        JButton jbMostrarClientes = new JButton("Mostrar clientes");
        jbMostrarClientes.setActionCommand("mostrar-clientes");
        jbMostrarClientes.addActionListener(escuchadorBotonesClientes);
        jpBotonesCliente.add(jbMostrarClientes);

        panel.add(jpBotonesCliente);
    }

    private void addPanelLamadas(JPanel panel) {
        jbMostrarInformacionLlamadas = new JLabel();
        panel.add(jbMostrarInformacionLlamadas);

        jtNifClienteLlamada = new JTextField(20);
        JLabel jlNIFCliente = new JLabel("NIF del cliente: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlNIFCliente);
        panel.add(jtNifClienteLlamada);

        jtNumeroDestino = new JTextField(20);
        JLabel jlNumeroDestino = new JLabel("Numero Destino: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlNumeroDestino);
        panel.add(jtNumeroDestino);

        jtDuracion = new JTextField(20);
        JLabel jlDuracion = new JLabel("Duración de la llamada: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlDuracion);
        panel.add(jtDuracion);

        EscuchadorBotonesLlamadas escuchadorBotonesLlamadas = new EscuchadorBotonesLlamadas(controlador);

        JPanel jpBotonesLlamadas = new JPanel();
        JButton jbHacerLlamadas = new JButton("Hacer llamada");
        jbHacerLlamadas.setActionCommand("hacer-llamada");
        jbHacerLlamadas.addActionListener(escuchadorBotonesLlamadas);
        jpBotonesLlamadas.add(jbHacerLlamadas);

        JButton jbListarLlamada = new JButton("Listar llamadas");
        jbListarLlamada.setActionCommand("listar-llamadas");
        jbListarLlamada.addActionListener(escuchadorBotonesLlamadas);
        jpBotonesLlamadas.add(jbListarLlamada);

        panel.add(jpBotonesLlamadas);
    }

    private void addPanelFacturas(JPanel panel){
        jbMostrarInformacionFacturas = new JLabel();
        panel.add(jbMostrarInformacionFacturas);

        jtNifClienteFactura = new JTextField(20);
        JLabel jlNIFCliente = new JLabel("NIF del cliente: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlNIFCliente);
        panel.add(jtNifClienteFactura);

        jtCodigoFactura = new JTextField(20);
        JLabel jlcodigoFactura = new JLabel("Codigo de la factura: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlcodigoFactura);
        panel.add(jtCodigoFactura);

        EscuchadorBotonesFacturas escuchadorBotonesFacturas = new EscuchadorBotonesFacturas(controlador);

        JPanel jpBotonesFacturas = new JPanel();
        JButton jbDatosFactura = new JButton("Datos factura");
        jbDatosFactura.setActionCommand("datos-factura");
        jbDatosFactura.addActionListener(escuchadorBotonesFacturas);
        jpBotonesFacturas.add(jbDatosFactura);

        JButton jbEmitirFactura = new JButton("Emitir factura");
        jbEmitirFactura.setActionCommand("emitir-factura");
        jbEmitirFactura.addActionListener(escuchadorBotonesFacturas);
        jpBotonesFacturas.add(jbEmitirFactura);

        JButton jbListarFacturas = new JButton("Listar facturas de un cliente");
        jbListarFacturas.setActionCommand("listar-facturas");
        jbListarFacturas.addActionListener(escuchadorBotonesFacturas);
        jpBotonesFacturas.add(jbListarFacturas);

        panel.add(jpBotonesFacturas);
    }


    public void mostrarInformacionClientesRojo(String informacion){
        jbMostrarInformacionClientes.setText(informacion);
        jbMostrarInformacionClientes.setForeground(Color.RED);
        jbMostrarInformacionClientes.setFont(new java.awt.Font("Tahoma", 0, 15));
    }
    public void mostrarInformacionClientesVerde(String informacion){
        jbMostrarInformacionClientes.setText(informacion);
        jbMostrarInformacionClientes.setForeground(Color.GREEN);
        jbMostrarInformacionClientes.setFont(new java.awt.Font("Tahoma", 0, 15));
    }
    public void mostrarInformacionLlamadasRojo(String informacion){
        jbMostrarInformacionLlamadas.setText(informacion);
        jbMostrarInformacionLlamadas.setForeground(Color.RED);
        jbMostrarInformacionLlamadas.setFont(new java.awt.Font("Tahoma", 0, 15));
    }
    public void mostrarInformacionLlamadasVerde(String informacion){
        jbMostrarInformacionLlamadas.setText(informacion);
        jbMostrarInformacionLlamadas.setForeground(Color.GREEN);
        jbMostrarInformacionLlamadas.setFont(new java.awt.Font("Tahoma", 0, 15));
    }
    public void mostrarInformacionFacturasRojo(String informacion){
        jbMostrarInformacionFacturas.setText(informacion);
        jbMostrarInformacionFacturas.setForeground(Color.RED);
        jbMostrarInformacionFacturas.setFont(new java.awt.Font("Tahoma", 0, 15));
    }
    public void mostrarInformacionFacturasVerde(String informacion){
        jbMostrarInformacionFacturas.setText(informacion);
        jbMostrarInformacionFacturas.setForeground(Color.GREEN);
        jbMostrarInformacionFacturas.setFont(new java.awt.Font("Tahoma", 0, 15));
    }

    public String tipoClienteSeleccionado(){
        try {
            String tipo = bgTipoCliente.getSelection().getActionCommand();
            return tipo;
        } catch (NullPointerException e){
            mostrarInformacionClientesRojo("Marca que tipo de cliente ");
        }

        return "nada-seleccionado";
    }

    public String getNifClientes(){
        if(!jtNif.getText().isEmpty()) {
            return jtNif.getText();
        } else {
            return null;
        }
    }

    public String getNifFacturas(){
        if(!jtNifClienteFactura.getText().isEmpty()) {
            return jtNifClienteFactura.getText();
        } else {
            return null;
        }
    }

    public ArrayList getInformacionAddCliente(){
        ArrayList informacionCliente = new ArrayList<String>();
            /*---- Check tipo de cliente que se quiere crear ----*/
        if (tipoClienteSeleccionado().equals("empresa")) {
            if (jtNif.getText().isEmpty() || jtNombre.getText().isEmpty() || jtCodigoPostal.getText().isEmpty() || jtProvincia.getText().isEmpty() ||jtPoblacion.getText().isEmpty() || jtEmail.getText().isEmpty()) {
                mostrarInformacionClientesRojo("Revisa el formulario te falta por introducir un dato");
                return null;
            } else {
                informacionCliente.add(jtNif.getText());
                informacionCliente.add(jtNombre.getText());
                informacionCliente.add(jtCodigoPostal.getText());
                informacionCliente.add(jtProvincia.getText());
                informacionCliente.add(jtPoblacion.getText());
                informacionCliente.add(jtEmail.getText());
            }
        } else {
            if (jtNif.getText().isEmpty() || jtNombre.getText().isEmpty() || jtApellido1.getText().isEmpty() || jtApellido2.getText().isEmpty() || jtCodigoPostal.getText().isEmpty() || jtProvincia.getText().isEmpty() ||jtPoblacion.getText().isEmpty() || jtEmail.getText().isEmpty()){
                mostrarInformacionClientesRojo("Revisa el formulario te falta por introducir un dato");
                return null;
            } else {
                informacionCliente.add(jtNif.getText());
                informacionCliente.add(jtNombre.getText());
                informacionCliente.add(jtApellido1.getText());
                informacionCliente.add(jtApellido2.getText());
                informacionCliente.add(jtCodigoPostal.getText());
                informacionCliente.add(jtProvincia.getText());
                informacionCliente.add(jtPoblacion.getText());
                informacionCliente.add(jtEmail.getText());
            }
        }
        return informacionCliente;
    }

    public void buscarCliente(){
        String nifCliente = jtNif.getText();
        if(!jtNif.getText().isEmpty()){
            if (telefonia.containsCliente(nifCliente)) {
                Cliente cliente = telefonia.datosCliente(nifCliente);
                jtNombre.setText(cliente.getNombre());
                Direccion direccion = cliente.getDireccion();
                jtPoblacion.setText(direccion.getPoblacion());
                jtProvincia.setText(direccion.getProvincia());
                jtCodigoPostal.setText(direccion.getCodigoPostal().toString());
                jtEmail.setText(cliente.getEmail());
                mostrarInformacionClientesVerde("Información ya disponible");
            } else {
                mostrarInformacionClientesRojo("El cliente no se encuentra en el sistema");
            }

        } else {
            mostrarInformacionClientesRojo("Tienes que rellenar el campo NIF");
        }

    }

    public ArrayList getInformacionAddLlamada(){
        ArrayList informacionLlamada = new ArrayList<String>();
        if(jtNifClienteLlamada.getText().isEmpty() || jtNumeroDestino.getText().isEmpty() || jtDuracion.getText().isEmpty()){
            mostrarInformacionClientesRojo("");
        } else {
            if(jtNifClienteLlamada.getText().isEmpty() || jtNumeroDestino.getText().isEmpty() || jtDuracion.getText().isEmpty()){
                mostrarInformacionLlamadasRojo("Rellena todos los campos");
            } else {
                informacionLlamada.add(jtNifClienteLlamada.getText());
                informacionLlamada.add(jtNumeroDestino.getText());
                informacionLlamada.add(jtDuracion.getText());
                return informacionLlamada;
            }
        }
        return null;
    }
}
