package clientes;

import factura.Factura;
import llamadas.Llamada;
import tarifa.Tarifa;

import java.util.ArrayList;
import java.util.Date;

public class Empresa extends Cliente {
<<<<<<< 28ddab7a9cffe52a95b48729b8203c6a692a5f6a
=======
	
	public Empresa(){}
	
>>>>>>> Desde clase ANTES PRACTICAA
    public Empresa(String nombre, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas, ArrayList<Llamada> llamadasSinFacturar) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas, llamadasSinFacturar);
    }

    public Empresa(String nombre, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas);
    }

}
