package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.clientes.Cliente;
import modelo.clientes.Direccion;
import modelo.empresaTelefonica.EmpresaTelefonia;
import modelo.facturas.Factura;
import modelo.llamadas.Llamada;

public class MenuConsola {

	private EmpresaTelefonia empresaTel;
	private Scanner scanner;
	
	public MenuConsola(EmpresaTelefonia tel){
		this.empresaTel = tel;
	}
	
	public void iniciarMenu(){
		loopMenu();
	}
	
	public void loopMenu(){
    	scanner = new Scanner(System.in);
        int opcion;
        MenuConsolaEnum opcionMenu = null;
        
        while(opcionMenu != MenuConsolaEnum.SALIR){
        	// OBTENER OPCION
			System.out.println("\n" + MenuConsolaEnum.getMenu());
			do{
				System.out.print("Elija una opción: ");
				opcion = Integer.parseInt(scanner.nextLine());
			} while(opcion < 0 || opcion >= MenuConsolaEnum.values().length);
			
			opcionMenu = MenuConsolaEnum.getOpcion(opcion);
			
			// EJECUTAR OPERACION
	        switch(opcionMenu) {
	            case ALTA_NUEVO_CLIENTE:
	                darDeAltaCliente();
	                break;
	            case BORRAR_CLIENTE:
	                borrarCliente();
	                break;
	            case APLICAR_OFERTA:
	                aplicarOferta();
	                break;
	            case DATOS_CLIENTE:
	                datosCliente();
	                break;
	            case ALTA_LLAMADA:
	                hacerLlamada();
	                break;
	            case LISTADO_CLIENTES:
	                listarCliente();
	                break;
	            case LISTAR_LLAMADAS_CLIENTE:
	                listarLlamadasCliente();
	                break;
	            case EMITIR_FACTURAS_CLIENTE:
	                emitirFactura();
	                break;
	            case DATOS_FACTURA:
	                mostrarDatosFactura();
	                break;
	            case LISTAR_FACTURAS_CLIENTE:
	                listarFacturasCliente();
	                break;
	            case ALTA_CLIENTES_ENTRE_DOS_FECHAS:
	            	altaClientesEntreDosFechas();
	            	break;
	            case LLAMADAS_ENTRE_DOS_FECHAS:
	            	llamadasEntreDosFechas();
	            	break;
				case FACTURAS_ENTRE_DOS_FECHAS:
					facturasEntreDosFechas();
					break;
	            case SALIR:
	            	break;
	        }
        }
        scanner.close();
    }
	
	private LocalDate[] pedirRangoDeFechas(){
		LocalDate[] aux = new LocalDate[2];
		int dia, mes, anyo;
		
		System.out.println("Introduciendo fecha de inicio");
		System.out.println("Introduce el día:");
		dia = Integer.parseInt(scanner.nextLine());
		System.out.println("Introduce el mes:");
		mes = Integer.parseInt(scanner.nextLine());
		System.out.println("Introduce el año:");
		anyo = Integer.parseInt(scanner.nextLine());
		aux[0] = LocalDate.of(anyo, mes, dia);
		
		System.out.println("Introduciendo fecha de finalización");
		System.out.println("Introduce el día:");
		dia = Integer.parseInt(scanner.nextLine());
		System.out.println("Introduce el mes:");
		mes = Integer.parseInt(scanner.nextLine());
		System.out.println("Introduce el año:");
		anyo = Integer.parseInt(scanner.nextLine());
		aux[1] = LocalDate.of(anyo, mes, dia);
		
		return aux;
	}
	@SuppressWarnings("unchecked")
	private void facturasEntreDosFechas() {
		System.out.print("Introduce el NIF:");
        String NIF = scanner.nextLine();
        LocalDate[] rango = pedirRangoDeFechas();
        ArrayList<Factura> resultado = (ArrayList<Factura>) empresaTel.filtrarPorFecha(empresaTel.listarFacturas(NIF), rango[0], rango[1]);
        for(Factura f : resultado)
        	f.toString();
	}
	@SuppressWarnings("unchecked")
	private void llamadasEntreDosFechas() {
		System.out.print("Introduce el NIF:");
        String NIF = scanner.nextLine();
		LocalDate[] rango = pedirRangoDeFechas();
		ArrayList<Llamada> resultado = (ArrayList<Llamada>) empresaTel.filtrarPorFecha(empresaTel.listarLlamadas(NIF), rango[0], rango[1]);
		for(Llamada f : resultado)
        	f.toString();
	}
	@SuppressWarnings("unchecked")
	private void altaClientesEntreDosFechas() {
		LocalDate[] rango = pedirRangoDeFechas();
		ArrayList<Cliente> resultado = (ArrayList<Cliente>) empresaTel.filtrarPorFecha(empresaTel.listarClientes(), rango[0], rango[1]);
		for(Cliente f : resultado)
        	f.toString();
	}
	private void screenInfoCliente(Cliente cliente){
        System.out.println(cliente.toString());
    }
	private void darDeAltaCliente() {
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
        if (tipo.equals("particular") || tipo.equals("p")) {
        	empresaTel.crearClienteParticular(nombre, apellido1, apellido2, NIF, dirNuevoCliente, email);
        } else {
        	empresaTel.crearClienteEmpresa(nombre, NIF, dirNuevoCliente, email );
        }
    }
    private void borrarCliente() {
        System.out.print("Introduce el NIF del cliente que quieras eliminar:");
        String NIF = scanner.nextLine();
        if (empresaTel.clientes.containsKey(NIF)) {
             Cliente clienteABorrar = empresaTel.datosCliente(NIF);
             screenInfoCliente(clienteABorrar);
             empresaTel.borrarCliente(NIF);
        } else {
            System.out.print("Este cliente no esta dado de alta.");
        }
    }
    
