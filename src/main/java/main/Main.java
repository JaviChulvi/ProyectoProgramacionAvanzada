package main;

import clientes.Cliente;
import clientes.Direccion;
import empresaTelefonica.EmpresaTelefonia;
import factura.Factura;
import llamadas.Llamada;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by al364498 on 20/02/18.
 */

public class Main {
    private static EmpresaTelefonia telefonica = new EmpresaTelefonia();
    private static Scanner scanner;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        screenMenu();
        scanner.close();
    }
    
    public static void screenMenu(){
        int opcion;
        Menu opcionMenu = null;
        
        while(opcionMenu != Menu.SALIR){
        	// OBTENER OPCION
			System.out.println("\n" + Menu.getMenu());
			do{
				System.out.print("Elija una opción: ");
				opcion = Integer.parseInt(scanner.nextLine());
			} while(opcion < 0 || opcion >= Menu.values().length);
			
			opcionMenu = Menu.getOpcion(opcion);
			
			// EJECUTAR OPERACION
	        switch(opcionMenu) {
	            case ALTA_NUEVO_CLIENTE:
	                screenDarDeAltaCliente();
	                break;
	            case BORRAR_CLIENTE:
	                screenBorrarCliente();
	                break;
	            case CAMBIAR_TARIFA:
	                screenCambiarTarifa();
	                break;
	            case DATOS_CLIENTE:
	                screenDatosCliente();
	                break;
	            case ALTA_LLAMADA:
	                screenHacerLlamada();
	                break;
	            case LISTADO_CLIENTES:
	                screenListarCliente();
	                break;
	            case LISTAR_LLAMADAS_CLIENTE:
	                screenListarLlamadasCliente();
	                break;
	            case EMITIR_FACTURAS_CLIENTE:
	                screenEmitirFactura();
	                break;
	            case DATOS_FACTURA:
	                screenMostrarDatosFactura();
	                break;
	            case LISTAR_FACTURAS_CLIENTE:
	                screenListarFacturasCliente();
	                break;
	            case SALIR:
	            	break;
	        }
        }
    }
    public static void screenInfoCliente(Cliente cliente){
        System.out.println(cliente.toString());
    }
    public static void screenDarDeAltaCliente() {
    	String tipo;
    	do{
	        System.out.print("¿Particular o Empresa? (p/e)");
	        tipo = scanner.nextLine();
    	}while(!tipo.equals("p") && !tipo.equals("e"));
        System.out.print("Introduce el nombre:");
        String nombre = scanner.nextLine();
        String apellido1 = null, apellido2 = null;
        if (tipo.equals("p")) {
            System.out.print("Introduce el primer apellido:");
            apellido1 = scanner.nextLine();
            System.out.print("Introduce el segundo apellido:");
            apellido2 = scanner.nextLine();
        }
        System.out.print("Introduce el NIF:");
        String NIF = scanner.nextLine();
        System.out.println("A continuación introduce los datos de la Dirreción.");
        System.out.print("  Introduce el código postal:");
        Integer CP = Integer.parseInt(scanner.nextLine());
        System.out.print("  Introduce la provincia:");
        String provincia = scanner.nextLine();
        System.out.print("  Introduce la población:");
        String poblacion = scanner.nextLine();
        Direccion dirNuevoCliente = new Direccion(CP, provincia, poblacion);
        System.out.print("Introduce el email:");
        String email = scanner.nextLine();
        System.out.print("Introduzca el precio de la tarifa en euros por minuto: ");
        Double precioSec = Double.parseDouble(scanner.nextLine());
        if (tipo.equals("particular") || tipo.equals("p")) {
            telefonica.crearClienteParticular(nombre, apellido1, apellido2, NIF, dirNuevoCliente, email , precioSec);
        } else {
            telefonica.crearClienteEmpresa(nombre, NIF, dirNuevoCliente, email , precioSec);
        }
    }
    public static void screenBorrarCliente() {
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
        System.out.print("Introduce el NIF del cliente que quieras cambiar la Tarifa: ");
        String NIF = scanner.nextLine();
        if (telefonica.clientes.containsKey(NIF)) {
            System.out.print("Introduce la nueva Tarifa para el cliente seleccionado: ");
            double nuevaTarifa = scanner.nextDouble();
            telefonica.cambiarTarifa(NIF, nuevaTarifa);
        } else {
            System.out.println("No se ha encontrado el cliente.");
        }

    }
    public static void screenDatosCliente (){
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
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(telefonica.clientes.containsKey(NIF)){
            System.out.println("Todas las llamadas del cliente con NIF: "+ NIF);
            for(Llamada llamada : telefonica.listarLlamadas(NIF)){
                System.out.println(llamada.toString());
            }
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
    public static void screenMostrarDatosFactura(){
        System.out.print("Introduce el codigo de la factura: ");
        Integer codigo = scanner.nextInt();
        if(telefonica.facturas.containsKey(codigo)){
            Factura facturaAmostrar = telefonica.datosFactura(codigo);
            System.out.println(facturaAmostrar.toString());
        } else {
            System.out.println("No existe la factura con el codigo = "+ codigo);
        }
    }
    public static void screenListarFacturasCliente(){
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(telefonica.clientes.containsKey(NIF)){
            System.out.println("Todas las facturas del cliente con NIF: "+ NIF);
            for(Factura factura : telefonica.listarFacturas(NIF)){
                System.out.println(factura.toString());
            }
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
    public static void screenHacerLlamada(){
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(telefonica.clientes.containsKey(NIF)){
            System.out.print("Introduce el telefono destino de la llamada: ");
            Integer telefonoDestino = Integer.parseInt(scanner.nextLine());
            System.out.print("Introduce la duración de la llamada: ");
            Integer duracion = Integer.parseInt(scanner.nextLine());
            telefonica.hacerLlamada(NIF, telefonoDestino, duracion);
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }

    }
    public static void screenEmitirFactura(){
        System.out.print("Introduce el NIF del cliente del cual quieras emitir una factura: ");
        String NIF = scanner.nextLine();
        if(telefonica.clientes.containsKey(NIF)){
            Factura emitida = telefonica.emitirFactura(NIF);
            System.out.println(emitida.toString());
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
}
