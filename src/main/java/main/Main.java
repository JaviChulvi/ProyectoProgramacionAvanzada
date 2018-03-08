package main;

import clientes.Cliente;
import clientes.Direccion;
import compañiaTelefonica.EmpresaTelefonia;
import factura.Factura;
import llamadas.Llamada;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by al364498 on 20/02/18.
 */

public class Main {
    private static EmpresaTelefonia telefonica = new EmpresaTelefonia();

    public static void main(String[] args) {
        String seguir = "si";
        Scanner scanner = new Scanner(System.in);
        while (seguir!="no") {
            screenMenu();
            System.out.println("¿Quieres seguir?");
            seguir = scanner.nextLine();
            System.out.println(seguir);
        }
    }
    public static void screenMenu(){
        System.out.println(Menu.getMenu());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elije una opción:");
        byte opcion = scanner.nextByte();
        Menu opcionMenu = Menu.getOpcion(opcion);
        switch(opcionMenu) {
            case ALTA_NUEVO_CLIENTE:
                System.out.println("Dar de alta un nuevo cliente.");
                screenDarDeAltaCliente();
                break;
            case BORRAR_CLIENTE:
                System.out.println("Borrar un cliente.");
                screenBorrarCliente();
                break;
            case CAMBIAR_TARIFA:
                System.out.println("Cambiar la tarifa de un cliente.");
                screenCambiarTarifa();
                break;
            case DATOS_CLIENTE:
                System.out.println("Mostrar datos de un cliente a partir de su NIF.");
                screenDatosCliente();
                break;
            case ALTA_LLAMADA:
                System.out.println("Introduce los siguientes datos para dar de alta una llamada.");
                screenHacerLlamada();
                break;
            case LISTADO_CLIENTES:
                System.out.println("A continuación se va a mostrar por pantalla todos los clientes.");
                screenListarCliente();
                break;
            case LISTAR_LLAMADAS_CLIENTE:
                System.out.println("Introduce el NIF de un cliente dado de alta para obtener el listado de las llamadas realizadas por el.");
                screenListarLlamadasCliente();
                break;
            case EMITIR_FACTURAS_CLIENTE:
                System.out.println("Emitir una factura para un cliente, calculando el importe de la misma en función de las llamadas.");
                break;
            case DATOS_FACTURA:
                System.out.println("Opción 3.");
                screenMostrarDatosFactura();
                break;
            case LISTAR_FACTURAS_CLIENTE:
                System.out.println("Recuperar todas las facturas de un cliente.");
                screenListarFacturasCliente();
                break;
        }
    }
    public static void screenInfoCliente(Cliente cliente){
        System.out.println("Datos del cliente.");
        System.out.println("Nombre: "+cliente.nombre);
        System.out.println("Direccion:");
        System.out.println("      Codigo Postal: "+ cliente.direccion.getCodigoPostal());
        System.out.println("      Provincia: "+ cliente.direccion.getProvincia());
        System.out.println("      Población: "+ cliente.direccion.getPoblacion());
        System.out.println("Email: "+cliente.email);
        System.out.println("Fecha de Alta: "+cliente.fechaDeAlta.toString());
    }
    public static void screenDarDeAltaCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Particular o Empresa:");
        String tipo = scanner.nextLine();
        System.out.print("Introduce el nombre:");
        String nombre = scanner.nextLine();
        String apellidos = null;
        if (tipo=="particular" || tipo=="p") {
            System.out.print("Introduce los apellidos:");
            apellidos = scanner.nextLine();
        }
        System.out.print("Introduce el NIF:");
        String NIF = scanner.nextLine();
        System.out.println("A continuación introduce los datos de la Dirreción.");
        System.out.print("  Introduce el codigo postal:");
        Integer CP = scanner.nextInt();
        System.out.print("  Introduce el provincia:");
        String provincia = scanner.nextLine();
        System.out.print("  Introduce el poblacion:");
        String poblacion = scanner.nextLine();
        Direccion dirNuevoCliente = new Direccion(CP, provincia, poblacion);
        System.out.print("Introduce el email:");
        String email = scanner.nextLine();
        if(apellidos!=null){
            telefonica.añadirClienteParticular(nombre, apellidos, NIF, dirNuevoCliente, email );
        } else {
            telefonica.añadirClienteEmpresa(nombre, NIF, dirNuevoCliente, email );
        }
    }
    public static void screenBorrarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el NIF del cliente que quieras eliminar:");
        String NIF = scanner.nextLine();
        if (telefonica.clientes.containsKey(NIF)) {
             Cliente clienteABorrar = telefonica.datosCliente(NIF);
             screenInfoCliente(clienteABorrar);
             telefonica.borrarCliente(NIF);
        } else {
            System.out.print("Este cliente no esta dado de alta.");
        }
    }
    public static void screenCambiarTarifa() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el NIF del cliente que quieras cambiar la Tarifa: ");
        String NIF = scanner.nextLine();
        if (telefonica.clientes.containsKey(NIF)) {
            System.out.print("Introduce la nueva Tarifa para el cliente seleccionado: ");
            Double nuevaTarifa = scanner.nextDouble();
            telefonica.cambiarTarifa(NIF, nuevaTarifa);
        } else {
            System.out.println("No se ha encontrado el cliente.");
        }

    }
    public static void screenDatosCliente (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if (telefonica.clientes.containsKey(NIF)) {
            screenInfoCliente(telefonica.datosCliente(NIF));
        } else {
            System.out.println("No se ha encontrado el cliente.");
        }
    }
    public static void screenListarCliente(){
        ArrayList<Cliente> listaClientes = telefonica.listarClientes();
        for( Cliente cliente : listaClientes){
            screenInfoCliente(cliente);
        }
    }
    public static void screenListarLlamadasCliente(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(telefonica.clientes.containsKey(NIF)){
            System.out.println("Todas las llamadas del cliente con NIF: "+ NIF);
            Integer contador = 1;
            for(Llamada llamada : telefonica.listarLlamadas(NIF)){
                System.out.print(contador + " Número destino: " + llamada.tlfDestino);
                System.out.print(" Fecha: " + llamada.fechaYhora.toString());
                System.out.println(" Duracion: " + llamada.duración);
            }
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
    public static void screenMostrarDatosFactura(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el codigo de la factura: ");
        Integer codigo = scanner.nextInt();
        if(telefonica.facturas.containsKey(codigo)){
            Factura facturaAmostrar = telefonica.datosFactura(codigo);
            System.out.println("Tarifa aplicada: "+ facturaAmostrar.tarifaAplicada);
            System.out.println("Fecha de emisión: "+ facturaAmostrar.fechaEmision.toString());
            System.out.println("Periodo de facturación: de"+ facturaAmostrar.periodoFacturacion[0].toString() +" a "+ facturaAmostrar.periodoFacturacion[1].toString());
        } else {
            System.out.println("No existe la factura con el codigo = "+ codigo);
        }
    }
    public static void screenListarFacturasCliente(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(telefonica.clientes.containsKey(NIF)){
            System.out.println("Todas las facturas del cliente con NIF: "+ NIF);
            Integer contador = 1;
            for(Factura factura : telefonica.listarFacturas(NIF)){
                System.out.println("Tarifa aplicada: "+ factura.tarifaAplicada);
                System.out.println("Fecha de emisión: "+ factura.fechaEmision.toString());
                System.out.println("Periodo de facturación: de"+ factura.periodoFacturacion[0].toString() +" a "+ factura.periodoFacturacion[1].toString());
            }
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
    public static void screenHacerLlamada(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(telefonica.clientes.containsKey(NIF)){
            System.out.print("Introduce el telefono destino de la llamada: ");
            Integer telefonoDestino = scanner.nextInt();
            System.out.print("Introduce la duración de la llamada: ");
            Integer duracion = scanner.nextInt();
            telefonica.hacerLlamada(NIF, telefonoDestino, duracion);
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }

    }
}
