package modelo.empresaTelefonica;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.clientes.Cliente;
import modelo.clientes.Direccion;
import modelo.clientes.fabrica.FabricaClientes;
import modelo.facturas.Factura;
import modelo.gestionTelefonia.ObjetosConFecha;
import modelo.llamadas.Llamada;
import modelo.tarifa.Tarifa;
import modelo.tarifa.fabrica.FabricaTarifas;

public class EmpresaTelefonia implements Serializable {
	private static final long serialVersionUID = -7784176046023705987L;
	public HashMap<String, Cliente> clientes;
	public HashMap<Integer, Factura> facturas;
	private FabricaClientes fabricaClientes;
	private FabricaTarifas fabricaTarifas;
	
	public EmpresaTelefonia() {
        this.clientes = new HashMap<String, Cliente>();
        this.facturas = new HashMap<Integer, Factura>();
        this.fabricaClientes = new FabricaClientes();
        this.fabricaTarifas = new FabricaTarifas();
    }

    // CLIENTES

    public void crearClienteParticular(String nombre, String apellido1, String apellido2, String NIF, Direccion direccion, String email) {
    	LocalDate fechaActual = LocalDate.now();
        Cliente particular = fabricaClientes.getClienteParticular(nombre, NIF, direccion, email, fechaActual, 0.15,  apellido1, apellido2);
        clientes.put(particular.getNIF(), particular);
    }

    public void crearClienteEmpresa(String nombre, String NIF, Direccion direccion, String email) {
    	LocalDate fechaActual = LocalDate.now();
        Cliente persona = fabricaClientes.getClienteEmpresa(nombre, NIF, direccion, email, fechaActual, 0.15);
        clientes.put(persona.getNIF(), persona);
    }

    public Boolean borrarCliente(String NIF){
        if(clientes.containsKey(NIF)){
            clientes.remove(NIF);
            return true;
        }else{
            return false;
        }
    }

    public boolean aplicarOfertaHora(String NIF, double precio, int horaIni, int horaFin){
    	if(!clientes.containsKey(NIF))
    		return false;
    	Cliente c = datosCliente(NIF);
    	Tarifa actual = c.getTarifa();
    	
    	actual = fabricaTarifas.getTarifaIntervaloHorario(actual, precio, LocalTime.of(horaIni, 0), LocalTime.of(horaFin, 0));
    	
    	c.setTarifa(actual);
    	return true;
    }
    
    public boolean aplicarOfertaDia(String NIF, double precio, int diaDeLaSemana){
    	if(!clientes.containsKey(NIF))
    		return false;
    	Cliente c = datosCliente(NIF);
    	Tarifa actual = c.getTarifa();
    	
    	actual = fabricaTarifas.getTarifaDiaDeLaSemana(actual, precio, DayOfWeek.of(diaDeLaSemana));
    	
    	c.setTarifa(actual);
    	return true;
    }

    public Cliente datosCliente(String NIF) {
        if(clientes.containsKey(NIF)){
            return clientes.get(NIF);
        }else {
            return null;
        }
    }

    public ArrayList<Cliente> listarClientes(){
        ArrayList<Cliente> todosClientes = new ArrayList<Cliente>();
        for(Cliente consumidor: clientes.values()){
            todosClientes.add(consumidor);
        }
        return todosClientes;
    }

    //  LLAMADAS

    public Boolean hacerLlamada(String NIF, int telefonoDestino, int duracion){
        if(clientes.containsKey(NIF)){
        	Cliente cliente = clientes.get(NIF);
        	cliente.generarLlamada(telefonoDestino, duracion);
        	return true;
        }else{
            return false;
        }
    }

    public ArrayList<Llamada> listarLlamadas(String NIF) {
    	return clientes.get(NIF).getLlamadas();
    }

    //FACTURAS

    //Emitir una factura para un cliente, calculando el importe de la misma en función de las modelo.llamadas

    public Factura emitirFactura(String NIF){
        Factura emision = clientes.get(NIF).generarFactura();
        facturas.put(emision.getCodigo(), emision);
        return emision;
    }

    public Factura datosFactura(Integer codigo){
        return facturas.get(codigo);
    }

    //Recuperar todas las modelo.facturas de un cliente.
    public ArrayList<Factura> listarFacturas(String NIF){
        return clientes.get(NIF).getFacturas();
    }

    public ArrayList<? extends ObjetosConFecha> filtrarPorFecha(ArrayList<? extends ObjetosConFecha> datos, LocalDate fechaInicio, LocalDate fechaFin){
        ArrayList<ObjetosConFecha> datosComprendidos = new ArrayList<ObjetosConFecha>();
        for (ObjetosConFecha dato : datos) {
            if((dato.getFecha().isBefore(fechaFin)) && (dato.getFecha().isAfter(fechaInicio))) {
                datosComprendidos.add(dato);
            }
        }
        return datosComprendidos;
    }

    public boolean containsCliente(String nif){
	    return clientes.containsKey(nif);
    }
}
