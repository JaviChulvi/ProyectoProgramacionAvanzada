package main;

import clientes.Cliente;
import clientes.Direccion;
import compañiaTelefonica.EmpresaTelefonia;

import java.util.Scanner;

/**
 * Created by al364498 on 20/02/18.
 */
public class Main {
    private static EmpresaTelefonia telefonica = new EmpresaTelefonia();

    public static void main(String[] args) {
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
                System.out.println("Opción 3.");
                break;
            case LISTADO_CLIENTE:
                System.out.println("Opción 3.");
                break;
            case LISTAR_LLAMADAS_CLIENTE:
                System.out.println("Opción 3.");
                break;
            case EMITIR_FACTURAS_CLIENTE:
                System.out.println("Opción 3.");
                break;
            case DATOS_FACTURA:
                System.out.println("Opción 3.");
                break;
            case LISTAR_FACTURAS_CLIENTE:
                System.out.println("Opción 3.");
                break;
        }
    }

    public static void screenInfoCliente(Cliente cliente){
        System.out.print("Datos del cliente.");
        System.out.print("Nombre: "+cliente.nombre);
        System.out.print("Direccion:");
        System.out.print("      Codigo Postal: "+ cliente.direccion.getCodigoPostal());
        System.out.print("      Provincia: "+ cliente.direccion.getProvincia());
        System.out.print("      Población: "+ cliente.direccion.getPoblacion());
        System.out.print("Email: "+cliente.email);
        System.out.print("Fecha de Alta: "+cliente.fechaDeAlta.toString());
    }

    public static void screenDarDeAltaCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Particular o Empresa:");
        String tipo = scanner.nextLine();
        System.out.print("Introduce el nombre:");
        String nombre = scanner.nextLine();
        String apellidos = null;
        if (tipo == "particular" || tipo == "p") {
            System.out.print("Introduce los apellidos:");
            apellidos = scanner.nextLine();
        }
        System.out.print("Introduce el NIF:");
        String NIF = scanner.nextLine();
        System.out.print("A continuación introduce los datos de la Dirreción.");
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
             telefonica.clientes.remove(NIF);
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
            telefonica.clientes.get(NIF).tarifa.setTarifa(nuevaTarifa);
        } else {
            System.out.println("No se ha encontrado el cliente.");
        }

    }

    public static void screenDatosCliente (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if (telefonica.clientes.containsKey(NIF)) {
            screenInfoCliente(telefonica.clientes.get(NIF));
        } else {
            System.out.println("No se ha encontrado el cliente.");
        }
    }



}
