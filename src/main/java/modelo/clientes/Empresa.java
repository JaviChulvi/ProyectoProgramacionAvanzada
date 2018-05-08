package modelo.clientes;

import java.time.LocalDate;

import modelo.tarifa.Tarifa;

public class Empresa extends Cliente {
	private static final long serialVersionUID = -3095163831484710123L;

	public Empresa(){}

    public Empresa(String nombre, String NIF, Direccion direccion, String email, LocalDate fechaDeAlta, Tarifa tarifa) {
        super(nombre, NIF, direccion, email, fechaDeAlta, tarifa);
    }
}
