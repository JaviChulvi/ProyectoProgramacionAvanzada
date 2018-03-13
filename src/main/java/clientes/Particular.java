package clientes;

import factura.Factura;
import llamadas.Llamada;
import tarifa.Tarifa;

import java.util.ArrayList;
import java.util.Date;

public class Particular extends Cliente{
    public String apellido1;
    public String apellido2;
<<<<<<< 28ddab7a9cffe52a95b48729b8203c6a692a5f6a



    public Particular(String nombre, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas, String apellido1, String apellido2, ArrayList<Llamada> llamadasSinFacturar) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas, llamadasSinFacturar);
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
=======
    
    public Particular(){}
    
    public Particular(String nombre, String NIF, Direccion direccion, String email, Date fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas, String apellido1, String apellido2, ArrayList<Llamada> llamadasSinFacturar) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa, facturas, llamadas, llamadasSinFacturar);
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente " + nombre + " " + apellido1 + " " + apellido2 + " (" + NIF + "):\n");
        sb.append("  Correo electrónico: " + email + "\n");
        sb.append("  Dirección: " + direccion.toString() + "\n");
        sb.append("  Fecha de alta: " + fechaDeAlta.toString() + "\n");
        sb.append("  Tarifa: " + tarifa.toString());
        return sb.toString();
>>>>>>> Desde clase ANTES PRACTICAA
    }


}
