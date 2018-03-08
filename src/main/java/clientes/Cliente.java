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
    private String nombre;
    private String NIF;
    private Direccion direccion;
    private String email;
    private Date fechaDeAlta;
    private Tarifa tarifa;
    public ArrayList<Factura> facturas;
    public ArrayList<Llamada> llamadas;
    public ArrayList<Llamada> llamadasSinFacturar;


    public Cliente(String nombre, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas, ArrayList<Llamada> llamadasSinFacturar) {
        this.nombre = nombre;
        this.NIF = NIF;
        this.direccion = direccion;
        this.email = email;
        this.fechaDeAlta = fechaDeAlta;
        this.tarifa = tarifa;
        this.facturas = facturas;
        this.llamadas = llamadas;
        this.llamadasSinFacturar = llamadasSinFacturar;
    }

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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente " + nombre + " (" + NIF + "):\n");
        sb.append("  Correo electrónico: " + email + "\n");
        sb.append("  Dirección: " + direccion.toString() + "\n");
        sb.append("  Fecha de alta: " + fechaDeAlta.toString() + "\n");
        sb.append("  Tarifa: " + tarifa.toString());
        return sb.toString();
    }

    public void setTarifa(Tarifa tarifa){
        this.tarifa = tarifa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNIF() {
        return NIF;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public ArrayList<Llamada> getLlamadas() {
        return llamadas;
    }

    public ArrayList<Llamada> getLlamadasSinFacturar() {
        return llamadasSinFacturar;
    }
}