    private void aplicarOferta() {
        System.out.print("Introduce el NIF del cliente que quieras cambiar la Tarifa: ");
        String NIF = scanner.nextLine();
        if (empresaTel.clientes.containsKey(NIF)) {
            System.out.print("Introduce el tipo de oferta que quieres aplicar (1=Según hora, 2=según día): ");
            int tipo = Integer.parseInt(scanner.nextLine());
            System.out.println("Introduce el precio de la oferta: ");
            double precio = Double.parseDouble(scanner.nextLine());
            if(tipo == 1){
            	aplicarOfertaHora(NIF, precio);
            } else if(tipo == 2){
            	aplicarOfertaDia(NIF, precio);
            }
        } else {
            System.out.println("No se ha encontrado el cliente.");
        }
    }
    private void aplicarOfertaHora(String nif, double precio){
    	System.out.println("Introduce la hora de inicio: ");
    	int horaIni = Integer.parseInt(scanner.nextLine());
    	System.out.println("Introduce la hora de finalización: ");
    	int horaFin = Integer.parseInt(scanner.nextLine());
    	empresaTel.aplicarOfertaHora(nif, precio, horaIni, horaFin);
    }
    private void aplicarOfertaDia(String nif, double precio){
    	System.out.println("Introduce el día de la semana (1=Lunes, 7=Domingo): ");
    	int dia = Integer.parseInt(scanner.nextLine());
    	empresaTel.aplicarOfertaDia(nif, precio, dia);
    }
    
    private void datosCliente (){
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if (empresaTel.clientes.containsKey(NIF)) {
            screenInfoCliente(empresaTel.datosCliente(NIF));
        } else {
            System.out.println("No se ha encontrado el cliente.");
        }
    }
    private void listarCliente(){
        ArrayList<Cliente> listaClientes = empresaTel.listarClientes();
        for( Cliente cliente : listaClientes){
            screenInfoCliente(cliente);
        }
    }
    private void listarLlamadasCliente(){
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(empresaTel.clientes.containsKey(NIF)){
            System.out.println("Todas las modelo.llamadas del cliente con NIF: "+ NIF);
            for(Llamada llamada : empresaTel.listarLlamadas(NIF)){
                System.out.println(llamada.toString());
            }
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
    private void mostrarDatosFactura(){
        System.out.print("Introduce el codigo de la factura: ");
        Integer codigo = scanner.nextInt();
        if(empresaTel.facturas.containsKey(codigo)){
            Factura facturaAmostrar = empresaTel.datosFactura(codigo);
            System.out.println(facturaAmostrar.toString());
        } else {
            System.out.println("No existe la factura con el codigo = "+ codigo);
        }
    }
    private void listarFacturasCliente(){
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(empresaTel.clientes.containsKey(NIF)){
            System.out.println("Todas las modelo.facturas del cliente con NIF: "+ NIF);
            for(Factura factura : empresaTel.listarFacturas(NIF)){
                System.out.println(factura.toString());
            }
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
    private void hacerLlamada(){
        System.out.print("Introduce el NIF del cliente: ");
        String NIF = scanner.nextLine();
        if(empresaTel.clientes.containsKey(NIF)){
            System.out.print("Introduce el telefono destino de la llamada: ");
            Integer telefonoDestino = Integer.parseInt(scanner.nextLine());
            System.out.print("Introduce la duración de la llamada: ");
            Integer duracion = Integer.parseInt(scanner.nextLine());
            empresaTel.hacerLlamada(NIF, telefonoDestino, duracion);
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
    private void emitirFactura(){
        System.out.print("Introduce el NIF del cliente del cual quieras emitir una factura: ");
        String NIF = scanner.nextLine();
        if(empresaTel.clientes.containsKey(NIF)){
            Factura emitida = empresaTel.emitirFactura(NIF);
            System.out.println(emitida.toString());
        } else {
            System.out.println("No existe un cliente con el NIF = "+ NIF);
        }
    }
}