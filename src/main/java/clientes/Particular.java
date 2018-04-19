package clientes;

import java.time.LocalDate;
import java.util.ArrayList;

import facturas.Factura;
import llamadas.Llamada;
import tarifa.Tarifa;

public class Particular extends Cliente{
    public String apellido1;
    public String apellido2;

    public Particular(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, Tarifa tarifa, ArrayList<Factura> facturas, ArrayList<Llamada> llamadas, String apellido1, String apellido2, ArrayList<Llamada> llamadasSinFacturar) {
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
    }


}
