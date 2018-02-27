package clientes;

import factura.Factura;
import llamadas.Llamada;
import tarifa.Tarifa;

import java.util.ArrayList;
import java.util.Date;

public class Particular extends Cliente{
    public String apellidos;

    public Particular(String nombre, String apellidos, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas);
        this.apellidos = apellidos;
    }
}
