package compañiaTelefonica;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import clientes.Particular;
import factura.Factura;
import llamadas.Llamada;
import tarifa.Tarifa;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EmpresaTelefonia {
    public HashMap<String, Cliente> clientes;
    public HashMap<Integer, Factura> facturas;
    public EmpresaTelefonia() {
        this.clientes = new HashMap<String, Cliente>();
        this.facturas = new HashMap<Integer, Factura>();
    }

    // CLIENTES

    public void añadirClienteParticular(String nombre, String apellidos, String NIF, Direccion direccion, String email) {
        ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        Cliente particular = new Particular(nombre, NIF, direccion, email, new Date(), new Tarifa(), facturas, llamadas,  apellidos);
        clientes.put(particular.NIF, particular);
    }

    public void añadirClienteEmpresa(String nombre, String NIF, Direccion direccion, String email) {
        ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        Cliente persona = new Empresa(nombre, NIF, direccion, email, new Date(), new Tarifa(), facturas, llamadas);
        clientes.put(persona.NIF, persona);
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
            clientes.get(NIF).tarifa.setTarifa(costeSec);
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
            Llamada llamada = new Llamada(telefonoDestino, new Date(), duracion);
            clientes.get(NIF).llamadasSinFacturar.add(llamada);
            clientes.get(NIF).llamadas.add(llamada);
            return true;
        }else{
            return false;
        }

    }

    public ArrayList<Llamada> listarLlamadas(String NIF) {
        ArrayList<Llamada> llamadasCliente = clientes.get(NIF).llamadas;
        return llamadasCliente;
    }

    //FACTURAS

    //Emitir una factura para un cliente, calculando el importe de la misma en función de las llamadas

    public Factura emitirFactura(String NIF){
        Tarifa tarActual = clientes.get(NIF).tarifa;
        Integer totalMinutos = 0;
        Date fechaPrimeraSinFacturar = clientes.get(NIF).llamadasSinFacturar.get(0).getFecha();
        for(Llamada llamada : clientes.get(NIF).llamadasSinFacturar) {
            totalMinutos += llamada.duración;
        }
        Double importe = tarActual.precioSec * totalMinutos;
        Date[] periodoFacturacion = new Date[]{fechaPrimeraSinFacturar, new Date()};
        Factura emision = new Factura(tarActual, new Date(), periodoFacturacion);
        clientes.get(NIF).facturas.add(emision);
        clientes.get(NIF).llamadasSinFacturar.clear();
        facturas.put(emision.codigo, emision);
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




}
