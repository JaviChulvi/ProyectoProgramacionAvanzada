package clientes;

import factura.Factura;
import llamadas.Llamada;
import tarifa.Tarifa;

import java.util.ArrayList;
import java.util.Date;

public class Particular extends Cliente{
    public String apellidos;

    public Particular(String nombre, String apellidos, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas, ArrayList<Llamada> llamadasSinFacturar) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas, llamadasSinFacturar);
        this.apellidos = apellidos;
    }

    public Particular(String nombre, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas, String apellidos) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas);
        this.apellidos = apellidos;
    }
}
