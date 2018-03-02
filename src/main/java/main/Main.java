package main;

import clientes.Direccion;
import compañiaTelefonica.EmpresaTelefonia;

import java.util.Scanner;

/**
 * Created by al364498 on 20/02/18.
 */
public class Main {
    public static void screenDarDeAltaCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Particular o Empresa:");
        String tipo = scanner.nextLine();
        System.out.print("Introduce el nombre:");
        String nombre = scanner.nextLine();
        if (tipo == "particular" || tipo == "p") {
            System.out.print("Introduce los apellidos:");
            String apellidos = scanner.nextLine();
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

        /*if(tipo=="particular" || tipo=="p"){
            telefonica.añadirClienteParticular(nombre, apellidos, NIF, dirNuevoCliente, email );
        }
        telefonica.añadirClienteParticular(nombre, )*/
        //añadirClienteParticular(String nombre, String apellidos, String NIF, Direccion direccion, String email)


        if (tipo == "particular" || tipo == "p") {

        }
    }


    public static void main(String[] args){
        EmpresaTelefonia telefonica = new EmpresaTelefonia();
        System.out.println(Menu.getMenu());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elije una opción:");
        byte opcion = scanner.nextByte();
        Menu opcionMenu = Menu.getOpcion(opcion);
        switch(opcionMenu) {
            case ALTA_NUEVO_CLIENTE:
                System.out.println("Opción 1.");
                screenDarDeAltaCliente();
                break;
            case BORRAR_CLIENTE:
                System.out.println("Opción 2.");
                break;
            case DATOS_CLIENTE:
                System.out.println("Opción 3.");
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


}
