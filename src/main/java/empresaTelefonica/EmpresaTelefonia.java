package empresaTelefonica;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import clientes.Particular;
import factura.Factura;
import gestionTelefonia.ObjetosConFecha;
import llamadas.Llamada;
import tarifa.Tarifa;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class EmpresaTelefonia {
    public HashMap<String, Cliente> clientes;
    public HashMap<Integer, Factura> facturas;
    public EmpresaTelefonia() {
        this.clientes = new HashMap<String, Cliente>();
        this.facturas = new HashMap<Integer, Factura>();
    }

    // CLIENTES

    public void crearClienteParticular(String nombre, String apellido1, String apellido2, String NIF, Direccion direccion, String email, Double precioSec) {
        ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        ArrayList<Llamada> llamadasSinFacturar = new ArrayList<Llamada>();
        Date input = new Date();
        LocalDate fechaActual = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Cliente particular = new Particular(nombre, NIF, direccion, email, fechaActual, new Tarifa(precioSec), facturas, llamadas,  apellido1, apellido2, llamadasSinFacturar);
        clientes.put(particular.getNIF(), particular);
    }

    public void crearClienteEmpresa(String nombre, String NIF, Direccion direccion, String email, Double precioSec) {
        ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        ArrayList<Llamada> llamadasSinFacturar = new ArrayList<Llamada>();
        Date input = new Date();
        LocalDate fechaActual = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Cliente persona = new Empresa(nombre, NIF, direccion, email, fechaActual, new Tarifa(precioSec), facturas, llamadas, llamadasSinFacturar);
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

    public Boolean cambiarTarifa(String NIF, double costeSec){
        if(clientes.containsKey(NIF)){
            clientes.get(NIF).setTarifa(new Tarifa(costeSec));
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

    public Boolean hacerLlamada(String NIF, Integer telefonoDestino, Integer duracion){
        if(clientes.containsKey(NIF)){
            Date input = new Date();
            LocalDate fechaActual = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Llamada llamada = new Llamada(telefonoDestino, fechaActual,duracion);
            clientes.get(NIF).llamadasSinFacturar.add(llamada);
            clientes.get(NIF).llamadas.add(llamada);
            return true;
        }else{
            return false;
        }

    }

    public ArrayList<Llamada> listarLlamadas(String NIF) {
        ArrayList<Llamada> llamadasCliente = clientes.get(NIF).getLlamadas();
        return llamadasCliente;
    }

    //FACTURAS

    //Emitir una factura para un cliente, calculando el importe de la misma en función de las llamadas

    public Factura emitirFactura(String NIF){
        Tarifa tarActual = clientes.get(NIF).getTarifa();
        Date input = new Date();
        LocalDate finFacturacion = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate principioFacturacion = finFacturacion.minusMonths(1);
        Factura emision = new Factura(tarActual, finFacturacion, finFacturacion, principioFacturacion);
        
        List<Llamada> llamadas = clientes.get(NIF).llamadasSinFacturar;
        emision.calcularImporte(llamadas);
        
        clientes.get(NIF).facturas.add(emision);
        clientes.get(NIF).llamadasSinFacturar.clear();
        facturas.put(emision.getCodigo(), emision);
        
        return emision;
    }

    public Factura datosFactura(Integer codigo){
        return facturas.get(codigo);
    }

    //Recuperar todas las facturas de un cliente.
    public ArrayList<Factura> listarFacturas(String NIF){
        ArrayList<Factura> facturasClientes = clientes.get(NIF).facturas;
        return facturasClientes;
    }

    //SERIALIZABLE

    public  ArrayList serFiltrarPorFecha(ArrayList<ObjetosConFecha> datos, LocalDate fechaInicio, LocalDate fechaFin){
        ArrayList datosComprendidos = new <ObjetosConFecha>ArrayList();
        for (ObjetosConFecha dato : datos) {
            if((dato.getFecha().isBefore(fechaInicio)) && (dato.getFecha().isAfter(fechaFin))) {
                datosComprendidos.add(dato);
            }
        }
        return datosComprendidos;
    }
}
