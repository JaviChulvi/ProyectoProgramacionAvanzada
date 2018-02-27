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
    HashMap<String, Cliente> clientes;

    // CLIENTES

    public void añadirClienteParticular(String nombre, String apellidos, String NIF, Direccion direccion, String email) {
        ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        Cliente persona = new Particular(nombre, apellidos, NIF, direccion, email, new Date(), new Tarifa(), facturas, llamadas);
        clientes.put(persona.NIF, persona);
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
            if(clientes.get(NIF).llamadas.add(llamada)) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }

    }
}
