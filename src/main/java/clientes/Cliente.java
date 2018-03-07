package clientes;

import factura.Factura;
import llamadas.Llamada;
import tarifa.Tarifa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by al364498 on 20/02/18.
 */

public abstract class Cliente {
    public String nombre;
    public String NIF;
    public Direccion direccion;
    public String email;
    public Date fechaDeAlta;
    public Tarifa tarifa;
    public ArrayList<Factura> facturas;
    public ArrayList<Llamada> llamadas;

    public Cliente(String nombre, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas) {
        this.nombre = nombre;
        this.NIF = NIF;
        this.direccion = direccion;
        this.email = email;
        this.fechaDeAlta = fechaDeAlta;
        this.tarifa = tarifa;
        this.facturas = facturas;
        this.llamadas = llamadas;
    }
    public Date getFecha(){
        return this.fechaDeAlta;
    }
}
