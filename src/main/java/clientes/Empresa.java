package clientes;

import factura.Factura;
import llamadas.Llamada;
import tarifa.Tarifa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Empresa extends Cliente {
	public Empresa(){}

    public Empresa(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas, ArrayList<Llamada> llamadasSinFacturar) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas, llamadasSinFacturar);
    }

    public Empresa(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas);
    }

}
