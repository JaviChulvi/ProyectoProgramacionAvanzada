package vista;


import javax.swing.*;
import java.awt.*;

/**
 * Created by al364498 on 24/04/18.
 */

public class InterfazGrafica extends JFrame{
    public InterfazGrafica(){
        super("Programa gestion empresa telefonia");
    }
    public void ejecutar(){
        this.addWindowListener(new EscuchadorVentana());
        this.addMenuPrincipal();
        this.pack();
        setVisible(true);
    }


    protected JPanel createInnerPanel() {
        JPanel jplPanel = new JPanel();
        jplPanel.setLayout(new GridLayout(1, 1));
        return jplPanel;
    }
    public void addMenuPrincipal(){
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
    public void addPanelClientes(JPanel panel) {
        JTextField nif = new JTextField(20);
        JLabel jlNIF = new JLabel("NIF: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlNIF);
        panel.add(nif);

        JTextField nombre = new JTextField(20);
        JLabel jlNombre = new JLabel("Nombre: ");
        panel.add(jlNombre);
        panel.add(nombre);

        JTextField apellido1 = new JTextField(20);
        JLabel jlApellido1 = new JLabel("Primer apellido: ");
        panel.add(jlApellido1);
        panel.add(apellido1);

        JTextField apellido2 = new JTextField(20);
        JLabel jlApellido2 = new JLabel("Segundo apellido: ");
        panel.add(jlApellido2);
        panel.add(apellido2);

        JTextField codigoPostal = new JTextField(20);
        JLabel jlCodigoPostal = new JLabel("Codigo Postal: ");
        panel.add(jlCodigoPostal);
        panel.add(codigoPostal);

        JTextField provincia = new JTextField(20);
        JLabel jlProvincia = new JLabel("Provincia: ");
        panel.add(jlProvincia);
        panel.add(provincia);

        JTextField poblacion = new JTextField(20);
        JLabel jlPoblacion = new JLabel("Poblacion: ");
        panel.add(jlPoblacion);
        panel.add(poblacion);

        JTextField email = new JTextField(20);
        JLabel jlEmail = new JLabel("Email: ");
        panel.add(jlEmail);
        panel.add(email);

        EscuchadorBotonesClientes escuchadorBotonesClientes = new EscuchadorBotonesClientes();

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
        jbBuscarCliente.addActionListener(escuchadorBotonesClientes);
        jpBotonesCliente.add(jbBuscarCliente);

        JButton jbMostrarClientes = new JButton("Mostrar cliente");
        jbMostrarClientes.setActionCommand("mostrar-cliente");
        jbMostrarClientes.addActionListener(escuchadorBotonesClientes);
        jpBotonesCliente.add(jbMostrarClientes);

        panel.add(jpBotonesCliente);
    }

    public void addPanelLamadas(JPanel panel) {
        JTextField nifCliente = new JTextField(20);
        JLabel jlNIFCliente = new JLabel("NIF del cliente: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlNIFCliente);
        panel.add(nifCliente);

        JTextField numeroDestino = new JTextField(20);
        JLabel jlNumeroDestino = new JLabel("Numero Destino: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlNumeroDestino);
        panel.add(numeroDestino);

        JTextField duracion = new JTextField(20);
        JLabel jlDuracion = new JLabel("Duración de la llamada: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlDuracion);
        panel.add(duracion);

        EscuchadorBotonesLlamadas escuchadorBotonesLlamadas = new EscuchadorBotonesLlamadas();

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

    public void addPanelFacturas(JPanel panel){
        JTextField nifCliente = new JTextField(20);
        JLabel jlNIFCliente = new JLabel("NIF del cliente: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlNIFCliente);
        panel.add(nifCliente);

        JTextField codigoFactura = new JTextField(20);
        JLabel jlcodigoFactura = new JLabel("Codigo de la factura: ");
        panel.setLayout(new BoxLayout(panel , 1));
        panel.add(jlcodigoFactura);
        panel.add(codigoFactura);

        EscuchadorBotonesFacturas escuchadorBotonesFacturas = new EscuchadorBotonesFacturas();

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
}
