package empresaTelefonica;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import clientes.Particular;
import facturas.Factura;
import gestionTelefonia.ObjetosConFecha;
import llamadas.Llamada;
import tarifa.Tarifa;
import tarifa.TarifaBasica;
import tarifa.TarifaDomingos;
import tarifa.TarifaTardes;

public class EmpresaTelefonia implements Serializable {
	private static final long serialVersionUID = 203388081094728352L;
	public HashMap<String, Cliente> clientes;
	public HashMap<Integer, Factura> facturas;
	public EmpresaTelefonia() {
        this.clientes = new HashMap<String, Cliente>();
        this.facturas = new HashMap<Integer, Factura>();
    }

    // CLIENTES

    public void crearClienteParticular(String nombre, String apellido1, String apellido2, String NIF, Direccion direccion, String email) {
        ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        ArrayList<Llamada> llamadasSinFacturar = new ArrayList<Llamada>();
        Date input = new Date();
        LocalDate fechaActual = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Tarifa tarifa = new TarifaBasica();
        Cliente particular = new Particular(nombre, NIF, direccion, email, fechaActual, tarifa, facturas, llamadas,  apellido1, apellido2, llamadasSinFacturar);
        clientes.put(particular.getNIF(), particular);
    }

    public void crearClienteEmpresa(String nombre, String NIF, Direccion direccion, String email) {
        ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        ArrayList<Llamada> llamadasSinFacturar = new ArrayList<Llamada>();
        Date input = new Date();
        LocalDate fechaActual = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Tarifa tarifa = new TarifaBasica();
        Cliente persona = new Empresa(nombre, NIF, direccion, email, fechaActual, tarifa, facturas, llamadas, llamadasSinFacturar);
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

    public Boolean aplicarOferta(String NIF, String tipo){
        if(clientes.containsKey(NIF)){
            if(tipo.equals("domingos") || tipo.equals("Domingos") || tipo.equals("d")){
                clientes.get(NIF).setTarifa(new TarifaDomingos(new TarifaBasica()));
            }
            if(tipo.equals("tardes") || tipo.equals("Tardes") || tipo.equals("t")){
                clientes.get(NIF).setTarifa(new TarifaTardes(new TarifaBasica()));
            }
            return true;
        }else{
            return false;
        }
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

    //Emitir una factura para un cliente, calculando el importe de la misma en función de las llamadas

    public Factura emitirFactura(String NIF){
        Factura emision = clientes.get(NIF).generarFactura();
        facturas.put(emision.getCodigo(), emision);
        return emision;
    }

    public Factura datosFactura(Integer codigo){
        return facturas.get(codigo);
    }

    //Recuperar todas las facturas de un cliente.
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
}
