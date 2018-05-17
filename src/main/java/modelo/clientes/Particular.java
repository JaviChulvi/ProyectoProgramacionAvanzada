package modelo.clientes;

import java.time.LocalDate;

import modelo.tarifa.Tarifa;

public class Particular extends Cliente{
	private static final long serialVersionUID = 700436340053446098L;
	public String apellido1;
    public String apellido2;

    public Particular(){}
    
    public Particular(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, Tarifa tarifa, String apellido1, String apellido2) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa);
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
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
